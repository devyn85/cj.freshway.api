package cjfw.wms.tm.tmap;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.core.utility.ContextUtil;
import cjfw.wms.tm.dto.TmTmapFullAddrGeoResDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TmTmapService {
    private static final String TMAP_URL = ContextUtil.getProperty("spring.tmap.url");
    private static final String TMAP_APPKEY = ContextUtil.getProperty("spring.tmap.appkey");
    private static final String TMAP_PATH = "/geo/fullAddrGeo";
    private static final int CONN_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 30000;
    private RestTemplate restTemplate;

    /**
     * <매칭 구분 코드>
     * M11
     * - 법정동 정매칭
     * - 법정동 코드 + 지번이 모두 일치
     */
    private static final String MATCH_FLAG_M11 = "M11";

    /**
     * M21
     * - 행정동 정매칭
     * - 행정동 코드 + 지번이 모두 일치
     */
    private static final String MATCH_FLAG_M21 = "M21";

    /**
     * N51
     * - 새(도로명) 주소 정매칭입니다.
     * - 새(도로명) 주소 도로명이 일치하고 건물의 주번/부번이 모두 일치
     */
    private static final String NEWMATCH_FLAG_N51 = "N51";

    @PostConstruct
    public void init() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(CONN_TIMEOUT); // 30초 연결 타임아웃
        requestFactory.setReadTimeout(READ_TIMEOUT); // 120초 읽기 타임아웃

        this.restTemplate = new RestTemplate(requestFactory);

        // RestTemplate의 MessageConverter 설정
        this.restTemplate.getMessageConverters().forEach(converter -> {
            if (converter instanceof MappingJackson2HttpMessageConverter messageConverter) {
                ObjectMapper converterObjectMapper =  messageConverter.getObjectMapper();
                converterObjectMapper.findAndRegisterModules();
                converterObjectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            }
        });
    }

    public Coordinates getCoordinates(String address) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("appkey", TMAP_APPKEY);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            String url = TMAP_URL + TMAP_PATH;
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            String requestUrl = UriComponentsBuilder.fromUriString(url)
                    .queryParam("version", "1")
                    .queryParam("page", "1")
                    .queryParam("coordType", "WGS84GEO")
                    .queryParam("fullAddr", address)
                    .queryParam("format", "json")
                    .build()
                    .toUriString();

            ResponseEntity<TmTmapFullAddrGeoResDto> response = restTemplate.exchange(
                    requestUrl,
                    HttpMethod.GET,
                    entity,
                    TmTmapFullAddrGeoResDto.class
            );

            if (response.getBody() == null) return null;
            if (response.getBody().getCoordinateInfo() == null ||
                    response.getBody().getCoordinateInfo().getCoordinate() == null ||
                    response.getBody().getCoordinateInfo().getCoordinate().length == 0) return null;

            TmTmapFullAddrGeoResDto.Coordinate[] coordData = response.getBody().getCoordinateInfo().getCoordinate();
            for (TmTmapFullAddrGeoResDto.Coordinate coord : coordData) {
                if (MATCH_FLAG_M11.equals(coord.getMatchFlag()) ||
                        MATCH_FLAG_M21.equals(coord.getMatchFlag())) {
                    return Coordinates.builder()
                            .latitude(coord.getLat())
                            .longitude(coord.getLon())
                            .build();
                } else if (NEWMATCH_FLAG_N51.equals(coord.getNewMatchFlag())) {
                    return Coordinates.builder()
                            .latitude(coord.getNewLat())
                            .longitude(coord.getNewLon())
                            .build();
                }
            }
        } catch (Exception e) {
            log.error("Failed to call tmap api fullAddrGeo : {}", e.getMessage(), e);
        }
        return null;
    }
}
