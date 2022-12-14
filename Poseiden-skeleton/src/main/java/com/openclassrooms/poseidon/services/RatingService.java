package com.openclassrooms.poseidon.services;

import com.openclassrooms.poseidon.models.RatingModel;
import com.openclassrooms.poseidon.repositories.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private static final Logger logger = LogManager.getLogger("RatingService");

    @Autowired
    private RatingRepository ratingRepository;

    public List<RatingModel> getAllRatings() {
        logger.info("Find All Ratings");
        return ratingRepository.findAll();
    }

    public RatingModel getRatingById(int id) {
        logger.info("Find Rating By Id");
        return ratingRepository.findById(id);
    }

    public boolean checkIfIdExists(int id) {
        logger.info("Check if Rating exists ById");
        return ratingRepository.existsById(id);
    }

    public void saveRating(RatingModel rating) {
        ratingRepository.save(rating);
        logger.info("Rating saved in RatingList");
    }

    public void deleteRatingById(int id) {
        ratingRepository.deleteById(id);
        logger.info("Delete Rating ById");
    }

}
