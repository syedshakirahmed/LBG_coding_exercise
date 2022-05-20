package com.lloyds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBean {

    private int code;
    private boolean success;
    private String message;
    private Object data;

    public static ResponseBean success(Object data) {
        return new ResponseBean(0, true, null, data);
    }

    public static ResponseBean success() {
        return new ResponseBean(0, true, null, null);
    }

    public static ResponseBean error(String message) {
        return new ResponseBean(-1, false, message, null);
    }
    public static ResponseBean error(int code,String message) {
        return new ResponseBean(code, false, message, null);
    }

}

