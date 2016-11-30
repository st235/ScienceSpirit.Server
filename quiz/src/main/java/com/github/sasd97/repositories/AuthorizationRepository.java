package com.github.sasd97.repositories;

import com.github.sasd97.models.AuthorizationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */

@Repository
public interface AuthorizationRepository extends CrudRepository<AuthorizationModel, Long> {
}
