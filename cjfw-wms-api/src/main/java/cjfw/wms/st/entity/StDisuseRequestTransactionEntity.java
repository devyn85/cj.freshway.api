package cjfw.wms.st.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * StTransactionSnEntity
 *
 * @author AutoGen
 */
@Schema(description = "StDisuseRequestTransactionEntity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StDisuseRequestTransactionEntity {
    @Schema(description = "�ĺ���")
    private Long id;

    @Schema(description = "�̸�")
    private String name;
}
