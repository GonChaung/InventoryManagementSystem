package com.example.InventoryManagementSystem.mapper;
import com.example.InventoryManagementSystem.dto.UserDto;
import com.example.InventoryManagementSystem.dto.RoleDto;
import com.example.InventoryManagementSystem.model.User;
import com.example.InventoryManagementSystem.model.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private RoleMapper roleMapper = new RoleMapper();

    public UserDto employeeToEmployeeDTO(User user){
       UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        userDto.setLastName(user.getLastName());
        RoleDto roleDto = roleMapper.roleToRoleDTO(user.getRole());
        userDto.setRoleDto(roleDto);
        userDto.setNrc(user.getNrc());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
            return userDto;
    }

    public User employeeDTOToEmployee(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(new Role());
        Role role = roleMapper.roleDtoToRole(userDto.getRoleDto());
        user.setRole(role);
        user.setNrc(userDto.getNrc());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        return user;
    }

}
