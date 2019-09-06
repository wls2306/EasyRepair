package com.tech.repair.repository;

import com.tech.repair.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserOpenId(String openId);

    /**
     * update 后写类名 不是表明
     */
    @Query("update User set userCompanyId=?1 where userOpenId=?2")
     int  updateUserCompanyByUserOpenId(String userCompanyId, String userOpenId);

    @Query("update User set userRole=?1 where userOpenId=?2")
    int updateUserRoleByUserOpenId(String userRole,String userOpenId);

    @Query("update User set userPhone=?1 where userOpenId=?2")
    int updateUserPhoneByUserOpenId(String userPhone, String userOpenId);

    @Query("update User set userStatus=?1 where userOpenId=?2")
    int updateUserStatusByUserOpenId(String userStatus,String userOpenId);
}
