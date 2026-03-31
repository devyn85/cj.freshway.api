package cjfw.core.utility;

import java.beans.Introspector;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.exception.SystemException;
/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : StringUtil 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
public class StringUtil {
	private static final Logger log = LoggerFactory.getLogger(StringUtil.class);
	private static final String DELIMITER_COMMA = ",";
	private static final String VALUE_OF_FALSE = "0";
	private static final String VALUE_OF_TRUE = "1";

	/**
	 * 
	 * @description : StringUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	protected StringUtil() {}

	/**
	 * 
	 * @description : removeAndHump 기능을 구현한 Method
	 * 				  입력받은 문자 "data"에서 입력받은 언더바"_"를 제거하고 Camel Hump 형태로 변환하여 반환
	 * 				  ex. foo_barBar => FooBarBar
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String removeAndHump(String key) {
		return removeAndHump(key, "_");
	}

	/**
	 * 
	 * @description : removeAndHump 기능을 구현한 Method
	 * 				  입력받은 문자 "data"에서 입력받은 "replaceThis"를 제거하고 Camel Hump 형태로 변환하여 반환
	 * 				  ex. foo_barBar => FooBarBar
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String removeAndHump(String data, String replaceThis) {
		String temp = null;
		StringBuilder out = new StringBuilder();
		temp = data;

		StringTokenizer st = new StringTokenizer(temp, replaceThis);

		while(st.hasMoreTokens()) {
			String element = (String)st.nextElement();
			out.append(capitalizeFirstLetter(element));
		}//while

		return out.toString();
	}

	/**
	 * 
	 * @description : capitalizeFirstLetter 기능을 구현한 Method
	 * 				  첫번째 글자를 대문자로 변환하여 반환
	 * 				  ex. fooBar => FooBar
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String capitalizeFirstLetter(String data) {
		String firstLetter = data.substring(0, 1).toUpperCase();
		String restLetters = data.substring(1);
		return firstLetter + restLetters;
	}

	/**
	 * 
	 * @description : removeCamelHump 기능을 구현한 Method
	 * 				  Camel 표기법으로 표기된 내용을 '_' 로 분리하여 반환
	 * 				  ex. fooBar => foo_bar
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String removeCamelHump(String data) {
		return removeCamelHump(data, "_");
	}

	/**
	 * 
	 * @description : removeCamelHump 기능을 구현한 Method
	 * 				  Camel 표기법으로 표기된 내용을 입력한 문자 로 분리하여 표기
	 * 				  ex. removeCamelHump("fooBar", "_")
	 * 					  <code>fooBar</code> --> <code>foo_bar</code>
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String removeCamelHump(String data, String addThis) {

		String localData = data;
		String regexp = "[A-Z]";
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(localData);
		while(matcher.find()) {
			String left = localData.substring(0, matcher.start());
			String matchCharacter = localData.substring(matcher.start(), matcher.end());
			String right = localData.substring(matcher.end());
			localData = matchCharacter.toLowerCase() + right;
			result.append(left).append(addThis);
			matcher = pattern.matcher(localData);
		}
		result.append(localData);
		return result.toString();
	}

	/**
	 * 
	 * @description : substringByByteUTF8 기능을 구현한 Method
	 * 				  입력받은 문자열(src)의 시작부터 byteLength 까지 바이트 길이를 기준으로 자른 문자열을 반환
	 * 				  멀티바이트 문자가 포함된 문자열일 경우, byteLength까지 잘라도, 문자열을 구성하면서 byteLength를 넘어서는 경우가 있는데,
	 * 				  그 경우, 바로 전 문자까지만 구성하여 리턴하도록 처리되어 있으므로, 리턴되는 문자열의 바이트 길이는 byteLength보다 작아지게 된다.
	 * 				  현재는 UTF-8 형식의 문자열만 지원하고 있다.
	 * 
	 * 				  src - 입력 문자열
	 * 				  byteLength - 몇 바이트까지만 잘라서 리턴할 것인지
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String substringByByteUTF8(String src, int byteLength) {

		String ret = null;
		String encoding = CanalFrameConstants.DEFAULT_CHARACTERSET;
		if(StringUtils.isBlank(src)) {
			return "";
		}
		if(byteLength < 0) {
			throw new SystemException("byteLength must be positive");
		}
		byte[] srcBytes = null;
		int srcByteLength = 0;
		try {
			srcBytes = src.getBytes(encoding);
			srcByteLength = srcBytes.length;

			//자를 필요가 없을 경우 원본 문자열 리턴
			if(srcByteLength <= byteLength) {
				return src;
			}

			//잘라야 할 필요가 있는 (srcByteLength > byteLength) 경우 
			/*
			 * 	byte로 잘랐을 때, 마지막 byte의 다음 byte가 다음 문자의 시작 byte이면 정확히 문자 단위로 잘린 것이라서 이상이 없지만,
			 * 멀티바이트 문자일 경우, 마지막 byte의 다음 byte가 다음 문자의 시작 byte가 아닐 경우, 그 byte에 이어서 다른 byte를 더 붙여서 문자를 완결짓기 때문에,
			 * 최초 제한하려고 했던 byte보다 더 긴 byte 길이를 갖는 결과를 리턴하게 되어 DB 처리시 오류를 발생시킬 수 있다.
			 * 그렇기 때문에 byte로 잘랐을 때 문자단위로 끊어지지 않는다면, 바로 전 문자까지만 잘라서 리턴해 주어야 한다.
			 * (결과적으로, 최초 제한하려고 했던 byte 보다 작은 길이의 byte로 된 문자열을 리턴하게 됨)
			 * 
			 * UTF-8 설계 원칙
			 * 1바이트로 표시된 문자의 최상위 비트는 항상 0이다.
			 * 2바이트 이상으로 표시된 문자의 경우, 첫 바이트의 상위 비트들이 그 문자를 표시하는 데 필요한 바이트 수를 결정한다. 예를 들어서 2바이트는 110으로 시작하고, 3바이트는 1110으로 시작한다.
			 * 첫 바이트가 아닌 나머지 바이트들은 상위 2비트가 항상 10이다. 
			 */
			//얻고자 하는 마지막 바이트의 배열 인덱스
			int lastByteIndex = byteLength - 1;
			//얻고자 하는 마지막 바이트의 바로 다음 바이트의 배열 인덱스 
			int lastByteNextIndex = lastByteIndex + 1;
			//얻고자 하는 마지막 바이트의 바로 다음 바이트
			byte lastByteNextByte = srcBytes[lastByteNextIndex];

			//lastByteNextByte가 멀티바이트 문자의 시작바이트가 아닌 경우 - 멀티바이트 문자의 중간을 자른 형국이므로, 바로 전 시작바이트 앞을 기점으로 끊도록 lastByteIndex의 값을 보정한다.
			String lastByteNextBinaryString = StringUtil.toBinary(new byte[]{lastByteNextByte});
			//마지막 이후 바이트 이진수 변환
			if(lastByteNextBinaryString.startsWith("10")) {
				int offset = 0;
				for(int i = lastByteIndex; i >= 0; i--) {
					offset--;
					String bin = StringUtil.toBinary(new byte[]{srcBytes[i]});
					if(!bin.startsWith("10")) {
						break;
					}
				}
				lastByteIndex += offset;
				
				log.info(" cutting offset for multibyte charecter processing : " + offset);
			}
			//그 외의 경우 마지막에 자른 바이트가 문자 단위로 잘렸으므로 별다른 처리를 하지 않는다.

			ret = new String(srcBytes, 0, (lastByteIndex + 1), encoding);
			log.info(" src length : " + src.length());
			log.info(" src byte length : " + srcByteLength);
			log.info(" lastByteIndex : " + lastByteIndex);
			log.info(" lastByteNextBinaryString : " + lastByteNextBinaryString);
			log.info(" ret length : " + ret.length());
			log.info(" ret byte length : " + ret.getBytes(encoding).length);

		} catch(Exception e) {
			log.error("StringUtil.substringByByteUTF8().SecurityException : ", e);
		}
		return ret;
	}

	/**
	 * 
	 * @description : toBinary 기능을 구현한 Method
	 * 				  바이트 배열을 입력 받아 이진수 문자열로 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String toBinary(byte[] bytes) {
		final int BYTE_SIZE = Byte.SIZE; // 8
		StringBuilder sb = new StringBuilder(bytes.length * BYTE_SIZE);
		for(int i = 0; i < BYTE_SIZE * bytes.length; i++) {
			sb.append(((bytes[i / BYTE_SIZE] << (i % BYTE_SIZE)) & 0x80) == 0 ? '0' : '1');
		}
		return sb.toString();
	}

	/**
	 * 
	 * @description : defaultString 기능을 구현한 Method
	 * 				  StringUtils.defaultString()과 같으나 Object 인수를 받을 수 있도록 처리함
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String defaultString(Object obj) {
		return defaultString(obj, "");
	}

	/**
	 * 
	 * @description : defaultString 기능을 구현한 Method
	 * 				  StringUtils.defaultString()과 같으나 Object 인수를 받을 수 있도록 처리함
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String defaultString(Object obj, String defaultStr) {
		return obj != null ? obj.toString() : defaultStr;
	}

	/**
	 * 
	 * @description : encodeUrlUtf8 기능을 구현한 Method
	 * 				  원본문자열을 URL encode (UTF-8) 하여 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String encodeUrlUtf8(String src) {
		String ret = null;
		try {
			ret = URLEncoder.encode(StringUtils.defaultString(src), CanalFrameConstants.DEFAULT_CHARACTERSET);
		} catch(UnsupportedEncodingException e) {
			log.error("StringUtil.encodeUrlUtf8().UnsupportedEncodingException : ", e);
		}
		return ret;
	}

	/**
	 * 
	 * @description : decodeUrlUtf8 기능을 구현한 Method
	 * 				  원본문자열을 URL decode (UTF-8) 하여 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String decodeUrlUtf8(String src) {
		String ret = null;
		try {
			ret = URLDecoder.decode(StringUtils.defaultString(src), CanalFrameConstants.DEFAULT_CHARACTERSET);
		} catch(UnsupportedEncodingException e) {
			log.error("StringUtil.decodeUrlUtf8().UnsupportedEncodingException : ", e);
		}
		return ret;
	}

	/**
	 * 
	 * @description : getUpperKeyList 기능을 구현한 Method
	 * 				  입력받은 List<HashMap>에서 각각의 Map객체의 Key들을 대문자로 변환하여 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static List<HashMap<String, String>> getUpperKeyList(List<HashMap<String, String>> inList) {
		List<HashMap<String, String>> outList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> m : inList) {
			Object[] hashKey = m.keySet().toArray();
			HashMap<String, String> h = new HashMap<String, String>();
			for(Object o : hashKey) {
				String s = (String)o;
				String upperS = s.toUpperCase();
				h.put(upperS, m.get(s));
			}
			outList.add(h);
		}

		return outList;
	}
	
	/**
	 * 
	 * @description : getBindMessage 기능을 구현한 Method
	 * 				  bind arguments 를 '|' 로 연결된 String 으로 반환
	 * 
	 * 				  bindArgs - bind argument 배열
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getBindMessage(Object[] bindArgs) {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < bindArgs.length; i++) {
			result.append(bindArgs[i].toString());
			if(i != bindArgs.length - 1) {
				result.append("|");
			}
		}
		return result.toString();
	}
	
	/**
	 * 
	 * @description : isEmpty 기능을 구현한 Method
	 * 				  StringUtils.isEmpty()에 Object 인수를 받을 수 있도록 overloading
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static boolean isEmpty(Object obj) {
		if(obj == null) {
			return true;
		}
		return StringUtils.isEmpty(obj.toString());
	}

	/**
	 * 
	 * @description : isNotEmpty 기능을 구현한 Method
	 * 				  StringUtils.isEmpty()에 Object 인수를 받을 수 있도록 overloading
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	/**
	 * 
	 * @description : getStringList 기능을 구현한 Method
	 * 				  properties 파일에서 한개의 행이 ,로 구분된 것들을 List<String>로 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static List<String> getStringList(String value) {
	    String[] valueArray = value.split(DELIMITER_COMMA);
	    List<String> result = Arrays.asList(valueArray);
	    return result;
	}
	
	/**
	 * 
	 * @description : getTokens 기능을 구현한 Method
	 * 				  구분자를 가진 String을 받아서 List형태로 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static List<String> getTokens(String str, String delim) {
		if (str == null || delim == null || str.equals("") || delim.equals("")) {
			return Collections.emptyList();
		}

		List<String> list = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(str, delim);

		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}

		return list;
	}
	
	/**
	 * 
	 * @description : toLocaleFormat 기능을 구현한 Method
	 * 				  Locale 생성을 위한 Locale 포맷으로 변경(언어는 소문자, 지역은 대문자 변환)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String toLocaleFormat(String localeLetter) {
		if(localeLetter.contains("_")) {
			String[] langCountry = localeLetter.split("_");
			return langCountry[0].toLowerCase() + "_" + langCountry[1].toUpperCase();
		} else {
			return localeLetter.toLowerCase();
		}
	}

	/**
	 * 
	 * @description : getHttpOrigin 기능을 구현한 Method
	 * 				  url로부터 origin(스키마,호스트,포트)정보를 파싱하여 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getHttpOrigin(String url){
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build();
		return uriComponents.getScheme() + "://" + uriComponents.getHost() + ((uriComponents.getPort() > 0)? ":" + uriComponents.getPort() : "");
	}
	/**
	 * 
	 * @description : isNull 기능을 구현한 Method
	 * 				  null인지 체크
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.09        suah.jo              생성
	 */
	public static boolean isNull(Object obj) {
		return obj == null; 
	}
	/**
	 * 
	 * @description : nvl 기능을 구현한 Method
	 * 				  null일 경우 defaultValue로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.09        suah.jo              생성
	 */
	public static String nvl(String data, String defaultValue) {
		  return (data != null) ? data : defaultValue;
	}
	
	/**
	 * 
	 * @description : nvl 기능을 구현한 Method
	 * 				  null일 경우 defaultValue로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.13        sss                생성
	 */
	public static String nvl(String data) {
		  return (data != null) ? data : "";
	}	
	/**
	 * 
	 * @description : removeSpecChar 기능을 구현한 Method
	 * 				  문자열에 들어 있는 특정한 문자를 지움
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.09        suah.jo              생성
	 */
	public static String removeSpecChar(String data) {
		  // 정규 표현식을 사용하여 특수문자 제거
        String result = data.replaceAll("[^a-zA-Z0-9]", "");
        return result;
	}

	/**
	 * 
	 * @description : getValueInListByKey 기능을 구현한 Method
	 * 				  Map에서 key에 맞는 value값을 String 형태로 return
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.09        suah.jo              생성
	 */
	public static String getValueInListByKey(List<Map<String, String>> list, String key, String keyValue, String value) {
		String val = "";

	        for (Map<String, String> item : list) {
	            if (item.containsKey(key) && keyValue.equals(item.get(key))) {
	                val = item.get(value);
	                break;  // 첫 번째 일치하는 항목을 찾았으면 루프 중단
	            }
	        }

        return val;
	}
	/**
	 * 
	 * @description : getObjectInListByKey 기능을 구현한 Method
	 * 				  Map에서 key에 맞는 value값을 object 형태로 return
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.09        suah.jo              생성
	 */
	public static Object getObjectInListByKey(List<Map<String, Object>> list, String key, String keyValue, String value) {
		Object val = "";

	        for (Map<String, Object> item : list) {
	            if (item.containsKey(key) && keyValue.equals(item.get(key))) {
	                val = item.get(value);
	                break;  // 첫 번째 일치하는 항목을 찾았으면 루프 중단
	            }
	        }

        return val;
    }
	/**
	 * 수정!!
	 * @description : lpad 기능을 구현한 Method
	 * 				  문자열의 왼쪽을 padding 문자로 채운다.
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */
	public static String lpad(String input, char padding, int length) {
        if (input == null) {
            input = new String();
        }

        if (input.length() >= length) {
            return input;
        }

        StringBuffer result = new StringBuffer();
        int numChars = length - input.length();

        for (int i = 0; i < numChars; i++) {
            result.append(padding);
        }

        result.append(input);

        return result.toString();
    }
	/**
	 * 수정!! 
	 * @description : rpad 기능을 구현한 Method
	 * 				  문자열의 오른쪽을 padding 문자로 채운다.
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */ 
	public static String rpad(String input, char padding, int length) {
        if (input == null) {
            input = new String();
        }

        if (input.length() >= length) {
            return input;
        }

        StringBuffer result = new StringBuffer(input);
        int numChars = length - input.length();

        for (int i = 0; i < numChars; i++) {
            result.append(padding);
        }

        return result.toString();
    }
	/**
	 * 
	 * @description : ltrim 기능을 구현한 Method
	 * 				  문자열의 왼쪽공백을 제거한다.
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */
	public static Object ltrim(String data) {
	    if (data == null) {
            return null;  // 입력이 null이면 null 반환
        }
        return data.replaceAll("^\\s+", ""); 
    }
	
	/**
	 * 
	 * @description : rtrim 기능을 구현한 Method
	 * 				  문자열의 오른쪽공백을 제거한다.
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */
	public static Object rtrim(String data) {
	    if (data == null) {
            return null;  // 입력이 null이면 null 반환
        }
	    return data.replaceAll("\\s+$", ""); 
 
    }
	/**
	 * 
	 * @description : repeatNumber 기능을 구현한 Method
	 * 				  문자열에서 해당 num만큼 숫자가 사용되는지 여부 확인
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */
    public static boolean repeatNumber(String data, int num) {
    	String pattern = "[0-9]{" + num + "}";
        return Pattern.compile(pattern).matcher(data).find();
    }	
    /**
	 * 
	 * @description : repeatEng 기능을 구현한 Method
	 * 				  문자열에서 해당 num만큼 영문이 사용되는지 여부 확인
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */
    public static boolean repeatEng(String data, int num) {
    	String pattern = "[a-zA-Z]{" + num + "}";
        return Pattern.compile(pattern).matcher(data).find();
    }
    /**
	 * 
	 * @description : repeatEng 기능을 구현한 Method
	 * 				  문자열에서 해당 num만큼 영문이 사용되는지 여부 확인
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */
    public static boolean repeatSpecial(String data, int num) {
    	String pattern = "[~!@#$%^&*()_+|<>?:{}]{" + num + "}";
        return Pattern.compile(pattern).matcher(data).find();
    }
    /**
	 * 
	 * @description : repeatEng 기능을 구현한 Method
	 * 				  문자열에서 해당 num만큼 영문이 사용되는지 여부 확인
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */
    public static boolean isImage(String str) {
        Pattern pattern = Pattern.compile("gif|jpe?g|tiff?|png|webp|bmp", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
    /**
	 * 
	 * @description : getFileExtension 기능을 구현한 Method
	 * 				  파일명에서 확장자만 추출
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */	
    public static String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex + 1);
        }
        return "";
    }
    /**
	 * 
	 * @description : isImageFile 기능을 구현한 Method
	 * 				  
	 * @issues      : 이미지 확장자인지 확인 (파일전체명으로 확장자만 추출해서 진행)
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.10        suah.jo              생성
	 */    
    public static boolean isImageFile(String filePath) {
    	 String extension = getFileExtension(filePath);
    	 Pattern pattern = Pattern.compile("gif|jpe?g|tiff?|png|webp|bmp", Pattern.CASE_INSENSITIVE); //대소문자 구분 X
         Matcher matcher = pattern.matcher(extension);
         return matcher.find();
         
//        private static final Set<String> IMAGE_EXTENSIONS = new HashSet<>();
//        IMAGE_EXTENSIONS
//        // 파일 경로에서 확장자 추출
//        String extension = getFileExtension(filePath);
//
//        // 추출한 확장자가 이미지 파일 확장자인지 확인
//        return IMAGE_EXTENSIONS.contains(extension.toLowerCase());
    }
    /**
	 * 
	 * @description : calculateSum 기능을 구현한 Method
	 * 				  array sum
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */	
	public static int calculateSum(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }
	/**
	 * 
	 * @description : calculateCount 기능을 구현한 Method
	 * 				  array count
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */	
	public static int calculateCount(int[] array) {
		return array.length;
	}
	/**
	 * 
	 * @description : calculateAverage 기능을 구현한 Method
	 * 				  array avg
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */	
	public static double calculateAverage(int[] array) {
		if (array.length == 0) {
		  return 0.0; // 배열이 비어있을 경우 평균은 0으로 정의
		}
		  int sum = calculateSum(array);
	      return (double) sum / array.length;
	}
	/**
	 * 
	 * @description : sortMapArray 기능을 구현한 Method
	 * 				  List<Map<String, Object>>를 Key값에 따라 비교하여 sorting
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */	
    public static void sortMapArray(List<Map<String, Object>> mapArray, String key) {
        mapArray.sort(Comparator.comparingInt(map -> (int) map.get(key)));
    }
    /**
	 * 
	 * @description : truncate 기능을 구현한 Method
	 * 				  소수점 버리기
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */	
    public static int truncate(double number) {
        return (int) number;
    }
    /**
	 * 
	 * @description : truncate 기능을 구현한 Method
	 * 				  Boolean Type을 자동으로 판단하여 Boolean을 반환한다.
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */	
    public static boolean parseBoolean(String value) {
        if (value == null) {
            return false;
        }

        String lowerCaseValue = value.toLowerCase();

        return lowerCaseValue.equals("1") ||
               lowerCaseValue.equals("y") ||
               lowerCaseValue.equals("t") ||
               lowerCaseValue.equals("yes") ||
               lowerCaseValue.equals("true");
    }



    public static String convertCamelToUnderscore(String str) {
        return Introspector.decapitalize(str)
                .replaceAll("([A-Z])", "_$1")
                .toLowerCase();
    }

    public static List<Map<String, Object>> convertArrayKeysCamelToUnderscore(List<Map<String, Object>> jsonList) {
        List<Map<String, Object>> convertedList = new ArrayList<>();

        // JSON 객체 목록 순회
        for (Map<String, Object> jsonObject : jsonList) {
            Map<String, Object> convertedObject = new HashMap<>();

            // 키 포맷 변경
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String camelCaseKey = convertCamelToUnderscore(entry.getKey());
                convertedObject.put(camelCaseKey, entry.getValue());
            }

            // 변경된 객체를 리스트에 추가
            convertedList.add(convertedObject);
        }

        return convertedList;
    }

    public static List<Map<String, Object>> convertArrayKeysUnderscoreToCamel(List<Map<String, Object>> jsonList) {
        List<Map<String, Object>> convertedList = new ArrayList<>();

        // JSON 객체 목록 순회
        for (Map<String, Object> jsonObject : jsonList) {
            Map<String, Object> convertedObject = new HashMap<>();

            // 키 포맷 변경
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String camelCaseKey = convertUnderscoreToCamel(entry.getKey());
                convertedObject.put(camelCaseKey, entry.getValue());
            }

            // 변경된 객체를 리스트에 추가
            convertedList.add(convertedObject);
        }

        return convertedList;
    }

    public static Map<String, Object> createMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
    /**
	 * 
	 * @description : underscoreToCamelCase 기능을 구현한 Method
	 * 				  CAMEL CASE 형태로 변경
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
    public static String convertUnderscoreToCamel(String underscoreKey) {
        StringBuilder camelCaseKey = new StringBuilder();

        boolean toUpperCase = false;
        for (char ch : underscoreKey.toCharArray()) {
            if (ch == '_') {
                toUpperCase = true;
            } else {
                camelCaseKey.append(toUpperCase ? Character.toUpperCase(ch) : ch);
                toUpperCase = false;
            }
        }

        return camelCaseKey.toString();
    }
    
 

    public static Map<Object, Object> convertKeysUnderscoreToCamel(Map<Object, Object> map) {
        Map<Object, Object> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key instanceof String) {
                key = convertUnderscoreToCamel((String) key);
            }
            result.put(key, value);
        }
        return result;
    }
    
    public static Map<Object, Object> convertKeysCamelToUnderscore(Map<Object, Object> map) {
        Map<Object, Object> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key instanceof String) {
                key = convertCamelToUnderscore((String) key);
            }
            result.put(key, value);
        }
        return result;
    }

    public static String kv(String key, String value){
        String keyValue = "Key: " + key + " Value : " + value;
        return keyValue;
    }

    /**
     * @description : XML 형태의 문자열로부터 파라미터를 추출하거나 삽입하는 등의 처리를 한다.
     * @issues      :
     * @param cmd              명령
     * @param data              XML 형태의 문자열
     * @param field             추출할 파라미터의 이름
     * @param defaultString 값이 없을 경우 대체할 문자열
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2024.01.11        suah.jo            생성
     */
    public static String getParameter(String cmd, String data, String field, String defaultString){
        if (cmd == null  || data == null  || field == null){ return ""; }
        if ("".equals(cmd.trim()) || "".equals(data.trim()) || "".equals(field.trim())){ return ""; }

        String  curData ="";
        int  startPos = 0;
        int  endPos = 0;    
        startPos = data.indexOf("<"+field+">");
        endPos = data.indexOf("</"+field+">");

        if ((startPos >= 0 && endPos >= 0) && (startPos < endPos)) {
            if ("INSERT".equals(cmd) || "UPDATE".equals(cmd) || "SELECT".equals(cmd)) {
                startPos = startPos + ("<"+field+">").length();
                curData  = data.substring(startPos ,endPos);
            }
            if ("GET".equals(cmd) || "DEL".equals(cmd) || "SET".equals(cmd)) {
                startPos = startPos + ("<"+field+">").length();
                curData  = data.substring(startPos ,endPos);
            }
            if ("DEL".equals(cmd) || "SET".equals(cmd)) {
                curData = data.replace("<"+field+">"+curData+"</"+field+">","");
            }
            if ("SET".equals(cmd)) {
                curData = data+"<"+field+">"+curData+"</"+field+">";
            }
        }
        if (StringUtils.isBlank(curData)) { //fallback
           if ("INSERT".equals(cmd) || "SELECT".equals(cmd)) {
               curData = defaultString;
           } else {
               if ("UPDATE".equals(cmd)){
                   curData = "NOTFOUND";
               } else {
                   curData = "FAILURE";
               }
           }
        }
        return curData;         
    }
}
