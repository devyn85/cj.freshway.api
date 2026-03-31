package cjfw.core.utility.masking;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : MaskingTypeEnum 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
public enum MaskingTypeEnum {
	NAME(0),
	ID(1),
	PHONE(2),
	MAIL(3),
	BIRTH(4),
	CARD(5),
	CARD_VALID_DATE(6),
	ACCOUNT(7),
	IP(8),
	RRN(9),
	BIZ_NO(10),
	DRIVER_LICENSE(11),
	PASSPORT_NO(12),
	OLD_ADDR(13),
	NEW_ADDR(14),
	ADDR_DTL(15),
	OVERSEE_PHONE(16),
	ALL(17),
	ADDR(18);
	
	/**
	 * 열거자 저장 변수
	 */
	private final int value;
	
	/**
	 * 
	 * @description : MaskingTypeEnum의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
    private MaskingTypeEnum (int value) {
        this.value = value;
    }
    
    /**
     * 
     * @description : value 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public int value() {
        return value;
    }
}