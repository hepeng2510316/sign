package com.demo.hibernate.repository;

import com.demo.hibernate.entities.SignRecord;
import com.demo.hibernate.utils.DateUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SignRecordRepository extends JpaRepository<SignRecord, Integer> {

    @Query("FROM SignRecord s WHERE s.openId =:openId and s.signTime>= :today0")
    SignRecord findTodaySignRecord(@Param(value = "openId") String openId, @Param(value = "today0") Date today0);

    @Query("FROM SignRecord s WHERE s.openId =:openId and s.signTime>= :yesterday0 and s.signTime < :today0")
    SignRecord findYesterdaySignRecord(@Param(value = "openId") String openId, @Param(value = "yesterday0") Date yesterday0, @Param(value = "today0") Date today0);
}
