package com.github.sasd97.repositories;

import com.github.sasd97.models.BankModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexadner Dadukin on 12/31/2016.
 */

@Repository
public interface BankRepository extends CrudRepository<BankModel, Long> {
}
