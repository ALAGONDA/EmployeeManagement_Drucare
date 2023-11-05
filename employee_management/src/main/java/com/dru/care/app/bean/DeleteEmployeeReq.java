package com.dru.care.app.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteEmployeeReq {

	@ApiModelProperty(dataType = "java.lang.Long", required = true)
    private Long empId;

}
