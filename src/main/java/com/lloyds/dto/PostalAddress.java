package com.lloyds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostalAddress {
    private List<String> AddressLine;
    private String StreetName;
    private String TownName;
    private List<String> CountrySubDivision;
    private String Country;
    private String PostCode;
}
