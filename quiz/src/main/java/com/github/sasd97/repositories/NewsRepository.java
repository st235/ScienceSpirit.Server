package com.github.sasd97.repositories;

import com.github.sasd97.models.NewsModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexadner Dadukin on 1/2/2017.
 */

@Repository
public interface NewsRepository extends CrudRepository<NewsModel, Long> {

    @Query("from NewsModel order by creationDate DESC")
    List<NewsModel> findAllWithPage(Pageable pageable);
}
