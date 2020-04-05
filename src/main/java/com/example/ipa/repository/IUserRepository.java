package com.example.ipa.repository;

import com.example.ipa.Domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User,Long> {
    //no params
    @Query("SELECT u FROM User u WHERE u.status = 1")
    Collection<User> findAllActiveUsers();

    @Query(value= "SELECT * FROM app_user u WHERE u.status=1", nativeQuery = true)
    Collection<User> findAllActiveUsersNative();


    //unnamed parameters
    @Query("SELECT u FROM User u WHERE u.status = ?1")
    Collection<User> findUsersByStatus(Integer status);

    @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name=?2")
    Collection<User> findUsersByStatusAndNameNumbered(Integer status, String name);

    //named parameters
    @Query("SELECT u FROM User u WHERE u.status = :status and u.name=:name")
    Collection<User> findUsersByStatusAndNameNamed(
            @Param("status") Integer status
            ,@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.status = :status and u.name IN :names")
    Collection<User> findUsersByStatusAndNameNamed(
            @Param("status") Integer status
            ,@Param("names") Collection<String> names);

    //Modyfikująće
    @Modifying
    @Query(value = "update app_users set status=:status where name = :name",
        nativeQuery = true)
    int updateUserSetStatusForNameNative(@Param("status") Integer status,@Param("name") String name);


    @Modifying
    @Query(value = "insert into app_user (name, age, email, status) VALUES (:name, :age, :email, :status)",
            nativeQuery = true)
    void insertUserSetStatusForNameNative(
            @Param("status") Integer status
            , @Param("name")String name
            , @Param("email") String email
            , @Param("age") Integer age
            );

    @Query(
            "from User u where u.contactDate BETWEEN :startDate AND :endDate"
    )
    Collection<User> findDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
