package cjfw.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = { "cjfw.core", "cjfw.auth", "cjfw.wms" })
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ApiApplication.class);
		application.run(args);
	}
}
