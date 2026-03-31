package cjfw.wms.dev.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 테스트용 Controller
 */
@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

    /**
     * 기본 테스트 API
     * @return "test" JSON 응답
     */
    @GetMapping
    public Map<String, String> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "test");
        return response;
    }

    /**
     * 헬스 체크 API
     * @return 서비스 상태
     */
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        response.put("message", "Service is running");
        return response;
    }

    /**
     * 간단한 정보 조회 API
     * @return 기본 정보
     */
    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "CJ Freshway API");
        response.put("version", "1.0.0");
        response.put("status", "active");
        return response;
    }
}
