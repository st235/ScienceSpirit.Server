package com.github.sasd97.repositories;

import com.github.sasd97.models.VariantModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexadner Dadukin on 12/24/2016.
 */

@Repository
public interface VariantRepository extends CrudRepository<VariantModel, Long> {
}
