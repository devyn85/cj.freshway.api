package cjfw.wms.cm.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmMyMenuPopupReqDto;
import cjfw.wms.cm.dto.CmMyMenuPopupResDto;
import cjfw.wms.cm.entity.CmMyMenuEntity;
import cjfw.wms.common.extend.CommonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.11.20 
 * @description : 기준정보 > 기타기준정보 > 메뉴 즐겨찾기마스터 목록 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmMyMenuPopupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmMyMenuPopupService.";
	private transient static final String SERVICEID_INSERT = "insertMyMenu";
	private transient static final String SERVICEID_DELETE = "deleteMyMenu";

	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 메뉴즐겨찾기 팝업 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<CmMyMenuPopupResDto> getMyMenuPopupList(CommonDto reqDto) {
		// 검색 기준에 따른 쿼리 분류
		return commonDao.selectList(SERVICEID_PREFIX + "getFavoriteMenuList", reqDto);
	}
	
	/**
	 * @description : 메뉴즐겨찾기 팝업 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<CmMyMenuPopupResDto> getMyFavoriteMenuList(CommonDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMyFavoriteMenuList", reqDto);
		
	}
	
	/**
	 * @description : 메뉴즐겨찾기 팝업 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public String saveMyMenuPopupList(List<CmMyMenuPopupReqDto> cmMyMenuPopupReqDto) {
		// ***** 데이터 가공할 부분 ***** 
		// 1. 리스트의 순서대로 menu seq 지정
		// 2. 폴더 키값을 모두 지우고 F001 형태로 순서대로 INSERT (1번째 폴더 : F001, 2번째 폴더 : F002, ...)
		// 3. 폴더 하위에 있는 메뉴들 (children[]값) 키값 뒤에 붙은 값들 제거 (XXXXX_F001 -> XXXXX)
		// =>  11/21 변경 key 값에 progCd -> progNo 로 변경
		
		// insert 전에 모든 데이터 delete
		commonDao.delete(SERVICEID_PREFIX + SERVICEID_DELETE, cmMyMenuPopupReqDto.get(0));
		
		AtomicInteger menuSeq = new AtomicInteger(1);   // 메뉴 순서
		AtomicInteger folderSeq = new AtomicInteger(1); // 폴더 순서 (F001, F002, ...)
		
		for (CmMyMenuPopupReqDto myMenu : cmMyMenuPopupReqDto) {
			processNode(myMenu, null, menuSeq, folderSeq);
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	// Tree구조 재귀적으로 처리하여 폴더,메뉴 INSERT
	private void processNode(CmMyMenuPopupReqDto node, String parentFolderId, AtomicInteger menuSeq, AtomicInteger folderSeq) {
		if (node == null) return; 

		// 메뉴 순번 지정
		node.setMenuSeq(menuSeq.getAndIncrement());
		
		// userId 보정: DTO에 gUserId만 있는 경우를 대비
		if (node.getUserId() == null || node.getUserId().isEmpty()) {
			node.setUserId(node.getGUserId());
		}

		// 폴더 노드 처리
		if ("F".equals(node.getMenuType())) {
			// 폴더 생성
			String folderId = String.format("F%03d", folderSeq.getAndIncrement()); // F001, F002, ...
			node.setProgCd(folderId);
			node.setUprFolderId(parentFolderId); // 부모 폴더 ID 설정

			CmMyMenuEntity folderEntity = ModelMapperUtil.map(node, userContext, CmMyMenuEntity.class);
			folderEntity.setFolderNm(node.getTitle());
			commonDao.insert(SERVICEID_PREFIX + SERVICEID_INSERT, folderEntity);

			// 폴더 하위 자식 처리 (있으면 재귀)
			if (node.getChildren() != null && !node.getChildren().isEmpty()) {
				for (CmMyMenuPopupReqDto child : node.getChildren()) {
					// 부모의 gUserId를 자식에 보조로 전달
					if (child.getUserId() == null || child.getUserId().isEmpty()) {
						child.setUserId(node.getGUserId());
					}
					// 자식의 상위폴더는 현재 folderId
					child.setUprFolderId(folderId);

					// 재귀 호출 — 자식이 폴더면 또 폴더 처리, 메뉴면 메뉴로 insert
					processNode(child, folderId, menuSeq, folderSeq);
				}
			}

		} else {
			// 메뉴 노드 처리
			node.setUprFolderId(parentFolderId);

			CmMyMenuEntity menuEntity = ModelMapperUtil.map(node, userContext, CmMyMenuEntity.class);
			menuEntity.setUserId(node.getUserId());
			commonDao.insert(SERVICEID_PREFIX + SERVICEID_INSERT, menuEntity);
		}
	}

}