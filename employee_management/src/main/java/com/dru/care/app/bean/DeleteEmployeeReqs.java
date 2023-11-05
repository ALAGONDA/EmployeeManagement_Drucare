package com.dru.care.app.bean;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteEmployeeReqs {
	@ApiModelProperty(dataType = "java.lang.Integer", required = true)
    private Integer orgId;
	private List<DeleteEmployeeBean> deleteEmployeeBeans;

}
