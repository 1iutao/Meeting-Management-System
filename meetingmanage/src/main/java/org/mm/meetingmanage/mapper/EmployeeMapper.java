package org.mm.meetingmanage.mapper;

import org.apache.ibatis.annotations.Param;
import org.mm.meetingmanage.model.Employee;

import java.util.List;

public interface EmployeeMapper {

//    通过用户名查询员工
    Employee loadEmpByUsername(String username);

    Integer doReg(Employee employee);

    List<Employee> getAllEmpsByStatus(Integer status);

    Integer updatestatus(@Param("employeeid") Integer employeeid, @Param("status") Integer status);

    Long getTotal(Employee employee);

    List<Employee> getAllEmps(@Param("emp") Employee employee, @Param("page") Integer page, @Param("pagesize") Integer pageSize);
    

    void doChang(String username, String newpassword);

    List<Employee> getEmpsByDepId(Integer depId);

    List<Employee> getAllEmpsByid(List<Integer> list);
}
