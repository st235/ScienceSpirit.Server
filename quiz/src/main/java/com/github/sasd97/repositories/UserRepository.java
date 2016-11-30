package com.github.sasd97.repositories;

import com.github.sasd97.models.UserModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alexander on 21.11.16.
 */

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    @Modifying
    @Query("select s from UserModel s where s.socialId like :socialId")
    List<UserModel> findByUserBySocialId(@Param("socialId") String socialId);
}
