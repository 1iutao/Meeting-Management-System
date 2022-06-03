package org.mm.meetingmanage.mapper;

import org.apache.ibatis.annotations.Param;
import org.mm.meetingmanage.model.Department;

import java.util.List;

public interface DepartmentMapper {
    //根据编号获取部门信息
    Department getDepById(@Param("id") Integer id);

    //获取所有部门信息 返回到list集合里
    List<Department> getAllDeps();

    Department getDepByName(String departmentname);

    Integer adddepartment(String departmentname);

    Integer deletedep(Integer departmentid);

    Integer updatedep(Integer id, String name);
}
