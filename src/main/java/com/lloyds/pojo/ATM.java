package com.lloyds.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lloyds.dto.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ATM {
    private String Identification;
    private List<Currency> SupportedCurrencies;
    private Address Location;
}
