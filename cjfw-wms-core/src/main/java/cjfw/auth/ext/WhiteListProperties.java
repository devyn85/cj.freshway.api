package cjfw.auth.ext;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "whitelist")
@Data
public class WhiteListProperties {
    private List<Company> companies;

    @Data
    public static class Company {
        private String name;
        private String apiKey;
        private List<String> ips;
    }
}
