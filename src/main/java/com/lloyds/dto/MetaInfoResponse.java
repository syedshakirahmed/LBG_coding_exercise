package com.lloyds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaInfoResponse {
    private String LastUpdated;
    private Integer totalResults;
    private String Agreement;
    private String License;
    private String TermsOfUse;
}
