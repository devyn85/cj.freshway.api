package cjfw.wms.cm.utility;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.core.utility.StringUtil;
import cjfw.wms.common.extend.CommonProcedureDto;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net)
 * @date : 2025.05.23
 * @description : 일반적인 프로시저 호출시 사용하는 파라미터용 Map 객체를 생성하는 팩토리
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.23 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
public class ProcedureParametersFactory {

	/**
	 * @description : 프로시저의 인수로 사용되는 요소들을 추출해서 dto으로 리턴
	 * <pre>
	 * 오라클 패키지를 호출 시 CommonProcedureDto를 확장해서 dto를 사용해야 한다.
	 * // 글로벌 변수 값 설정
	 * dto.setPackagename(packagename);           // 패키지명
	 * dto.setAvc_SYSTEM(dto.getGSystem());       // 시스템
	 * dto.setAvc_DCCODE(dto.getGDccode());       // 센터코드
	 * dto.setAvc_STORERKEY(dto.getGStorerkey()); // 고객사코드
	 * dto.setAvc_WORKER(dto.getUserId());        // 작업자
	 * dto.setAi_SPID(dto.getGSpid());            // SPID
	 * ...
	 * </pre>
	 *
	 * @param CommonProcedureDto 오라클패키지 Dto
	 * @param packagename 패키지명
	 *
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.26 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public static final void createParamDto(CommonProcedureDto dto, String packagename){

		if(dto instanceof CommonProcedureDto) {
			// 글로벌 변수 값 설정
			dto.setPackagename(packagename); 		   // 패키지명
			dto.setAvc_SYSTEM(dto.getGSystem());       // 시스템
			dto.setAvc_DCCODE(dto.getGDccode());       // 센터코드
			dto.setAvc_STORERKEY(dto.getGStorerkey()); // 고객사코드
			dto.setAvc_WORKER(dto.getGUserId());        // 작업자
			dto.setAi_SPID(dto.getGSpid());            // SPID
		}

	}

	/**
	 * @description : UI에서 넘오면 공통변수를 그리드 row의 dto에 복사한다.
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.29 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public static final void initParamDto(CommonProcedureDto sourceDto, CommonProcedureDto targetDto, String packagename){
		targetDto.setPackagename(packagename); 		  		   // 패키지명
		targetDto.setAvc_COMMAND(sourceDto.getAvc_COMMAND());  // 액션커멘드
		//
		targetDto.setAvc_SYSTEM(sourceDto.getGSystem());       // 시스템
		targetDto.setAvc_DCCODE(sourceDto.getGDccode());       // 센터코드
		targetDto.setAvc_STORERKEY(sourceDto.getGStorerkey()); // 고객사코드
		targetDto.setAvc_WORKER(sourceDto.getGUserId());       // 작업자
		targetDto.setAvc_ORGANIZE(sourceDto.getGOrganize());   // 조직
		targetDto.setAvc_AREA(sourceDto.getGArea());  	   // 창고
		targetDto.setAi_SPID(sourceDto.getGSpid());            // SPID
	}

	/**
	 * @description : 프로시저의 인수로 사용되는 요소들을 추출해서 Map으로 리턴
	 * @param fromValue Map으로 변환시킬 DTO
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.23 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public static final Map<String, Object> createParamMap(Object fromValue){
		// DTO를 Map으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> inParams = objectMapper.convertValue(fromValue, Map.class);

		// 글로벌 변수 값 설정
		inParams.put("avc_SYSTEM", inParams.get("gSystem"));
		inParams.put("avc_EXECUTEMODE", "");
		inParams.put("avc_COMMAND", "");
		inParams.put("avc_DCCODE", inParams.get("gDccode"));
		inParams.put("avc_STORERKEY", inParams.get("gStorerkey"));
		inParams.put("avc_ORGANIZE", inParams.get("gOrganize"));
		inParams.put("avc_AREA", inParams.get("gArea"));
		inParams.put("avc_REQUESTCODE", "");
		inParams.put("avc_REQUESTMSG", "");
		inParams.put("avc_WORKER", inParams.get("gUserId"));
		inParams.put("avc_SECURITYKEY", "");
		inParams.put("ai_SPID", inParams.get("gSpid"));
		inParams.put("ERR", "");
		inParams.put("RESULTMSG", "");
		inParams.put("RETURNMSG", "");
		return inParams;
	}

	/**
	 * @description : 프로시저 호출하기 위한 "avc_REQUESTMSG" XML 만들기
	 * <pre>
	 *   {@code
	 *   	<STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
	 *   }
	 * </pre>
	 * @param keyList key 값이 되는 String List
	 * @param valueList key 값에 해당하는 value 값 List
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.26 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public static final String makeRequestMsgXml(String[] keyList, Object[] valueList){
		StringBuffer resultMsg = new StringBuffer();

		if (keyList.length != valueList.length) {
			// key값 길이와 value값 길이다 다름
		} else {
			for(int idx = 0; idx < keyList.length; idx++) {
				resultMsg.append("<").append(keyList[idx]).append(">").append(StringUtil.defaultString(valueList[idx], "")).append("</").append(keyList[idx]).append(">");
			}
		}

		return resultMsg.toString();
	}

}
