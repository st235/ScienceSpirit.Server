package com.github.sasd97.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alexander on 21.11.16.
 */

@Repository
public interface UserCrud extends CrudRepository<User, Long> {
}
