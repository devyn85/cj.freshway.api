# cjfw-wms-api

## [20250926]센터권한 및 권한고객사 권한 쿼리 적용
- 요건1.센터권한.ONS 요청으로 CM_USER_AUTHORITY 사용하는 쿼리는 성능향상을 위해 JOIN에서 EXISTS 방식으로 변경 

```sh
      WHERE 1=1
        AND MG_MODIFYLOG.DCCODE    = #{fixdccode} /*센터*/
        AND MG_MODIFYLOG.STORERKEY = #{gStorerkey} /*고객사*/
        <!-- 창고.다중검색(필요 시) -->
        <if test='gOrganizeMulti != null and gOrganizeMulti.length > 0'>
            AND MG_MODIFYLOG.ORGANIZE IN
            <foreach collection="gOrganizeMulti" item="code" open="(" close=")" separator=",">
              #{code}
            </foreach>
        </if>         
	    <!-- START.권한센터 -->
	    AND EXISTS (SELECT 1
                      FROM CM_USER_AUTHORITY 
                     WHERE CM_USER_AUTHORITY.DCCODE    = MG_MODIFYLOG.DCCODE       
                       AND CM_USER_AUTHORITY.STORERKEY = MG_MODIFYLOG.STORERKEY    
                       AND CM_USER_AUTHORITY.ORGANIZE  = MG_MODIFYLOG.ORGANIZE     
                       AND CM_USER_AUTHORITY.AREA      = MG_MODIFYLOG.AREA         
                       AND CM_USER_AUTHORITY.STORERKEY = #{gStorerkey}
                       AND CM_USER_AUTHORITY.USERID    = #{gUserId}
                       AND CM_USER_AUTHORITY.DCCODE    = #{fixdccode}
                       AND CM_USER_AUTHORITY.AREA      = #{gArea}   
			           <!-- 창고.다중검색 -->
			           <if test='gOrganizeMulti != null and gOrganizeMulti.length > 0'>
				           AND CM_USER_AUTHORITY.ORGANIZE IN
				           <foreach collection="gOrganizeMulti" item="code" open="(" close=")" separator=",">
				             #{code}
				           </foreach>
			           </if>                       
                   )
	    <!-- END.권한센터	-->  
```
    
- 요건2.고객사권한.ONS 요청으로 CM_USER_AUTHORITY_STORER 사용하는 쿼리는 성능향상을 위해 JOIN에서 EXISTS 방식으로 변경 

```sh
	   WHERE 1=1
        AND ST_STOCK_SERIALINFOHISTO.DCCODE = #{gStorerkey}  /*센터*/
        <!-- START.권한고객사 -->
        AND EXISTS( SELECT 1
                      FROM CM_USER_AUTHORITY_STORER
                     WHERE CM_USER_AUTHORITY_STORER.USERID    = #{gUserId}
                       AND CM_USER_AUTHORITY_STORER.STORERKEY = #{gStorerkey}
                       AND CM_USER_AUTHORITY_STORER.STORERKEY = ST_STOCK_SERIALINFOHISTO.STORERKEY)
        <!-- END.권한고객사 -->  
```
    
- 예제
	
```sh
MgModifyLogService.sqlx
```

## [20250925]상품.다중OR검색 및 일반 다중검색
- 요건1.상품팝업/거래처팝업은 5000개 검색 후 업무에서 '다중OR검색' 로 처리(20250924)
	- ParamArrayManagerAop AOP에서 @MultiSearch 참조하여 다중데이터 생성되고
     1000개씩 IN이 OR로 변환되어 쿼리가 수행
- 요건2.REGEXP_SUBSTR는 사용가능으로 협의했으나 튜닝과정에서 실행계획이 변경될 소지 존재하여 지양하고 '다중검색'으로 처리(20250924)
 

- 상품.다중OR검색(1/2)
	- 업무 dto에 변수 추가(Multi라고 붙여주고 List<List<String>> 형태로 정의)
	- 거래처도 같은 방법으로 처리
	
```sh
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 
```

- 상품.다중OR검색(2/2)
	- 쿼리수정
	
```sh
        <!-- START.상품.다중OR검색 -->
        <if test="skuMulti != null and skuMulti != '' ">
			<trim prefix="AND (" suffix=")" prefixOverrides="OR">
				<foreach collection="skuMulti" item="item">
					OR MS_SKU.SKU IN
					<foreach collection="item" item="code" open="(" close=")" separator=",">
						#{code} 	 
		      		</foreach>
		    	</foreach>
		  	</trim>			                                    
        </if>
        <!-- END.상품.다중OR검색 -->	
```

- zone.다중검색(1/2)
	- 업무 dto에 변수 추가(Multi라고 붙여주고 List<String> 형태로 정의)
	- 상품/거래처 제외하고 나머지 조회조건의 팝업은 같은 방법으로 처리
	
```sh
    /** zone(다중검색) */
	@MultiSearch
    @Schema(description = "zone-다중검색")
    private List<String> zoneMulti; 
    
    /** 창고(다중검색) */
	@MultiSearch
    @Schema(description = "창고-다중검색")
    private List<String> organizeMulti;     
```

- zone.다중검색(2/2)
	- 쿼리수정
	
```sh
        <!-- zone.다중검색 -->
        <if test="zoneMulti != null and zoneMulti != ''">
	       AND MS_LOCATION.ZONE IN
	       <foreach collection="zoneMulti" item="code" open="(" close=")" separator=",">
	         #{code}
	       </foreach>   
        </if>
```

```sh
        <!-- 창고.다중검색 -->
        <if test='organizeMulti != null and organizeMulti.length > 0'>
            AND ST_STOCK.ORGANIZE IN
            <foreach collection="organizeMulti" item="code" open="(" close=")" separator=",">
              #{code}
            </foreach>
        </if>
```

- 예제
	
```sh
StStockService.sqlx
```