package com.openclassrooms.poseidon.repositories;

import com.openclassrooms.poseidon.models.CurvePointModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CurvePointRepository extends JpaRepository<CurvePointModel, Integer> {

    CurvePointModel findById(int id);


}
