package com.example.tdbssinexttel.controller;

import com.example.tdbssinexttel.exception.DirectionNotFoundException;
import com.example.tdbssinexttel.exception.UserNotFoundException;
import com.example.tdbssinexttel.model.Direction;
import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.repository.DirectionRepository;
import com.example.tdbssinexttel.service.DirectionService;
import com.example.tdbssinexttel.utils.enums.FileUploadUtil;
import com.example.tdbssinexttel.utils.enums.ListeDesRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/directions")
public class DirectionController {
    @Autowired
    DirectionService directionService;

    @GetMapping("")
    public String divisions(Model model) {

        List<Direction> utilisateurs = directionService.list();

        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("pageTitle", "Liste des directions");


        return "directions";
    }

    @GetMapping("/restDirections")
    public ResponseEntity<List<Direction>> restDirections() {

        return new ResponseEntity<>(directionService.list(), HttpStatus.OK);

    }


    @GetMapping("/{id}/enabled/{status}")
    public String enabled(@PathVariable("id") Integer id, @PathVariable("status") boolean status, RedirectAttributes redirectAttributes, Model model) {


        directionService.updateUserEnabledStatus(id, status);

        Direction directionServiceById = directionService.getById(id);


        String enabled = status ? "active" : "inactive";
        String message = " La direction " + directionServiceById.getNom() + " est désormais" + "["+ enabled +"]" +"pour les opérations";
        redirectAttributes.addFlashAttribute("message", message);


        return "redirect:/directions";


    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Model model) {


        try {
            directionService.delete(id);


            redirectAttributes.addFlashAttribute("message", "Direction avec " + id + " a été supprimé pour les opérations");


        } catch (DirectionNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());

        }
        return "redirect:/directions";


    }


    @GetMapping("/new")
    public String formUser(Model model) {

        Direction direction = new Direction();
        direction.setStatus(true);



        model.addAttribute("utilisateur", direction);
        model.addAttribute("pageTitle", "Création d'une direction");
        return "direction_form";
    }

    @ResponseBody
    @PostMapping("/check_mail")
    public String check_mail(@Param("email") String email, @Param("id") Integer id) {

        Boolean aBoolean = directionService.checkDirectionByNm(id, email);
        System.err.println(aBoolean);
        return directionService.checkDirectionByNm(id, email) ? "OK" : "Cette direction existe deja";
    }
    @PostMapping("/save")
    public String saveUser(Direction direction, RedirectAttributes redirectAttributes) throws IOException {

        System.err.println(direction);

        directionService.save(direction);



//
        redirectAttributes.addFlashAttribute("message", "Opération réussie");
        return "redirect:/directions";
    }

}
