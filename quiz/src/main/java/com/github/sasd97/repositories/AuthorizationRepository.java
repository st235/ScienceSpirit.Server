package com.github.sasd97.repositories;

import com.github.sasd97.models.AuthorizationModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
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
}