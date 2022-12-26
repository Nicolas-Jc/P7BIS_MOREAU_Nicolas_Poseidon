package com.openclassrooms.poseidon.controllers;

import com.openclassrooms.poseidon.models.RatingModel;
import com.openclassrooms.poseidon.repositories.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RatingController {

    private static final Logger logger = LogManager.getLogger(RatingController.class);

    private static final String ATTRIB_NAME = "rating";
    private static final String REDIRECT_TRANSAC = "redirect:/rating/list";
    private static final String RATING_NOT_EXISTS = "Rating {} not exists ! : ";


    @Autowired
    RatingRepository ratingRepository;


    // return view containing all Ratings
    @GetMapping("/rating/list")
    //@RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute(ATTRIB_NAME, ratingRepository.findAll());
        logger.info("Rating List Data loading");
        return "rating/list";
    }

    // view Rating/add
    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        model.addAttribute(ATTRIB_NAME, new RatingModel());
        logger.info("View Rating Add loaded");
        return "rating/add";
    }

    // Button Add Rating To List
    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute(ATTRIB_NAME) RatingModel rating, BindingResult result, RedirectAttributes redirAttrs) {

        if (!result.hasErrors()) {
            ratingRepository.save(rating);
            redirAttrs.addFlashAttribute("successSaveMessage", "Rating successfully added to list");
            logger.info("Rating Id:{} was added to Rating List", rating.getId());
            return REDIRECT_TRANSAC;
        }
        logger.info("Error creation Rating");
        return "rating/add";
    }

    // Show Update Form Load
    /*@GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            if (!ratingRepository.existsById(id)) {
                logger.info(RATING_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            model.addAttribute(ATTRIB_NAME, ratingRepository.findById(id));
            logger.info("Success Rating Update");
        } catch (Exception e) {
            logger.info("Error to update \"Rating\" : {}", id);
        }
        return "rating/update";
    }*/

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RatingModel rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        logger.info("GET /rating/update : OK");
        return "rating/update";
    }


    // Update Rating Button
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute(ATTRIB_NAME) RatingModel rating,
                               BindingResult result, RedirectAttributes redirAttrs) {
        if (!ratingRepository.existsById(id)) {
            logger.info(RATING_NOT_EXISTS, id);
            return REDIRECT_TRANSAC;
        }
        if (!result.hasErrors()) {
            ratingRepository.save(rating);
            logger.info("UPDATE Rating {} : OK", id);
            redirAttrs.addFlashAttribute("successUpdateMessage", "Rating successfully updated");
            return REDIRECT_TRANSAC;
        }
        logger.info("UPDATE Rating : KO");
        return REDIRECT_TRANSAC;
    }


    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model, RedirectAttributes redirAttrs) {
        try {
            if (!ratingRepository.existsById(id)) {
                logger.info(RATING_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            ratingRepository.deleteById(id);
            logger.info("Delete Rating : OK");
            model.addAttribute(ATTRIB_NAME, ratingRepository.findAll());
            redirAttrs.addFlashAttribute("successDeleteMessage", "Rating successfully deleted");
        } catch (Exception e) {
            redirAttrs.addFlashAttribute("errorDeleteMessage", "Error during deletion");
            logger.info("Error to delete \"Rating\" : {}", id);
        }
        return REDIRECT_TRANSAC;
    }

}
