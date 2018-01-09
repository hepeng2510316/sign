package com.demo.hibernate.repository;

import com.demo.hibernate.entities.Tips;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipsRepository extends JpaRepository<Tips, Integer> {
}
