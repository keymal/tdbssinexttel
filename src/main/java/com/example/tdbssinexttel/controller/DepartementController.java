package com.example.tdbssinexttel.controller;

import com.example.tdbssinexttel.exception.DepartementNotFoundException;
import com.example.tdbssinexttel.exception.DirectionNotFoundException;
import com.example.tdbssinexttel.exception.UserNotFoundException;
import com.example.tdbssinexttel.model.Departement;
import com.example.tdbssinexttel.model.Direction;
import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.service.DepartementService;
import com.example.tdbssinexttel.service.DirectionService;
import com.example.tdbssinexttel.service.UtilisateurService;
import com.example.tdbssinexttel.utils.enums.ListeDesRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/departements")
public class DepartementController {

    @Autowired
    DepartementService departementService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    DirectionService directionService;

    @GetMapping("")
    public String divisions(Model model) {

        List<Departement> utilisateurs = departementService.listeDepartement();
        System.err.println(utilisateurs);

        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("pageTitle", "Liste des départements");


        return "departements";
    }
    @GetMapping("/restDirections")
    public ResponseEntity<List<Departement>> restDirections() {

        return new ResponseEntity<>(departementService.listeDepartement(), HttpStatus.OK);

    }


    @GetMapping("/{id}/enabled/{status}")
    public String enabled(@PathVariable("id") Integer id, @PathVariable("status") boolean status, RedirectAttributes redirectAttributes, Model model) throws DepartementNotFoundException {


        System.err.println("bonjour");
        departementService.updateUserEnabledStatus(id, status);


        Departement departement = departementService.getById(id);

        System.err.println(departement);


        String enabled = status ? "actif" : "inactif";
        String message = " Le département " + departement.getNom() + " est désormais" + "["+ enabled +"]" +"pour les opérations";
        redirectAttributes.addFlashAttribute("message", message);


        return "redirect:/departements";


    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Model model) throws DepartementNotFoundException {
        try {
            departementService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Le départment  [ " + id + "] a été supprimé pour les opérations");
        } catch (DepartementNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/departements";
    }

    @GetMapping("/new")
    public String formUser(Model model) {

        Departement direction = new Departement();
        direction.setStatus(true);


        List<Utilisateur> user = utilisateurService.listUtilisateur();
        List<Direction> departements = directionService.list();

        model.addAttribute("utilisateur", direction);
        model.addAttribute("user", user);
        model.addAttribute("departements", departements);
        model.addAttribute("pageTitle", "Création d'un nouveau département");
        return "departement_form";
    }

    @ResponseBody
    @PostMapping("/check_mail")
    public String check_mail(@Param("email") String email,@Param("direct") String direction, @Param("id") Integer id) {

        System.err.println(direction);
        Boolean aBoolean = directionService.checkDirectionByNm(id, email);
        System.err.println(aBoolean);
        return directionService.checkDirectionByNm(id, email) ? "OK" : "Cette direction existe deja";
    }


}
