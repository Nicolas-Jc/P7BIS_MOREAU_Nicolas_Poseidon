package com.openclassrooms.poseidon.repositories;

import com.openclassrooms.poseidon.models.RuleNameModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RuleNameRepository extends JpaRepository<RuleNameModel, Integer> {
    List<RuleNameModel> findAll();

    RuleNameModel findById(int id);

    boolean existsById(int id);
}
