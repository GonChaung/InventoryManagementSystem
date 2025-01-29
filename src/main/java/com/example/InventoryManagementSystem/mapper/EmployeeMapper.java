package com.example.InventoryManagementSystem.mapper;
import com.example.InventoryManagementSystem.dto.EmployeeDto;
import com.example.InventoryManagementSystem.dto.RoleDto;
import com.example.InventoryManagementSystem.model.Employee;
import com.example.InventoryManagementSystem.model.Role;

public class EmployeeMapper {
    private RoleMapper roleMapper = new RoleMapper();

    public EmployeeDto employeeToEmployeeDTO(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        RoleDto roleDto = roleMapper.roleToRoleDTO(employee.getRole());
        employeeDto.setRoleDto(roleDto);
        employeeDto.setNrc(employee.getNrc());
        employeeDto.setPassword(employee.getPassword());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setAddress(employee.getAddress());
        return employeeDto;
    }
    public Employee employeeDTOToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setRole(new Role());
        Role role = roleMapper.roleDtoToRole(employeeDto.getRoleDto());
        employee.setRole(role);
        employee.setNrc(employeeDto.getNrc());
        employee.setPassword(employeeDto.getPassword());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setAddress(employeeDto.getAddress());
        return employee;
    }

}
