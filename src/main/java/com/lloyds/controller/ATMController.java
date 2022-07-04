package com.lloyds.controller;


import com.lloyds.dto.ResponseBean;
import com.lloyds.service.AtmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/atmdetails")
@Slf4j
public class ATMController {
    private final AtmService atmService;

    public ATMController (AtmService atmService) {
        this.atmService = atmService;
    }


    @Operation(summary = "Get ATM details by identification ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ATM Details found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseBean.class)) }),
            @ApiResponse(responseCode = "400", description = "Incorrect input url ",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Required ATM Details not found",
                    content = @Content) })
    @GetMapping()
    @ResponseBody
    public ResponseBean getATMDetails(@RequestParam String url, @RequestParam String identification) {
        ResponseBean responseBean = atmService.getATMDetails(url, identification);
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
