package com.openclassrooms.poseidon.repositories;

import com.openclassrooms.poseidon.models.TradeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TradeRepository extends JpaRepository<TradeModel, Integer> {

    TradeModel findById(int id);

}
