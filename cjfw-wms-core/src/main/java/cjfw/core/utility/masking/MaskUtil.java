package cjfw.core.utility.masking;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : MaskUtil 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
public class MaskUtil {	
	private static final Logger log = LoggerFactory.getLogger(MaskUtil.class);
	
	/**
	 * @field REGEX_SSN_DASH
	 * @type String
	 * @description 주민번호 (-) 정규식
	 */
	private final static String REGEX_SSN_DASH  		= "(\\d{6})([- \\t\\n\\x0B\\f\\r]*)(\\d{1})\\d{6}";
	
	/**
	 * @field MASK_SSN_DASH
	 * @type String
	 * @description 주민번호 마스킹 
	 */
	private final static String MASK_SSN_DASH      		= "$1$2$3******";	
	
	/**
	 * @field REGEX_BIRTH
	 * @type String
	 * @description 생년월일 정규식
	 */	
	private final static String REGEX_BIRTH  		         =  "(\\d{2})(\\d{2})([-/. \\t\\n\\x0B\\f\\r]*)(\\d{2})([-/. \\t\\n\\x0B\\f\\r]*)\\d{2}";
	
	/**
	 * @field MASK_BIRTH
	 * @type String
	 * @description 생년월일 마스킹 
	 */
	private final static String MASK_BIRTH  = "$1**$3$4$5**";
	
	/**
	 * @field REGEX_BIZ_NO
	 * @type String
	 * @description 사업자/법인번호 정규식 
	 */
	private final static String REGEX_BIZ_NO 	= "(\\d{3})([- \\t\\n\\x0B\\f\\r])(\\d{2})([- \\t\\n\\x0B\\f\\r])\\d{5}";
	
	/**
	 * @field MASK_BIZ_NO
	 * @type String
	 * @description 사업자/법인번호 마스킹 
	 */
	private final static String MASK_BIZ_NO  	= "$1$2$3$4*****";
	
	/**
	 * @field REGEX_TEL_NO
	 * @type String
	 * @description 전화번호(-) 정규식 
	 */
	private final static String REGEX_TEL_NO  = "(\\d{2,3})([- \\t\\n\\x0B\\f\\r]*)(\\d{1,2})(\\d{2})([- \\t\\n\\x0B\\f\\r]*)(\\d{1})(\\d{3})";
	
	/**
	 * @field MASK_TEL_NO
	 * @type String
	 * @description 전화번호 (-) 마스킹 
	 */
	private final static String MASK_TEL_NO   = "$1$2****$5$6$7";
	
    /**
     * @field REGEX_16_DIGIT_CREDIT_CARD
     * @type String
     * @description 16자리 신용카드 정규식 
     */
    private final static String REGEX_16_DIGIT_CREDIT_CARD  = "(\\d{4})([- ]*)(\\d{2})(\\d{2})([- ]*)(\\d{4})([- ]*)(\\d{3})(\\d{1})"; 
    
    /**
     * @field MASK_16_DIGIT_CREDIT_CARD
     * @type String
     * @description 16자리 신용카드 마스킹 
     */
    private final static String MASK_16_DIGIT_CREDIT_CARD      = "$1$2$3**$5****$7***$9";
    
    /**
     * @field REGEX_15_DIGIT_CREDIT_CARD
     * @type String
     * @description 15자리 신용카드(AMEX) 정규식 
     */
    private final static String REGEX_15_DIGIT_CREDIT_CARD   = "(\\d{4})([- ]*)(\\d{2})(\\d{2})([- ]*)(\\d{4})([- ]*)(\\d{2})(\\d{1})";
    
    /**
     * @field MASK_15_DIGIT_CREDIT_CARD
     * @type String
     * @description 15자리 신용카드(AMEX) 마스킹 
     */
    private final static String MASK_15_DIGIT_CREDIT_CARD     = "$1$2$3**$5****$7**$9";
    
    /**
     * @field REGEX_14_DIGIT_CREDIT_CARD
     * @type String
     * @description 14자리 신용카드(Diners Club) 정규식
     */
    private final static String REGEX_14_DIGIT_CREDIT_CARD   = "(\\d{4})([- ]*)(\\d{2})(\\d{2})([- ]*)(\\d{4})([- ]*)(\\d{1})(\\d{1})";
    
    /**
     * @field MASK_14_DIGIT_CREDIT_CARD
     * @type String
     * @description 14자리 신용카드(Diners Club) 마스킹 
     */
    private final static String MASK_14_DIGIT_CREDIT_CARD     = "$1$2$3**$5****$7*$9";
    
    /**
     * @field MASK_OTHER_CREDIT_CARD
     * @type String
     * @description 그 외 신용 카드 마스킹 
     */
    private final static String MASK_OTHER_CREDIT_CARD  = "****-****-****-****"; 
	
	/**
	 * @field REGEX_CARD_YYYYMM
	 * @type String
	 * @description 카드유효일(YYYYMM) 정규식
	 */
	private final static String REGEX_CARD_YYYYMM 	= "(\\d{4})([/ ]*)(\\d{2})";
	
	/**
	 * @field MASK_CARD_YYYYMM
	 * @type String
	 * @description 카드유효일(YYYYMM) 마스킹 
	 */
	private final static String MASK_CARD_YYYYMM  	= "****/**";
	
	/**
	 * @field REGEX_CARD_YYYYMMDD
	 * @type String
	 * @description 카드유효일 (YYYYMMDD) 정규식
	 */
	private final static String REGEX_CARD_YYYYMMDD 	= "(\\d{4})([/ ]*)(\\d{2})([/ ]*)(\\d{2})";
	
	/**
	 * @field MASK_CARD_YYYYMMDD
	 * @type String
	 * @description 카드유효일 (YYYYMMDD) 마스킹 
	 */
	private final static String MASK_CARD_YYYYMMDD  	= "****/**/**";
	
	/**
	 * @field REGEX_ID
	 * @type String
	 * @description ID 류 정규식 
	 */
	private final static String REGEX_ID = "([a-zA-Z][\\w\\.-]*)([a-zA-Z0-9]{3})"; 
	
	/**
	 * @field MASK_ID
	 * @type String
	 * @description ID 류 마스킹 
	 */
	private final static String MASK_ID   			= "$1***";
	
	/**
	 * @field REGEX_IP
	 * @type String
	 * @description IP 주소 정규식 
	 */
	private final static String REGEX_IP = "(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}";

	/**
	 * @field MASK_IP
	 * @type String
	 * @description IP 주소 마스킹 
	 */
	private final static String MASK_IP   		= "***.$2.***.$4";

	/**
	 * @field REGEX_DRIVER_DASH
	 * @type String
	 * @description DASH 포함한 운전면허 번호 정규식 
	 */
	private final static String REGEX_DRIVER_DASH  		= "(\\d{2}-\\d{2}-\\d{6}-\\d{2})";
	
	/**
	 * @field MASK_DRIVER_DASH
	 * @type String
	 * @description DASH 포함한 운전면허 번호 마스킹 
	 */
	private final static String MASK_DRIVER_DASH   		= "$1-******-$5";
	
 	/**
 	 * @field REGEX_DRIVER
 	 * @type String
 	 * @description DASH 없는 운전면허 번호 정규식  
 	 */
 	private final static String REGEX_DRIVER     		= "(\\d{2})(\\d{6})(\\d{2})";
 	
	/**
	 * @field MASK_DRIVER
	 * @type String
	 * @description DASH 없는 운전면허 번호 마스킹 
	 */
	private final static String MASK_DRIVER   		= "$1-******-$3"; 
	
	/**
	 * @field REGEX_INTERTEL
	 * @type String
	 * @description 국제전화 정규식 
	 */
	private final static String REGEX_INTERTEL = "(\\d{1,2})(\\d{1})([- \\t\\n\\x0B\\f\\r]*)(\\d{2,3})([- \\t\\n\\x0B\\f\\r]*)(\\d{1,2})(\\d{2})([- \\t\\n\\x0B\\f\\r]*)(\\d{1})(\\d{3})";
	
	/**
	 * @field MASK_INTERTEL
	 * @type String
	 * @description 국제전화 마스킹 
	 */
	private final static String MASK_INTERTEL = "$1$2$3$4$5$6**$8*$10";
	
	/**
	 * @field MASK_CHAR
	 * @type char
	 * @description 마스킹 문자 
	 */
	private final static char MASK_CHAR = '*';
	
	/**
	 * 
	 * @description : MaskUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private MaskUtil() {}

	/**
	 * 
	 * @description : mask 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public static String mask(String value, MaskingTypeEnum type) {
		String result = null;
		try {
			switch(type) {
			case ID:
				result = getMaskedID(value);
				break;
			case NAME:
				result = getMaskedName(value);
				break;
			case PHONE:
				result = getMaskedTelNo(value);
				break;		
			case MAIL:
				result = getMaskedEmail(value);
				break;	
			case BIRTH:
				result = getMaskedBirth(value);
				break;		
			case CARD:
				result = getMaskedByCreditCard(value);
				break;		
			case CARD_VALID_DATE:
				result = getMaskedCardExpire(value);
				break;				
			case ACCOUNT:
				result = getMaskedByBankAccountNum(value);
				break;		
			case IP:
				result = getMaskedIpAddr(value);
				break;		
			case RRN:
				result = getMaskedSSN(value);
				break;		
			case BIZ_NO:
				result = getMaskedBizNo(value);
				break;
			case DRIVER_LICENSE:
				result = getMaskedDriver(value);
				break;
			case PASSPORT_NO:
				result = getMaskedPassportNo(value);
				break;
			case OLD_ADDR:
				result = getMaskedByAddress(value, false);
				break;		
			case NEW_ADDR:
				result = getMaskedByAddress(value, true);
				break;		
			case ADDR_DTL:
				result = getMaskedDetailAddress(value);
				break;
			case OVERSEE_PHONE:
				result = getMaskedInterTel(value);
				break;	
			case ALL:
				result = getMaskedAll(value);
				break;	
			case ADDR:
				result = getMaskedByAddress(value);
				break;				
			default:
				result = value;
				break;
			}
		} catch (Exception e) {
			log.error("MaskHelper::mask", e);
			result = value;
		}
		return result;
	}	
	
	/**
	 * 
	 * @description : getMaskedSSN(주민등록번호) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedSSN(String value){
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			ret = value.replaceAll(REGEX_SSN_DASH,MASK_SSN_DASH);
		}
		return ret;
	}

	/**
	 * 
	 * @description : getMaskedBirth(생년월일) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedBirth(String value){
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			ret = value.replaceAll(REGEX_BIRTH,MASK_BIRTH);
		}
		return ret;
	}	
	
	/**
	 * 
	 * @description : getMaskedBizNo(법인번호/사업자번호) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedBizNo(String value){
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			ret = value.replaceAll(REGEX_BIZ_NO,MASK_BIZ_NO);
		}
		return ret;
	}
	
	/**
	 * 
	 * @description : getMaskedTelNo(전화번호 (-포함시)) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedTelNo(String value){
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			ret = value.replaceAll(REGEX_TEL_NO,MASK_TEL_NO);
		}
		return ret;
	}

	/**
	 * 
	 * @description : getMaskedInterTel(국제전화번호) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedInterTel(String value){
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			ret = value.replaceAll(REGEX_INTERTEL, MASK_INTERTEL);
		}
		return ret;
	}

	/**
	 * 
	 * @description : getMaskedCreditCard(신용카드) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
    private static String getMaskedCreditCard(String value,int length){
        String ret = "";

        if (!StringUtils.isBlank(value)) {
            if (length==16) {
                ret = value.replaceAll(REGEX_16_DIGIT_CREDIT_CARD, MASK_16_DIGIT_CREDIT_CARD);
            }else if (length==15) {
                ret = value.replaceAll(REGEX_15_DIGIT_CREDIT_CARD, MASK_15_DIGIT_CREDIT_CARD);
            }else if (length==14) {
                ret = value.replaceAll(REGEX_14_DIGIT_CREDIT_CARD, MASK_14_DIGIT_CREDIT_CARD);
            }else {
                ret = MASK_OTHER_CREDIT_CARD;
            }
        }
        return ret;
    }
	
    /**
     * 
     * @description : getMaskedCardExpire(카드 유효기간) 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
	private static String getMaskedCardExpire(String value){
		return getMaskedCardByExpire(value);
	}
	
	/**
	 * 
	 * @description : getMaskedEmail(이메일) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedEmail(String value){
		return getMaskedByEmail(value);
	}
	
	/**
	 * 
	 * @description : getMaskedID(아이디(ID)) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedID(String value){
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			ret = value.replaceAll(REGEX_ID,MASK_ID);
		}
		return ret;
	}
	
	/**
	 * 
	 * @description : getMaskedIpAddr(IP주소) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedIpAddr(String value){
		return getMaskedByIP(value);
	}
	
	/**
	 * 
	 * @description : getMaskedPassportNo(여권번호) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedPassportNo(String value){
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			ret = getMaskedValue(value, 4,false);
		}
		return ret;
	}
	
	/**
	 * 
	 * @description : getMaskedDriver(운전면허) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedDriver(String value){
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			ret = (value.indexOf("-") < 0)  ? value.replaceAll(REGEX_DRIVER,MASK_DRIVER) : value.replaceAll(REGEX_DRIVER_DASH,MASK_DRIVER_DASH); ;
		}
		return ret;
	}

	/**
	 * 
	 * @description : getMaskedName(이름) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedName(String value){
		String result = "";
		if (StringUtils.isBlank(value)) {
			result="";
		} else {
			int length = value.length();
			int startIdx = 0;
			int endIdx = 0;
			if (length > 1) {
				StringBuilder sb = new StringBuilder(value);
				startIdx = 1;
				endIdx = 2;
				for (int i = startIdx; i < endIdx; i++) {
					sb.setCharAt(i, MASK_CHAR);
				}
				result = sb.toString();
			} else if (length == 1) {
				StringBuilder sb = new StringBuilder(value);
				sb.setCharAt(0, MASK_CHAR);
				result = sb.toString();
			} else {
				result = value;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @description : getMaskedValue 기능을 구현한 Method
	 * 				  maskLength 마스킹 처리 길이
	 * 				  dirOption 마스킹 처리 앞/뒤 여부
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedValue(String orgValue,int maskLength, boolean dirOption) {
		String result = "";
		if (StringUtils.isBlank(orgValue)) {
			result="";
		}else{
			int length = orgValue.length();
			int startIdx = 0;
			int endIdx = 0;
			if (length > 0) {
				StringBuilder sb = new StringBuilder(orgValue);
				
				if(dirOption){ // true 앞
					startIdx = 0;
					endIdx = maskLength;
				}else{ // false 뒤
					startIdx = length - maskLength;
					endIdx = length;
				}
				for (int i = startIdx; i < endIdx; i++) {
					sb.setCharAt(i, MASK_CHAR);
				}
				result = sb.toString();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @description : getMaskedValue 기능을 구현한 Method
	 * 				  길이와 방향, 마스킹 옵션에 따른  마스킹 처리
	 * 
	 * 				  maskLength 마스킹 길이
	 * 				  dirOption 마스킹 방향
	 * 				  toggle 마스킹 toggle
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedValue(String orgValue,int maskLength, boolean dirOption,boolean toggle) {
		// maskOption : true이면 maskLength만큼 *로변경, false이면 maskLength를 제외한 나머지를 *로 변경
		String result = "";
		if (StringUtils.isBlank(orgValue)) {
			result="";
		}else{
			int length = orgValue.length();
			int startIdx = 0;
			int endIdx = 0;
			if (length > 0) {
				StringBuilder sb = new StringBuilder(orgValue);
				
				if(toggle) {
					if(dirOption) {
						// true 앞, masking
						startIdx = 0;
						endIdx = maskLength;
					} else {
						// false 뒤, masking
						startIdx = length - maskLength;
						endIdx = length;
					}
				} else {
					if(dirOption) {
						// true 앞,나머지를 masking
						startIdx = maskLength;
						endIdx = length;
					} else {
						// false 뒤,나머지를 masking
						startIdx = 0;
						endIdx = length-maskLength;
					}
				}

				
				for (int i = startIdx; i < endIdx; i++) {
					sb.setCharAt(i, MASK_CHAR);
				}
				result = sb.toString();
			}
		}
		return result;
	}

	/**
	 * 
	 * @description : getMaskedAll(전체 마스킹) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedAll(String orgValue) {
		String result = "";
		if (StringUtils.isBlank(orgValue)) {
			result="";
		}else{
			int length = orgValue.length();
			if (length > 0) {
				StringBuilder sb = new StringBuilder(orgValue);
				
				for (int i = 0; i < length; i++) {
					sb.setCharAt(i, MASK_CHAR);
				}
				result = sb.toString();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @description : getMaskedByValue(사이즈 기반 마스킹) 기능을 구현한 Method
	 * 				  maskLength 마스킹 처리 길이
	 * 				  dirOption 마스킹 처리 앞/뒤 여부
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedByValue(String orgValue,int maskLength, boolean dirOption) {
		String result = "";
		if (StringUtils.isBlank(orgValue)) {
			result="";
		}else{
			int length = orgValue.length();
			int startIdx = 0;
			int endIdx = 0;
			if (length > 0) {
				StringBuilder sb = new StringBuilder(orgValue);
				
				if (orgValue.length() < maskLength) {
					maskLength = orgValue.length();
				}
				if(dirOption){ // true 앞
					startIdx = 0;
					endIdx = maskLength;
				}else{ // false 뒤
					startIdx = length - maskLength;
					endIdx = length;
				}
				for (int i = startIdx; i < endIdx; i++) {
					sb.setCharAt(i, MASK_CHAR);
				}
				result = sb.toString();
			}
		}
		return result;
	}

	/**
	 * 
	 * @description : getMaskedByCreditCard(신용카드 -> 앞6글자,뒤1글자 표기) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
    private static String getMaskedByCreditCard(String orgValue) {
        int len = 0;
        String orgValueTmp = "";

        orgValue = ((orgValue.replace(" ", "")).replace("-", ""));
        len = orgValue.length();

        orgValueTmp = orgValue.substring(0, 4) + "-";
        orgValueTmp = orgValueTmp + orgValue.substring(4, 8) + "-";
        orgValueTmp = orgValueTmp + orgValue.substring(8, 12) + "-";
        orgValueTmp = orgValueTmp + orgValue.substring(12, len);
        orgValue = orgValueTmp;
        
        return getMaskedCreditCard(orgValue,len);
    }

    /**
     * 
     * @description : getMaskedByBankAccountNum(계좌번호 -> 앞6글자 표기) 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
	private static String getMaskedByBankAccountNum(String orgValue) {
		String ret = "";
		if (!StringUtils.isBlank(orgValue)) {
			ret = getMaskedValue(orgValue, 6,true,false);
		}
		return ret;
	}

	/**
	 * 
	 * @description : getMaskedByAddress(주소 : (구)번지부터, (신)도로명 이후부터) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedByAddress(String addr) {
		boolean isNewAddr = false;
		String value =null;
		if (addr.endsWith("대로") ||  addr.endsWith("로") || addr.endsWith("길")) {
			value = addr + " ";
		} else {
			value = addr;
		}			
		int idxDaeRo = value.indexOf("대로 ");
		int idxRo = value.indexOf("로 ");
		int idxGil = value.indexOf("길 ");
		
		isNewAddr = (idxDaeRo > 0 || idxRo > 0 || idxGil > 0);
		
		if (isNewAddr) {
			return getMaskedByNewAddress(addr);
		} else {
			return getMaskedByOldAddress(addr);
		}
	}
	
	/**
	 * 
	 * @description : getMaskedByAddress(주소 : (구)번지부터, (신)도로명 이후부터) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedByAddress(String addr, boolean isNewAddr){
		if (isNewAddr) {
			return getMaskedByNewAddress(addr);
		} else {
			return getMaskedByOldAddress(addr);
		}
	}
	
	/**
	 * 
	 * @description : getMaskedByOldAddress(지번 주소) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedByOldAddress(String addr) {
		String ret = "";
		if (!StringUtils.isBlank(addr)) {
			String value = null;
			if (addr.endsWith("면") ||  addr.endsWith("읍") || addr.endsWith("리") || addr.endsWith("동") || addr.endsWith("로") || addr.endsWith("가")) {
				value = addr + " ";
			} else {
				value = addr;
			}
			int idxMyun = value.indexOf("면 ");
			int idxUb = value.indexOf("읍 ");
			int idxRi = value.indexOf("리 ");
			int idxDong = value.indexOf("동 ");
			int idxRo = value.indexOf("로 ");
			int idxGa = value.indexOf("가 ");
			
			int idxMaskingStart = 0;
			if (idxGa != -1) {
				idxMaskingStart = idxGa;
			} else if (idxRo != -1) {
				idxMaskingStart = idxRo;
			} else if (idxRi != -1) {
				idxMaskingStart = idxRi;
			} else if (idxUb != -1) {
				idxMaskingStart = idxUb;
			} else if (idxMyun != -1) {
				idxMaskingStart = idxMyun;
			} else if (idxDong != -1) {
				idxMaskingStart = idxDong;
			}
			
			idxMaskingStart += 2;
			
			ret = getMaskedValue(addr, idxMaskingStart, true, false);
		}
		return ret;
		
	}
		
	/**
	 * 
	 * @description : getMaskedByNewAddress(도로명 주소) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedByNewAddress(String addr) {
		String ret = "";
		if (!StringUtils.isBlank(addr)) {
			String value = null;
			if (addr.endsWith("대로") ||  addr.endsWith("로") || addr.endsWith("길")) {
				value = addr + " ";
			} else {
				value = addr;
			}			
			int idxDaeRo = value.indexOf("대로 ");
			int idxRo = value.indexOf("로 ");
			int idxGil = value.indexOf("길 ");
			
			int idxMaskingStart = 0;
			if (idxGil != -1) {
				idxMaskingStart = idxGil;
			} else if (idxRo != -1) {
				idxMaskingStart = idxRo;
			} else if (idxDaeRo != -1) {
				idxMaskingStart = idxDaeRo;
			}
			
			idxMaskingStart += 2;
			
			ret = getMaskedValue(addr, idxMaskingStart, true, false);
		}
		return ret;
	}

	/**
	 * 
	 * @description : getMaskedByEmail(이메일 -> 뒤3글자) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedByEmail(String value) {
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			int indexAtSign = value.indexOf("@");
			if (indexAtSign == -1) {
				ret = getMaskedByValue(value, 3,false);
			} else {
				ret = getMaskedByValue(value.substring(0, indexAtSign), 3,false) + value.substring(indexAtSign);
			}
		}
		return ret;
	}

	/**
	 * 
	 * @description : getMaskedCardByExpire(카드유효일 -> 모두 마스킹) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedCardByExpire(String value) {
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			if (value.length() == 8) {
				ret = value.replaceAll(REGEX_CARD_YYYYMMDD,MASK_CARD_YYYYMMDD);
			} else {
				ret = value.replaceAll(REGEX_CARD_YYYYMM,MASK_CARD_YYYYMM);
			}
		}
		return ret;
	}

	/**
	 * 
	 * @description : getMaskedByIP(IP -> 첫째/셋째 3자리씩) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedByIP(String value) {
		String ret = "";
		if (!StringUtils.isBlank(value)) {
			ret = value.replaceAll(REGEX_IP,MASK_IP);
		}
		return ret;
	}

	/**
	 * 
	 * @description : getMaskedDetailAddress(상세 주소 마스킹) 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private static String getMaskedDetailAddress(String value) {
		String f_value = value.substring(0,1);
		int len = value.length();
		String l_value = "";
		while (l_value.length() < len-1)
			l_value += "*";
		l_value = l_value.length() >= len-1 ? l_value.substring(0, len-1) : l_value;
		
		String result =	f_value + l_value;	
		return result;
	}	
}