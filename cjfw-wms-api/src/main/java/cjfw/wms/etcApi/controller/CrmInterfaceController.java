package cjfw.wms.etcApi.controller;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.etcApi.service.CrmApiService;
import cjfw.wms.gwms.service.GwmsUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


@Tag(name = "CRM API 호출", description = "CRM API 호출")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/crm")
public class CrmInterfaceController {

    private final CrmApiService crmApiService;


    @PostMapping(value="/selectCustDlvInfo")
    public String selectCustDlvInfo(HttpServletRequest request, @RequestBody String jsonData) {
        Map<String, Object> outParameters = new HashMap<String, Object>();
        Map<String, Object> inParams = new HashMap<String, Object>();
        Map<String, Object> outParamMap;

        String systemTypeCd      = "LIF1017";
        String opcode            = "MD005";
        String outParam          = "";
        Object errMsg            = "";
        String errCode           = "0";

        try {
            HashMap<String, Object> jsonMap = GwmsUtils.parseJsonToMap(jsonData);
            inParams.putAll(jsonMap);

            // 서비스 호출
            outParamMap = (Map<String, Object>) crmApiService.selectCustDlvInfo(inParams).get("ds_list");
            outParamMap.put("system_type_cd", systemTypeCd);
            outParamMap.put("opcode", opcode);
            outParam = GwmsUtils.toJsonString(outParamMap);
        } catch (UserHandleException e) {	// UserHandleException
            errCode = "-1";
            errMsg  = e.getMessage();
        } catch (InvocationTargetException e) {	// target에서 Exception
            try {
                errCode = "-1";
                errMsg  = e.getMessage();
            }catch (Exception e2) {
                errCode = "-1";
                errMsg = e.getTargetException().toString();
            }
        } catch (Exception e){	// Exception
            errCode = "-1";
            errMsg = e.toString();
        } finally {
            // 에러일경우 응답값 처리
            if (!errCode.equals("0")) {
                outParameters.put("err_cd", errCode);
                outParameters.put("err_msg_ctt", errMsg);
                outParam = GwmsUtils.toJsonString(outParameters);
            }
        }

        return outParam;
    }


    @PostMapping(value="/insertCustDlvInfo")
    public String insertCustDlvInfo(HttpServletRequest request, @RequestBody String jsonData) {
        Map<String, Object> outParameters = new HashMap<String, Object>();
        Map<String, Object> inParams = new HashMap<String, Object>();
        Map<String, Object> outParamMap;

        String systemTypeCd      = "LIF1017";
        String opcode            = "MD001";
        String outParam          = ""; //return
        Object errMsg            = "";
        String errCode           = "0";

        try {
            HashMap<String, Object> jsonMap = GwmsUtils.parseJsonToMap(jsonData);
            inParams.putAll(jsonMap);

            outParamMap = (Map<String, Object>) crmApiService.insertCustDlvInfo(inParams).get("ds_list");
            outParamMap.put("system_type_cd", systemTypeCd);
            outParamMap.put("opcode", opcode);
            outParam = GwmsUtils.toJsonString(outParamMap);
        } catch (UserHandleException e) {	// UserHandleException
            errCode = "-1";
            errMsg  = e.getMessage();
        } catch (InvocationTargetException e) {	// target에서 Exception
            try {
                // target에서 UserHandleException
                errCode = "-1";
                errMsg  = e.getMessage();
            }catch (Exception e2) {
                // target에서 Exception
                errCode = "-1";
                errMsg = e.getTargetException().toString();
            }
        } catch (Exception e){	// Exception
            errCode = "-1";
            errMsg = e.toString();
        } finally {
            // 에러일경우 응답값 처리
            if (!errCode.equals("0")) {
                outParameters.put("err_cd", errCode);
                outParameters.put("err_msg_ctt", errMsg);
                outParam = GwmsUtils.toJsonString(outParameters);
            }
        }

        return outParam;
    }


    @PostMapping(value="/selectDailyMemo")
    public String selectDailyMemo(HttpServletRequest request, @RequestBody String jsonData) {
        Map<String, Object> outParameters = new HashMap<String, Object>();
        Map<String, Object> inParams = new HashMap<String, Object>();
        Map<String, Object> outParamMap;

        String systemTypeCd      = "LIF1017";
        String opcode            = "MD006";
        String outParam          = ""; //return
        Object errMsg            = "";
        String errCode           = "0";

        try {
            HashMap<String, Object> jsonMap = GwmsUtils.parseJsonToMap(jsonData);
            inParams.putAll(jsonMap);

            outParamMap = (Map<String, Object>) crmApiService.selectDailyMemo(inParams).get("ds_list");
            outParamMap.put("system_type_cd", systemTypeCd);
            outParamMap.put("opcode", opcode);
            outParam = GwmsUtils.toJsonString(outParamMap);
        } catch (UserHandleException e) {
            errCode = "-1";
            errMsg  = e.getMessage();
        } catch (InvocationTargetException e) {
            try {
                errCode = "-1";
                errMsg  = e.getMessage();
            }catch (Exception e2) {
                errCode = "-1";
                errMsg = e.getTargetException().toString();
            }
        } catch (Exception e){
            errCode = "-1";
            errMsg = e.toString();
        } finally {
            // 에러일경우 응답값 처리
            if (!errCode.equals("0")) {
                outParameters.put("err_cd", errCode);
                outParameters.put("err_msg_ctt", errMsg);
                outParam = GwmsUtils.toJsonString(outParameters);
            }
        }

        return outParam;
    }


    @PostMapping(value="/insertDailyMemo")
    public String insertDailyMemo(HttpServletRequest request, @RequestBody String jsonData) {
        Map<String, Object> outParameters = new HashMap<String, Object>();
        Map<String, Object> inParams = new HashMap<String, Object>();
        Map<String, Object> outParamMap;

        String systemTypeCd      = "LIF1017";
        String opcode            = "MD003";
        String outParam          = ""; //return
        Object errMsg            = "";
        String errCode           = "0";

        try {
            HashMap<String, Object> jsonMap = GwmsUtils.parseJsonToMap(jsonData);
            inParams.putAll(jsonMap);

            outParamMap = (Map<String, Object>) crmApiService.insertDailyMemo(inParams).get("ds_list");
            outParamMap.put("system_type_cd", systemTypeCd);
            outParamMap.put("opcode", opcode);
            outParam = GwmsUtils.toJsonString(outParamMap);

        } catch (UserHandleException e) {
            errCode = "-1";
            errMsg  = e.getMessage();
        } catch (InvocationTargetException e) {
            try {
                errCode = "-1";
                errMsg  = e.getMessage();
            }catch (Exception e2) {
                errCode = "-1";
                errMsg = e.getTargetException().toString();
            }
        } catch (Exception e){
            errCode = "-1";
            errMsg = e.toString();
        } finally {
            // 에러일경우 응답값 처리
            if (!errCode.equals("0")) {
                outParameters.put("err_cd", errCode);
                outParameters.put("err_msg_ctt", errMsg);
                outParam = GwmsUtils.toJsonString(outParameters);
            }
        }

        return outParam;
    }
}
