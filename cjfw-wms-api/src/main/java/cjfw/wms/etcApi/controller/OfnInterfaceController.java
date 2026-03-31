package cjfw.wms.etcApi.controller;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.etcApi.service.OfnApiService;
import cjfw.wms.gwms.service.GwmsUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


@Tag(name = "OFN API 호출", description = "OFN API 호출")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ofn")
public class OfnInterfaceController {

    private final OfnApiService ofnApiService;

    @PostMapping(value="/selectLocList")
    public String selectLocList(HttpServletRequest request, @RequestBody String jsonData) {
        Map<String, Object> outParameters = new HashMap<String, Object>();
        Map<String, Object> inParams = new HashMap<String, Object>();
        Map<String, Object> outParamMap;

        String outParam          = ""; //return
        Object errMsg            = "";
        String errCode           = "0";

        try {
            HashMap<String, Object> jsonMap = GwmsUtils.parseJsonToMap(jsonData);
            inParams.putAll(jsonMap);

            // 서비스 호출
            outParamMap = (Map<String, Object>) ofnApiService.selectLocList(inParams).get("ds_list");
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
