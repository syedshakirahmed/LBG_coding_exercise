package com.lloyds.controller;


import com.lloyds.dto.AtmResponse;
import com.lloyds.dto.ResponseBean;
import com.lloyds.pojo.ATM;
import com.lloyds.service.AtmService;
import com.lloyds.util.CommonUtil;
import com.lloyds.util.HttpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ATMController {
    private final AtmService atmService;

    public ATMController (AtmService atmService) {
        this.atmService = atmService;
    }


    @Operation(summary = "Get a designated ATM information by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ATM information found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseBean.class)) }),
            @ApiResponse(responseCode = "400", description = "input url is incorrect",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Designated ATM information not found",
                    content = @Content) })
    @GetMapping("/atm")
    @ResponseBody
    public ResponseBean getATMbyId(@RequestParam String url, @RequestParam String identification) {
        ResponseBean responseBean = atmService.getATMByIdentification(url, identification);
        if (responseBean.isSuccess()) {
            return responseBean;
        } else if (responseBean.getCode() == 404) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, responseBean.getMessage()
            );
        } else{
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, responseBean.getMessage()
            );
        }
    }
}
