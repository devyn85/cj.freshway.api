/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.commoncode.service;

import java.util.List;
import java.util.Random;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlAsyncTemplateGetReqDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlGetResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonCodeAsyncService{
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** Random */
	private Random random = new Random();  // Compliant

	@Async
	public void getCommonCdAsync(int idx, CodeDtlAsyncTemplateGetReqDto.CodeGrp codegrp)
	{
		// 넘겨받은 공통그룹코드를 기준으로 조회
		List<CodeDtlGetResDto> list = commonDao.selectList("commonCodeService.getCommonCdList", codegrp);

		try {
			//Random rand = new Random();
			int rNum = this.random.nextInt(10); // 0 이상 10 이하의 정수를 반환 
			Thread.sleep(1000*rNum);	 // 조회 케이스별 응답시간을 랜덤하게 모의하여 배정
		} catch (InterruptedException e) {
			
		}
		
		// 아래 메타정보들이 콘솔로그에 실제 출력되는 부분을 확인
		log.info("## idx :{} ## grp code {} ## res size {}",idx, codegrp.getComGrpCd(),list.size());
	}
}