package cjfw.wms.tm.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmCalendarListReqDto;
import cjfw.wms.tm.dto.TmCalendarListResDto;
import cjfw.wms.tm.entity.TmCalendarEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.22 
 * @description : 휴일관리 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.22 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmCalendarService {

	private transient static final String SERVICEID_PREFIX = "tmCalendarService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 휴일관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.22 ParkJinWoo 생성
	 */
	public List<TmCalendarListResDto> getMasterList(TmCalendarListReqDto tmCalendarListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",tmCalendarListReqDto);
	}
	
	
	/**
	 * @description : 휴일관리 수정 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.22 ParkJinWoo 생성
	 */
	public String saveConfirm(TmCalendarListReqDto req) {
		if(req != null) {
		List<TmCalendarListResDto> list = req.getSaveList();
		for (var dto : list) {
			var entity = ModelMapperUtil.map(dto, userContext, TmCalendarEntity.class);
			if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
				commonDao.insert(SERVICEID_PREFIX +"calanderInsert", entity);
			} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX +"calanderUpdate", entity);
			} 
		}
		}
	
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * @description : 휴일관리 달력생성 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.24 ParkYoSep 생성
	 */
	public String saveCalendar(TmCalendarListReqDto req) {
	    if(req != null) {
		    List<TmCalendarEntity> calendarDataList = new ArrayList<>();
            int year = Integer.parseInt(req.getYy());

            // 요일 배열 선언 (1:일요일, 2:월요일 ... 에 맞게 인덱스 0은 비워둠)
            final String[] dayNames = {"", "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};

            for(var dcCode : req.getDcCodeList()) {
            	TmCalendarEntity dayData;
                req.setDcCode(dcCode);
                LocalDate date = LocalDate.of(year, 1, 1);
                while (date.getYear() == year) {
                    DayOfWeek dayOfWeek = date.getDayOfWeek();
                     // 요일 계산 (일요일:1 ~ 토요일:7)
                    int dayGbInt = (dayOfWeek.getValue() % 7) + 1;
                    boolean isSunday = (dayGbInt == 1);
                    
                    // 3. DTO 객체 생성
                    dayData = TmCalendarEntity.builder()
            		                          .yy(req.getYy())
            		                          .mm(String.format("%02d", date.getMonthValue()))
            		                          .dd(String.format("%02d", date.getDayOfMonth()))
            		                          .dayGb(String.valueOf(dayGbInt))
            		                          .calendarId("TRSP")
            		                          .restYn(isSunday ? "Y" : "N")
            		                          .restDesc(dayNames[dayGbInt])
            		                          .workYn(isSunday ? "N" : "Y") // 일요일이 아니면 우선 'Y'로 설정
            		                          .dcCode(dcCode)
            		                          .addWho(userContext.getUserId())
            		                          .editWho(userContext.getUserId())
            		                          .build();

                    calendarDataList.add(dayData);
                    date = date.plusDays(1); 
                    }
            }  
            // 1) 200건씩 끊어서 insert
            List<TmCalendarEntity> insertList = new ArrayList<>();
            final int chunkSize = 200;
            for (int i = 0; i < calendarDataList.size(); i++) {
            	TmCalendarEntity row = calendarDataList.get(i);
                insertList.add(row);
                if (insertList.size() == chunkSize || i == calendarDataList.size() - 1) {
                    commonDao.insert(SERVICEID_PREFIX + "centerCalanderInsert", insertList);
                    insertList.clear();
                }
            }

            
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
