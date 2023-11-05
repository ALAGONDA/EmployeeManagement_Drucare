package com.dru.care.app.bean;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeReqs {
	@ApiModelProperty(dataType = "java.lang.Integer", required = true)
     private Integer orgId;
     private List<UpdateEmployeeBean> updateEmployeeBeans;

}
