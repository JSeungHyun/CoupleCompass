package com.CoupleCompass;

import com.CoupleCompass.dao.RegionDao;
import com.CoupleCompass.dto.RegionDto;
import com.google.gson.Gson;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class CoupleCompassApplicationTests {

    private final String baseUrl = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*000000";
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private RegionDao regionDao;

    @Test
    void contextLoads() {
    }

    /**
     * 구/동 데이터 삽입
     */
    @Test
    void insertGuDong() throws ParseException {
        // 구/동 데이터 가져오기
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseUrl, String.class);
        String jsonString = responseEntity.getBody(); // 구/동 데이터 (가공 전)

        // String -> Parser -> To JsonObject
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

        JSONArray jsonArray = (JSONArray) jsonObject.get("regcodes");
        List<RegionsDto> regionsDtos = new ArrayList<>();

        for (Object object : jsonArray) {
            JSONObject jsonRegion = (JSONObject) object;
            RegionsDto regionDto = new Gson().fromJson(jsonRegion.toJSONString(), RegionsDto.class);
            regionsDtos.add(regionDto);
        }
        System.out.println(regionsDtos);

        // 구/동 데이터 삽입하기
        AtomicInteger i = new AtomicInteger(0);
        AtomicInteger j = new AtomicInteger(0);

        List<RegionDto> insertRegionDtos = new ArrayList<>();

        regionsDtos.forEach(regionDto -> {
            String[] regionNameSplit = regionDto.getName().split(" ");
            int regionNameLength = regionNameSplit.length;

            if (regionNameLength > 1) { // 시/군/구 데이터
                RegionDto dto = RegionDto.builder()
                        .city(regionNameSplit[0])
                        .gu(regionNameSplit[1])
                        .cityCode(String.format("%02d", i.get()))
                        .guCode(String.format("%02d_%02d", i.get(), j.getAndIncrement()))
                        .build();

                insertRegionDtos.add(dto);
            } else { // 시/군 데이터
                i.incrementAndGet();
                j.set(1);
            }
        });
        System.out.println(insertRegionDtos);
        regionDao.insertRegions(insertRegionDtos);
    }
}
