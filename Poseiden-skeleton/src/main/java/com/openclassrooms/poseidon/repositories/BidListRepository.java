package com.openclassrooms.poseidon.repositories;

import com.openclassrooms.poseidon.models.BidListModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BidListRepository extends JpaRepository<BidListModel, Integer> {

    BidListModel findByBidListId(int bidListId);

    List<BidListModel> findAll();
}
