package cjfw.core.utility;

import com.nhncorp.lucy.security.xss.XssFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : XssCheckUtil 기능을 구현한 Class
 * 				  XSS(Cross Site Scripting) 공격을 방어할 목적
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
public class XssCheckUtil {
	private static final Logger log = LoggerFactory.getLogger(XssCheckUtil.class);

	/**
	 * 
	 * @description : XssCheckUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	private XssCheckUtil() {}

	/**
	 * XSS로 의심되는 문자열을 제거하기 위한 정규식 및 정규식 적용 관련 플래그를 static List로 정의한다.<br>
	 * 인수값을 넣어 생성된 Pattern List를 반환.<br>
	 *	 
	 */
	private static final List<Pattern> XSS_PATTERN_LIST = getXssPatternList();

	/**
	 * Lucy XssFilter 등록
	 */
	// private static final XssFilter lucyXssFilter = XssFilter.getInstance("config/lucy-xss-superset.xml", true);
	private static final XssFilter lucyXssFilter = XssFilter.getInstance(true);

	/**
	 * 
	 * @description : getXssPatternList 기능을 구현한 Method
	 * 				  XSS로 의심되는 패턴을 정의한 객체를 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	private static List<Pattern> getXssPatternList() {
		List<Pattern> ret = new ArrayList<>();
		// Avoid anything between script tags
		ret.add(Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL));
		// Avoid anything in a src='...' or src="..." type of expression
		ret.add(Pattern.compile("src\\s*=\\s*[\"'](.*?)[\"']", Pattern.CASE_INSENSITIVE));
		// Remove any lonesome </script> tag
		ret.add(Pattern.compile("</script>", Pattern.CASE_INSENSITIVE));
		// Remove any lonesome <script ...> tag
		ret.add(Pattern.compile("<script\\b[^>]*>", Pattern.CASE_INSENSITIVE));
		// Avoid eval(...) expressions
		ret.add(Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL));
		// Avoid expression(...) expressions
		ret.add(Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL));
		// Avoid javascript:... expressions
		ret.add(Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE));
		// Avoid vbscript:... expressions
		ret.add(Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE));
		// Avoid onload= expressions
		ret.add(Pattern.compile("onload\\s*=\\s*[\"']?.*?[\"']?", Pattern.CASE_INSENSITIVE));

		return ret;
	}

	/**
	 * 
	 * @description : stripXss 기능을 구현한 Method
	 * 				  원본문자열을 받아서 XSS 공격이 예상되는 패턴을 replace 처리한 문자열을 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String stripXss(String value) {

		if(value != null) {
			Matcher matcher = null;
			String regex = null;
			Integer flag = null;
			
			// QuillEditor TAG는 필터링 제외
			// "<img src=" (QuillEditor를 통해 이미지 업로드시)
			// "_blank" (QuillEditor에서 링크 추가시)
		    if (value.contains("<img src=") || value.contains("_blank")) {
		        return value; 
		    }

			for(Pattern arr : XSS_PATTERN_LIST) {
				matcher = arr.matcher(value);
				//해당하는 패턴이 있으면
				if(matcher.find()) {
					//해당 문자열 제거		
					value = matcher.replaceAll("");
					log.info("XSS pattern matched => regex: " + regex + ", flag: " + flag);
				}
			}
			value = lucyXssFilter.doFilter(value);
		}
		return value;
	}
}
