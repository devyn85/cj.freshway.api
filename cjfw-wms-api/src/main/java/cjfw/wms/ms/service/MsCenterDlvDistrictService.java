package cjfw.wms.ms.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupPopReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupPopResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictHjdongReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictHjdongResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictResDto;
import cjfw.wms.ms.dto.MsDistrictGroupPopReqDto;
import cjfw.wms.ms.dto.MsDistrictGroupPopResDto;
import cjfw.wms.ms.dto.MsDlvDistrictPolygonReqDto;
import cjfw.wms.ms.dto.MsDlvDistrictPolygonResDto;
import cjfw.wms.ms.entity.MsDlvDistrictGroupPopEntity;
import cjfw.wms.ms.entity.MsDlvDistrictHjdongEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Deprecated
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCenterDlvDistrictService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * 
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCenterDlvDistrictService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 배송 권역 목록 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDlvDistrictResDto> getMasterList(MsCenterDlvDistrictReqDto dto){
		dto.setGMultiDccodeList(dto.getGMultiDccode().split(","));
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * @description : 배송 권역 목록 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String saveMasterList(MsCenterDlvDistrictReqDto dto){
		MsCenterDlvDistrictReqDto searchDto = new MsCenterDlvDistrictReqDto();
        int iCnt = 0; // 등록 건수
        int uCnt = 0; // 수정 건수
        int dCnt = 0; // 삭제 건수
		
		if((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
			searchDto.setDccode(dto.getDccode());
			searchDto.setDlvdistrictId(dto.getDlvdistrictId());
//			List<MsCenterDlvDistrictResDto> duplicateData = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", searchDto);
//			if(!duplicateData.isEmpty()) {
//				throw new UserHandleException("MSG.COM.VAL.067", new String[] {String.format("권역 코드 %s", dto.getDlvdistrictId())});
//			}
			iCnt +=  commonDao.insert(SERVICEID_PREFIX + "saveMasterList", dto);
		}else if((CanalFrameConstants.UPDATE).equals(dto.getRowStatus()) || (CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
			if((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				
				searchDto.setSerialkey(dto.getSerialkey());
				MsCenterDlvDistrictResDto originData = commonDao.selectOne(SERVICEID_PREFIX + "getMasterList", searchDto);
				dto.setDelYn("N");
				
				if(originData == null) {
					throw new UserHandleException("MSG.COM.VAL.121");
				}
				
				
				// 권역그룹을 변경했을경우 -> ( 기존권역 기존 TODATE D+2일, 신규 FROMDATE D+3일 )
				if(!originData.getDlvgroupId().equals(dto.getDlvgroupId())) {
					
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			        LocalDate fromDate = LocalDate.parse(originData.getFromDate(), formatter);
			        LocalDate today = LocalDate.now();
			        
			        dto.setDlvgroupId(dto.getDlvgroupId() == null ? " " : dto.getDlvgroupId());
			        
					if(fromDate.isAfter(today)) {
						uCnt += commonDao.update(SERVICEID_PREFIX + "updateMasterList", dto);
					} else {
						iCnt += commonDao.insert(SERVICEID_PREFIX + "updateAndInsertDistrict", dto);
					}

					// 2. 센터 권역 그룹 폴리곤 갱신
					dCnt += commonDao.delete(SERVICEID_PREFIX + "deleteCenterDlvDistrictGroupPolygon", originData);
					iCnt += commonDao.insert(SERVICEID_PREFIX + "saveCenterDlvDistrictGroupPolygon", originData);
					dCnt += commonDao.delete(SERVICEID_PREFIX + "deleteCenterDlvDistrictGroupPolygon", dto);
					iCnt += commonDao.insert(SERVICEID_PREFIX + "saveCenterDlvDistrictGroupPolygon", dto);
					
					return CanalFrameConstants.MSG_COM_SUC_CODE;
				}
				uCnt += commonDao.update(SERVICEID_PREFIX + "updateMasterList", dto);
			}
			
			if((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
				dto.setDelYn("Y");
				uCnt += commonDao.update(SERVICEID_PREFIX + "deleteMasterList", dto);
			}
			
			dto.setDlvgroupId(dto.getDlvgroupId() == null ? " " : dto.getDlvgroupId());
			

			uCnt += commonDao.update(SERVICEID_PREFIX + "updateHjdongListByDistrictSerialkey", dto);
			dCnt += commonDao.delete(SERVICEID_PREFIX + "deleteCenterDlvDistrictPolygon", dto);
			iCnt += commonDao.insert(SERVICEID_PREFIX + "saveCenterDlvDistrictPolygon", dto);
		}
		return (iCnt + uCnt + dCnt) > 1 ? CanalFrameConstants.MSG_COM_SUC_CODE : CanalFrameConstants.MSG_COM_ERR_CODE;
	}
	
	/**
	 * @description : 센터 배송 권역 / 그룹 폴리곤 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsDlvDistrictPolygonResDto> getDlvDistrictPolygon(MsDlvDistrictPolygonReqDto dto) {
		dto.setGMultiDccodeList(dto.getGMultiDccode().split(","));
		if((dto.getDlvDistrictType()).equals("DISTRICT")) {
			return commonDao.selectList(SERVICEID_PREFIX + "getDlvCenterDistrictPolygonList", dto);
		} else if((dto.getDlvDistrictType()).equals("GROUP")) {
			return commonDao.selectList(SERVICEID_PREFIX + "getDlvCenterDistrictGroupPolygonList", dto);
		} else {
			throw new UserHandleException("MSG.COM.VAL.121");
		}
	}
	
	/**
	 * @description : 배송 권역 행정동 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String saveHjdongList(MsCenterDlvDistrictHjdongReqDto dto) {
		
		// 중복된 행정동 권역 폴리곤 갱신용 권역 아이디 SET
		HashSet<String> duplicateHjdongDistrictIdSet = new HashSet<>();
		
		// 중복된 행정동 권역그룹 폴리곤 갱신용 권역그룹 아이디 SET
		HashSet<String> updatedDistrictGroupIdSet = new HashSet<>();

		MsCenterDlvDistrictReqDto districtDto = new MsCenterDlvDistrictReqDto();
		districtDto.setSerialkey(dto.getSerialkey());
		
		List<MsCenterDlvDistrictResDto> dlvDistrictResDto = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", districtDto);
		if(dlvDistrictResDto.isEmpty()) throw new UserHandleException("MSG_COM_ERR_056", new String[] {"배송 권역"});
		
		for(MsCenterDlvDistrictHjdongReqDto.Hjdong hjdongDto : dto.getHjdongList()) {
			MsDlvDistrictHjdongEntity msDlvDistrictHjdongEntity = ModelMapperUtil.map(hjdongDto, userContext, MsDlvDistrictHjdongEntity.class);
			msDlvDistrictHjdongEntity.setDccode(dto.getDccode());
			msDlvDistrictHjdongEntity.setDlvdistrictId(dto.getDlvdistrictId());
			
			if((CanalFrameConstants.INSERT).equals(hjdongDto.getRowStatus())) {
				
				List<MsCenterDlvDistrictHjdongResDto> validateDataList = commonDao.selectList(SERVICEID_PREFIX + "getSaveHjdongValidate", msDlvDistrictHjdongEntity);
				
				
				// 추가될 권역 행정동과 겹치는 데이터 삭제처리
				if(validateDataList != null) {
					for (MsCenterDlvDistrictHjdongResDto validateData : validateDataList) {
						MsDlvDistrictHjdongEntity duplicateHjdongEntity = new MsDlvDistrictHjdongEntity();
						duplicateHjdongEntity.setSerialkey(validateData.getSerialkey());
						duplicateHjdongEntity.setDccode(validateData.getDccode());
						duplicateHjdongEntity.setDelYn("Y");
						duplicateHjdongEntity.setToDate(hjdongDto.getFromDate() + 1);
						commonDao.update(SERVICEID_PREFIX + "deleteHjdongList", msDlvDistrictHjdongEntity);
						duplicateHjdongDistrictIdSet.add(validateData.getDlvdistrictId());
						updatedDistrictGroupIdSet.add(validateData.getDlvgroupId());
					}
				}
				
				commonDao.insert(SERVICEID_PREFIX + "saveHjdongList", msDlvDistrictHjdongEntity);
			}else if((CanalFrameConstants.UPDATE).equals(hjdongDto.getRowStatus())) {
				msDlvDistrictHjdongEntity.setDelYn("N");
				// [TODO] 업데이트로 미래의 다른 행정동 데이터와 겹칠시 삭제 /폴리곤 갱신 필요
				
				commonDao.update(SERVICEID_PREFIX + "updateHjdongList", msDlvDistrictHjdongEntity);
			}else if((CanalFrameConstants.DELETE).equals(hjdongDto.getRowStatus())) {
				msDlvDistrictHjdongEntity.setDelYn("Y");
				commonDao.update(SERVICEID_PREFIX + "deleteHjdongList", msDlvDistrictHjdongEntity);
			}
		}
		
		// 1. 중복된 행정동 권역 폴리곤 갱신
		if(!duplicateHjdongDistrictIdSet.isEmpty()) {
			duplicateHjdongDistrictIdSet.forEach(districtId -> {
				MsCenterDlvDistrictReqDto duplicateDistrictDto = new MsCenterDlvDistrictReqDto();	
				duplicateDistrictDto.setDccode(dto.getDccode());
				duplicateDistrictDto.setDlvdistrictId(districtId);
				commonDao.delete(SERVICEID_PREFIX + "deleteCenterDlvDistrictPolygon", districtDto);
				commonDao.insert(SERVICEID_PREFIX + "saveCenterDlvDistrictPolygon", districtDto);
			});
			
			// 2. 중복된 권역 그룹 폴리곤 갱신
			if(!updatedDistrictGroupIdSet.isEmpty()) {
				updatedDistrictGroupIdSet.forEach(districtGroupId -> {
					MsCenterDlvDistrictReqDto duplicateDistrictGroupDto = new MsCenterDlvDistrictReqDto();	
					duplicateDistrictGroupDto.setDlvgroupId(districtGroupId);
					duplicateDistrictGroupDto.setDccode(dto.getDccode());
					commonDao.delete(SERVICEID_PREFIX + "deleteCenterDlvDistrictGroupPolygon", duplicateDistrictGroupDto);
					commonDao.insert(SERVICEID_PREFIX + "saveCenterDlvDistrictGroupPolygon", duplicateDistrictGroupDto);
				});
			}
		}
		

		
		districtDto.setDlvdistrictId(dlvDistrictResDto.get(0).getDlvdistrictId());
		districtDto.setDlvgroupId(dlvDistrictResDto.get(0).getDlvgroupId() == null ? " " : dlvDistrictResDto.get(0).getDlvgroupId());
		districtDto.setDccode(dlvDistrictResDto.get(0).getDccode());
		commonDao.delete(SERVICEID_PREFIX + "deleteCenterDlvDistrictPolygon", districtDto);
		commonDao.insert(SERVICEID_PREFIX + "saveCenterDlvDistrictPolygon", districtDto);
		commonDao.delete(SERVICEID_PREFIX + "deleteCenterDlvDistrictGroupPolygon", districtDto);
		commonDao.insert(SERVICEID_PREFIX + "saveCenterDlvDistrictGroupPolygon", districtDto);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	
	public List<MsCenterDlvDistrictHjdongResDto> getSaveHjdongListValidation(MsCenterDlvDistrictHjdongReqDto dto){
		List<MsCenterDlvDistrictHjdongResDto> resultList = new ArrayList<>();
		for(MsCenterDlvDistrictHjdongReqDto.Hjdong hjdongDto : dto.getHjdongList()) {
			MsCenterDlvDistrictHjdongResDto resDto = ModelMapperUtil.map(hjdongDto, userContext, MsCenterDlvDistrictHjdongResDto.class);
			resDto.setDccode(dto.getDccode());
			if((CanalFrameConstants.INSERT).equals(hjdongDto.getRowStatus())) {
				List<MsCenterDlvDistrictHjdongResDto> validateDataList = commonDao.selectList(SERVICEID_PREFIX + "getSaveHjdongValidate", resDto);
				if(validateDataList != null) {
					for(MsCenterDlvDistrictHjdongResDto validateRes : validateDataList) {
						resDto.addMessage(MessageUtil.getMessage("MSG.COM.VAL.067", new String[]{"권역 " + validateRes.getDlvdistrictId()}));
					}
				}
			}
			resultList.add(resDto);
		}
		return resultList;
	}
	
	/**
	 * @description : 배송 권역 행정동 목록 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDlvDistrictHjdongResDto> getHjdongList(MsCenterDlvDistrictReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getHjdongList", dto);
	}
	
	
	/**
	 * @description : 센터 배송 권역 그룹 리스트 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDlvDistrictGroupResDto> getDistrictGroupList(MsCenterDlvDistrictGroupReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getDistrictGroupList", dto);
	}	
	
	/**
	 * @description : 센터 배송 권역 사용 현황 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDlvDistrictHjdongResDto> getCenterDistrictUsageList(MsCenterDlvDistrictGroupReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getCenterDistrictUsageList", dto);
	}
	
	
	/**
	 * @description : 센터 배송 권역 X POP 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String saveDistrictGroupXPop(MsCenterDlvDistrictGroupPopReqDto dto) {
		
		MsCenterDlvDistrictGroupReqDto districtGroupIdsearchDto = new MsCenterDlvDistrictGroupReqDto();
		districtGroupIdsearchDto.setDccode(dto.getDccode());
		districtGroupIdsearchDto.setDlvgroupId(dto.getDlvgroupId());
		
		if((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
			List<MsCenterDlvDistrictGroupResDto> validateDtoList = commonDao.selectList(SERVICEID_PREFIX + "getDistrictGroupList", districtGroupIdsearchDto);
//			if(!validateDtoList.isEmpty()) {
//				validateDtoList.forEach(validateDto -> {
//					if(validateDto.getDelYn() == "N") {
//						throw new UserHandleException("MSG.COM.VAL.067", new String[] {"권역 그룹 ID"});
//					}
//				});
//			}
			int serialkey = commonDao.insert(SERVICEID_PREFIX + "saveDlvDistrictGroup", dto);
			dto.setSerialkey(String.valueOf(serialkey));
		}else if((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
			commonDao.update(SERVICEID_PREFIX + "updateDlvDistrictGroup", dto);
		}else if((CanalFrameConstants.DELETE).equals(dto.getRowStatus())){
			dto.setDelYn("Y");
			commonDao.update(SERVICEID_PREFIX + "deleteDlvDistrictGroup", dto);
		}
		
		for (MsCenterDlvDistrictGroupPopReqDto.Pop popDto : dto.getPopList()) {
			MsDlvDistrictGroupPopEntity msDlvDistrictGroupPopEntity = ModelMapperUtil.map(popDto, userContext, MsDlvDistrictGroupPopEntity.class);
			msDlvDistrictGroupPopEntity.setDccode(dto.getDccode());
			msDlvDistrictGroupPopEntity.setDlvgroupId(dto.getDlvgroupId());
			
			if((CanalFrameConstants.INSERT).equals(popDto.getRowStatus())) {
				commonDao.insert(SERVICEID_PREFIX + "saveDlvDistrictGroupPop", msDlvDistrictGroupPopEntity);
			}else if((CanalFrameConstants.UPDATE).equals(popDto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX + "updateDlvDistrictGroupPop", msDlvDistrictGroupPopEntity);
			}else if((CanalFrameConstants.DELETE).equals(popDto.getRowStatus())){
				popDto.setDelYn("Y");
				commonDao.update(SERVICEID_PREFIX + "deleteDlvDistrictGroupPop", msDlvDistrictGroupPopEntity);
			}
		}
		
		
		// 데이터 검증
		MsCenterDlvDistrictGroupPopReqDto searchDto = new MsCenterDlvDistrictGroupPopReqDto();
		searchDto.setDccode(dto.getDccode());
		searchDto.setDlvgroupId(dto.getDlvgroupId());
		searchDto.setEffectiveDate(dto.getEffectiveDate());
		int valdateData = commonDao.selectOne(SERVICEID_PREFIX + "getDistrictGroupXPopValidate", dto);
		
		if(valdateData == 1) {
			throw new UserHandleException("MSG.COM.VAL.067", new String[] {"POP 번호 시간대"});
		}
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 배송 권역 그룹 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String saveDistrictGroup(List<MsCenterDlvDistrictGroupReqDto> dtoList) {
		for (MsCenterDlvDistrictGroupReqDto dto : dtoList) {
			MsCenterDlvDistrictGroupReqDto districtGroupIdsearchDto = new MsCenterDlvDistrictGroupReqDto();
			districtGroupIdsearchDto.setDccode(dto.getDccode());
			districtGroupIdsearchDto.setDlvgroupId(dto.getDlvgroupId());
			
			if((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
//				List<MsCenterDlvDistrictGroupResDto> validateDtoList = commonDao.selectList(SERVICEID_PREFIX + "getDistrictGroupList", districtGroupIdsearchDto);
//				if(!validateDtoList.isEmpty()) {
//					validateDtoList.forEach(validateDto -> {
//						if(validateDto.getDelYn().equals("N")) {
//							throw new UserHandleException("MSG.COM.VAL.067", new String[] {"권역 그룹 ID"});
//						}
//					});
//				}
				commonDao.insert(SERVICEID_PREFIX + "saveDlvDistrictGroup", dto);
			}else if((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX + "updateDlvDistrictGroup", dto);
			}else if((CanalFrameConstants.DELETE).equals(dto.getRowStatus())){
				dto.setDelYn("Y");
				commonDao.update(SERVICEID_PREFIX + "deleteDlvDistrictGroup", dto);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터 배송 권역 X POP 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDlvDistrictGroupPopResDto> getDistrictGroupXPop(MsCenterDlvDistrictGroupPopReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDistrictGroupXPop", dto);
	}

	/**
	 * @description : 대표 POP 마스터 리스트 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.08 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsDistrictGroupPopResDto> getPopList(MsDistrictGroupPopReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getPopList", dto);
	}
	
	/**
	 * @description : 대표 POP 마스터 리스트 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.08 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String savePopList(List<MsDistrictGroupPopReqDto> dtoList){
		for(MsDistrictGroupPopReqDto dto : dtoList) {
			if((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
				List<MsDistrictGroupPopResDto> validationResult = commonDao.selectList(SERVICEID_PREFIX + "getPopValidationInsert", dto);
				if(!validationResult.isEmpty()) {
					validationResult.forEach(
							result-> {
								if(result.getPopNo().equals(dto.getPopNo())) {
									throw new UserHandleException("MSG.COM.VAL.067", new String[] {"POP 번호"+ " "+ result.getPopNo()+ " "+ result.getPopName() + "(" + result.getFromDate()+ "-"+ result.getToDate() + ")"});
								}
								if("Y".equals(dto.getBaseYn()) && "Y".equals(result.getBaseYn())){
									throw new UserHandleException("MSG.COM.VAL.067",new String[] {"대표POP는 1건만 설정할 수 있습니다. "+ result.getPopNo() + " "+ result.getPopName() + "(" + result.getFromDate()+ "-"+ result.getToDate() + ")"});
								}
							}
						);
					}
				commonDao.insert(SERVICEID_PREFIX + "savePopList", dto);
			}else if((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				List<MsDistrictGroupPopResDto> validationResult = commonDao.selectList(SERVICEID_PREFIX + "getPopValidationUpdate", dto);
				validationResult.forEach(
						result-> {
							if(!result.getSerialkey().equals(dto.getSerialkey())) {
								if(result.getPopNo().equals(dto.getPopNo())) {
									throw new UserHandleException("MSG.COM.VAL.067", new String[] {"POP 번호"+ " "+ result.getPopNo()+ " "+ result.getPopName() + "(" + result.getFromDate()+ "-"+ result.getToDate() + ")"});	
								}
								if("Y".equals(dto.getBaseYn()) && "Y".equals(result.getBaseYn())){
									throw new UserHandleException("MSG.COM.VAL.067",new String[] {"대표 POP 대표POP는 1건만 설정할 수 있습니다."+ result.getPopNo() + " "+ result.getPopName() + "(" + result.getFromDate()+ "-"+ result.getToDate() + ")"});
								}
							} else {
								if(result.getFromDate() != null) {
									throw new UserHandleException("MSG_COM_ERR_055", new String[] {"(해당 POP를 사용중인 권역이 존재합니다) 업데이트"});	
								}
							}
						}
					);
				dto.setDelYn("N");
				commonDao.update(SERVICEID_PREFIX + "updateDistrictGroupPop", dto);
			}else if((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
				List<MsDistrictGroupPopResDto> validationResult = commonDao.selectList(SERVICEID_PREFIX + "getPopValidationUpdate", dto);
				validationResult.forEach(
						result-> {
							if(result.getSerialkey().equals(dto.getSerialkey())) {
								if(result.getFromDate() != null) {
									throw new UserHandleException("MSG_COM_ERR_055", new String[] {"(해당 POP를 사용중인 권역이 존재합니다) 삭제"});	
								}
							}
						}
					);
				dto.setDelYn("Y");
				commonDao.update(SERVICEID_PREFIX + "deleteDistrictGroupPop", dto);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
