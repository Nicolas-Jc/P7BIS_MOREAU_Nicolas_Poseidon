package com.openclassrooms.poseidon.repositories;

import com.openclassrooms.poseidon.models.RatingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingModel, Integer> {

    boolean existsById(int id);

    RatingModel findById(int id);

    List<RatingModel> findAll();
}
