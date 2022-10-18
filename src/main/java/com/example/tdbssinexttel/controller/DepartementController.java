package com.example.tdbssinexttel.controller;

import com.example.tdbssinexttel.exception.UserNotFoundException;
import com.example.tdbssinexttel.model.Departement;
import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.service.DepartementService;
import com.example.tdbssinexttel.utils.enums.ListeDesRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/departements")
public class DepartementController {

    @Autowired
    DepartementService departementService;

    @GetMapping("/new")
    public String formUser(Model model) {
        model.addAttribute("utilisateur", new Departement());
        model.addAttribute("pageTitle", "Création d'un département");
        return "departementForm";
    }


}
