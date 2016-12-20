package com.github.sasd97.repositories;

import com.github.sasd97.models.AdminSecretModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alexander on 20.12.16.
 */

@Repository
public interface AdminSecretRepository extends CrudRepository<AdminSecretModel, Long> {
}
