package cjfw.wms.comfunc.func.ext.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmCodeReqDto;
import cjfw.wms.cm.dto.CmFaxReqDto;
import cjfw.wms.cm.dto.CmSendReqDto;
import cjfw.wms.cm.dto.CmUserManagerConnectReqDto;
import cjfw.wms.cm.dto.CmUserManagerReqDto;
import cjfw.wms.cm.dto.CmUserManagerResDto;
import cjfw.wms.cm.entity.CmSmsMmsSendEntity;
import cjfw.wms.cm.service.CmUserManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.09.01 
 * @description : 타 시스템 연동 테스트 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ExtTestService {
	
	private final CommonDao commonDao;
	
	private final CmUserManagerService cmUserManagerService;
	
	private final UserContext userContext;
	
	/**
	 * @description : FAX 전송 테스트
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveFax(CmFaxReqDto cmFaxReqDto) {
		// FAX 일련번호 가져오기
		String batchId = (String) commonDao.selectOne("cmFaxService.getKey");
		cmFaxReqDto.setTrBatchid(batchId);
		
		// TO-DO(RD) : 현재 보여지는 RD 리포트의 이미지를 서버의 특정 PATH에 저장시키는 로직 필요
		// TO-DO(RD) : 서버 특정 PATH "/app/uploads/report/report" 이여야 함
		// TO-DO(RD) : 저장시켜주는 파일명 서로간 협의해야 함 (ex. fc_20250314083631cgy6572_1184054071.tif)
		// ContextUtil.getProperty("cf.upload.dir.fax")
		if(cmFaxReqDto.getTrDocname() == null || cmFaxReqDto.getTrDocname().equals("") || cmFaxReqDto.getTrDocname().isEmpty()) {
			cmFaxReqDto.setTrDocname("TM_Claim_List.tif"); // 예제 파일 (개발서버에 올라간 파일명임)
		}
	
		
		// FAX Meta 정보 등록
		commonDao.insert("cmFaxService.insertFaxMeta", cmFaxReqDto);
		
		// FAX Msg 정보 등록
		commonDao.insert("cmFaxService.insertFaxMsg", cmFaxReqDto);
		
		// FAX 발송 상태값 변경 (TR_SENDSTAT = '0' 값이면 전송 됨)
		commonDao.update("cmFaxService.updateFaxMetaStatus", cmFaxReqDto);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 드라이버 사용자 ID 생성
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveDriverUser(CmUserManagerResDto dto) {
		// STEP0 : 필수값 체크
		if (dto.getUserId() == null || "".equals(dto.getUserId())) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"사용자ID"})); // {0} 항목은 필수 입니다.
		} else if (dto.getUserNm() == null || "".equals(dto.getUserNm())) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"사용자명"})); // {0} 항목은 필수 입니다.
		} else if (dto.getDefDccode() == null || "".equals(dto.getDefDccode())) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"기본센터코드"})); // {0} 항목은 필수 입니다.
		} else if (dto.getCustkey() == null || "".equals(dto.getCustkey())) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"업체코드"})); // {0} 항목은 필수 입니다.
		} else if (dto.getHandphoneNo() == null || "".equals(dto.getHandphoneNo())) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"핸드폰번호"})); // {0} 항목은 필수 입니다.
		}
		
		
		
		// STEP1 : 신규 사용자 등록을 위한 상위 DTO 생성
		CmUserManagerReqDto cmUserManagerReqDto = new CmUserManagerReqDto();
		
		
		
		// STEP2 : 센터 권한 설정
		List<CmUserManagerConnectReqDto> connectList = new ArrayList<CmUserManagerConnectReqDto>();
		CmUserManagerConnectReqDto connect = new CmUserManagerConnectReqDto();
		connect.setUserId(dto.getUserId());							// 사용자ID
		connect.setDccode(dto.getDefDccode());						// 권한 부여할 센터코드 (STD: 전체 센터 권한)
		connect.setOrganize("STD");									// 권한 부여할 창고 (STD: 전체 창고 권한)
		connect.setRowStatus(CanalFrameConstants.INSERT);			// 상태값 INSERT (고정값)
		connectList.add(connect);
		cmUserManagerReqDto.setConnectList(connectList);
		
		
		
		// STEP3 : 사용자 상세 정보 DTO 생성 및 값 설정
		CmUserManagerResDto userDtlDto = ModelMapperUtil.map(dto, userContext, CmUserManagerResDto.class);
		
		userDtlDto.setUserId(dto.getUserId()); 						// 사용자ID ("_WAYLO" 붙여줘야 함)
		userDtlDto.setUserNm(dto.getUserNm());						// 사용자명
		userDtlDto.setAuthority("410");								// 권한코드 (고정값 410: [파트너] 물류센터 배송기사)
		userDtlDto.setEmpType("C01");								// 소속구분 (고정값 C01: 배송업체)
		userDtlDto.setDefDccode(dto.getDefDccode());				// 기본센터코드
		userDtlDto.setDefStorerkey("FW00");							// 기본고객코드 (고정값)
		userDtlDto.setDefOrganize("STD");							// 기본조직코드 (고정값)
		userDtlDto.setDefArea("1000");								// 기본창고코드 (고정값)
		userDtlDto.setCustkey(dto.getCustkey());					// 업체코드
		userDtlDto.setHandphoneNo(dto.getHandphoneNo());			// 핸드폰번호
		userDtlDto.setDlvAppUserYn("Y");							// 배송APP사용여부 (고정값)
		
		userDtlDto.setRowStatus(CanalFrameConstants.INSERT); 		// 상태값 INSERT (고정값)
		userDtlDto.setDriverYn("Y"); 								// 드라이버 신규 사용자 등록여부 (고정값)
		cmUserManagerReqDto.setUserDtl(userDtlDto);
		
		
		
		// STEP4 : 신규 사용자 등록
		cmUserManagerService.saveUser(cmUserManagerReqDto);
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 공통코드 대용량 다운로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void getCodeHeaderList(CmCodeReqDto cmCodeReqDto, LargeExcel largeExcel) {
		commonDao.selectLargeExcelDataset("cmCodeService.getCodeHeaderList", cmCodeReqDto, largeExcel);
	}
	
	/**
	 * @description : SMS 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveSms(CmSendReqDto cmSendReqDto) {
		CmSmsMmsSendEntity cmSmsMmsSendEntity = ModelMapperUtil.map(cmSendReqDto, userContext, CmSmsMmsSendEntity.class);
		cmSmsMmsSendEntity.setSendTitle(cmSendReqDto.getTitle()); // 제목
		cmSmsMmsSendEntity.setSendMessage(cmSendReqDto.getCnts()); // 메세지
		cmSmsMmsSendEntity.setReceiveName(cmSendReqDto.getRcvrNm()); // 수신자명
		cmSmsMmsSendEntity.setReceivePhone(cmSendReqDto.getRcvrPhone()); // 수신자핸드폰번호
		
		commonDao.insert("cmSendService.insertSendSms", cmSmsMmsSendEntity);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
