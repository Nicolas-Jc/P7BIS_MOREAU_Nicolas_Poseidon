package com.openclassrooms.poseidon.repositories;

import com.openclassrooms.poseidon.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<UserModel, Integer>, JpaSpecificationExecutor<UserModel> {
    //boolean existsByUsername(String username);

    //UserModel findByUsername(String username);

    @Query(value = "select * from users where username = :username", nativeQuery = true)
    UserModel findByUserName(@Param("username") String userName);
}
