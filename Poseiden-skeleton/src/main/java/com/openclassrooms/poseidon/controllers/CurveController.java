package com.openclassrooms.poseidon.controllers;

import com.openclassrooms.poseidon.models.CurvePointModel;
import com.openclassrooms.poseidon.repositories.CurvePointRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
public class CurveController {

    @Autowired
    CurvePointRepository curvePointRepository;

    private static final Logger logger = LogManager.getLogger(CurveController.class);
    private static final String ATTRIB_NAME = "curvePoint";
    private static final String CURVE_POINT_NOT_EXISTS = "CurvePoint {} not exists ! : ";
    private static final String REDIRECT_TRANSAC = "redirect:/curvePoint/list";


    // return view containing all CurvePoint
    @GetMapping("/curvePoint/list")
    //@RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute(ATTRIB_NAME, curvePointRepository.findAll());
        logger.info("CurvePoint List Data loading");
        return "curvePoint/list";
    }

    // view CurvePoint/add
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
        model.addAttribute(ATTRIB_NAME, new CurvePointModel());
        logger.info("View CurvePoint Add loaded");
        return "curvePoint/add";
    }

    // // Button Add CurvePoint To List
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute(ATTRIB_NAME) CurvePointModel curvePoint, BindingResult result, RedirectAttributes redirAttrs) {

        if (!result.hasErrors()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            curvePoint.setAsOfDate(timestamp);
            curvePoint.setCreationDate(timestamp);
            curvePointRepository.save(curvePoint);
            redirAttrs.addFlashAttribute("successSaveMessage", "CurvePoint successfully added to list");
            logger.info("CurvePoint Id:{} was added to Curve Point List", curvePoint.getCurveId());

            return REDIRECT_TRANSAC;
        }
        logger.info("Error creation CurvePoint");
        return "curvePoint/add";
    }


    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePointModel curvePoint = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        logger.info("GET /curvePoint/update : OK");
        return "curvePoint/update";
    }

    // Update CurvePoint Button
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute(ATTRIB_NAME) CurvePointModel curvePoint,
                            BindingResult result, RedirectAttributes redirAttrs) {
        if (!curvePointRepository.existsById(id)) {
            logger.info(CURVE_POINT_NOT_EXISTS, id);
            return REDIRECT_TRANSAC;
        }
        if (!result.hasErrors()) {
            curvePointRepository.save(curvePoint);
            logger.info("UPDATE CurvePoint {} : OK", id);
            redirAttrs.addFlashAttribute("successUpdateMessage", "CurvePoint successfully updated");
            return REDIRECT_TRANSAC;
        }
        logger.info("UPDATE CurvePoint : KO");
        return REDIRECT_TRANSAC;
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model, RedirectAttributes redirAttrs) {
        try {
            if (!curvePointRepository.existsById(id)) {
                logger.info(CURVE_POINT_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            curvePointRepository.deleteById(id);
            logger.info("Delete CurvePoint : OK");
            model.addAttribute(ATTRIB_NAME, curvePointRepository.findAll());
            redirAttrs.addFlashAttribute("successDeleteMessage", "CurvePoint successfully deleted");
        } catch (Exception e) {
            redirAttrs.addFlashAttribute("errorDeleteMessage", "Error during deletion");
            logger.info("Error to delete \"CurvePoint\" : {}", id);
        }
        return REDIRECT_TRANSAC;
    }
}
