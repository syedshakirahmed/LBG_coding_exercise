package com.lloyds.service;

import com.lloyds.dto.AtmResponse;
import com.lloyds.dto.ResponseBean;
import com.lloyds.pojo.ATM;
import com.lloyds.util.CommonUtil;
import com.lloyds.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AtmService {

    public ResponseBean getATMByIdentification(String url, String identification) {
        log.error("Get the designated ATM information( url: {}, identification: {})", url, identification);
        List<ATM> atmList = getAllATMs(url);
        if (atmList != null && atmList.size() > 0) {
            List<ATM> targetATMList = atmList.stream()
                    .filter(o ->
                        o.getIdentification().equals(identification)
                    )
                    .collect(Collectors.toList());

            ATM atm = targetATMList.stream()
                    .findFirst().orElse(null);

            if (atm != null) {
                ResponseBean responseBean = ResponseBean.success(atm);
                log.error("Get the designated ATM information success, response body: {}", CommonUtil.toJson(responseBean));
                return responseBean;
            } else {
                log.error("Get the designated ATM information failed, resources not found");
                return ResponseBean.error(404, "Get designated ATM information failed, resources not found");
            }
        } else {
            log.error("Get the designated ATM information failed, incorrect request url");
            return ResponseBean.error(-1, "Get designated ATM information failed, please check the input url is correct");
        }

    }

    public List<ATM> getAllATMs(String url) {
        String response = null;
        try {
            response = HttpUtil.sendGetRequest(url, null, null, null);
        } catch (Exception e) {
            log.error("Get designated ATM information failed, please check the input url is correct");
            return null;
        }
        AtmResponse atmResponse = CommonUtil.parseToObject(response, AtmResponse.class);
        if (atmResponse.getData() != null && atmResponse.getData().size() > 0) {
            List<ATM> atmList = atmResponse
                    .getData()
                    .get(0)
                    .getBrand()
                    .get(0)
                    .getATM()
                    .stream()
                    .collect(Collectors.toList());
            return atmList;
        }

        return null;
    }
}
