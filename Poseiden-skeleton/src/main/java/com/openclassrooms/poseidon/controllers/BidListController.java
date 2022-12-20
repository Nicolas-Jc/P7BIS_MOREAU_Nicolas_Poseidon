package com.openclassrooms.poseidon.controllers;

import com.openclassrooms.poseidon.models.BidListModel;
import com.openclassrooms.poseidon.repositories.BidListRepository;
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
public class BidListController {

    @Autowired
    private BidListRepository bidListRepository;

    private static final Logger logger = LogManager.getLogger("BidListController");
    private static final String REDIRECT_TRANSAC = "redirect:/bidList/list";
    private static final String ATTRIB_NAME = "bidList";
    private static final String BID_NOT_EXISTS = "Bid {} not exists ! : ";


    // return view containing all Bids
    // @RequestMapping ou GetMapping ?????
    //@RequestMapping("/bidList/list")
    @GetMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute(ATTRIB_NAME, bidListRepository.findAll());
        logger.info("BidList Data loading");
        return "bidList/list";
    }


    // view bidList/add
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute(ATTRIB_NAME, new BidListModel());
        logger.info("View bidListAdd : OK");
        return "bidList/add";
    }

    // Button Add Bid To List
    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute(ATTRIB_NAME) BidListModel bidList,
                           BindingResult result, RedirectAttributes redirAttrs) {

        if (!result.hasErrors()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            bidList.setBidListDate(timestamp);
            bidList.setCreationDate(timestamp);
            bidListRepository.save(bidList);
            redirAttrs.addFlashAttribute("successSaveMessage", "Bid successfully added to list");
            logger.info("Bid {} was added to BidList", bidList);
            return REDIRECT_TRANSAC;
        }
        logger.info("Error creation Bid");
        return "bidList/add";
    }

    // Show Update Form Load
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            if (!bidListRepository.existsById(id)) {
                logger.info(BID_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            model.addAttribute(ATTRIB_NAME, bidListRepository.findByBidListId(id));
            logger.info("Succes Bid Update");
        } catch (Exception e) {
            logger.info("Error to update \"Bid Id\" : {}", id);
        }
        return "bidList/update";
    }

    // Update Bid Button
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute(ATTRIB_NAME) BidListModel bidList,
                            BindingResult result, RedirectAttributes redirAttrs) {
        if (!bidListRepository.existsById(id)) {
            logger.info(BID_NOT_EXISTS, id);
            return REDIRECT_TRANSAC;
        }
        if (!result.hasErrors()) {
            bidListRepository.save(bidList);
            logger.info("UPDATE Bid {} : OK", id);
            redirAttrs.addFlashAttribute("successUpdateMessage", "Bid successfully updated");
            return REDIRECT_TRANSAC;
        }
        logger.info("UPDATE Bid : KO");
        return "/bidList/add";
    }

    // Bid Delete Button
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model, RedirectAttributes redirAttrs) {
        try {
            if (!bidListRepository.existsById(id)) {
                logger.info(BID_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            bidListRepository.deleteById(id);
            logger.info("Delete Bid : OK");
            model.addAttribute(ATTRIB_NAME, bidListRepository.findAll());
            redirAttrs.addFlashAttribute("successDeleteMessage", "DELETE Bid : OK");
        } catch (Exception e) {
            redirAttrs.addFlashAttribute("errorDeleteMessage", "Error during deletion");
            logger.info("Error to delete \"Bid \" : {}", id);
        }
        return REDIRECT_TRANSAC;
    }
}
