package cjfw.wms.om.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.ms.dto.MsCustDeliveryInfoResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupDetailResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupHistoryResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupInfoResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupReqDto;
import cjfw.wms.om.dto.OmAutoOrderSetupResDto;
import cjfw.wms.om.entity.OmAutoOrderSetupEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.07.24 
 * @description : 주문 > 주믄등록 > 저장품자동발주관리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmAutoOrderSetupService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omAutoOrderSetupService.";
	private transient static final String PAKAGE_NAME = "SPOM_AUTOORD";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 자동발주관리 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmAutoOrderSetupResDto> getMasterList(OmAutoOrderSetupReqDto dto) {
		List<OmAutoOrderSetupResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	/**
	 * @description : 자동발주관리 상품내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmAutoOrderSetupDetailResDto> getDetailList(OmAutoOrderSetupReqDto dto) {
		List<OmAutoOrderSetupDetailResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
		return result;
	}
	
	/**
	 * @description : 자동발주관리 이력내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmAutoOrderSetupHistoryResDto> getHistoryList(OmAutoOrderSetupReqDto dto) {
		List<OmAutoOrderSetupHistoryResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getHistoryList", dto);
		return result;
	}
	
	/**
	 * @description : 자동발주관리 상세내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public OmAutoOrderSetupInfoResDto getMasterInfo(OmAutoOrderSetupReqDto dto) {
		OmAutoOrderSetupInfoResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getMasterInfo", dto);
		return result;
	}
	
	/**
	 * @description :자동발주관리 상품 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public OmAutoOrderSetupDetailResDto getDetailListCheck(List<OmAutoOrderSetupReqDto> dtoList) {
		
		OmAutoOrderSetupDetailResDto result = new OmAutoOrderSetupDetailResDto();
				
		List<OmAutoOrderSetupDetailResDto> validExcelList = new ArrayList<OmAutoOrderSetupDetailResDto>();
		if (null != dtoList) {
			validExcelList = commonDao.selectList(SERVICEID_PREFIX + "validateSkuExcelDetail", dtoList);

			//sku 중복확인
			Set<String> uniqueSku = new HashSet<>();
			Set<String> duplicatedSku = new HashSet<>();
			for(OmAutoOrderSetupDetailResDto resultDto : validExcelList) {
				
				String sku = resultDto.getSku();
				if (uniqueSku.contains(sku)) {
					duplicatedSku.add(sku);
		            // 이 시점에서는 현재 데이터에 중복 오류를 즉시 설정하고,
		            // 이전 데이터들은 루프가 끝난 후 한 번에 업데이트
		            resultDto.setDuplicateChk("N");
		        } else {
		            // 처음 등장한 sku는 uniqueskus에 추가
		        	uniqueSku.add(sku);
		        }				
			}
			// 중복이 발견된 sku에 대해 이전에 정상 처리된 건도 오류 처리
		    if (!duplicatedSku.isEmpty()) {
		        for (OmAutoOrderSetupDetailResDto editDto : validExcelList) {
		            // 중복 리스트에 포함된 sku인 경우 (첫 번째 건 포함)
		            if (duplicatedSku.contains(editDto.getSku()) && !"N".equals(editDto.getSkuChk()) && !"N".equals(editDto.getSkuDupChk())) {
		                // 이미 오류가 아닌 경우에만 중복 오류를 설정
		            	editDto.setDuplicateChk("N");
		            }
		        }
		    }
		}
		
		if(validExcelList.size() > 0 ) {
			result.setValidExcelList(validExcelList);
		}
	
		return result;
	}
		
	/**
	 * @description :자동발주관리 상품 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public OmAutoOrderSetupDetailResDto saveDetailList(List<OmAutoOrderSetupReqDto> dtoList) {
		
		OmAutoOrderSetupDetailResDto result = new OmAutoOrderSetupDetailResDto();
		StringJoiner errSkuList = new StringJoiner(",");
		StringJoiner dupSkuList = new StringJoiner(",");
		StringBuffer errMsg = new StringBuffer();
		
		if (null != dtoList) {
			for(OmAutoOrderSetupReqDto dto : dtoList) {
				OmAutoOrderSetupEntity entity = ModelMapperUtil.map(dto, userContext, OmAutoOrderSetupEntity.class);

				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					OmAutoOrderSetupDetailResDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "validateSkuDetail", dto);
					
					// 상품코드 유효성 검증
					if(checkData.getSkuChk() == "N") {
						errSkuList.add(dto.getSku().toString());
						continue;
					}
					// 중복등록 유효성 검증
					if(checkData.getSkuDupChk() == "Y") {
						dupSkuList.add(dto.getSku().toString());
						continue;
					}
					commonDao.insert(SERVICEID_PREFIX +"insertDetailList", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateDetailList", entity);
				} else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteDetailList", entity);
				}
			}
		}
		
		if(errSkuList.length() > 0) {
			errMsg.append(errSkuList.toString()).append("은 존재하지 않는 상품코드여서 제외되었습니다.");
		}
		
		if(dupSkuList.length() > 0) {
			errMsg.append(dupSkuList.toString()).append("은 이미등록되어있는 상품코드여서 제외되었습니다.");
		}
		
		result.setResultMessage(errMsg.length() > 0 ? errMsg.toString() : CanalFrameConstants.MSG_COM_SUC_CODE);
	
		return result;
	}
	
	/**
	 * @description :자동발주관리 상세 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public OmAutoOrderSetupInfoResDto saveMasterInfo(OmAutoOrderSetupReqDto dto) {
		
		OmAutoOrderSetupInfoResDto result = new OmAutoOrderSetupInfoResDto();
		OmAutoOrderSetupInfoResDto checkData;
		StringJoiner errOrganizeList = new StringJoiner(",");
		StringBuffer errMsg = new StringBuffer();
		
		if (null != dto) {
			
			if("1000".equals(dto.getFromDccode()) || "2170".equals(dto.getFromDccode())) {
				checkData = commonDao.selectOne(SERVICEID_PREFIX + "validateSkuInfo", dto);
				
				if(checkData.getOrganizeChk() <=0) {
					errOrganizeList.add(dto.getOrganize().toString());
				}
			}
			OmAutoOrderSetupEntity entity = ModelMapperUtil.map(dto, userContext, OmAutoOrderSetupEntity.class);
	
			if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
				commonDao.insert(SERVICEID_PREFIX +"insertMasterInfo", entity);
			} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				commonDao.selectOne(SERVICEID_PREFIX +"updateMasterInfo", entity);
			}
		}
		
		if(errOrganizeList.length() > 0) {
			errMsg.append(errOrganizeList.toString()).append("은 존재하지 않는 저장위치 입니다.확인후 재등록 해주세요.");
		}
		
		result.setResultMessage(errMsg.length() > 0 ? errMsg.toString() : CanalFrameConstants.MSG_COM_SUC_CODE);
		
		return result;
	}
	
	/**
	 * @description :자동발주관리 예정량확인 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmAutoOrderSetupHistoryResDto> saveAutoOrderList(OmAutoOrderSetupReqDto dto) {
		
		/*END.Temp Table Insert*/
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(dto, dto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 및 실행 - 업무(2/4)
		String[] keyList = {"PROCEDURE","PROCESSTYPE","PROCESSCREATOR","SPID","DELIVERYDATE","STORERKEY","PURCHASE_CD","RUNMODE"};
		Object[] valueList = {PAKAGE_NAME,dto.getProcesstype(), dto.getGUserId(), dto.getGSpid(), dto.getDeliverydate(), dto.getStorerkey(), dto.getPurchaseCd(), "MANUAL"};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		dto.setAvc_ORGANIZE("%");
		dto.setAvc_AREA("%");
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto);
		
		// 프로시저 OUT Parameter(3/4)
		String resultCode    = (String)dto.getResultCode();
		String resultMessage = (String)dto.getResultMessage();
		String processName = "";
		
		if("getHistoryList".equals(dto.getQueryId())) {
			processName = "강제실행";
		}else if("getDataCalcRsltlist".equals(dto.getQueryId())) {
			processName = "예정량확인";
		}
		
		// 프로시저 Exception 처리(4/4)
		if (!resultCode.equals("0")) {
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{processName}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		
		return commonDao.selectList(SERVICEID_PREFIX + dto.getQueryId(), dto);
	}
	
	/**
	 * @description : 자동발주관리 상세내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmAutoOrderSetupInfoResDto> getMasterInfoSetupList(OmAutoOrderSetupReqDto dto) {
		List<OmAutoOrderSetupInfoResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterInfoSetupList", dto);
		return result;
	}
	
	/**
	 * @description :발주 상세설정내역 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public OmAutoOrderSetupInfoResDto saveMasterInfoSetupList(List<OmAutoOrderSetupReqDto> dtoList) {
		
		OmAutoOrderSetupInfoResDto result = new OmAutoOrderSetupInfoResDto();
		OmAutoOrderSetupInfoResDto checkData;
		StringJoiner errCustkeyList = new StringJoiner(",");
		StringJoiner errClosetypeList = new StringJoiner(",");
		StringJoiner errDistancetypeList = new StringJoiner(",");
		StringBuffer errMsg = new StringBuffer();
		
		if (null != dtoList) {
			for(OmAutoOrderSetupReqDto dto : dtoList) {
				
				OmAutoOrderSetupEntity entity = ModelMapperUtil.map(dto, userContext, OmAutoOrderSetupEntity.class);
				
				if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"deleteMasterInfoSetup", entity);
				}
				
				if("INC_CUSTKEY".equals(dto.getPurchaseConfCd())) {
					checkData = commonDao.selectOne(SERVICEID_PREFIX + "validateSetupInfoForCustkey", dto);
					
					if(checkData.getCustkeyChk() <=0) {
						errCustkeyList.add(dto.getConfCd().toString());
						continue;
					}
				}else if("INC_CLOSETYPE".equals(dto.getPurchaseConfCd())) {
					checkData = commonDao.selectOne(SERVICEID_PREFIX + "validateSetupInfoForClosetype", dto);
					
					if(checkData.getClosetypeChk() <=0) {
						errClosetypeList.add(dto.getConfCd().toString());
						continue;
					}
				}else if("INC_DISTANCETYPE".equals(dto.getPurchaseConfCd())) {
					checkData = commonDao.selectOne(SERVICEID_PREFIX + "validateSetupInfoForDistancetype", dto);
					
					if(checkData.getDistancetypeChk() <=0) {
						errDistancetypeList.add(dto.getConfCd().toString());
						continue;
					}
				} else {
					 throw new UserHandleException("지정된 설정코드에 대한 입력이 아닙니다. 재조회하여 시도해 주세요.");
				}

				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus()) || (CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					//유효성 검증 완료시에 신규등록
					commonDao.insert(SERVICEID_PREFIX +"saveMasterInfoSetup", entity);	
				}
			}
		}
		
		if(errCustkeyList.length() > 0) {
			errMsg.append(errCustkeyList.toString()).append("은 존재하지 않는 고객코드여서 제외되었습니다.");
		}
		if(errClosetypeList.length() > 0) {
			if( errMsg.length() > 0 ){
				errMsg.append("\n");
			}
			errMsg.append(errClosetypeList.toString()).append("은 존재하지 않는 마감유형코드여서 제외되었습니다.");
		}
		if(errDistancetypeList.length() > 0) {
			if( errMsg.length() > 0 ){
				errMsg.append("\n");
			}
			errMsg.append(errDistancetypeList.toString()).append("은 존재하지 않는 원거리유형코드여서 제외되었습니다.");
		}
		
		result.setResultMessage(errMsg.length() > 0 ? errMsg.toString() : CanalFrameConstants.MSG_COM_SUC_CODE);
		
		return result;
	}
	
	/**
	 * @description : 자동발주관리 변경이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmAutoOrderSetupHistoryResDto> getEditHistoryList(OmAutoOrderSetupReqDto dto) {
		List<OmAutoOrderSetupHistoryResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getEditHistoryList", dto);
		return result;
	}
	
	/**
	 * @description : 자동발주관리 마감유형 상세설정 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.18 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	public OmAutoOrderSetupInfoResDto saveDeleteCloseType(List<OmAutoOrderSetupReqDto> dtoList) {
		OmAutoOrderSetupInfoResDto result = new OmAutoOrderSetupInfoResDto();
		if (null != dtoList) {
			for(OmAutoOrderSetupReqDto dto : dtoList) {
				log.info(">>>>>>>>>>>> rowIndex:", dto.getRowIndex());
				commonDao.update(SERVICEID_PREFIX +"saveDeleteCloseType", dto);
			}
		}
		return result;
	}
	
}
