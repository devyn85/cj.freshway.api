package cjfw.wms.portal.common.logback;

import com.fasterxml.jackson.core.JsonGenerator;

import net.logstash.logback.decorate.JsonGeneratorDecorator;

/**
 * Copyright 2024. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : th.jeon
 * @date : 2024.05.13
 * @description : Log를 Json으로 변환 하는 기능을 구현한 Controller Class
 * @issues : ----------------------------------------------------------- DATE
 *         AUTHOR MAJOR_ISSUE
 *         -----------------------------------------------------------
 *         2024.05.13 th.jeon 생성
 **/
public class PrettyPrintingDecorator implements JsonGeneratorDecorator {
	@Override
	public JsonGenerator decorate(JsonGenerator jsonGenerator) {
		return jsonGenerator.useDefaultPrettyPrinter();
	}
}
