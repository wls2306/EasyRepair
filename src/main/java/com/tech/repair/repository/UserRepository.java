package com.tech.repair.repository;

import com.tech.repair.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserOpenId(String openId);

    int deleteByUserOpenId(String userOpenId);




    /**
     * update 后写类名 不是表明
     */
    @Query("update User set userCompanyId=?1 where userOpenId=?2")
     int  updateUserCompanyByUserOpenId(String userCompanyId, String userOpenId);

    @Query("update User set userRole=?1 where userOpenId=?2")
    int updateUserRoleByUserOpenId(String userRole,String userOpenId);

    @Query("update User set userPhone=?1 where userOpenId=?2")
    int updateUserPhoneByUserOpenId(String userPhone, String userOpenId);

   /* @Query("update User set ?1=?2 where userOpenId=?3")
    int updateUser()*/
}
