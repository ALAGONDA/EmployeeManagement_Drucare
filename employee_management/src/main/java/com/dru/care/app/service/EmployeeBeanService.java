package com.dru.care.app.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dru.care.app.bean.DeleteEmployeeReq;
import com.dru.care.app.bean.DeleteEmployeeReqs;
import com.dru.care.app.bean.FetchDepartmentDto;
import com.dru.care.app.bean.FetchEmployeeDto;
import com.dru.care.app.bean.FetchEmployeeReq;
import com.dru.care.app.bean.InsertEmployeeReqs;
import com.dru.care.app.bean.InsertEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReqs;
import com.itextpdf.text.DocumentException;

public interface EmployeeBeanService {

	Boolean insertEmployeeBeanUsingMap(InsertEmployeeReq insertEmployeeReq);

	Boolean insertEmployeeBeanUsingMapSqlParameter(InsertEmployeeReq insertEmployeeReq);

	Boolean insertEmployeeBeanUsingBeanProperty(InsertEmployeeReq insertEmployeeReq);

	Boolean updateEmployeeBeanUsingMap(UpdateEmployeeReq updateEmployeeReq);

	Boolean updateEmployeeBeanUsingMapSqlParameter(UpdateEmployeeReq updateEmployeeReq);

	Boolean updateEmployeeBeanUsingBeanProperty(UpdateEmployeeReq updateEmployeeReq);

	Boolean softDeleteEmployeeBeanUsingMap(DeleteEmployeeReq deleteEmployeeReq);

	Boolean softDeleteEmployeeBeanUsingMapSqlParameter(DeleteEmployeeReq deleteEmployeeReq);

	Boolean softDeleteEmployeeBeanUsingBeanParameter(DeleteEmployeeReq deleteEmployeeReq);

	Boolean insertEmployeeBeanUsingBatchWithCreateBatch(InsertEmployeeReqs insertEmployeeReq);

	Boolean updateEmployeeBeanUsingBatchWithCreateBatch(UpdateEmployeeReqs updateEmployeeReqs);

	Boolean softDeleteEmployeeBeanUsingBatchWithCreateBatch(DeleteEmployeeReqs deleteEmployeeReqs);

	Boolean insertEmployeeBeanUsingBatchWithMapSqlParameter(InsertEmployeeReqs listEmployeeReqs);

	Boolean updateEmployeeBeanUsingBatchWithMapSqlParameter(UpdateEmployeeReqs updateEmployeeReqs);

	Boolean softDeleteEmployeeBeanUsingBatchWithMapSqlParameter(DeleteEmployeeReqs deleteEmployeeReqs);

	Boolean insertEmployeeBeanUsingBatchWithMap(InsertEmployeeReqs listEmployeeReqs);

	Boolean updateEmployeeBeanUsingBatchWithMap(UpdateEmployeeReqs updateEmployeeReqs);

	Boolean softDeleteEmployeeBeanUsingBatchWithMap(DeleteEmployeeReqs deleteEmployeeReqs);

	Boolean insertEmployeeBeanUsingBatch(InsertEmployeeReqs insertEmployeeReq);

	Boolean updateEmployeeBeanUsingBatch(UpdateEmployeeReqs updateEmployeeReqs);

	Boolean softDeleteEmployeeBeanUsingBatch(DeleteEmployeeReqs deleteEmployeeReqs);

	Boolean insertAndUpdateEmployee(InsertEmployeeReqs insertEmployeeReq);

	Boolean insertEmployeeUsingJdbcTemplate(InsertEmployeeReqs insertEmployeeReq);

	Boolean updateEmployeeUsingJdbcTemplate(UpdateEmployeeReqs updateEmployeeReqs);

	Boolean insertEmployeeUsingNamed(InsertEmployeeReqs insertEmployeeReq);

	/*
	 * Insert Employee Details With KeyHolder Using JdbcTemplate
	 */
	Boolean insertEmployeeWithKeyHolderUsingJdbc(InsertEmployeeReq insertEmployeeReq);

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate
	 */
	Boolean insertEmployeeWithKeyHolderUsingNamed(InsertEmployeeReq insertEmployeeReq);

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate with
	 * Batch Update
	 */
	Boolean insertEmployeeUsingKeyHolderWithBatch(InsertEmployeeReqs insertEmployeeReq);

	/*
	 * Insert Employee Details With KeyHolder Using JdbcTemplate with Batch Update
	 */
	Boolean insertEmployeeUsingKeyHolder(InsertEmployeeReqs insertEmployeeReq);

	/*
	 * fetching employee details Using QueryForObject With NamedParameter JdbcTemplate
	 */
	FetchEmployeeDto fecthEmployeeDetailsUsingQueryForObjectWithNamed(
			FetchEmployeeReq fetchEmployeeReq);

	/*
	 * fetching employee details Using QueryForMap With NamedParameter JdbcTemplate
	 */
    FetchEmployeeDto fecthEmployeeDetailsUsingQueryForMapWithNamed(
			FetchEmployeeReq fetchEmployeeReq);

	/*
	 * fetching employee details Using QueryForList With NamedParameter JdbcTemplate
	 */
	List<FetchEmployeeDto> fecthEmployeeDetailsUsingQueryForListWithNamed(FetchEmployeeReq fetchEmployeeReq);

	/*
	 * Using Single EndPoint for insert and update employee details with KeyHolder using
	 * NamedParameterJdbcTemplate
	 */
	Boolean insertAndUpdateEmployeeWithDepartment(InsertEmployeeReqs insertEmployeeReqs);

	/*
	 * fetching employee details Using DynamicQuery With NamedParameter JdbcTemplate
	 */
	List<FetchEmployeeDto> fecthEmployeeDetailsUsingDynamicQuery(FetchEmployeeReq fetchEmployeeReq);

	/*
	 * fetching employee details Using Query With NamedParameter JdbcTemplate
	 */
	List<FetchEmployeeDto> fecthEmployeeDetailsUsingQuery(FetchEmployeeReq fetchEmployeeReq);

	/*
	 * fetching particular employee department details Using NamedParameter JdbcTemplate
	 */
	List<FetchEmployeeDto> fecthEmployeeWithDepartmentDetailsUsingQuery(FetchEmployeeReq fetchEmployeeReq);

	List<FetchDepartmentDto> fetchDepartmentWiseGenderCount(FetchEmployeeReq fetchEmployeeReq);

	byte[] generateEmployeePdfReport() throws DocumentException,IOException;
	
	Boolean insertEmployeeDetailsThroughExcelFile(MultipartFile file,String fileFormate) throws IOException;
	
	ByteArrayInputStream employeeDetailsExportToExcelFile() throws IOException;

}
