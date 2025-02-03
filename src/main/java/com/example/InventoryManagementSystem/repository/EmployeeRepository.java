package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<User> getAllEmployee();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO employee (first_name, last_name, password, nrc, address, phone_number, role_id) " +
            "VALUES (:first_name, :last_name, :password, :nrc, :address, :phone_number, :role_id)", nativeQuery = true)
    int addEmployee(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("password") String password,
            @Param("nrc") String nrc,
            @Param("address") String address,
            @Param("phone_number") String phoneNumber,
            @Param("role_id") Long roleId
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE employee e SET first_name = :first_name, last_name = :last_name, password = :password, nrc = :nrc, address = :address, phone_number = :phone_number WHERE id = :id", nativeQuery = true)
    int updateEmployeeByid(@Param("id") Long id,
                           @Param("first_name") String firstName,
                           @Param("last_name") String lastName,
                           @Param("password") String password,
                           @Param("nrc") String nrc,
                           @Param("address") String address,
                           @Param("phone_number") String phoneNumber);

    @Transactional
    @Query(value = "SELECT * FROM employee where id=:id", nativeQuery = true)
    User getEmployeeById(@Param("id") Long id);

    @Transactional
    @Query(value = "SELECT * FROM employee WHERE nrc = :nrc", nativeQuery = true)
    User getEmployeeByNRC(@Param("nrc") String nrc);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM employee WHERE id = :id", nativeQuery = true)
    int deleteEmployeeById(@Param("id") Long id);


}
