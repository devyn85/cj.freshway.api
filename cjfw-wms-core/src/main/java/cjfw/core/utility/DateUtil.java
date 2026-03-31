package cjfw.core.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : DateUtil 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 * 2024.01.11        suah.jo            util.Date->time.LocalDateTime로 전환 작업 진행(Java8이상부터 LocalDateTime 권고)
 */
public class DateUtil {
	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	private static final String DATE_TYPE = "yyyyMMdd";
	private static final String DATETIME_TYPE = "yyyyMMddHHmmss";
	private static final String DATETIME_YMD= "yyyy-MM-dd";
	private static final String DATETIME_YMDHMS= "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * @description : DateUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private DateUtil() {}
	
	/**
	 * 
	 * @description : parseDate_YMD 기능을 구현한 Method
	 * 				  yyyy-MM-dd 형태의 string 을 localDate로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            util.Date->time.LocalDate로 업데이트
	 */
	public static LocalDateTime getStringToDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMD);
		LocalDateTime localDate;
        try {
            localDate = LocalDateTime.parse(dateStr, formatter);
            log.info("localDate : {}", localDate);

            return localDate;
        } catch (DateTimeParseException e) {
            // 파싱에 실패한 경우 처리
            log.error("에러발생", e);
        }
        return null;
	}
	/**
	 * 
	 * @description : parseDateTime 기능을 구현한 Method
	 * 				  yyyy-MM-dd HH:mm:ss 형태의 string 을 LocalDate로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            util.Date->time.LocalDate로 업데이트
	 */
	public static LocalDateTime getStringToDateTime(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMDHMS);
		LocalDateTime localDate;
        try {
            localDate = LocalDateTime.parse(dateStr, formatter);
            log.info("localDate : {}", localDate);
            return localDate;
        } catch (DateTimeParseException e) {
            // 파싱에 실패한 경우 처리
            log.error("에러발생", e);
        }
        return null;
	}

	/**
	 * 
	 * @description : parseDate_YMDHMS 기능을 구현한 Method
	 * 				  yyyyMMddHHmmss 형태의 string 을 localDate로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            util.Date->time.LocalDate로 업데이트
	 */
	public static LocalDateTime  getStringToDateTime_YMDHMS(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_TYPE);
		LocalDateTime localDate;
        try {
            localDate = LocalDateTime.parse(dateStr, formatter);
            log.info("localDate : {}", localDate);
            return localDate;
        } catch (DateTimeParseException e) {
            // 파싱에 실패한 경우 처리
            log.error("에러발생", e);
        }
        return null;
	}
	

	/**
	 * 
	 * @description : parseDate 기능을 구현한 Method
	 * 				  입력된 pattern 형태의 string 을 Date로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            util.Date->time.LocalDate로 업데이트
	 */
	public static LocalDateTime getStringToDatePattern(String dateStr, String pattern) {
		LocalDateTime localDate = LocalDateTime.now(); // 기본값으로 현재 날짜 설정
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	        try {
	            localDate = LocalDateTime.parse(dateStr, formatter);
	        } catch (DateTimeParseException e) {
	            // 파싱에 실패한 경우 처리
	            log.error("DateParser.parseDate().DateTimeParseException : ", e);
	        }
	        return localDate;
	}

//	/**
//	 * 
//	 * @description : parseCalendar 기능을 구현한 Method
//	 * 				  임력된 문자열의 날짜 형식을 원하는 패턴으로 반환
//	 * @issues      :
//	 * -----------------------------------------------------------
//	 * DATE              AUTHOR             MAJOR_ISSUE
//	 * -----------------------------------------------------------
//	 * 2023.10.22        sungyeon.lee       생성
//	 */
//	public static Calendar parseCalendar(String dateStr, String pattern) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(parseDate(dateStr, pattern));
//		return cal;
//	}

	/**
	 * 
	 * @description : formatString 기능을 구현한 Method
	 * 				  Date 를 yyyy-MM-dd HH:mm:ss 의 string 형태로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            util.Date->time.LocalDate로 업데이트
	 */
	public static String getDateTimeToString(LocalDateTime date) {
		 String pattern = DATETIME_YMDHMS;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		 return date.format(formatter);
	}

	/**
	 * 
	 * @description : formatDateString 기능을 구현한 Method
	 * 				  Date 를 yyyy-MM-dd 의 string 형태로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getDateToString(LocalDateTime date) {
		String pattern = DATETIME_YMD;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	    return date.format(formatter);
	}

	/**
	 * 
	 * @description : getDateTime 기능을 구현한 Method
	 * 				  현재 시간을 주어진 패턴으로 반환(ex. YYYYMMDDHHmmssSSS)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getDateToStringPatern(String pattern) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		 return LocalDateTime.now().format(formatter);
	}

//	/**
//	 * 
//	 * @description : getOperationTime 기능을 구현한 Method
//	 * 				  현재 시간에서 주어진 분 만큼 차이 시간 반환
//	 * 				  currentTime - 연산 기준 날짜의 문자열 String(ex. yyyyMMdd)
//	 * @issues      :
//	 * -----------------------------------------------------------
//	 * DATE              AUTHOR             MAJOR_ISSUE
//	 * -----------------------------------------------------------
//	 * 2023.10.22        sungyeon.lee       생성
//	 */
//	public static Date getOperationTime(String currentTime, int minute) {
//
//		Date curDate = parseDate(currentTime, DATETIME_TYPE);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(curDate);
//		cal.add(Calendar.MINUTE, minute);
//		//연산된 날자를 생성.
//		Date operationTime = cal.getTime();
//
//		return operationTime;
//	}
	
	/**
	 * 
	 * @description : plusMinutes 기능을 구현한 Method
	 * 				  분 더하기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDatePlusMinutes(String currentTimeStr, int minute) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMDHMS);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.plusMinutes(minute);
	}
	
	/**
	 * 
	 * @description : plusHours 기능을 구현한 Method
	 * 				  시간 더하기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDatePlusHours(String currentTimeStr, int hour) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMDHMS);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.plusHours(hour);
	} 
	
	/**
	 * 
	 * @description : plusSeconds 기능을 구현한 Method
	 * 				  시간 더하기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDatePlusSeconds(String currentTimeStr, int second) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMDHMS);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.plusSeconds(second);
	} 
	/**
	 * 
	 * @description : plusDays 기능을 구현한 Method
	 * 				  일 더하기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDatePlusDays(String currentTimeStr, int day) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMD);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.plusDays(day);
	}
	
	/**
	 * 
	 * @description : plusMonths 기능을 구현한 Method
	 * 				  달 더하기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDatePlusMonths(String currentTimeStr, int month) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMD);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.plusMonths(month);
	} 
	
	/**
	 * 
	 * @description : plusYears 기능을 구현한 Method
	 * 				  년 더하기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDatePlusYears(String currentTimeStr, int year) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMD);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.plusYears(year);
	} 
	
	
	/**
	 * 
	 * @description : minusMinutes 기능을 구현한 Method
	 * 				  분 빼기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDateMinusMinutes(String currentTimeStr, int minute) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMDHMS);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.minusMinutes(minute);
	}
	
	/**
	 * 
	 * @description : minusHours 기능을 구현한 Method
	 * 				  시간 빼기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDateMinusHours(String currentTimeStr, int hour) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMDHMS);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.minusHours(hour);
	} 
	
	/**
	 * 
	 * @description : minusSeconds 기능을 구현한 Method
	 * 				  초 빼기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDateMinusSeconds(String currentTimeStr, int second) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMDHMS);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.minusSeconds(second);
	} 
	/**
	 * 
	 * @description : plusDays 기능을 구현한 Method
	 * 				  일 더하기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDateMinusDays(String currentTimeStr, int day) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMD);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.minusDays(day);
	}
	
	/**
	 * 
	 * @description : minusMonths 기능을 구현한 Method
	 * 				  달 더하기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDateMinusMonths(String currentTimeStr, int month) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMD);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.minusMonths(month);
	} 
	
	/**
	 * 
	 * @description : plusYears 기능을 구현한 Method
	 * 				  년 더하기
	 * 				  currentDate - 연산 기준 날짜 문자열 String
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static LocalDateTime getDateMinusYears(String currentTimeStr, int year) {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMD);
	      LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
	        return currentTime.minusYears(year);
	} 
	
	
	/**
	 * 
	 * @description : getYesterday 기능을 구현한 Method
	 * 				  주어진 날짜의 어제 날짜 반환
	 * 				  currentDate - 연산 기준 날짜 문자열 String(ex. yyyyMMdd)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            update
	 */
	public static LocalDateTime getYesterday(String currentTimeStr) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_YMDHMS);
		 LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
		  return currentTime.minusDays(1);

	}

//	/**
//	 * 
//	 * @description : getOperationDay 기능을 구현한 Method
//	 * 				  주어진 날짜에 입력한 만큼 빼거나 더한 날짜 반환
//	 * 				  currentDate - 연산 기준 날짜 문자열 String(ex. yyyyMMdd)
//	 * @issues      :
//	 * -----------------------------------------------------------
//	 * DATE              AUTHOR             MAJOR_ISSUE
//	 * -----------------------------------------------------------
//	 * 2023.10.22        sungyeon.lee       생성
//	 */
//	public static LocalDate getOperationDay(String currentDate, int amount) {
//		Date curDate = parseDate(currentDate, DATE_TYPE);
//		
//		System.out.println(curDate);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(curDate);
//		cal.add(Calendar.DAY_OF_MONTH, amount);
//		//연산된 날자를 생성. 
//		Date operationDate = cal.getTime();
//
//		return operationDate;
//	}

	/**
	 * 
	 * @description : getDiffDays 기능을 구현한 Method
	 * 				  두 날짜 사이의 날짜를 배열로 반환
	 * 				  ex) fromData = 20130301
	 * 					  toDate = 20130305
	 * 					  return = [20130301, 20130302, 20130303, 20130304, 20130305]
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            변경
	 */
	public static String[] getDiffDays(String fromDatestr, String toDatestr) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TYPE);
		 LocalDate fromDate = LocalDate.parse(fromDatestr, formatter);
		 LocalDate toDate = LocalDate.parse(toDatestr, formatter);
		 List<String> dateList = fromDate.datesUntil(toDate.plusDays(1))
	                .map(date -> date.format(formatter))
	                .collect(Collectors.toList());

	        return dateList.toArray(new String[0]);
	}

	/**
	 * 
	 * @description : getDiffDayCount 기능을 구현한 Method
	 * 				  두 날짜 사이의 일수를 반환
	 * 				  ex) fromData = 20130301
	 * 					  toDate = 20130305
	 * 					  return = 4
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            변경
	 */
	public static int getDiffDayCount(String fromDatestr, String toDatestr) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TYPE);
		 LocalDate fromDate = LocalDate.parse(fromDatestr, formatter);
		 LocalDate toDate = LocalDate.parse(toDatestr, formatter);
		 return (int) ChronoUnit.DAYS.between(fromDate, toDate);
	}

	/**
	 * 
	 * @description : getTodayDay 기능을 구현한 Method
	 * 				  오늘의 요일 숫자 형태로 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            변경
	 */
	public static int getTodayDay() {
		 LocalDate currentDate = LocalDate.now();
		 DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		 return dayOfWeek.getValue();
	}
	
	/**
	 * 
	 * @description : getToday 기능을 구현한 Method
	 * 				  현재 날짜를 주어진 dateformat 문자열 형식에 맞게 만들어 반환
	 * 				  ex) 날짜까지 : yyyyMMdd
	 * 					  초단위시간까지 : yyyyMMddHHmmss
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 * 2024.01.11        suah.jo            변경
	 */
	public static String getToday(String dateFormat) {
		LocalDateTime currentDate = LocalDateTime.now();
		 return currentDate.format(DateTimeFormatter.ofPattern(dateFormat));
	}

	/**
	 * 
	 * @description : getDateDay 기능을 구현한 Method
	 * 				  해당 일자의 요일 숫자 형태로 반환
	 * 				  기준 - 일요일:1, 월요일:2, 화요일:3, 수요일:4, 목요일:5, 금요일:6, 토요일:7
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static int getDateDay(String dateStr) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TYPE);
		 LocalDate date = LocalDate.parse(dateStr, formatter);
		  return date.getDayOfWeek().getValue();
	}
	/**
	 * 
	 * @description : isBefore 기능을 구현한 Method
	 * 				  이전일자 여부 
	 * 				  true (from < to), false (시작 >= 종료)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.12        suah.jo            생성
	 */
	public static boolean isBefore(String fromstr, String tostr) {
	    LocalDate from = LocalDate.parse(fromstr);
        LocalDate to = LocalDate.parse(tostr);
        
        return from.isBefore(to);
	}
	/**
	 * 
	 * @description : isAfter 기능을 구현한 Method
	 * 				  이후일자 여부 
	 * 				  true (from > to), false (시작 <= 종료)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.12        suah.jo            생성
	 */
	public static boolean isAfter(String fromstr, String tostr) {
	    LocalDate from = LocalDate.parse(fromstr);
        LocalDate to = LocalDate.parse(tostr);
        
        return from.isAfter(to);
	}
	/**
	 * 
	 * @description : isSameOrBefore 기능을 구현한 Method
	 * 				  이전일자 여부  (동일포함)
	 * 				  true (from <= to), false (시작 > 종료)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.12        suah.jo            생성
	 */
	public static boolean isSameOrBefore(String fromstr, String tostr) {
	    LocalDate from = LocalDate.parse(fromstr);
        LocalDate to = LocalDate.parse(tostr);
        
        return from.isEqual(to) || from.isBefore(to);
	}
	/**
	 * 
	 * @description : isSameOrAfter 기능을 구현한 Method
	 * 				  이후일자 여부 (동일포함)
	 * 				  true (from >= to), false (시작 < 종료)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.12        suah.jo            생성
	 */
	public static boolean isSameOrAfter(String fromstr, String tostr) {
	    LocalDate from = LocalDate.parse(fromstr);
        LocalDate to = LocalDate.parse(tostr);
        
        return from.isEqual(to) || from.isAfter(to);
	}
	/**
	 * 
	 * @description : convertTimeZone 기능을 구현한 Method
	 * 				  string형태의 date에서 현재 타임존에서 다른 타임존으로 변경한 값
	 * 				  반환 타입은 타임존이 적용되어야하므로 ZonedDateTime
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR           	  MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.12        suah.jo            생성
	 */
	public static ZonedDateTime convertTimeZone(String dateTimeStr, String fromTimeZone, String toTimeZone) {
	    // 입력된 문자열을 LocalDateTime으로 파싱
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // fromTimeZone으로 ZonedDateTime 객체 생성
        ZonedDateTime fromZonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of(fromTimeZone));

        // toTimeZone으로 타임존 변경
        ZonedDateTime toZonedDateTime = fromZonedDateTime.withZoneSameInstant(ZoneId.of(toTimeZone));

        return toZonedDateTime;
	}	
	/**
	 * 
	 * @description : getDayOfWeek 기능을 구현한 Method
	 * 				  특정 일의 요일 구하기
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static String getDayOfWeek(String dateStr) {
		  // 입력된 문자열을 LocalDate로 파싱
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        
        //한글 번역 작업 추가
        Map<String, String> dayOfWeekMap = new HashMap<>();
        dayOfWeekMap.put("MONDAY", "월");
        dayOfWeekMap.put("TUESDAY", "화");
        dayOfWeekMap.put("WEDNESDAY", "수");
        dayOfWeekMap.put("THURSDAY", "목");
        dayOfWeekMap.put("FRIDAY", "금");
        dayOfWeekMap.put("SATURDAY", "토");
        dayOfWeekMap.put("SUNDAY", "일");
        
        // DayOfWeek 열거형을 통해 요일 얻기
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 요일 문자열로 반환
        return dayOfWeekMap.get(dayOfWeek.toString());
	}
	/**
	 * 
	 * @description : getDayOfWeekName 기능을 구현한 Method
	 * 				  숫자로 요일 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static String getDayOfWeekName(int dayOfWeek) {
        String[] dayNm = new String[]{" ", "일", "월", "화", "수", "목", "금", "토"};
        return dayNm[dayOfWeek];
    }
	/**
	 * 
	 * @description : getLastDayOfMonth 기능을 구현한 Method
	 * 				  특정 월의 마지막날짜 구하기
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static int getLastDayOfMonth(int year, int month) {
	    // YearMonth 객체를 통해 특정 연도와 월의 마지막 날짜 얻기
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
	}
	/**
	 * 
	 * @description : getWeekNumber 기능을 구현한 Method
	 * 				  특정 날짜가 속한 년도의 주차 구하기
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static int getWeekNumber(String dateStr) {
	     // 입력된 문자열을 LocalDate로 파싱
        LocalDate date = LocalDate.parse(dateStr);

        // WeekFields를 사용하여 ISO 주차 규칙 적용
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfYear()); // ex)1월1일이 토요일인 경우 1주차로 오픈 

        return weekNumber;
	}
	/**
	 * 
	 * @description : getMonthWeekNumber 기능을 구현한 Method
	 * 				  특정 날짜가 속한 월의 주차 구하기
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static int getMonthWeekNumber(String dateStr) {
		 LocalDate date = LocalDate.parse(dateStr);

	     WeekFields weekFields = WeekFields.of(Locale.getDefault());
	     int weekNumber = date.get(weekFields.weekOfMonth());

	     return weekNumber;
	}
	/**
	 * 
	 * @description : convertStringToDayOfWeek 기능을 구현한 Method
	 * 				  월, 화, 수~ 한글 요일을 DayOfWeek으로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static DayOfWeek convertStringToDayOfWeek(String dayOfWeekStr) {
		 switch (dayOfWeekStr.toLowerCase()) {
         case "월": return DayOfWeek.MONDAY;
         case "화": return DayOfWeek.TUESDAY;
         case "수": return DayOfWeek.WEDNESDAY;
         case "목": return DayOfWeek.THURSDAY;
         case "금": return DayOfWeek.FRIDAY;
         case "토": return DayOfWeek.SATURDAY;
         case "일": return DayOfWeek.SUNDAY;
         default: throw new IllegalArgumentException("Invalid day of week: " + dayOfWeekStr);
     }
	}
	/**
	 * 
	 * @description : WeekdayInWeek 기능을 구현한 Method
	 * 				  특정 날짜 주차의 특정 요일의 일자 구하기
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.11        suah.jo            생성
	 */
	public static String getWeekdayInWeek(String dateStr, String dayOfWeekStr) {
        LocalDate date = LocalDate.parse(dateStr);

        // 사용자로부터 받은 문자열을 DayOfWeek 상수로 변환
        DayOfWeek targetDay = convertStringToDayOfWeek(dayOfWeekStr);

        // 주의 시작을 월요일로 설정
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        // 특정 요일로 조정
        LocalDate resultDate = startOfWeek.with(TemporalAdjusters.nextOrSame(targetDay));

        return resultDate.format(DateTimeFormatter.ofPattern(DATETIME_YMD));
	}
	/** 
	 * 
	 * @description : getDateRange 기능을 구현한 Method
	 * 				  입력받은 두 날짜("2023-03-02"형식의 String)차이 만큼의 날짜리스트를 List<String> 형식으로 반환.
	 * 			      (시작일과 종료일 포함).
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2024.01.14        suah.jo            생성
	 */
	 public static List<String> getDateRange(String startDateStr, String endDateStr) {
	        List<String> dateList = new ArrayList<>();
	        LocalDate startDate = LocalDate.parse(startDateStr);
	        LocalDate endDate = LocalDate.parse(endDateStr);

	        if (startDate.isAfter(endDate)) {
	            throw new IllegalArgumentException("시작일은 종료일보다 이전이어야 합니다.");
	        }

	        LocalDate currentDate = startDate;
	        while (!currentDate.isAfter(endDate)) {
	            dateList.add(currentDate.toString());
	            currentDate = currentDate.plusDays(1);
	        }
	        return dateList;
	    }

	/**
	 * 입력된 문자열이 지정한 날짜 형식에 맞는지 여부를 반환한다.
	 *
	 * @param dateStr 검사할 문자열
	 * @param pattern 날짜 포맷 예: "yyyyMMdd"
	 * @return 유효한 날짜 형식이면 true, 아니면 false
	 */
	public static boolean isValidDate(String dateStr, String pattern) {
		if (dateStr == null || !dateStr.matches("\\d{8}") || pattern == null) {
			return false;
		}

		// 미래연도 체크 안하고 싶을 경우 해당 부분 삭제 하면 됨.
		int year = Integer.parseInt(dateStr.substring(0, 4));
		if (year > 2100) return false;

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);  // 엄격한 파싱
		try {
			sdf.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	

}
