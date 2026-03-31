package cjfw.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@Slf4j
@SpringBootApplication
@EnableBatchProcessing
@ComponentScan(basePackages = { "cjfw.core", "cjfw.auth", "cjfw.wms", "cjfw.batch" })
public class BatchApplication {

	public static void main(String[] args) throws Exception {
		try {
			SpringApplication application = new SpringApplication(BatchApplication.class);
			application.run(args);
		} catch (Exception e) {
			log.error("BatchApplication.getMethod.Exception : ", e);
		}
	}
}
