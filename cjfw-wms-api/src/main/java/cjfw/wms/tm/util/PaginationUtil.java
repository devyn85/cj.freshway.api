package cjfw.wms.tm.util;

import cjfw.core.model.Page;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.23
 * @description : 페이지네이션 유틸리티 클래스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.23 OhEunbeom      생성 </pre>
 */
public class PaginationUtil {

    /** 기본 페이지 번호 */
    private static final int DEFAULT_PAGE_NUM = 1;
    
    /** 기본 페이지당 항목 수 */
    private static final int DEFAULT_LIST_COUNT = 10;

    /**
     * @description : 페이지네이션을 위한 Page 객체를 생성합니다
     * @param pageNum 현재 페이지 번호 (1부터 시작)
     * @param listCount 페이지당 항목 수
     * @return Page 객체
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 OhEunbeom      생성 </pre>
     */
    public static <T> Page<T> createPage(int pageNum, int listCount) {
        Page<T> page = new Page<>();
        
        // 페이지 번호 검증 및 설정 (1 이상)
        int validPageNum = Math.max(DEFAULT_PAGE_NUM, pageNum);
        page.setPageNum(validPageNum);
        
        // 페이지당 항목 수 검증 및 설정 (10 이상)
        int validListCount = Math.max(DEFAULT_LIST_COUNT, listCount);
        page.setListCount(validListCount);
        
        // 시작 행 번호 계산: (pageNum - 1) * listCount
        int startRow = (validPageNum - 1) * validListCount;
        page.setStartRow(startRow);
        
        return page;
    }

    /**
     * @description : 페이지네이션을 위한 Page 객체를 생성합니다 (기본값 사용)
     * @return Page 객체 (기본값: pageNum=1, listCount=10)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 OhEunbeom      생성 </pre>
     */
    public static <T> Page<T> createPage() {
        return createPage(DEFAULT_PAGE_NUM, DEFAULT_LIST_COUNT);
    }

    /**
     * @description : 페이지네이션을 위한 Page 객체를 생성합니다 (페이지 번호만 지정)
     * @param pageNum 현재 페이지 번호 (1부터 시작)
     * @return Page 객체 (기본 listCount=10 사용)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 OhEunbeom      생성 </pre>
     */
    public static <T> Page<T> createPage(int pageNum) {
        return createPage(pageNum, DEFAULT_LIST_COUNT);
    }
}
