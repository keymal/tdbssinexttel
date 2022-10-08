package com.example.tdbssinexttel.controller;

import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.service.UtilisateurService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("")
    public String utilisateurs(Model model) {

        List<Utilisateur> utilisateurs = utilisateurService.listUtilisateur();
        model.addAttribute("utilisateurs", utilisateurs);

        return "utilisateurs";
    }

    @GetMapping("/new")
    public String formUser(Model model) {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setStatus(true);

        List<Role> listRoles = utilisateurService.listRoles();

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("listRoles", listRoles);
        return "utilisateurForm";
    }


    @GetMapping("/restListeUtilisateurs")
    public ResponseEntity<List<Utilisateur>> restListeUtilisateurs() {

        return new ResponseEntity<>(utilisateurService.listUtilisateur(), HttpStatus.OK);

    }

    @PostMapping("/save")
    public String saveUser(Utilisateur utilisateur, RedirectAttributes redirectAttributes) {
        utilisateurService.saveUser(utilisateur);

        redirectAttributes.addFlashAttribute("message", "Opération réussie");
        return "redirect:/utilisateurs";
    }
}
