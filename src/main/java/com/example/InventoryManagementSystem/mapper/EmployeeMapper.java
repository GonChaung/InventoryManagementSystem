package com.example.InventoryManagementSystem.mapper;
import com.example.InventoryManagementSystem.dto.EmployeeDto;
import com.example.InventoryManagementSystem.dto.RoleDto;
import com.example.InventoryManagementSystem.model.User;
import com.example.InventoryManagementSystem.model.Role;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    private RoleMapper roleMapper = new RoleMapper();

    public EmployeeDto employeeToEmployeeDTO(User user){
       EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(user.getId());
        employeeDto.setFirstName(user.getFirstName());
        employeeDto.setEmail(user.getEmail());
        employeeDto.setLastName(user.getLastName());
        RoleDto roleDto = roleMapper.roleToRoleDTO(user.getRole());
        employeeDto.setRoleDto(roleDto);
        employeeDto.setNrc(user.getNrc());
        employeeDto.setPassword(user.getPassword());
        employeeDto.setPhoneNumber(user.getPhoneNumber());
        employeeDto.setAddress(user.getAddress());
            return employeeDto;
    }

    public User employeeDTOToEmployee(EmployeeDto employeeDto){
        User user = new User();
        user.setId(employeeDto.getId());
        user.setEmail(employeeDto.getEmail());
        user.setFirstName(employeeDto.getFirstName());
        user.setLastName(employeeDto.getLastName());
        user.setRole(new Role());
        Role role = roleMapper.roleDtoToRole(employeeDto.getRoleDto());
        user.setRole(role);
        user.setNrc(employeeDto.getNrc());
        user.setPassword(employeeDto.getPassword());
        user.setPhoneNumber(employeeDto.getPhoneNumber());
        user.setAddress(employeeDto.getAddress());
        return user;
    }

}
