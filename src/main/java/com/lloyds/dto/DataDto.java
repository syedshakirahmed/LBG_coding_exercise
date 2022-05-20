package com.lloyds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lloyds.pojo.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto {
    private List<Brand> Brand;
}
