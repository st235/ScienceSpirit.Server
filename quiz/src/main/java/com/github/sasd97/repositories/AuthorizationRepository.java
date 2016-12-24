package com.github.sasd97.repositories;

import com.github.sasd97.models.AuthorizationModel;
import com.github.sasd97.models.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */

@Repository
public interface AuthorizationRepository extends CrudRepository<AuthorizationModel, Long> {

    @Query("select a from AuthorizationModel a where a.user = :userId")
    List<AuthorizationModel> findByUserId(@NotNull Long userId);

    @Query("select a from AuthorizationModel a inner join a.user u where u.role = 0 and a.token = :token")
    List<AuthorizationModel> findAdminsByToken(@Param("token") String token);

    @Query("select a from AuthorizationModel a where a.token = :token")
    List<AuthorizationModel> findByToken(@Param("token") String token);
}
