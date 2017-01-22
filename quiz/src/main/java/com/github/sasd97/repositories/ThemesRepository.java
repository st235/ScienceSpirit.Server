package com.github.sasd97.repositories;

import com.github.sasd97.models.NewsModel;
import com.github.sasd97.models.ThemeModel;
import com.github.sasd97.utils.LanguageUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexadner Dadukin on 12/22/2016.
 */

@Repository
public interface ThemesRepository extends CrudRepository<ThemeModel, Long> {

    @Query("select t from ThemeModel t where t.locale=:locale")
    List<ThemeModel> sortByLanguage(@Param("locale") LanguageUtils.Language locale);
}
