package cjfw.wms.gwms.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.batch.dto.BatchMngReqDto;
import cjfw.wms.gwms.service.GwmsUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Canal Framework style controller (no FrameOne dependencies).
 * - Uses @RestController and returns JSON directly.
 * - Accepts/produces JSON and uses Map<String,Object> instead of FrameOne Parameters.
 */
@Tag(name = "중계 API 호출", description = "중계 API 호출")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/adapterGwms")
public class GwmsInterfaceController {
//    private final BatchMngService batchMngService

    @GetMapping(value="/v1.0/multiSelectTest")
    public String multiSelectTest(@Valid BatchMngReqDto dto) {
        Map<String, Object> outParameters = new LinkedHashMap<String, Object>();
        String outParam = "";
        outParam = GwmsUtils.toJsonString(outParameters);
        return outParam;
    }

    @PostMapping(value="/v1.0/multiSelect")
    public String multiSelect(HttpServletRequest request, @RequestBody String jsonData, Map<String, Object> inParams) {
        Map<String, Object> outParameters = new LinkedHashMap<String, Object>();
        String outParam = "";
        String errCode = "0";
        Object errMsg = "";

        // 인증처리를 위한 값셋팅 - AS-IS 인증처리부분 주석으로 되여있음.
        //String interfaceId      = StringUtils.defaultIfBlank(request.getHeader("Interface_id"), "");
        //String interfaceAuthKey = StringUtils.defaultIfBlank(request.getHeader("Interface_auth_key"), "");

        try {
            Map<String, Object> jsonMap = GwmsUtils.parseJsonToMap(jsonData);

            String className    = String.valueOf(jsonMap.get("CLASS_NM"));
            String methodName   = String.valueOf(jsonMap.get("METHOD_NM"));
            String systemTypeCd = String.valueOf(jsonMap.get("system_type_cd"));
            String opcode       = String.valueOf(jsonMap.get("opcode"));

            Object item = jsonMap.get("inParams");
            if (item instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> itemMap = (Map<String, Object>) item;
                inParams.putAll(itemMap);
            }

            outParameters = GwmsUtils.adapter(className, methodName, inParams);
            if (outParameters == null) outParameters = new LinkedHashMap<String, Object>();
            outParameters.put("system_type_cd", systemTypeCd);
            outParameters.put("opcode", opcode);
            outParam = GwmsUtils.toJsonString(outParameters);

        } catch (UserHandleException e1) {
            errCode = "-1";
            errMsg = (e1.getMessage() != null ? e1.getMessage() : e1.toString());
        } catch (InvocationTargetException e2) {
            try {
                // target에서 UserHandleException
                errCode = "-1";
                errMsg  = (e2.getMessage() != null ? e2.getMessage() : e2.toString());
            }catch (Exception e3) {
                // target에서 Exception
                errCode = "-1";
                errMsg = e2.getTargetException().toString();
            }
        } catch (Exception e4) {
            errCode = "-1";
            errMsg = e4.toString();
        } finally {
            // 에러일경우 응답값 처리
            if (!"0".equals(errCode)) {
                outParameters.put("ErrorCode", errCode);
                outParameters.put("SVC_ERR_MSG_TEXT", errMsg);
                outParam = GwmsUtils.toJsonString(outParameters);
            }
        }

        return outParam;
    }

	/**
	 * 행정동기준 출고센터 전송
	 * @param  요청Map
	 * @return 처리결과(요청Map+결과코드)
	 */
    @PostMapping(value="/hjdong_dccode")
    public String hjdongDccode(HttpServletRequest request, @RequestBody String jsonData) {
    	final String CONST_IF_ID   = "MDM0001";												//인증용 ID
    	final String CONST_IF_KEY  = ContextUtil.getProperty("cf.apiJwt.secretKey");		//인증용 KEY
		final String CONST_CLS_NM  = "cjfw.wms.gwms.service.GwmsHjdongDccodeApiService"; 	//요청전문에 CLASS명  미포함. 강제지정함
        final String CONST_MTHD_NM = "getDccodeApiData";									//요청전문에 METHOD명 미포함. 강제지정함
        //
        final String CONST_ERR_AUTH_FAIL = "AUTH_FAIL";
        final String CONST_ERR_INVALID_PARAMS = "INVALID_PARAMS";
        
    	Map<String, Object> outParameters = new LinkedHashMap<>();
        String outParam = "";
        String errCode = "0";
        Object errMsg = "";

        try {
            Map<String, Object> jsonMap = GwmsUtils.parseJsonToMap(jsonData);
            jsonMap.put("ip", (String)request.getAttribute(CanalFrameConstants.CLIENT_IP));
            jsonMap.put("url", request.getRequestURL().toString());

			// 인증처리 체크
			String interfaceId      = String.valueOf(jsonMap.get("Interface_id")).trim();
			String interfaceAuthKey = String.valueOf(jsonMap.get("Interface_auth_key")).trim();
			if (!(interfaceId.equals(CONST_IF_ID) && interfaceAuthKey.equals(CONST_IF_KEY))) {
				throw new UserHandleException(CONST_ERR_AUTH_FAIL);  //Authentication Failed
			}
			// 검색 조건값 체크
			String hjdcd  = (String) jsonMap.get("pv_hjdcd");    
			String ogrpid = (String) jsonMap.get("pv_otcogrpid");
            if (StringUtils.isEmpty(hjdcd) || StringUtils.isEmpty(ogrpid)) {
				throw new UserHandleException(CONST_ERR_INVALID_PARAMS);  //파라미터 누락 (검색조건)
			}

			String className        = CONST_CLS_NM;		//String.valueOf(orgnMap.get("CLASS_NM"))
            String methodName       = CONST_MTHD_NM;	//String.valueOf(orgnMap.get("METHOD_NM"))
            //String systemTypeCd     = String.valueOf(orgnMap.get("system_type_cd")): not_used
            //String opcode           = String.valueOf(orgnMap.get("opcode")): not_used

            outParameters = GwmsUtils.adapter(className, methodName, jsonMap);	//Call Service
            
            if (CollectionUtils.isEmpty(outParameters)) {
            	outParameters = new LinkedHashMap<>(jsonMap);
                errCode = "-29999";
                errMsg  = "내부 오류";
            }
            //outParam = GwmsUtils.toJsonString(outParameters) //finally {}에서 처리함

        } catch (UserHandleException e1) {
        	if (e1.getErrorCode().equals(CONST_ERR_AUTH_FAIL)) {
            	errCode = "-1";
                errMsg = "Authentication Failed";
        	} else if (e1.getErrorCode().equals(CONST_ERR_INVALID_PARAMS)) {
            	errCode = "-5";
                errMsg = "INVALID PARAMS:[pv_hjdcd],[pv_otcogrpid]";
        	}
        } catch (InvocationTargetException e2) {
            try {
                // target에서 UserHandleException
                errCode = "-2";
                errMsg  = (e2.getMessage() != null ? e2.getMessage() : e2.toString());
            }catch (Exception e3) {
                // target에서 Exception
                errCode = "-3";
                errMsg = e2.getTargetException().toString();
            }
        } catch (Exception e4) {
            errCode = "-4";
            errMsg = e4.toString();
        } finally {
            // 에러일경우 응답값 처리
            if (!"0".equals(errCode)) {
                outParameters.put("result_code", errCode);
                outParameters.put("error_message", errMsg);
            }
            outParam = GwmsUtils.toJsonString(outParameters);
        }

        return outParam;
    }
}
