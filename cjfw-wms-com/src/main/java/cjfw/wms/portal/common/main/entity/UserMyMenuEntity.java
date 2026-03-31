package cjfw.wms.portal.common.main.entity;

import lombok.Data;

/**
 * 즐겨찾기 Entity
 */
@Data
public class UserMyMenuEntity {
	//사용자ID
	private String userId;
	//메뉴id
	private String menuId;
	//메뉴명
    private String menuNm;
    //메뉴url
    private String menuUrl;
}