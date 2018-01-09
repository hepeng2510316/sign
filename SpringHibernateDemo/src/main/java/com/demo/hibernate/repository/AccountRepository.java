package com.demo.hibernate.repository;


import com.demo.hibernate.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

   Account findByOpenId(String openId);

}
