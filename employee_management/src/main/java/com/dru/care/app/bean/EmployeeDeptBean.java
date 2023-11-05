package com.dru.care.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDeptBean {
	@JsonIgnore
	@ApiModelProperty(dataType = "java.lang.Integer", required = false)
	private Integer empDepId;
	@ApiModelProperty(dataType = "java.lang.Integer", required = true)
    private Integer empId;
	@ApiModelProperty(dataType = "java.lang.Integer", required = true)
    private Integer depId;
	@ApiModelProperty(dataType = "java.lang.Integer", required = false)
    private Integer orgId;

}
