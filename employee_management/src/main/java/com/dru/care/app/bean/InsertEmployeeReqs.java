package com.dru.care.app.bean;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "All attributes of employee data")
public class InsertEmployeeReqs {
	
	 @ApiModelProperty(value ="Every Client Have Unique OrgId",dataType = "java.lang.Integer",required = true,example ="120")
     private Integer orgId;
     private List<InsertEmployeeBean> insertEmployeeBeans;
}
