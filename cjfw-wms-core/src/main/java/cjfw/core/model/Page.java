package cjfw.core.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : Page 기능을 위한 모델 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
@Data
public class Page<T> {
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int startRow;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int listCount;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean skipCount; // count쿼리 수행 여부
    private long totalCount; // skipCount가 true이면 -1
    private List<T> list;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int pageNum;  // 현재 페이지 번호
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int totalPages;  // 전체 페이지 수
}

