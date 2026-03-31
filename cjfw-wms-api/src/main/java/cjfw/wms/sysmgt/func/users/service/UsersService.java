/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.CryptoUtil;
import cjfw.wms.sysmgt.func.users.dto.UsersGetReqDto;
import cjfw.wms.sysmgt.func.users.dto.UsersGetResDto;
import cjfw.wms.sysmgt.func.users.dto.UsersSaveReqDto;
import cjfw.wms.sysmgt.func.users.dto.UsersSaveResDto;
import cjfw.wms.sysmgt.func.users.entity.UsersEntity;
import cjfw.wms.sysmgt.func.users.entity.UsersEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersService {

	private static final String INIT_PWD = "1q2w3e4r!@";

	private final CommonDao commonDao;

	private final UserContext userContext;

	/**
	 * 사용자 리스트 조회
	 */
	public List<UsersGetResDto> getUserList(UsersGetReqDto userGetReqDto) {
		return commonDao.selectList("usersService.getUserList", userGetReqDto);
	}

	/**
	 * 사용자 정보 저장(CUD)
	 */
	public UsersSaveResDto saveUsers(UsersSaveReqDto userSaveReqDto) {

		List<UsersSaveReqDto.User> users = userSaveReqDto.getUsers();

		if(users != null){
			for(UsersSaveReqDto.User user: users){
				UsersEntity userEntity = UsersEntityMapper.INSTANCE.saveUsersDtoToEntity(user, userContext);
				log.info("{}", userEntity);
				if((CanalFrameConstants.INSERT).equals(user.getRowStatus())) {
					// Client 에서 보내주지 않음으로 SHA256 2회 처리
					userEntity.setUserPwd(CryptoUtil.SHA256(CryptoUtil.SHA256(INIT_PWD)));
					int duplCnt  = commonDao.getTotalCount("usersService.getDuplUserCnt",userEntity);
					if(duplCnt > 0) {
						//중복된 "사용자정보"(이)가 존재합니다
						throw new UserHandleException("MSG.COM.VAL.067", new String[]{"LABEL.COM.USR.INF"});
					}
					commonDao.insert("usersService.insertUsers", userEntity);
					commonDao.insert("usersService.insertSecurityRules", userEntity);  //TB_CF_SECURITY_RULES 생성
					commonDao.insert("usersService.insertAuthority", userEntity);  //기본권한 생성
				} else if((CanalFrameConstants.UPDATE).equals(user.getRowStatus())) {
					//비밀번호 초기화 시
					if( "03".equals(userEntity.getUserStatus())) {
						// Client 에서 보내주지 않음으로 SHA256 2회 처리
						userEntity.setUserPwd(CryptoUtil.SHA256(CryptoUtil.SHA256(INIT_PWD)));
						userEntity.setUserStatus("01");
					}
					commonDao.update("usersService.updateUsers", userEntity);
				} else if((CanalFrameConstants.DELETE).equals(user.getRowStatus())) {
					commonDao.delete("usersService.deleteSecurityRules", userEntity); //TB_CF_SECURITY_RULES 삭제
					commonDao.delete("usersService.deleteAuthority", userEntity); //권한 삭제
					commonDao.delete("usersService.deleteUsers", userEntity);
				}
			}
		}
		return UsersSaveResDto.builder().initPwd(INIT_PWD).build();
	}

}