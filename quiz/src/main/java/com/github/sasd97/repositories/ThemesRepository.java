package com.github.sasd97.repositories;

import com.github.sasd97.models.ThemeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexadner Dadukin on 12/22/2016.
 */

@Repository
public interface ThemesRepository extends CrudRepository<ThemeModel, Long> {
}
