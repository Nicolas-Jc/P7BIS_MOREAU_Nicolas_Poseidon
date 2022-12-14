package com.openclassrooms.poseidon.services;


import com.openclassrooms.poseidon.models.TradeModel;
import com.openclassrooms.poseidon.repositories.TradeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {
    private static final Logger logger = LogManager.getLogger("TradeService");

    @Autowired
    private TradeRepository tradeRepository;

    public List<TradeModel> getAllTrades() {
        logger.info("Find All Trades");
        return tradeRepository.findAll();
    }

    public TradeModel getTradeById(int id) {
        logger.info("Find Trade By Id");
        return tradeRepository.findById(id);
    }

    public boolean checkIfTradeIdExists(int id) {
        logger.info("Check if Trade exists ById");
        return tradeRepository.existsById(id);
    }

    public void saveTrade(TradeModel trade) {
        tradeRepository.save(trade);
        logger.info("Trade saved in TradeList");
    }

    public void deleteTradeById(int id) {
        tradeRepository.deleteById(id);
        logger.info("Delete Trade ById");
    }


}
