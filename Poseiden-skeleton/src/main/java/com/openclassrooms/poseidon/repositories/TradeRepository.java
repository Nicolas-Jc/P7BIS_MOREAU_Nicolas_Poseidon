package com.openclassrooms.poseidon.repositories;

import com.openclassrooms.poseidon.models.TradeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TradeRepository extends JpaRepository<TradeModel, Integer> {
    boolean existsByTradeId(int tradeId);

    TradeModel findById(int id);

    List<TradeModel> findAll();
}
