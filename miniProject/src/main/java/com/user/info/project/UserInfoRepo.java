package com.user.info.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo,String> {

    @Query(value = " select * from USER_INFO  " +
            " where salary >= :min " +
            " and salary <= :max " +
            " order by :sort " +
            " limit :limit offset :offset ",nativeQuery = true)
    List<UserInfo> getUserInfo(@Param("min") double min,
                               @Param("max") double max,
                               @Param("offset") int offset,
                               @Param("limit") String limit,
                               @Param("sort") Integer sort);
    @Query(value = " select * from USER_INFO  " +
            " where salary >= :min " +
            " and salary <= :max " +
            " limit :limit offset :offset ",nativeQuery = true)
    List<UserInfo> getUserInfo(@Param("min") double min,
                               @Param("max") double max,
                               @Param("offset") int offset,
                               @Param("limit") String limit);


}
