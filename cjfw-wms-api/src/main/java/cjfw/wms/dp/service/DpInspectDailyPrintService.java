package cjfw.wms.dp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.dp.dto.DpInspectDailyPrintReqDto;
import cjfw.wms.dp.dto.DpInspectDailyPrintResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.10
 * @description :일배입고검수출력  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class DpInspectDailyPrintService {
	private final UserContext userContext;
	private final CommonDao commonDao;
	private final CmCommonService cmCommonService;
	private transient static final String PAKAGE_NAME = "SPDP_SRMPOSOMAP";
	private transient static final String TEMPTABLETYPE = "DP";
	private transient static final String PROCESSTYPE = "DP_INSPECT_DAILY_PRINT";

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 *
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DpInspectDailyPrintService.class.getSimpleName()) + ".";

	/** @description : 일배입고검수출력 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getMasterList", reqDto);
	}

	/** @description : 일배입고검수출력 상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getPoNotMapList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getPoNotMapList", reqDto);
	}

	/** @description : 일배입고검수출력 상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getDetailList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getDetailList", reqDto);
	}

	/** @description : 일배입고검수출력 상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getPoNotMapDetailList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getPoNotMapDetailList", reqDto);
	}

	/** @description : 일배입고검수출력 상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getPrintMasterList(T paramDto) {
		// 파라미터 위변조 적용(paramDto->reqDto)
		DpInspectDailyPrintReqDto reqDto = ModelMapperUtil.map(paramDto, DpInspectDailyPrintReqDto.class);
		List<DpInspectDailyPrintResDto> saveList = reqDto.getSaveList(); // 저장리스트
		log.info("▶saveList.size->{}",saveList);
		reqDto.setTemptabletype(TEMPTABLETYPE); // 임시테이블타입
		reqDto.setProcesstype(PROCESSTYPE); // 임시테이블타입

		// START.필수입력 check
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getGDccode()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"gDccode"} ) ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		// END.필수입력 check

		log.info("▶saveList.size->{}",saveList.size());

		/*START.Temp Table Insert*/
		// 임시테이블 삭제(1/3)
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto);

		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		for (int i = 0; i < saveList.size(); i++) {
			DpInspectDailyPrintResDto dto = saveList.get(i);
			// 임시테이블에 등록(2/3)
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
			entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			entity.setTemptabletype(TEMPTABLETYPE); // 프로세스타입
			// UI.params

			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSlipno()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"slipno"} )  );
			if("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSlipdt()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"slipdt"} )  );
			if("".equals(cjfw.core.utility.StringUtil.nvl(dto.getCustkey()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"custkey"} )  );
			if("".equals(cjfw.core.utility.StringUtil.nvl(dto.getWavekey()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"wavekey"} )  );
			// END.필수입력 check
			insertList.add(entity);

			// 200개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "insertTemp", insertList);
				insertList.clear();
			}
		}
		/*END.Temp Table Insert*/

		if("TMINPLAN".equals(reqDto.getPrintType())) {
			return commonDao.selectList(SERVICEID_PREFIX+"getTmPrintList", reqDto);
		}else{
			return commonDao.selectList(SERVICEID_PREFIX+"getPrintList", reqDto);
		}
	}

	/** @description : 일배입고검수출력 PO맵핑
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	 */
	public String savePoMapping(DpInspectDailyPrintReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";

		// 파라미터 위변조 적용(paramDto->reqDto)
		DpInspectDailyPrintReqDto reqDto = ModelMapperUtil.map(paramDto, DpInspectDailyPrintReqDto.class);

		DpInspectDailyPrintReqDto dto = new DpInspectDailyPrintReqDto();

		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE" , "DCCODE",             "STORERKEY",           "FROM_SLIPDT",            "TO_SLIPDT"  	};
		Object[] valueList = {PAKAGE_NAME , reqDto.getGDccode(), reqDto.getGStorerkey(), reqDto.getDocdtFrom(), reqDto.getDocdtTo()};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		int rv = cmCommonService.saveProcedure(dto);
		log.info("rv->{}",rv);

		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(dto.getResultCode());
		resultMessage = StringUtil.nvl((String)dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);

		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶PO맵핑시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"PO맵핑"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/


		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
