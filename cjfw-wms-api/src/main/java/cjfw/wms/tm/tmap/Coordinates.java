package cjfw.wms.tm.tmap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinates {
    private String latitude;
    private String longitude;
}
