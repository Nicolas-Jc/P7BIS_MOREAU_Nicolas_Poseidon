package com.openclassrooms.poseidon.services;

import com.openclassrooms.poseidon.models.BidListModel;
import com.openclassrooms.poseidon.repositories.BidListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    private static final Logger logger = LogManager.getLogger("BidListService");

    @Autowired
    private BidListRepository bidListRepository;

    public BidListModel getBidByBidListId(int bidListId) {
        logger.info("Find Bid By Id");
        return bidListRepository.findByBidListId(bidListId);
    }

    public List<BidListModel> getAllBids() {
        logger.info("Find All Bids");
        return bidListRepository.findAll();
    }

    public void deleteBidById(int bidListId) {
        bidListRepository.deleteById(bidListId);
        logger.info("Delete Bid ById");
    }

    public void saveBid(BidListModel bidList) {
        bidListRepository.save(bidList);
        logger.info("Bid saved in BidList");
    }

    public boolean checkIfIdExists(int id) {
        logger.info("Check if bid exists ById");
        return bidListRepository.existsById(id);
    }

}
