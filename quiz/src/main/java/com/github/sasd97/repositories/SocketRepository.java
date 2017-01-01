package com.github.sasd97.repositories;

import com.github.sasd97.models.SocketModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Alexadner Dadukin on 1/1/2017.
 */

@Repository
@Transactional
public interface SocketRepository extends CrudRepository<SocketModel, Long> {

    @Query("select s from SocketModel s where s.session=:session")
    SocketModel findOneBySocket(@Param("session") String session);

    @Modifying
    @Query("delete from SocketModel s where s.session=:session")
    void deleteBySocket(@Param("session") String session);
}
