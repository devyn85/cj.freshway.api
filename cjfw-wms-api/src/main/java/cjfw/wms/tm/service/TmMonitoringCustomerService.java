package cjfw.wms.tm.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.ib.entity.IbExpenseEmailLogEntity;
import cjfw.wms.tm.dto.TmMonitoringCustomerReqDto;
import cjfw.wms.tm.dto.TmMonitoringCustomerResDto;
import cjfw.wms.tm.entity.TmMonitoringCustomerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.24
 * @description : 배송고객모니터링 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.24 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmMonitoringCustomerService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmMonitoringCustomerService.";

	private final CommonDao commonDao;
	private final UserContext userContext;

	/**
	 * @description : getMasterList
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 JiHoPark  생성 </pre>
	 */
	public List<TmMonitoringCustomerResDto> getMonitoringCustomerGroupList(TmMonitoringCustomerReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMonitoringCustomerGroupList", dto);
	}

	/**
	 * @description : 배송고객모니터링 - 모니터링 그룹 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 JiHoPark  생성 </pre>
	 */
	public List<TmMonitoringCustomerResDto> getMonitoringCustomerGroupDetailList(TmMonitoringCustomerReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMonitoringCustomerGroupDetailList", dto);
	}

	/**
	 * @description : 배송고객모니터링 - 배송고객모니터링 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 JiHoPark  생성 </pre>
	 */
	public List<TmMonitoringCustomerResDto> getMasterList(TmMonitoringCustomerReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 배송고객모니터링 - 배송고객모니터링 목록 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.26 JiHoPark  생성 </pre>
	 */
    public String saveMasterList(TmMonitoringCustomerReqDto dto) {
    	TmMonitoringCustomerEntity entity = new TmMonitoringCustomerEntity();

        List<TmMonitoringCustomerResDto> saveList = dto.getSaveMasterList();
        if (saveList.size() > 0) {
        	for (TmMonitoringCustomerResDto saveDto : saveList) {
        		entity = ModelMapperUtil.map(saveDto, userContext, TmMonitoringCustomerEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "updateMasterList", entity);
        	}
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

    /**
	 * @description : 배송고객모니터링 - 모니터링 그룹 목록 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.27 JiHoPark  생성 </pre>
	 */
    public String saveMonitoringCustomerGroupList(TmMonitoringCustomerReqDto dto) {
    	TmMonitoringCustomerEntity entity = new TmMonitoringCustomerEntity();

    	List<TmMonitoringCustomerResDto> insertList = dto.getInsertCustomerGroupMasterList();
        if (insertList.size() > 0) {
        	for (TmMonitoringCustomerResDto insertDto : insertList) {
        		entity = ModelMapperUtil.map(insertDto, userContext, TmMonitoringCustomerEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "insertMonitoringCustomerGroup", entity);
        	}
        }

        List<TmMonitoringCustomerResDto> updateList = dto.getUpdateCustomerGroupMasterList();
        if (updateList.size() > 0) {
        	for (TmMonitoringCustomerResDto updateDto : updateList) {
        		entity = ModelMapperUtil.map(updateDto, userContext, TmMonitoringCustomerEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "updateMonitoringCustomerGroup", entity);
        	}
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

    /**
	 * @description : 배송고객모니터링 - 모니터링 그룹 상세 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.27 JiHoPark  생성 </pre>
	 */
    public String saveMonitoringCustomerGroupDetailList(TmMonitoringCustomerReqDto dto) {
    	TmMonitoringCustomerEntity entity = new TmMonitoringCustomerEntity();

        List<TmMonitoringCustomerResDto> insertList = dto.getInsertCustomerGroupDetailList();
        if (insertList.size() > 0) {
        	for (TmMonitoringCustomerResDto insertDto : insertList) {
        		entity = ModelMapperUtil.map(insertDto, userContext, TmMonitoringCustomerEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "insertMonitoringCustomerGroupDetail", entity);
        	}
        }

        List<TmMonitoringCustomerResDto> updateList = dto.getUpdateCustomerGroupDetailList();
        if (updateList.size() > 0) {
        	for (TmMonitoringCustomerResDto updateDto : updateList) {
        		entity = ModelMapperUtil.map(updateDto, userContext, TmMonitoringCustomerEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "updateMonitoringCustomerGroupDetail", entity);
        	}
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

    /**
	 * @description : 배송고객모니터링 - 이메일 발송
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.28 JiHoPark  생성 </pre>
	 */
    public String sendEmail(TmMonitoringCustomerReqDto dto) {
        List<TmMonitoringCustomerResDto> emailList = dto.getEmailList();
        if (emailList.size() > 0) {
        	String emailHtmlStart = "<div class=\"tablecontent_sub_title\">"
				        			+ "<div class=\"label\">안녕하세요. CJ 프레시웨이 입니다.<br></div>"
				        			+ "<div class=\"label\"><br></div>"
				        			+ "</div>"
				        			+ "<table class=\"tablecontent tablecontent_grid\" id=\"datatable1\" border=\"1\" cellpadding=\"4\" cellspacing=\"0\" bordercolor=\"grey\">"
				        			+ "<colgroup>"
				        			+ "<col class=\"col1\" width=\"137\">"
				        			+ "<col class=\"col1\" width=\"108\">"
				        			+ "<col class=\"col1\" width=\"96\">"
				        			+ "<col class=\"col1\" width=\"243\">"
				        			+ "<col class=\"col1\" width=\"69\">"
				        			+ "<col class=\"col1\" width=\"105\">"
				        			+ "<col class=\"col1\" width=\"105\">"
				        			+ "<col class=\"col1\" width=\"100\">"
				        			+ "<col class=\"col1\" width=\"74\">"
				        			+ "<col class=\"col1\" width=\"74\">"
				        			+ "<col class=\"col1\" width=\"100\">"
				        			+ "<col class=\"col1\" width=\"109\">"
				        			+ "<col class=\"col1\" width=\"300\">"
				        			+ "</colgroup>"
				        			+ "<thead bgcolor=\"yellow\">"
				        			+ "<tr>"
				        			+ "<th>차량번호</th>"
				        			+ "<th>배송기사</th>"
				        			+ "<th>관리처코드</th>"
				        			+ "<th>관리처명</th>"
				        			+ "<th>중량</th>"
				        			+ "<th>사진촬영 유/무</th>"
				        			+ "<th>적온적재 유/무</th>"
				        			+ "<th>배송이슈 등재</th>"
				        			+ "<th>담당MA</th>"
				        			+ "<th>결품건수</th>"
				        			+ "<th>배송요청 시간</th>"
				        			+ "<th>도착시간 (예정)</th>"
				        			+ "<th>특이사항</th>"
				        			+ "</tr>"
				        			+ "</thead>"
				        			+ "<tbody>";

        	String emailHtmlEnd = "</tbody>"
        						+ "</table>";

        	IbExpenseEmailLogEntity entity = new IbExpenseEmailLogEntity();

        	Map<String, List<TmMonitoringCustomerResDto>> groupByMa = emailList.stream()
        	                                                                   .collect(Collectors.groupingBy(TmMonitoringCustomerResDto::getMa));

        	groupByMa.entrySet().forEach(entry -> {
        		String maUserId = entry.getKey();
        		// 사용자 정보 조회
        		if (StringUtil.isNotEmpty(maUserId)) {
	        		String emailTitle = "";
	        		String emailCont = "";
	        		List<TmMonitoringCustomerResDto> emailContList = entry.getValue();
	        		TmMonitoringCustomerResDto titleInfo = commonDao.selectOne(SERVICEID_PREFIX + "getEmailTitle", emailContList.get(0));

	        		// 이메일 정보가 없는 경우 건너 뜀
	        		if (StringUtil.isNotEmpty(titleInfo) && StringUtil.isNotEmpty(titleInfo.getMailId())) {
	        			emailTitle = titleInfo.getDccodename() + " " + titleInfo.getDeliverydt() + " " + titleInfo.getCustname();
	        			if (StringUtil.isNotEmpty(titleInfo.getGroupName())) {
	        				emailTitle = emailTitle + " " + "(" + titleInfo.getGroupName() + ")";
	        			}

	        			for (TmMonitoringCustomerResDto emailDto : emailContList) {
	            			emailCont = "<tr>"
	    		        					+ "<td align=\"left\">"
	    									+ emailDto.getCarno()
	    									+ "</td>"
	    									+ "<td align=\"center\">"
	    									+ StringUtil.nvl(emailDto.getDriver())
	    									+ "</td>"
	    									+ "<td align=\"center\">"
	    									+ emailDto.getCustkey()
	    									+ "</td>"
	    									+ "<td align=\"left\">"
	    									+ emailDto.getCustname()
	    									+ "</td>"
	    									+ "<td align=\"right\">"
	    									+ emailDto.getWeight().toString()
	    									+ "</td>"
	    									+ "<td align=\"center\">"
	    									+ emailDto.getPhotoYn()
	    									+ "</td>"
	    									+ "<td align=\"center\">"
	    									+ emailDto.getLoadtypeYn()
	    									+ "</td>"
	    									+ "<td align=\"center\">"
	    									+ emailDto.getIssueYn()
	    									+ "</td>"
	    									+ "<td align=\"center\">"
	    									+ emailDto.getMa()
	    									+ "</td>"
	    									+ "<td align=\"right\">"
	    									+ emailDto.getCancelqty().toString()
	    									+ "</td>"
	    									+ "<td align=\"center\">"
	    									+ emailDto.getDeliveryreqdt()
	    									+ "</td>"
	    									+ "<td align=\"center\">"
	    									+ StringUtil.nvl(emailDto.getCustarrivaldt())
	    									+ "</td>"
	    									+ "<td align=\"left\">"
	    									+ StringUtil.nvl(emailDto.getMemo())
	    									+ "</td>"
	    								+ "</tr>";
	            			}

		            		// email 전송
		            		entity.setTitle(emailTitle);
		            		entity.setCnts(emailHtmlStart + emailCont + emailHtmlEnd);
		            		entity.setSendrEmailAddr("cjfreshway@cjfreshway.co.kr");
		            		entity.setRcvrNm(titleInfo.getUserNm());
		            		entity.setRcvrEmailAddr(titleInfo.getMailId());

		            		commonDao.insert("ibExpenseService.insertEmailLog", entity);
	            		}

        		}

        	});

        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
