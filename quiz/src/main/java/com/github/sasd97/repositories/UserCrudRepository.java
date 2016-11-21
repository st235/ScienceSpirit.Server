package com.github.sasd97.repositories;

import com.github.sasd97.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alexander on 21.11.16.
 */

@Repository
public interface UserCrudRepository extends CrudRepository<UserModel, Long> {
}