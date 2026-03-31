package cjfw.core.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : TimeZoneUtil 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
public class TimeZoneUtil {

	/**
	 * 
	 * @description : TimeZoneUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	private TimeZoneUtil() {}
	
	/**
	 * 
	 * @description : nowFromZone 기능을 구현한 Method
	 * 				  TimeZone에 해당하는 현재시각의 LocalDateTime을 반환
	 * 				  timezone - 타임존(ex. Asia/Seoul)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
    public static LocalDateTime nowFromZone(String timezone) {
        if(StringUtil.isEmpty(timezone)){
            return ZonedDateTime.now().toLocalDateTime();
        }
        return ZonedDateTime.now(ZoneId.of(timezone)).toLocalDateTime();
    }

    /**
     * 
     * @description : nowFromZone 기능을 구현한 Method
     * 				  TimeZone에 해당하는 현재시각을 문자열형태로 패턴에 맞게 반환
     * 				  timezone - 타임존(ex. Asia/Seoul)
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public static String nowFromZone(String timezone, String pattern){
        LocalDateTime ldt = nowFromZone(timezone);
        if(StringUtil.isEmpty(pattern)){
            return ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        return ldt.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 
     * @description : changeZonedDateTime 기능을 구현한 Method
     * 				  소스 타임존 -> 목표 타임존으로 타임존 변경
     * 
     * 				  dateTime - 변경 DateTime(ex. 2021-10-15 09:30:00)
     * 				  pattern - dateTime의 패턴, 년월일시분초가 모두 포함되어야 함(ex. yyyy-MM-dd HH:mm:ss)
     * 				  fromTimeZone - 변경 소스 타임존
     * 				  toTimeZone - 변경 목표 타임존
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    private static ZonedDateTime changeZonedDateTime(String dateTime, String pattern, String fromTimeZone, String toTimeZone){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern + " z");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime + " " + fromTimeZone, dateTimeFormatter);
        return zonedDateTime.withZoneSameInstant(ZoneId.of(toTimeZone));
    }

    /**
     * 
     * @description : changeTimeZone 기능을 구현한 Method
     * 				  소스 타임존 -> 목표 타임존으로 타임존 변경
     * 
     * 				  dateTime - 변경 DateTime(ex. 2021-10-15 09:30:00)
     * 				  pattern - dateTime의 패턴, 년월일시분초가 모두 포함되어야 함(ex. yyyy-MM-dd HH:mm:ss)
     * 				  fromTimeZone - 변경 소스 타임존
     * 				  toTimeZone - 변경 목표 타임존
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public static String changeTimeZone(String dateTime, String pattern, String fromTimeZone, String toTimeZone){
        ZonedDateTime zonedDateTime = changeZonedDateTime(dateTime, pattern, fromTimeZone, toTimeZone);
        return zonedDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 
     * @description : changeTimeZoneFromSys 기능을 구현한 Method
     * 				  SystemDefault 타임존 -> 목표 타임존으로 변경
     * 
     * 				  dateTime - 변경 DateTime(ex. 2021-10-15 09:30:00)
     *				  pattern - dateTime의 패턴, 년월일시분초가 모두 포함되어야 함(ex. yyyy-MM-dd HH:mm:ss)
     *				  toTimeZone - 변경 목표 타임존
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public static String changeTimeZoneFromSys(String dateTime, String pattern, String toTimeZone){
        ZonedDateTime zonedDateTime = changeZonedDateTime(dateTime, pattern, ZoneId.systemDefault().toString(), toTimeZone);
        return zonedDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 
     * @description : changeTimeZoneToSys 기능을 구현한 Method
     * 				  소스 타임존 -> SystemDefault 타임존으로 변경
     * 
     * 				  dateTime - 변경 DateTime(ex. 2021-10-15 09:30:00)
     * 				  pattern - dateTime의 패턴, 년월일시분초가 모두 포함되어야 함(ex. yyyy-MM-dd HH:mm:ss)
     * 				  fromTimeZone - 변경 소스 타임존
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public static String changeTimeZoneToSys(String dateTime, String pattern, String fromTimeZone){
        ZonedDateTime zonedDateTime = changeZonedDateTime(dateTime, pattern, fromTimeZone, ZoneId.systemDefault().toString());
        return zonedDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 테스트용 메인 메소드
     * @param args
     * @throws Exception
    public static void main(String[] args) throws Exception{

        // 1. 시스템 디폴트 타임존 조회
        System.out.println(ZoneId.systemDefault());

        // 2. String 패턴 조회
        System.out.println(nowFromZone("UTC", "yyyy-MM-dd a KK:mm:ss"));

        // 3. ZonedDateTime 테스트
        System.out.println("------ ZonedDateTime 테스트 --------");
        String test = changeTimeZone("2021-10-15 09:30:00", "yyyy-MM-dd HH:mm:ss", "Asia/Calcutta", "Asia/Seoul");
        System.out.println(test);
        test = changeTimeZoneFromSys("2021-10-15 13:00:00", "yyyy-MM-dd HH:mm:ss", "UTC");
        System.out.println(test);
        test = changeTimeZoneToSys("093000-20211015", "HHmmss-yyyyMMdd", "UTC");
        System.out.println(test);
    }
     */
}
