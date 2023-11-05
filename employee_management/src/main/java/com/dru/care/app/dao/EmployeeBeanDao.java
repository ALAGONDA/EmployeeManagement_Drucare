package com.dru.care.app.dao;

import java.util.List;
import java.util.Map;

import com.dru.care.app.bean.DeleteEmployeeReq;
import com.dru.care.app.bean.DeleteEmployeeReqs;
import com.dru.care.app.bean.EmployeeDeptBean;
import com.dru.care.app.bean.FetchEmployeeReq;
import com.dru.care.app.bean.InsertEmployeeBean;
import com.dru.care.app.bean.InsertEmployeeReqs;
import com.dru.care.app.bean.InsertPatientBean;
import com.dru.care.app.bean.UpdateEmployeeBean;
import com.dru.care.app.bean.InsertEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReqs;

public interface EmployeeBeanDao {

	Boolean insertEmployeeBeanUsingMap(InsertEmployeeReq insertEmployeeReq);

	Boolean insertEmployeeBeanUsingMapSqlParameter(InsertEmployeeReq insertEmployeeReq);

	Boolean insertEmployeeBeanUsingBeanProperty(InsertEmployeeReq insertEmployeeReq);

	Boolean updateEmployeeBeanUsingMap(UpdateEmployeeReq updateEmployeeReq);

	Boolean updateEmployeeBeanUsingMapSqlParameter(UpdateEmployeeReq updateEmployeeReq);

	Boolean updateEmployeeBeanUsingBeanProperty(UpdateEmployeeReq updateEmployeeReq);

	Boolean softDeleteEmployeeBeanUsingMap(DeleteEmployeeReq deleteEmployeeReq);

	Boolean softDeleteEmployeeBeanUsingMapSqlParameter(DeleteEmployeeReq deleteEmployeeReq);

	Boolean softDeleteEmployeeBeanUsingBeanParameter(DeleteEmployeeReq deleteEmployeeReq);

	Boolean softDeleteEmployeeBeanUsingBatchWithCreateBatch(DeleteEmployeeReqs deleteEmployeeReqs);

	Boolean insertEmployeeBeanUsingBatchWithMapSqlParameter(List<InsertEmployeeBean> listEmployeeReqs);

	Boolean updateEmployeeBeanUsingBatchWithMapSqlParameter(UpdateEmployeeReqs updateEmployeeReqs);

	Boolean softDeleteEmployeeBeanUsingBatchWithMapSqlParameter(DeleteEmployeeReqs deleteEmployeeReqs);

	Boolean insertEmployeeBeanUsingBatchWithMap(List<InsertEmployeeBean> listEmployeeReqs);

	Boolean updateEmployeeBeanUsingBatchWithMap(UpdateEmployeeReqs updateEmployeeReqs);

	Boolean softDeleteEmployeeBeanUsingBatchWithMap(DeleteEmployeeReqs deleteEmployeeReqs);

	Boolean insertEmployeeBeanUsingBatch(InsertEmployeeReqs insertEmployeeReq);

	Boolean updateEmployeeBeanUsingBatch(UpdateEmployeeReqs updateEmployeeReqs);

	Boolean softDeleteEmployeeBeanUsingBatch(DeleteEmployeeReqs deleteEmployeeReqs);

	Boolean insertEmployee(List<InsertEmployeeBean> saveList);

	Boolean updateEmployee(List<InsertEmployeeBean> updateList);

	Boolean insertEmployeeUsingJdbcTemplate(InsertEmployeeReqs insertEmployeeReq);

	Boolean updateEmployeeUsingJdbcTemplate(UpdateEmployeeReqs updateEmployeeReqs);

	Boolean insertEmployeeUsingNamedParameter(List<InsertEmployeeBean> listInsertUpdateEmployeeBeans);

	Boolean insertEmployeeBeanUsingBatchWithCreateBatch(List<InsertEmployeeBean> listInsertUpdateEmployeeBeans);

	Boolean updateEmployeeBeanUsingBatchWithCreateBatch(List<UpdateEmployeeBean> listInsertUpdateEmployeeBeans);

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
	Boolean insertEmployeeUsingKeyHolderWithBatch(List<InsertEmployeeBean> listInsertEmployeeBeans);

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate with
	 * Batch Update
	 */
	Boolean insertEmployeeUsingKeyHolder(InsertEmployeeReqs insertEmployeeReq);

	/*
	 * fetching employee details Using QueryForObject With NamedParameter
	 * JdbcTemplate
	 */
	FetchEmployeeReq fecthEmployeeDetailsUsingQueryForObjectWithNamed(FetchEmployeeReq fetchEmployeeReq);

	/*
	 * fetching employee details Using QueryForMap With NamedParameter JdbcTemplate
	 */
	Map<String, Object> fecthEmployeeDetailsUsingQueryForMapWithNamed(FetchEmployeeReq fetchEmployeeReq);

	/*
	 * fetching employee details Using QueryForList With NamedParameter JdbcTemplate
	 */
	List<Map<String, Object>> fecthEmployeeDetailsUsingQueryForListWithNamed(FetchEmployeeReq fetchEmployeeReq);

	Long insertEmployee(InsertEmployeeBean insertEmployeeBeanObj);

	Boolean insertEmpDept(Long empId, List<EmployeeDeptBean> saveEmpDeptList);

	Boolean updateEmpDept(List<EmployeeDeptBean> updateEmpDeptList);

	Boolean updateEmployee(InsertEmployeeBean insertEmployeeBeanObj);

	/*
	 * fetching employee details Using DynamicQuery With NamedParameter JdbcTemplate
	 */
	List<Map<String, Object>> fecthEmployeeDetailsUsingDynamicQuery(FetchEmployeeReq fetchEmployeeReq);

	/*
	 * fetching employee details Using Query With NamedParameter JdbcTemplate
	 */
	List<FetchEmployeeReq> fecthEmployeeDetailsUsingQuery(FetchEmployeeReq fetchEmployeeReq);

	/*
	 * fetching particular employee department details Using NamedParameter
	 * JdbcTemplate
	 */
	List<Map<String, Object>> fecthEmployeeWithDepartmentDetailsUsingQuery(FetchEmployeeReq fetchEmployeeReq);

	List<Map<String, Object>> fetchDepartmentWiseGenderCount(FetchEmployeeReq fetchEmployeeReq);

	Boolean insertEmployeeDetails(List<InsertPatientBean> list);
	List<Map<String, Object>> employeeDetailsExportToExcelFile();
}
