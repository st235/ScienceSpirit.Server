package com.github.sasd97.repositories;

import com.github.sasd97.models.UserModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alexander on 21.11.16.
 */

@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserModel, Long> {

    @Query("select s from UserModel s where s.socialId like :socialId")
    List<UserModel> findByUserBySocialId(@Param("socialId") String socialId);

    @Query("select s from UserModel s where s.nickname like :nickname")
    UserModel findByNickname(@Param("nickname") String nickname);

    @Modifying
    @Query("update UserModel u set u.nickname=:nickname, u.avatarUrl=:avatarUrl where u.userId=:userId")
    Integer editUser(@Param("userId") Long userId,
                       @Param("nickname") String nickname,
                       @Param("avatarUrl") String avatarUrl);
}
