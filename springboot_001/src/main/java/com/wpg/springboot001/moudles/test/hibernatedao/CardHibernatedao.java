package com.wpg.springboot001.moudles.test.hibernatedao;

import com.wpg.springboot001.moudles.test.pojo.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardHibernatedao extends JpaRepository<Card,Integer> {

}
