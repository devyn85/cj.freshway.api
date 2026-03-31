package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
	
/**
 * 팝업 상품정보조회 Response DTO
 */
@Data
@Schema(description = "상품 정보 조회 Response DTO")
public class CmSkuInfoPopupResDto {
	
	@Schema(description = "고객사코드", example = "FW00")
    private String storerkey;

    @Schema(description = "상품코드", example = "193599")
    private String sku;

    @Schema(description = "기본 제품명")
    private String description;

    @Schema(description = "스타일코드")
    private String stylecode;

    @Schema(description = "스타일명")
    private String styledescr;

    @Schema(description = "컬러코드")
    private String colorcode;

    @Schema(description = "컬러명")
    private String colordescr;

    @Schema(description = "사이즈코드")
    private String sizecode;

    @Schema(description = "사이즈명")
    private String sizedescr;

    @Schema(description = "제품 브랜드")
    private String brandcode;

    @Schema(description = "브랜드명")
    private String branddescr;

    @Schema(description = "상품그룹")
    private String skugroup;

    @Schema(description = "상품유형")
    private String skutype;

    @Schema(description = "시리얼 관리 여부")
    private String serialyn;

    @Schema(description = "바코드1")
    private String barcode1;

    @Schema(description = "바코드2")
    private String barcode2;

    @Schema(description = "바코드3")
    private String barcode3;

    @Schema(description = "상품그룹 설명")
    private String skugroupdescr;

    @Schema(description = "상품유형 설명")
    private String skutypedescr;

    @Schema(description = "유효 기간")
    private String duration;

    @Schema(description = "시리얼 관리 여부 설명")
    private String serialyndescr;

    @Schema(description = "라벨 타입")
    private String labeltype;

    @Schema(description = "파일 ID")
    private String fileId;

    /** STORAGETYPE */
    @Schema(description = "STORAGETYPE")
    private String storagetype;

    /** STORAGETYPENM */
    @Schema(description = "STORAGETYPENM")
    private String storagetypenm;

    /** NETWEIGHT */
    @Schema(description = "NETWEIGHT")
    private String netweight;

    /** GROSSWEIGHT */
    @Schema(description = "GROSSWEIGHT")
    private String grossweight;

    /** BOXPERPLT */
    @Schema(description = "BOXPERPLT")
    private String boxperplt;

    /** SERIALTYPE */
    @Schema(description = "SERIALTYPE")
    private String serialtype;

    /** SERIALTYPENM */
    @Schema(description = "SERIALTYPENM")
    private String serialtypenm;

    /** LINE01 */
    @Schema(description = "LINE01")
    private String line01;

    /** LINE02 */
    @Schema(description = "LINE02")
    private String line02;

    /** BUYERKEY */
    @Schema(description = "BUYERKEY")
    private String buyerkey;

    /** BUYERNAME */
    @Schema(description = "BUYERNAME")
    private String buyername;

    /** USERID */
    @Schema(description = "USERID")
    private String userid;

    /** USERNM */
    @Schema(description = "USERNM")
    private String usernm;
}