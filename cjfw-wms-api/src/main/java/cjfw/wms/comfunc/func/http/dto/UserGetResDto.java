package cjfw.wms.comfunc.func.http.dto;

import lombok.Data;

@Data
public class UserGetResDto {
	
	private String id;
	private String name;
	private String username;
	private String email;
	private Address address;
	
}
