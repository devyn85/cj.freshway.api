package cjfw.wms.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.SystemException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.sys.dto.SysProgramReqDto;
import cjfw.wms.sys.dto.SysProgramResDto;
import cjfw.wms.sys.entity.SysProgramEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : ADMIN > 시스템운영 > 프로그램 정보를 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysProgramService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "sysProgramService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 프로그램 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<SysProgramResDto> getProgramList(SysProgramReqDto sysProgramReqDto) {
		List<SysProgramResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getProgramList", sysProgramReqDto);

		// Tree 참조용 컬럼 추가(검색결과 처리용)
		for(SysProgramResDto sysProgramResDto: list){
			String upperProgNo = sysProgramResDto.getProgNo().substring(0, sysProgramResDto.getProgNo().length() - 2);
			sysProgramResDto.setRefUpperProgNo(upperProgNo);
		}
		
		return list;
	}
	
	/**
	 * @description : 프로그램 저장(CUD)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveProgram(List<SysProgramReqDto> programs) {
		if (null != programs) {
			for (SysProgramReqDto program : programs) {	
				SysProgramEntity sysProgramEntity = ModelMapperUtil.map(program, userContext, SysProgramEntity.class);
				if ((CanalFrameConstants.INSERT).equals(program.getRowStatus())) {
					commonDao.selectOne(SERVICEID_PREFIX +"insertProgram", sysProgramEntity);
					if(sysProgramEntity.getErrCode() != 0){
						throw new SystemException(new UserHandleException(""+"에러코드 : "+ sysProgramEntity.getErrCode() + ", 에러메세지 : " + sysProgramEntity.getErrMsg()));
					}
				} else if ((CanalFrameConstants.UPDATE).equals(program.getRowStatus())) {
					commonDao.selectOne(SERVICEID_PREFIX +"updateProgram", sysProgramEntity);
					if(sysProgramEntity.getErrCode() != 0){
						throw new SystemException(new UserHandleException(""+"에러코드 : "+ sysProgramEntity.getErrCode() + ", 에러메세지 : " + sysProgramEntity.getErrMsg()));
					}
				} else if ((CanalFrameConstants.DELETE).equals(program.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteProgram", sysProgramEntity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
