package com.openclassrooms.poseidon.controllers;

import com.openclassrooms.poseidon.models.UserModel;
import com.openclassrooms.poseidon.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger("UserController");
    private static final String REDIRECT_TRANSAC = "redirect:/user/list";
    private static final String ATTRIB_NAME = "users";
    private static final String USER_NOT_EXISTS = "User {} not exists ! : ";

    @Autowired
    private UserService userService;

    @RolesAllowed("ADMIN")
    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute(ATTRIB_NAME, userService.getAllUsers());
        logger.info("User List Data loading");
        return "user/list";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute(ATTRIB_NAME, new UserModel());
        logger.info("View User Add loaded");
        return "user/add";
    }

    // Button Add User To List
    @RolesAllowed("ADMIN")
    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") UserModel user, BindingResult result,
                           Model model, RedirectAttributes redirAttrs) {
        if (userService.checkIfUserExistsByUsername(user.getUsername())) {
            redirAttrs.addFlashAttribute("errorAddUserMessage", "This user already exists");
            return "redirect:/user/add";
        }
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.saveUser(user);
            redirAttrs.addFlashAttribute("successSaveMessage", "User successfully added to list");
            logger.info("User {} was added to Trade List", user);
            model.addAttribute(ATTRIB_NAME, userService.getAllUsers());
            return REDIRECT_TRANSAC;
        }
        logger.info("Error creation User");
        return "user/add";
    }

    // Show User Update Form
    @RolesAllowed("ADMIN")
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        //UserModel user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        //user.setPassword("");
        //model.addAttribute(ATTRIB_NAME, user);
        //return "user/update";
        try {
            if (!userService.checkIfUserExistsById(id)) {
                logger.info(USER_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            model.addAttribute(ATTRIB_NAME, userService.getUserById(id));
            logger.info("Success User Update");
        } catch (Exception e) {
            logger.info("Error to update \"User\" : {}", id);
        }
        return "user/update";

    }

    // Update User Button
    @RolesAllowed("ADMIN")
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") UserModel user,
                             BindingResult result, Model model, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            logger.info("UPDATE User : KO");
            return "user/update";
        }
        if (!userService.checkIfUserExistsById(id)) {
            logger.info(USER_NOT_EXISTS, id);
            return REDIRECT_TRANSAC;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.saveUser(user);
        model.addAttribute(ATTRIB_NAME, userService.getAllUsers());
        logger.info("UPDATE User {} : OK", id);
        redirAttrs.addFlashAttribute("successUpdateMessage", "User successfully updated");
        return REDIRECT_TRANSAC;
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirAttrs) {
        //UserModel user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        //userRepository.delete(user);
        //model.addAttribute(ATTRIB_NAME, userRepository.findAll());
        try {
            if (!userService.checkIfUserExistsById(id)) {
                logger.info(USER_NOT_EXISTS, id);
                return REDIRECT_TRANSAC;
            }
            userService.deleteUserById(id);
            logger.info("Delete User : OK");
            model.addAttribute(ATTRIB_NAME, userService.getAllUsers());
            redirAttrs.addFlashAttribute("successDeleteMessage", "User successfully deleted");
        } catch (Exception e) {
            redirAttrs.addFlashAttribute("errorDeleteMessage", "Error during deletion");
            logger.info("Error to delete \"User\" : {}", id);
        }
        return REDIRECT_TRANSAC;
    }
}
