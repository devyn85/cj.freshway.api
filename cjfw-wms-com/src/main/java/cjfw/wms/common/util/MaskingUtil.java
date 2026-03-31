package cjfw.wms.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.wms.common.annotation.MaskingAcntno;
import cjfw.wms.common.annotation.MaskingAll;
import cjfw.wms.common.annotation.MaskingBirthday;
import cjfw.wms.common.annotation.MaskingBizno;
import cjfw.wms.common.annotation.MaskingCardno;
import cjfw.wms.common.annotation.MaskingDrvno;
import cjfw.wms.common.annotation.MaskingEmail;
import cjfw.wms.common.annotation.MaskingEngName;
import cjfw.wms.common.annotation.MaskingId;
import cjfw.wms.common.annotation.MaskingIdntNo;
import cjfw.wms.common.annotation.MaskingInner;
import cjfw.wms.common.annotation.MaskingIp;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingPassPort;
import cjfw.wms.common.annotation.MaskingQrcd;
import cjfw.wms.common.annotation.MaskingSsn;
import cjfw.wms.common.annotation.MaskingTelno;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.06.13
 * @description : 마스킹 유틸
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.14 SangSuSung(kduimux@cj.com) 생성 </pre>
 * 2025.12.23 SangSuSung(kduimux@cj.com) LOG4J용 maskLog 추가(전화번호/이메일/주소 로그출력 시 ******처리) 
 * </pre>
 */
@Slf4j
public class MaskingUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper()
	        .setSerializationInclusion(JsonInclude.Include.NON_NULL);
	
    /**
	 * 전화번호 패턴 
	 */
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("(01[016789]\\d{7,8}|0\\d{1,2}-?\\d{3,4}-?\\d{4})");
    
    /**
     * 이메일 패턴
     */
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    /**
     *  시/도 + 구/군 + 동/길/로 까지 주소 인식 패턴
     */
    private static final Pattern ADDRESS_PATTERN =
            Pattern.compile("(서울|경기|부산|대구|인천|광주|대전|울산|세종|제주)[^\\n,]{5,40}");	

	/**
	 * 마스킹 - 이름
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingName(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		String regex = "(.)(.)(.*)";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.matches()) {
			return str.replaceAll(regex, "$1*$3"); // NOSONAR
		}
		return str;
	}

	/**
	 * 마스킹 - 아이디
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingId(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		String regex = "^.{3}(.*)";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.matches()) {
			String target = matcher.group(1);
			int length = target.length();
			char[] c = new char[length];
			Arrays.fill(c, '*');
			return str.replace(target, String.valueOf(c));
		}
		return str;
	}

	/**
	 * 마스킹 - 영문이름
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingEngName(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		String regex = "(\\w*{1})(.*$)";  // NOSONAR
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.find()) {
			String target = matcher.group(1);
			if (target.length() == 2) {
				target = target.substring(0, 1) + "*";
			} else {
				target = maskingInner(target);
			}
			return target + str.replaceAll(regex, "$2"); // NOSONAR
		}
		return str;
	}

	/**
	 * 마스킹 - 연락처
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingTelno(String myStr) {
		String str = myStr;
	    
	    // 1. 빈 값 체크
	    if (StringUtils.isEmpty(str)) {
	        return str;
	    }

	    // 2. [추가] 유효성 체크: 너무 짧은 문자열은 전화번호가 아니라고 판단하여 그대로 반환
	    // 하이픈을 제거한 순수 숫자가 최소 7자리 미만이면 처리 제외
	    String plainNum = str.replace("-", "");
	    if (plainNum.length() < 7) { 
	        return str; 
	    }

	    try {
	        int lengthWithoutHyphen = plainNum.length();
	        String regex;
	        
	        if (lengthWithoutHyphen == 8) {
	            regex = "(\\d{4})-?(\\d{4})$";
	        } else if (lengthWithoutHyphen == 12) {
	            regex = "(\\d{4})-?(\\d{4})-?(\\d{4})$";
	        } else {
	            regex = "(\\d{2,3})-?(\\d{3,4})-?(\\d{4})$";
	        }

	        // 여기서 에러 발생 가능성이 있으므로 유효성 체크 후 호출
	        str = telNoSetHyphen(str); 
	        
	        Matcher matcher = Pattern.compile(regex).matcher(str);
	        if (matcher.find()) {
	            String target = matcher.group(2);
	            int targetLength = target.length();
	            char[] stars = new char[targetLength];
	            Arrays.fill(stars, '*');

	            String dynamicReplacement;
	            if (lengthWithoutHyphen == 8) {
	                dynamicReplacement = "$1-" + String.valueOf(stars);
	            } else {
	                dynamicReplacement = "$1-" + String.valueOf(stars) + "-$3";
	            }
	            str = str.replaceAll(regex, dynamicReplacement);
	        }
	    } catch (Exception e) {
	        log.warn("Masking Error [Input: {}]: {}", myStr, e.getMessage());
	    }
	    return str;
	}

	/**
	 * 마스킹 - 이메일
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingEmail(String str) {
		if (StringUtils.isEmpty(str))
			return str;
		try {
			String regex = "^(...)(.*)@(.*)$";
			Matcher matcher = Pattern.compile(regex).matcher(str);
			if (matcher.matches()) {
				char[] c = new char[matcher.group(2).length()];
				Arrays.fill(c, '*');
				return str.replaceAll(regex, "$1" + String.valueOf(c) + "@$3"); // NOSONAR
			}
		} catch(Exception e) {
			log.warn("{}",e);
		}		
		return str;
	}

	/**
	 * 마스킹 - 주민번호 (하이픈 유무 모두 포함)
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingSsn(String str) {
		if (StringUtils.isEmpty(str))
			return str;
		try {
			String regex = "(\\d{6})(-)?(\\d{7})$";
			Matcher matcher = Pattern.compile(regex).matcher(str);
			if (matcher.find()) {
				return str.replaceAll(regex, "$1$2*******"); // NOSONAR
			}
		} catch(Exception e) {
			log.warn("{}",e);
		}			
		return str;
	}

	/**
	 * 마스킹 - 운전면허번호 (하이픈 유무 포함)
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingDrvno(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		// "^(.*)(\\d{2})-(\\d{6})-(\\d{2})$";
		String regex = "^(\\d{2})-?(\\d{2})-?(\\d{6})-?(\\d{2})$";
		Matcher matcher = Pattern.compile(regex).matcher(str.replaceAll("-", "")); // NOSONAR
		if (matcher.matches()) {
			String target = matcher.group(3);
			int length = target.length();
			char[] c = new char[length];
			Arrays.fill(c, '*');
			return str.replaceAll(regex, "$1-$2-" + String.valueOf(c) + "-$4"); // NOSONAR
		}
		return str;
	}

	/**
	 * 마스킹 - 생년월일(yyyyMMdd / yyyy-MM-dd / yyyy.MM.dd)
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingBirthday(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		String regex = "^((19|20)\\d\\d)?([-/.])?(0[1-9]|1[012])([-/.])?(0[1-9]|[12][0-9]|3[01])$";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.find()) {
			return str.replaceAll(regex, "$1$3$4$5**"); // NOSONAR
		}
		return str;
	}

	/**
	 * 마스킹 - 카드번호 (하이픈 유무 모두 포함)
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingCardno(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		if (str.replaceAll("-", "").length() == 11) { // NOSONAR
			// 카드번호 11자리 7번째 부터 4자리 마스킹
			String regex = "(\\d{4})(-)?(\\d{2})(\\d{2})(-)?(\\d{2})(\\d{1})$";
			Matcher matcher = Pattern.compile(regex).matcher(str);
			if (matcher.find()) {
				return str.replaceAll(regex, "$1$2$3**$5**$7"); // NOSONAR
			}
			return str;
		} else  {
			// 카드번호 16자리 또는 15자리 '-'포함/미포함 상관없음
			String regex = "(\\d{4})(-)?(\\d{4})(-)?(\\d{4})(-)?(\\d*)$";
			Matcher matcher = Pattern.compile(regex).matcher(str);
			if (matcher.find()) {
				return str.replaceAll(regex, "$1$2$3$4****$6$7"); // NOSONAR
			}
			return str;
		} 

	}

	/**
	 * 마스킹 - 계좌번호
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingAcntno(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		String regex = "^.{5}(.*)";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.matches()) {
			String target = matcher.group(1);
			int length = target.length();
			char[] c = new char[length];
			Arrays.fill(c, '*');
			return str.replace(target, String.valueOf(c));
		}
		return str;
	}

	/**
	 * 마스킹 - 사업자번호
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingBizno(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		String regex = "(\\d{3})-(\\d{2})-(\\d{5})$";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.find()) {
			return str.replaceAll("(.{2}).*(.{4})$", "$1*-**-*$2"); // NOSONAR
		}
		regex = "(\\d{3})(\\d{2})(\\d{5})$";
		matcher = Pattern.compile(regex).matcher(str);
		if (matcher.find()) {
			return str.replaceAll("(.{2}).*(.{4})$", "$1****$2"); // NOSONAR
		}
		return str;
	}

	/**
	 * 마스킹 - 아이피
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingIp(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		String regex = "(\\d*)\\.(\\d*)\\.(\\d*)\\.(\\d*)$";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.matches()) {
			String target = matcher.group(3);
			int length = target.length();
			char[] c = new char[length];
			Arrays.fill(c, '*');
			return str.replaceAll(regex, "$1.$2." + String.valueOf(c) + ".$4"); // NOSONAR
		}
		return str;
	}

	/**
	 * 마스킹 - QR코드
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingQrcd(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		String regex = "(\\d{3})-?(\\d{1})-?(\\d{4})-?(\\d{4})$";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.matches()) {
			String target = matcher.group(3);
			int length = target.length();
			char[] c = new char[length];
			Arrays.fill(c, '*');
			return str.replaceAll(regex, "$1-$2-" + String.valueOf(c) + "-$4"); // NOSONAR
		}
		return str;
	}

	/**
	 * 마스킹 - 여권번호
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingPassPort(String str) {
		if (StringUtils.isEmpty(str))
			return str;
		String regex = "(.{4}$)";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.find()) {
			return str.replaceAll(regex, "****"); // NOSONAR
		}
		return str;
	}

	@Deprecated
	public static String maskingAddress(String str) {
		// 신(구)주소, 도로명 주소
		String regex = "(([가-힣]+(\\d{1,5}|\\d{1,5}(,|.)\\d{1,5}|)+(읍|면|동|가|리))(^구|)((\\d{1,5}(~|-)\\d{1,5}|\\d{1,5})(가|리|)|))([ ](산(\\d{1,5}(~|-)\\d{1,5}|\\d{1,5}))|)|"; // NOSONAR
		String newRegx = "(([가-힣]|(\\d{1,5}(~|-)\\d{1,5})|\\d{1,5})+(로|길))";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		Matcher newMatcher = Pattern.compile(newRegx).matcher(str);
		if (matcher.find()) {
			return str.replaceAll("[0-9]", "*"); // NOSONAR
		} else if (newMatcher.find()) {
			return str.replaceAll("[0-9]", "*"); // NOSONAR
		}
		return str;
	}


	/**
	 * 마스킹 - 모든문자
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingAll(String str) {
		char[] c = new char[str.length()];
		Arrays.fill(c, '*');
		return str.replaceAll(".*", String.valueOf(c)); // NOSONAR
	}

	/**
	 * 마스킹 - 가운데문자 : A***Z
	 * 
	 * @param str
	 * @return
	 */
	public static String maskingInner(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		String regex = ".{1}(.*).{1}$";
		Matcher matcher = Pattern.compile(regex).matcher(str);
		if (matcher.matches()) {
			String target = matcher.group(1);
			int length = target.length();
			char[] c = new char[length];
			Arrays.fill(c, '*');
			return str.replace(target, String.valueOf(c));
		}
		return str;
	}

	/**
	 * 전화번호 하이픈
	 * 
	 * @param str
	 * @return
	 */
	public static String telNoSetHyphen(String str) {
		if (str.length() == 8) {
			return str.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
		} else if (str.length() == 12) {
			return str.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
		} else if ("02".equals(str.substring(0, 2))) {
			return str.replaceFirst("(^[0-9]{2})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
		}
		return str.replaceFirst("(^[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
	}
	
	/**
	 * dto의 마스킹을 처리한다.
	 * 
	 * @param dto
	 */
	public static void masking(Object dto) {
		try {
			Field[] dtoFields = dto.getClass().getDeclaredFields();
			for(Field fld : dtoFields) {
				//String only
				if(String.class.isAssignableFrom(fld.getType()) && fld.getDeclaredAnnotations().length > 0) {
					Annotation anno =  fld.getDeclaredAnnotations()[0];
					fld.setAccessible(true);
					Object val = fld.get(dto);
					if(val == null) continue;
					
					//log.info("FIELD '{}' - {} / {}", fld.getName(), fld.getType(), anno);
				
					if(anno instanceof MaskingId) {
						fld.set(dto, MaskingUtil.maskingId((String)val));
					}
					if(anno instanceof MaskingName) {
						fld.set(dto, MaskingUtil.maskingName((String)val));
					}
					if(anno instanceof MaskingEngName) {
						fld.set(dto, MaskingUtil.maskingEngName((String)val));
					}
					if(anno instanceof MaskingTelno) {
						fld.set(dto, MaskingUtil.maskingTelno((String)val));
					}
					if(anno instanceof MaskingEmail) {
						fld.set(dto, MaskingUtil.maskingEmail((String)val));
					}
					if(anno instanceof MaskingSsn) {
						fld.set(dto, MaskingUtil.maskingSsn((String)val));
					}
					if(anno instanceof MaskingDrvno) {
						fld.set(dto, MaskingUtil.maskingDrvno((String)val));
					}
					if(anno instanceof MaskingBirthday) {
						fld.set(dto, MaskingUtil.maskingBirthday((String)val));
					}
					if(anno instanceof MaskingCardno) {
						fld.set(dto, MaskingUtil.maskingCardno((String)val));
					}
					if(anno instanceof MaskingAcntno) {
						fld.set(dto, MaskingUtil.maskingAcntno((String)val));
					}
					if(anno instanceof MaskingBizno) {
						fld.set(dto, MaskingUtil.maskingBizno((String)val));
					}
					if(anno instanceof MaskingIp) {
						fld.set(dto, MaskingUtil.maskingIp((String)val));
					}
					if(anno instanceof MaskingAll) {
						fld.set(dto, MaskingUtil.maskingAll((String)val));
					}
					if(anno instanceof MaskingInner) {
						fld.set(dto, MaskingUtil.maskingInner((String)val));
					}
					if(anno instanceof MaskingQrcd) {
						fld.set(dto, MaskingUtil.maskingQrcd((String)val));
					}
					if(anno instanceof MaskingPassPort) {
						fld.set(dto, MaskingUtil.maskingPassPort((String)val));
					}
					
					if(anno instanceof MaskingIdntNo) {
						String myVal = (String)val;
						
						if(myVal.length() >= 16 && myVal.length() <= 20) {
							fld.set(dto, MaskingUtil.maskingCardno(myVal));
						} else if(myVal.length() == 11) {
							fld.set(dto, MaskingUtil.maskingTelno(myVal));
						} else if(myVal.length() == 13) {
							fld.set(dto, MaskingUtil.maskingSsn(myVal));
						} else if(myVal.length() == 10) {
							fld.set(dto, MaskingUtil.maskingBizno(myVal));
						}
						
					}					
				}
			}
		} catch (RuntimeException e) {
			log.error("check masking process1!!"); 
		}
		catch (Exception e) {
			log.error("check masking process2!!"); 
		}
	}  
	
	
	/**
	 * 메인 마스킹 함수	- DTO 입력 버전
	 * @param log
	 * @return
	 */
	public static String maskLog(Object dto) {
	    if (dto == null) return null;

	    try {
	        Class<?> clazz = dto.getClass();
	        StringBuilder sb = new StringBuilder();
	        sb.append(clazz.getSimpleName()).append(" { ");

	        for (Field field : clazz.getDeclaredFields()) {
	            field.setAccessible(true);
	            Object value = field.get(dto);

	            sb.append(field.getName()).append("=");

	            if (value instanceof String strVal) {
	                String masked = maskPhone(strVal);
	                masked = maskAddress(masked);
	                masked = maskingEmail(masked);
	                sb.append(masked);
	            } else {
	                sb.append(value);
	            }
	            sb.append(", ");
	        }

	        if (sb.lastIndexOf(", ") == sb.length() - 2) {
	            sb.setLength(sb.length() - 2);
	        }

	        sb.append(" }");
	        return sb.toString();

	    } catch (Exception e) {
	        return String.valueOf(dto);
	    }
	}
	
	/**
	 * 메인 마스킹 함수 - 문자열 입력 버전
	 * @param log
	 * @return
	 */
	public static String maskLog(String s) {
	    if (s == null) return null;
	    String masked = maskPhone(s);
	    masked = maskAddress(masked);
	    masked = maskingEmail(masked);
	    return masked;
	}


	/**
	 * 전화번호 마스킹
	 * 
	 * @param text
	 * @return
	 */
	private static String maskPhone(String text) {
		Matcher matcher = PHONE_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String phone = matcher.group();
            String masked = phone.replaceAll("\\d(?=\\d{4})", "*");
            matcher.appendReplacement(sb, masked);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

	/**
	 * 주소 마스킹
	 * 
	 * @param text
	 * @return
	 */
	private static String maskAddress(String text) {
		Matcher matcher = ADDRESS_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String addr = matcher.group();
            String[] parts = addr.split(" ");

            if (parts.length >= 2) {
                matcher.appendReplacement(sb,
                        parts[0] + " " + parts[1] + " ******");
            } else {
                matcher.appendReplacement(sb, "******");
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

	/**
	 * 공통 패턴 마스킹
	 * 
	 * @param text
	 * @param pattern
	 * @param mask
	 * @return
	 */
	private static String maskPattern(String text, Pattern pattern, String mask) {
		return pattern.matcher(text).replaceAll(mask);
	}

}