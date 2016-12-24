package com.github.sasd97.repositories;

import com.github.sasd97.models.QuestionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexadner Dadukin on 12/24/2016.
 */

@Repository
public interface QuestionRepository extends CrudRepository<QuestionModel, Long> {
}
