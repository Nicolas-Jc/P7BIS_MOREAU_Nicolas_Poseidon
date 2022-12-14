package com.openclassrooms.poseidon.services;

import com.openclassrooms.poseidon.models.RuleNameModel;
import com.openclassrooms.poseidon.repositories.RuleNameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {
    private static final Logger logger = LogManager.getLogger("RuleNameService");

    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleNameModel> getAllRuleNames() {
        logger.info("Find All RuleNames");
        return ruleNameRepository.findAll();
    }

    public RuleNameModel getRuleNameById(int id) {
        logger.info("Find RuleName By Id");
        return ruleNameRepository.findById(id);
    }

    public boolean checkIfIdExists(int id) {
        logger.info("Check if RuleName exists ById");
        return ruleNameRepository.existsById(id);
    }

    public void saveRuleName(RuleNameModel ruleName) {
        ruleNameRepository.save(ruleName);
        logger.info("RuleName saved in RuleNameList");
    }

    public void deleteRuleNameById(int id) {
        ruleNameRepository.deleteById(id);
        logger.info("Delete RuleName ById");
    }
}
