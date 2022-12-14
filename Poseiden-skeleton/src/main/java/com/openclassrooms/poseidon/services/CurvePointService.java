package com.openclassrooms.poseidon.services;

import com.openclassrooms.poseidon.models.CurvePointModel;
import com.openclassrooms.poseidon.repositories.CurvePointRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    private static final Logger logger = LogManager.getLogger("CurvePointService");

    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurvePointModel getCurvePointById(int curvePointId) {
        logger.info("Find Curve Point By Id");
        return curvePointRepository.findById(curvePointId);
    }

    public List<CurvePointModel> getAllCurvePoints() {
        logger.info("Find All CurvePoints");
        return curvePointRepository.findAll();
    }

    public void deleteCurvePointById(int id) {
        curvePointRepository.deleteById(id);
        logger.info("Delete CurvePoint ById");
    }

    public void saveCurvePoint(CurvePointModel curvePoint) {
        curvePointRepository.save(curvePoint);
        logger.info("CurvePoint saved in CurveList");
    }

    public boolean checkIfIdExists(int id) {
        logger.info("Check if bid exists ById");
        return curvePointRepository.existsById(id);
    }


}
