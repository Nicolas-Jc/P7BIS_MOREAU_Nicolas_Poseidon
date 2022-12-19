package com.openclassrooms.poseidon.controllers;

import com.openclassrooms.poseidon.models.CurvePointModel;
import com.openclassrooms.poseidon.services.CurvePointService;
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
    CurvePointService curvePointService;

    private static final Logger logger = LogManager.getLogger("CurveController");
    private static final String ATTRIB_NAME = "curvePoint";
    private static final String CURVE_POINT_NOT_EXISTS = "CurvePoint {} not exists ! : ";
    private static final String REDIRECT_TRANSAC = "redirect:/curvePoint/list";


    // return view containing all CurvePoint
    @GetMapping("/curvePoint/list")
    //@RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute(ATTRIB_NAME, curvePointService.getAllCurvePoints());
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
            curvePointService.saveCurvePoint(curvePoint);
            redirAttrs.addFlashAttribute("successSaveMessage", "CurvePoint successfully added to list");
            logger.info("CurvePoint {} was added to Curve Point List", curvePoint);

            return REDIRECT_TRANSAC;
        }
        logger.info("Error creation CurvePoint");
        return "curvePoint/add";
    }

    // Show Update Form Load
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            if (!curvePointService.checkIfIdExists(id)) {
                logger.info(CURVE_POINT_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            model.addAttribute(ATTRIB_NAME, curvePointService.getCurvePointById(id));
            logger.info("Success CurvePoint Update");
        } catch (Exception e) {
            logger.info("Error to update \"CurvePoint\" : {}", id);
        }
        return "curvePoint/update";
    }

    // Update CurvePoint Button
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute(ATTRIB_NAME) CurvePointModel curvePoint,
                            BindingResult result, RedirectAttributes redirAttrs) {
        if (!curvePointService.checkIfIdExists(id)) {
            logger.info(CURVE_POINT_NOT_EXISTS, id);
            return REDIRECT_TRANSAC;
        }
        if (!result.hasErrors()) {
            curvePointService.saveCurvePoint(curvePoint);
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
            if (!curvePointService.checkIfIdExists(id)) {
                logger.info(CURVE_POINT_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            curvePointService.deleteCurvePointById(id);
            logger.info("Delete CurvePoint : OK");
            model.addAttribute(ATTRIB_NAME, curvePointService.getAllCurvePoints());
            redirAttrs.addFlashAttribute("successDeleteMessage", "CurvePoint successfully deleted");
        } catch (Exception e) {
            redirAttrs.addFlashAttribute("errorDeleteMessage", "Error during deletion");
            logger.info("Error to delete \"CurvePoint\" : {}", id);
        }
        return REDIRECT_TRANSAC;
    }
}
