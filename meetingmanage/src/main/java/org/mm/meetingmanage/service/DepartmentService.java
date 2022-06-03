package org.mm.meetingmanage.service;

import org.mm.meetingmanage.mapper.DepartmentMapper;
import org.mm.meetingmanage.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    //根据编号获取部门
    public Department getDepById(Integer id) {
        return departmentMapper.getDepById(id);
    }

    //获取所有部门
    public List<Department> getAllDeps() {
        return departmentMapper.getAllDeps();
    }

    //增加部门
    public Integer adddepartment(String departmentname) {
        Department dep = departmentMapper.getDepByName(departmentname);
        if (dep != null) {
            return -1;
        }

        return departmentMapper.adddepartment(departmentname);
    }


     //删除部门
    public Integer deletedep(Integer departmentid) {

        return departmentMapper.deletedep(departmentid);
    }


     //更新部门

    public Integer updatedep(Integer id, String name) {

        return departmentMapper.updatedep(id, name);
    }
}