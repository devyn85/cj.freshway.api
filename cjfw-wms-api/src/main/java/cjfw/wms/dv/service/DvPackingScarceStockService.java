package cjfw.wms.dv.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.dv.dto.DvPackingScarceStockResDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.10
 * @description : 부족분리스트 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.10 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
@Tag(name = "DvPackingScarceStockService Service", description = "DvPackingScarceStockService ����Ͻ� ���� ����")
public class DvPackingScarceStockService {

	private final CommonDao commonDao;

	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DvPackingScarceStockService.class.getSimpleName()) + ".";

	/** @description : 부족분리스트 목록 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.10 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public  <T> List<DvPackingScarceStockResDto> getDvPackingScarceStockList( T reqDto) {

		return commonDao.selectList(SERVICEID_PREFIX + "getDvPackingScarceStockList" , reqDto);
	}

}
