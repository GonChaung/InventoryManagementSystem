package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "SELECT e FROM employee e WHERE e.first_name = :first_name AND e.last_name = :last_name",
            nativeQuery = true)
    List<Employee> findByFirstNameAndLastName(@Param("first_name") String firstName, @Param("last_name") String lastName);

    @Query(value = "SELECT e FROM employee e WHERE e.role_id = :role_id", nativeQuery = true)
    List<Employee> findByRoleId(@Param("role_id") Long roleId);

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployee();

    @Modifying
    @Transactional
    @Query(value = "UPDATE employee e SET first_name = : first_name, last_name = :last_name, password = :password, nrc = :nrc, address = :address, phone_number = :phone_number WHERE id = :id", nativeQuery = true)
    int updateEmployeeByid(@Param("id") Long id,
                           @Param("first_name") String firstName,
                           @Param("last_name") String lastName,
                           @Param("password") String password,
                           @Param("nrc") String nrc,
                           @Param("address") String address,
                           @Param("phone_number") String phoneNumber);

    @Transactional
    @Query(value = "SELECT * FROM employee where id=:id", nativeQuery = true)
    Employee getEmployeeById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM employee WHERE id = :id", nativeQuery = true)
    int deleteEmployeeById(@Param("id") Long id);

}
