package cjfw.wms.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The class StringUtil
 * 
 * StringUtil 클래스<br>
 * 
 *
 * @author LeeHyeonJae
 * @version 1.0
 * @since 2012. 5. 29.
 *
 */
public class StringUtil extends cjfw.core.utility.StringUtil { // NOSONAR

	private static final Log LOG = LogFactory.getLog(StringUtil.class);
	private StringUtil() {super();} //private 생성자
	
	
	/**
	 * StringUtils.defaultString()에 Object 인수를 받을 수 있도록 oveloading
	 * 
	 * @param obj
	 * @return
	 */
	public static String defaultString(Object obj) {
		return defaultString(obj, "");
	}

	/**
	 * StringUtils.defaultString()에 Object 인수를 받을 수 있도록 oveloading
	 * 
	 * @param obj
	 * @return
	 */
	public static String defaultString(Object obj, String defaultStr) {
		return isEmpty(obj) ? defaultStr : obj.toString();
	}

	/**
	 * StringUtils.isEmpty()에 Object 인수를 받을 수 있도록 oveloading 
	 * 
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if(obj == null) {
			return true;
		}
		return StringUtils.isEmpty(obj.toString());
	}

	/**
	 * StringUtils.isEmpty()에 Object 인수를 받을 수 있도록 oveloading
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * 배열 객체 등 toString()으로 내용이 출력되지 않는 객체들의 내용을 출력한다.
	 * 
	 * @param obj
	 */
	public static void printObj(String title, Object obj) {

		if(LOG.isDebugEnabled()) {
			LOG.info(""); //개행
			//null일 경우
			if(obj == null) {
				LOG.info(new StringBuilder("@@ ").append(title).append(" : is null").toString());
				return;
			}
			//배열일 경우
			if(obj.getClass().isArray()) {
				int idx = 0;
				for(Object o : (Object[])obj) {
					LOG.info(new StringBuilder("@@ ").append(title).append("[").append(idx).append("] : ").append(o));
					idx++;
				}
			}
			LOG.info("");//개행
		}
	}

	/**
	 * 역슬래시(\)를 슬래시(/)로 바꿔준다
	 * 파일 경로등에서 사용하며, 클라이언트단 JSON.parse() 등에서 에러를 피하는 용도로 사용된다.
	 * 
	 * @param src
	 * @return
	 */
	public static String replaceAllBackSlash(String src) {
		if(StringUtils.isBlank(src)) {
			return "";
		}
		return src.replace("\\\\", "/");
	}

	/**
	 * 임의의 알파벳 대문자 문자열을 리턴한다.
	 * 
	 * @param length 원하는 문자열의 길이
	 * @return
	 */
//	public static String getRandomString(int length) {
//		StringBuilder sb = new StringBuilder();
//		Random random = new Random();
//		String[] chars = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
//		for(int i = 0; i < length; i++) {
//			sb.append(chars[random.nextInt(chars.length)]);
//		}
//		return sb.toString();
//	}
	//20251031@보안점검 조치 by sss
	public static String getRandomString(int length) {
	    final char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	    StringBuilder sb = new StringBuilder(length);
	    java.security.SecureRandom random = new java.security.SecureRandom();
	    for (int i = 0; i < length; i++) {
	        sb.append(chars[random.nextInt(chars.length)]);
	    }
	    return sb.toString();
	}	

	/**
	 * 현재 날짜에 따른 경로구조를 문자열로 만들어 리턴
	 * 		- ex) /2012/06/21
	 * 
	 * @return
	 */
	public static String getTodayPath() {
		GregorianCalendar today = new GregorianCalendar();
		String year = String.valueOf(today.get(today.YEAR));
		String month = String.valueOf(today.get(today.MONTH) + 1);
		month = (month.length() == 1) ? "0" + month : month;
		String day = String.valueOf(today.get(today.DATE));
		day = (day.length() == 1) ? "0" + day : day;
		String ret = new StringBuilder("/").append(year).append("/").append(month).append("/").append(day).toString();
		return ret;
	}

	/**
	 * 현재 날짜를 주어진 dateformat 문자열 형식에 맞게 만들어 리턴한다.
	 * 
	 * 		- ex) 날짜까지 : yyyyMMdd
	 * 			    초단위시간까지 : yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getToday(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

	/**
	 * lpad 메서드
	 * 
	 * @param str 원본문자열
	 * @param len 원하는 전체 길이
	 * @param addStr 길이만큼 채울 문자
	 * @return
	 */
	public static String lpad(String str, int len, String addStr) {
		String result = str;
		int templen = len - result.length();
		for(int i = 0; i < templen; i++) {
			result = addStr + result;
		}
		return result;
	}

	/**
	 * 01~24까지 시간을 나타내는 문자열의 리스트를 반환한다. (시간 콤보박스 생성용)
	 * 		- 결과) [01, 02, ... 24]
	 * 
	 * @return
	 */
	public static List<String> getHourList() {
		List<String> hourList = new ArrayList<String>();
		for(int i = 1; i <= 24; i++) {
			hourList.add(StringUtil.lpad(String.valueOf(i), 2, "0"));
		}
		return hourList;
	}

	/**
	 * 01~24까지 시간을 나타내는 문자열의 리스트를 반환한다. (시간 콤보박스 생성용)
	 * 		- 시작시간(to)와 종료 시간(from)을 지정 
	 * 
	 * @return
	 */
	public static List<String> getHourList(int to, int from) {
		List<String> hourList = new ArrayList<String>();
		for(int i = to; i <= from; i++) {
			hourList.add(StringUtil.lpad(String.valueOf(i), 2, "0"));
		}
		return hourList;
	}

	/**
	 * 분(minute)을 나타내는 문자열의 리스트를 반환한다. (분 콤보박스 생성용)
	 * - 예) [00, 10, 20 ... 50]
	 * 
	 * @param interval 몇 분 간격의 리스트를 원하는지
	 * @return
	 */
	public static List<String> getMinuteList(int interval) {
		List<String> minuteList = new ArrayList<String>();
		for(int i = 0; i < 60; i = i + interval) {
			minuteList.add(StringUtil.lpad(String.valueOf(i), 2, "0"));
		}
		return minuteList;
	}



	/**
	 * Map<String,String>을 not null 처리해 주는 메서드
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> defaultMap(Object obj) {
		if(obj == null) {
			return new HashMap<String, String>();
		}
		return (Map<String, String>)obj;
	}

	/**
	 * URL을 인수로 받아서, 해당 URL에 붙어 있는 파라미터들을 취합하여 Map객체로 리턴한다.
	 * 
	 * @ahthor LeeHyeonJae
	 * @since 2012. 12. 5.
	 *
	 * @param url (ex) "/ehr/benefit/culture/condo/main/condoFrameSet/mainCondo.fo?SYSTEM_CL=systemCl&SEQ_NOTICE=6&VIEWYTPE=FRAME"
	 * @return 파라미터로 구성된 Map객체
	 */
	public static Map<String, String> getParamMapByURL(String url) {

		Map<String, String> map = new HashMap<String, String>();

		//원본문자열이 없으면 리턴
		if(StringUtils.isBlank(url)) {
			return map;
		}

		String[] paramStrArr = url.split("\\?");
		if(paramStrArr.length <= 1) { //파라미터가 없음
			return map;
		}

		String paramStr = paramStrArr[1];
		String[] paramArr = paramStr.split("&");

		for(String pairStr : paramArr) {
			String[] pairArr = pairStr.split("=");
			if(pairArr.length <= 1) {
				continue;
			}
			map.put(pairArr[0], pairArr[1]);
		}

		if(LOG.isDebugEnabled()) {
			LOG.info("@@ getParamMapByURL(): " + map);
		}
		return map;
	}

	/**
	 * UTF-8로 URLEncoding을 수행한다.
	 * 
	 * @param src
	 * @return
	 */
	public static String encodeString(String src) {
		String ret = null;
		try {
			ret = URLEncoder.encode(StringUtils.defaultString(src), "UTF-8");
		} catch(UnsupportedEncodingException e) {
			LOG.error("Exception", e);
		}
		return ret;
	}

	/**
	 * URLDecoding 함수
	 * 
	 * @param src
	 * @return
	 */
	public static String decodeString(String src) {
		String ret = null;
		try {
			ret = URLDecoder.decode(src, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			LOG.error("Exception", e);
			ret = "";
		}
		return ret;
	}

	/**
	 * 캐릭터셋 찾기
	 * 
	 * @param str_kr
	 * @throws UnsupportedEncodingException
	 */
	public static void charSet(String str_kr) {
		String charset[] = {"euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii", "UTF-8"};

		for(int i = 0; i < charset.length; i++) {
			for(int j = 0; j < charset.length; j++) {
				if(i == j)
					continue;
				try {
					LOG.info(charset[i] + " : " + charset[j] + " :"
							+ new String(str_kr.getBytes(charset[i]), charset[j]));
				} catch(UnsupportedEncodingException e) {
					LOG.error("Exception", e);
				}
			}
		}
	}


	public static String numberFormat(double num, String pattern) {
		DecimalFormat dFormatter = new DecimalFormat(pattern);
		NumberFormat nFormatter = NumberFormat.getInstance();

		String formatted;

		if(pattern.length() > 0) {
			formatted = dFormatter.format(num);
		} else {
			formatted = nFormatter.format(num);
		}

		return formatted;
	}

	/**
	* 숫자형을 '###,###,###,###,##0' 형식으로 바꿈
	* <p>
	* @param    String   str      값
	* @return   String   형식에 맞게 변환된결과, 결과가 "0"일경우 "" 반환
	*/
	public static String getComma(String strNum) {
		double dNum;
		String formatted;

		if(strNum == null) {
			dNum = 0;
		} else if(strNum.trim().length() == 0) {
			dNum = 0;
		} else if(strNum.indexOf(',') >= 0) {
			return strNum;
		} else {
			dNum = Double.parseDouble(strNum);
		}

		if(dNum == 0) {
			formatted = "";
		} else {
			formatted = numberFormat(dNum);
		}

		return formatted;
	}

	public static String numberFormat(double num) {
		return numberFormat(num, "");
	}

	/**
	 * 지정한 날짜에서 지정한 일수를 더한 일자를 구하거나 뺀날자를 구함
	 * 
	 * @ahthor CBSEO
	 * @since 2013. 3. 21.
	 *
	 * @param num
	 * @return
	 */
	public static String dayLimitForamt(String format, String date, int addOrDiffDay) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		Date dt;
		try {
			dt = sdf.parse(date);
			calendar.setTime(dt);
			calendar.add(Calendar.DATE, addOrDiffDay);
		} catch(ParseException e) {
			LOG.error("Exception", e);
		}

		String ret = sdf.format(calendar.getTime());

		return ret;
	}
	
	
	/**
	 * 프로시저 호출시 raise된 Exception에서 피드백할 내용만 추출하여 문자열로 리턴한다.
	 * 
	 * @param e
	 * @return
	 */
	public static String getExceptionMessage(Exception e) {
		String es = e.toString();
		int startIdx = es.indexOf("java.sql.SQLException: ");
		int endIdx = es.indexOf('\n', startIdx);
		String tempErrorMsg = es.substring(startIdx, endIdx);
		startIdx = tempErrorMsg.indexOf(':') + 2;
		return tempErrorMsg.substring(startIdx);
	}

	
	/**
	 *	특정 문자열의 다음문자부터 이후 개행문자까지만 추출하여 리턴하는 메서드 		
	 * 
	 * @param src 원본 문자열	
	 * @param beforeRegex 추출하기 원하는 문자열의 바로 전 문자열에 해당하는 정규식	
	 * @return match 되는 부분이 없을 경우에는 빈문자("")를 리턴한다.
	 */
	public static String substringNextCharToLineFeed(String src, String beforeRegex){
		/*
		 * (예시) beforeRegex가 "java\\.sql\\.SQL.*Exception:" 일 경우. 
		 * 
		 * [원본문자열]
		 * "...
		 * 	Caused by: java.lang.reflect.InvocationTargetException
		 *	             at sun.reflect.GeneratedMethodAccessor102.invoke(Unknown Source)
		 * Caused by: java.sql.SQLSyntaxErrorException: ORA-00933: SQL 명령어가 올바르게 종료되지 않았습니다
		 *
		 *	at oracle.jdbc.driver.SQLStateMapping.newSQLException(SQLStateMapping.java:91)
		 * ..."
	     *
		 * [추출한 문자열]
		 * "ORA-00933: SQL 명령어가 올바르게 종료되지 않았습니다"
		 */
//		if (LOG.isDebugEnabled()) {
//			LOG.info("\n\n\n######################### substringNextToLineFeed() > src - start ##########################");
//			LOG.info(src);
//			LOG.info("\n######################### substringNextToLineFeed() > src - end ###########################\n\n\n");
//		}
		String ret = "";
		String regex = "("+beforeRegex+")(.*\\r?\\n)(.*)"; //group 1,2,3
		if (LOG.isDebugEnabled()) {
			LOG.info("## substringNextToLineFeed() > regex: "+regex);
		}		
		Pattern pattern = Pattern.compile(regex);				
		Matcher matcher = pattern.matcher(src);
		//최초 매칭이 존재하면 true이다. 
		boolean isFound = matcher.find(); //find()메서드는 호출할 때마다 내부 카운터가 변경된다는 사실에 주의하자. 
		if (LOG.isDebugEnabled()) {
			LOG.info("## substringNextToLineFeed() > isFound: "+isFound);
		}		
		if( ! isFound){ 
			return ""; 
		}
		int groupCount = matcher.groupCount(); //정규식 그룹핑 카운트
		if (LOG.isDebugEnabled()) {
			LOG.info("## substringNextToLineFeed() > groupCount: "+groupCount);
			for(int i=0; i<groupCount; i++){
				LOG.info("## group("+i+"): "+matcher.group(i));
			}
		}
		if(groupCount < 2){
			return "";
		}
		ret = StringUtils.defaultString(matcher.group(2)).trim(); //group 2 추출
		if (LOG.isDebugEnabled()) {
			LOG.info("## substringNextToLineFeed() > ret: "+ret);
		}		
		return ret;
	}

	
	/**
	 * 정규식 문자열의 배열을 인수로 받아서, OR조건으로 묶은 정규식 문자열을 리턴한다.
	 * 
	 * @param regexList 정규식 문자열 배열. 배열의 각 요소는 정규식의 OR조건으로 묶인다.
	 * @return
	 */
	public static String getRegexMergedString(List<String> regexList){
		if(regexList == null){ 
			return ""; 
		}
		int regexArrayLength = regexList.size();
		if(regexArrayLength == 0){
			return "";
		}
		String regex = null;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<regexArrayLength; i++){
			regex = regexList.get(i);
			sb.append(regex);
			if(i < (regexArrayLength - 1)){
				sb.append("|");
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * Map객체를 받아서 LogisOne 프로시저 메시지 형태 (XML문자열) 로 변환하여 리턴한다.
	 * 
	 * @param map
	 * @return
	 */
	public static String makeProcedureMsgString(Map<String,String> map){
		String key, value;
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String,String> e : map.entrySet()){
			key = e.getKey();
			value = e.getValue();
			sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
		}
		if (LOG.isDebugEnabled()) {
			LOG.info("StringUtil.makeProcedureMsgString() : "+sb.toString());
		}
		return sb.toString();
	}


	/**
	 * getParameter : XML 형태의 문자열 파라미터를 핸들링하는 함수
     *					     XML 형태의 문자열로부터 파라미터를 추출하거나 삽입하는 등의 처리를 한다.
     *                        
     * 		ex) StringUtil.getParameter('SELECT', RESULTMSG, 'BBB' , ''); 
     *           => XML 형태의 문자열 RESULTMSG로부터 BBB라는 항목의 값을 추출하고, 없을 경우 ''를 리턴한다.                      
	 * 
     * @param cmd				명령
     * @param data	 			XML 형태의 문자열
     * @param field				추출할 파라미터의 이름
     * @param defaultString	값이 없을 경우 대체할 문자열
     * @return          
	 */
	public static String getParameter(String cmd, String data, String field, String defaultString){
		if(cmd == null  || data == null  || field == null){ return ""; }
		if("".equals(cmd.trim()) || "".equals(data.trim()) || "".equals(field.trim())){ return ""; }
//		if(StringUtils.isBlank(cmd) || StringUtils.isBlank(data) || StringUtils.isBlank(field)){ return ""; }
		String  curData ="";
		int  startPos = 0;
		int  endPos = 0;	
		startPos = data.indexOf("<"+field+">");
		endPos = data.indexOf("</"+field+">");
//		if (LOG.isDebugEnabled()) {
//			LOG.info("@ startPos: "+startPos+", endPos: "+endPos);
//		}
		if((startPos >= 0 && endPos >= 0) && (startPos < endPos)){
			if("INSERT".equals(cmd) || "UPDATE".equals(cmd) || "SELECT".equals(cmd)){
				startPos = startPos + ("<"+field+">").length();
				curData  = data.substring(startPos ,endPos);
			}
			if("GET".equals(cmd) || "DEL".equals(cmd) || "SET".equals(cmd)){
				startPos = startPos + ("<"+field+">").length();
				curData  = data.substring(startPos ,endPos);
			}
			if("DEL".equals(cmd) || "SET".equals(cmd)){
				curData = data.replace("<"+field+">"+curData+"</"+field+">","");
			}
			if("SET".equals(cmd)){
				curData = data+"<"+field+">"+curData+"</"+field+">";
			}
		}
	    if(StringUtils.isBlank(curData)){ //fallback
	       if("INSERT".equals(cmd) || "SELECT".equals(cmd)){
	           curData = defaultString;
	       }else{
	           if("UPDATE".equals(cmd)){
	               curData = "NOTFOUND";
	           }else{
	               curData = "FAILURE";
	           }
	       }
	    }
	    return curData;			
	}
	

	
	/**
	 * 전화번호 형식에 맞게 formatting 한다.
	 * @param str
	 * @return
	 */
    public static java.lang.String toTelephoneNumberFormat(java.lang.String str)
    {
        int endNumberDigit = 4;
        int minNumberDigit = 7;
        if(isEmpty(str))
            return null;
        String origin = str.trim();
        int originLength = origin.length();
        String tempNumber;
        if(isNotDigit(origin))
        {
            java.lang.StringBuilder sb = new StringBuilder();
            for(int i = 0; i < originLength; i++)
                if(java.lang.Character.isDigit(origin.charAt(i)))
                    sb.append(origin.charAt(i));

            tempNumber = sb.toString();
        } else
        {
            tempNumber = origin;
        }
        int numberLength = tempNumber.length();
        if(numberLength < minNumberDigit)
            return tempNumber;
        java.lang.String firstNumber = "";
        java.lang.String secondNumber = "";
        java.lang.String thirdNumber = "";
        if(tempNumber.charAt(0) == '0')
        {
            if(tempNumber.charAt(1) == '2')
            {
                firstNumber = tempNumber.substring(0, 2);
                secondNumber = tempNumber.substring(2, numberLength - endNumberDigit);
                thirdNumber = tempNumber.substring(numberLength - endNumberDigit, numberLength);
            } else
            {
                firstNumber = tempNumber.substring(0, 3);
                secondNumber = tempNumber.substring(3, numberLength - endNumberDigit);
                thirdNumber = tempNumber.substring(numberLength - endNumberDigit, numberLength);
            }
            return (new StringBuilder()).append(firstNumber).append("-").append(secondNumber).append("-").append(thirdNumber).toString();
        } else
        {
            firstNumber = tempNumber.substring(0, numberLength - endNumberDigit);
            secondNumber = tempNumber.substring(numberLength - endNumberDigit, numberLength);
            return (new StringBuilder()).append(firstNumber).append("-").append(secondNumber).toString();
        }
    }
    
    /**
     * 숫자를 제외한 나머지 체크
     * @param number
     * @return
     */
    public static boolean isNotDigit(String number)
    {
        if(number == null)
            return false;
        for(int i = 0; i < number.length(); i++)
            if(!java.lang.Character.isDigit(number.charAt(i)))
                return true;

        return false;
    }    
}