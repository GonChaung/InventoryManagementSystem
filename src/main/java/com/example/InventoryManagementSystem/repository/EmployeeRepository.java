package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {


    @Transactional
    @Query(value = "SELECT * FROM employee WHERE id = :id", nativeQuery = true)
    Optional<Employee> findById(@Param("id") Long id);

    @Query(value = "SELECT e FROM employee e WHERE e.first_name = :first_name AND e.last_name = :last_name",
            nativeQuery = true)
    List<Employee> findByFirstNameAndLastName(@Param("first_name") String firstName, @Param("last_name") String lastName);

    @Query(value = "SELECT e FROM employee e WHERE e.role_id = :role_id", nativeQuery = true)
    List<Employee> findByRoleId(@Param("role_id") Long roleId);


}
