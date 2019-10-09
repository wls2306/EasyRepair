package com.tech.repair.repository;

import com.tech.repair.po.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
    Goods findAllById(String id);

    Goods findAllByGoodsId(String goodsId);

    List<Goods> findAllByGoodsCompanyId(String goodsCompanyId);

    int deleteById(String id);

    int deleteByGoodsId(String goodsId);
}
