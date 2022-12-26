package com.openclassrooms.poseidon.controllers;

import com.openclassrooms.poseidon.models.RuleModel;
import com.openclassrooms.poseidon.repositories.RuleNameRepository;
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
public class RuleNameController {

    private static final Logger logger = LogManager.getLogger(RuleNameController.class);
    private static final String ATTRIB_NAME = "ruleName";
    private static final String REDIRECT_TRANSAC = "redirect:/ruleName/list";
    private static final String RULE_NOT_EXISTS = "Rule {} not exists ! : ";

    @Autowired
    RuleNameRepository ruleNameRepository;

    // return view containing all Rules
    @GetMapping("/ruleName/list")
    //@RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute(ATTRIB_NAME, ruleNameRepository.findAll());
        logger.info("Rules List Data loading");
        return "ruleName/list";
    }

    // view Rules/add
    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
        model.addAttribute(ATTRIB_NAME, new RuleModel());
        logger.info("View Rule Add loaded");
        return "ruleName/add";
    }

    // Button Add Rule To List
    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute(ATTRIB_NAME) RuleModel ruleName, BindingResult result, RedirectAttributes redirAttrs) {
        if (!result.hasErrors()) {
            ruleNameRepository.save(ruleName);
            redirAttrs.addFlashAttribute("successSaveMessage", "Rule successfully added to list");
            logger.info("Rule Id:{} was added to Rules List", ruleName.getId());
            return REDIRECT_TRANSAC;
        }
        logger.info("Error creation Rule");
        return "ruleName/add";
    }

    // Show Update Form
    /*@GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            if (!ruleNameRepository.existsById(id)) {
                logger.info(RULE_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            model.addAttribute(ATTRIB_NAME, ruleNameRepository.findById(id));
            logger.info("Success Rating Update");
        } catch (Exception e) {
            logger.info("Error to update \"Rule\" : {}", id);
        }
        return "ruleName/update";
    }*/

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleModel rule = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", rule);
        logger.info("GET /ruleName/update : OK");
        return "ruleName/update";
    }

    // Update Rule Button
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute(ATTRIB_NAME) RuleModel ruleName,
                                 BindingResult result, RedirectAttributes redirAttrs) {
        if (!ruleNameRepository.existsById(id)) {
            logger.info(RULE_NOT_EXISTS, id);
            return REDIRECT_TRANSAC;
        }
        if (!result.hasErrors()) {
            ruleNameRepository.save(ruleName);
            logger.info("UPDATE Rule {} : OK", id);
            redirAttrs.addFlashAttribute("successUpdateMessage", "Rule successfully updated");
            return REDIRECT_TRANSAC;
        }
        logger.info("UPDATE Rule : KO");
        return REDIRECT_TRANSAC;
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model, RedirectAttributes redirAttrs) {
        try {
            if (!ruleNameRepository.existsById(id)) {
                logger.info(RULE_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            ruleNameRepository.deleteById(id);
            logger.info("Delete Rule : OK");
            model.addAttribute(ATTRIB_NAME, ruleNameRepository.findAll());
            redirAttrs.addFlashAttribute("successDeleteMessage", "Rule successfully deleted");
        } catch (Exception e) {
            redirAttrs.addFlashAttribute("errorDeleteMessage", "Error during deletion");
            logger.info("Error to delete \"Rule\" : {}", id);
        }
        return REDIRECT_TRANSAC;
    }
}
