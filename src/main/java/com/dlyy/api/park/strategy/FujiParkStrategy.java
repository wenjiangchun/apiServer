package com.dlyy.api.park.strategy;

import com.dlyy.api.common.util.SproutDateUtils;
import com.dlyy.api.park.entity.ParkSystem;
import com.dlyy.api.park.exception.ParkException;
import com.dlyy.api.park.strategy.dto.LotReservation;
import com.dlyy.api.park.strategy.dto.Park;
import com.dlyy.api.web.vo.RestCode;
import com.dlyy.api.web.vo.RestResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FujiParkStrategy implements ParkStrategy {

    private ParkSystem parkSystem;

    private RestTemplate restTemplate;

    public FujiParkStrategy(ParkSystem parkSystem, RestTemplate restTemplate) {
        this.parkSystem = parkSystem;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getParkBrand() {
        return "富士停车场";
    }

    @Override
    public List<RestResult> getAvailableList(String departmentNo) {
        //TODO 后续完善停车位信息 根据企业获取指定停车场及停车位信息
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Park> getAllParkList() {
        String remoteRequestPath = parkSystem.getUrl() + "Park/GetByCustom";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode params = objectMapper.createObjectNode();
        params.put("PageSize", 1000);
        params.put("CurrentPage", 1);
        params.put("TotalCount", 1000);
        HttpEntity<String> entity = new HttpEntity<>(params.toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(remoteRequestPath, entity, String.class);
        List<Park> parkWrapperList = new ArrayList<>();
        try {
            Map<String, Object> tt = objectMapper.readValue(responseEntity.getBody(), Map.class);
            //封装停车场数据
            if (tt.containsKey("State")) {
                Map<String, Object> state = (Map<String, Object>) tt.get("State");
                if ((boolean) state.get("IsSucess")) {
                    //处理停车场数据
                    List<Map<String, Object>> parkList = (List<Map<String, Object>>) tt.get("Records");
                    parkList.forEach(p -> {
                        Park wrapper = new Park();
                        wrapper.setOldId(p.get("ID").toString());
                        wrapper.setName((String) p.get("Name"));
                        wrapper.setLotCount((int) p.get("LotCount"));
                        wrapper.setFreeCount((int) p.get("LotFree"));
                        wrapper.setRemark((String) p.get("Remark"));
                        parkWrapperList.add(wrapper);
                    });
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return parkWrapperList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Park getPark() {
        try {
            String remoteRequestPath = parkSystem.getUrl() + "Park/Get/" + parkSystem.getOldId();
            ObjectMapper objectMapper = new ObjectMapper();
            Park parkWrapper = new Park();
            parkWrapper.setParkId(parkSystem.getParkId());
            parkWrapper.setOldId(parkSystem.getOldId());
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(remoteRequestPath, String.class);
            Map<String, Object> tt = objectMapper.readValue(responseEntity.getBody(), Map.class);
            //封装停车场数据
            if (tt.containsKey("State") && ((boolean) ((Map<String, Object>) tt.get("State")).get("IsSucess"))) {
                //处理停车场数据
                Map<String, Object> park = (Map<String, Object>) tt.get("Model");
                parkWrapper.setName((String) park.get("Name"));
                parkWrapper.setLotCount((int) park.get("LotCount"));
                parkWrapper.setFreeCount((int) park.get("LotFree"));
                parkWrapper.setRemark((String) park.get("Remark"));
                parkWrapper.getQueryParams().put("Gid", park.get("Gid"));
                parkWrapper.getQueryParams().put("Rid", park.get("Rid"));
            }
            return parkWrapper;
        } catch (Exception e) {
            throw new ParkException(RestCode.FAILURE_OTHER.getCodeValue(), "请求错误");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public LotReservation reserveLotNo(String lotNo, String plateNo, Date startTime, Date endTime, String mobile, String orderFrom) {
        try {
            String remoteRequestPath = parkSystem.getUrl() + "LotReservationService/LotReservationAdd";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode params = objectMapper.createObjectNode();
            params.put("ParkNo", "");
            //params.put("LotNo", lotNo);
            params.put("LotNo", "");
            params.put("PlateNo", plateNo);
            params.put("BeginTime", SproutDateUtils.format(startTime));
            params.put("EndTime", SproutDateUtils.format(endTime));
            params.put("MinuteOfTimeout", 5);
            params.put("OperTime", SproutDateUtils.format(new Date()));
            params.put("OrderFrom", orderFrom);
            Park park = getPark();
            params.put("Gid", park.getQueryParams().get("Gid").toString());
            params.put("Rid", park.getQueryParams().get("Rid").toString());
            //params.put("Rid","a01a79a3-f42e-4055-8e5e-63c3565919bd");
            params.put("IsLegal", true);
            HttpEntity<String> entity = new HttpEntity<>(params.toString(), headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(remoteRequestPath, entity, String.class);
            Map<String, Object> tt = objectMapper.readValue(responseEntity.getBody(), Map.class);
            if (tt.containsKey("State") && ((boolean) ((Map<String, Object>) tt.get("State")).get("IsSucess"))) {
                //处理停车场数据
                Map<String, Object> model = (Map<String, Object>) tt.get("Model");
                LotReservation lotReservation = new LotReservation();
                lotReservation.setLotReservationId(model.get("ID").toString());
                lotReservation.setLotNo(model.get("LotNo").toString());
                lotReservation.setPlateNo(model.get("Plate").toString());
                lotReservation.setOrderId(model.get("OrderId").toString());
                lotReservation.setStartTime(SproutDateUtils.parseDate(model.get("BeginTime").toString(), SproutDateUtils.DATE_TIME_PATTERN_1));
                lotReservation.setEndTime(SproutDateUtils.parseDate(model.get("EndTime").toString(), SproutDateUtils.DATE_TIME_PATTERN_1));
                lotReservation.setCreateTime(formatDate(model.get("OperTime").toString()));
                lotReservation.setState(Integer.parseInt(model.get("State").toString()));
                return lotReservation;
            } else {
                String error = "请求错误";
                if (tt.containsKey("State") && !((boolean) ((Map<String, Object>) tt.get("State")).get("IsSucess"))) {
                    error = ((Map<String, Object>) tt.get("State")).get("Describe").toString();
                }
                throw new ParkException(RestCode.FAILURE_OTHER.getCodeValue(), error);
            }
        } catch (Exception e) {
            throw new ParkException(RestCode.FAILURE_OTHER.getCodeValue(), e.getMessage());
        }
    }

    private Date formatDate(String time) {
        String STANDARD_DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS";
        SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATE_FORMAT_UTC);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date dateTime = sdf.parse(time);
            return SproutDateUtils.addHours(dateTime, -8);
        } catch (ParseException e) {
            //TODO 暂不处理
        }
        return null;
    }
}
