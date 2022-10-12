package com.example.tdbssinexttel.controller;

import com.example.tdbssinexttel.exception.UserNotFoundException;
import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.repository.RoleRepository;
import com.example.tdbssinexttel.service.UtilisateurService;
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
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    RoleRepository roleRepository;

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
        Role role = roleRepository.findRoleByNom(ListeDesRoles.OPERATIONNEL.toString());

        utilisateur.addRole(role);

        List<Role> listRoles = utilisateurService.listRoles();

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("pageTitle", "Créer un utilisateur");
        return "utilisateurForm";
    }


    @GetMapping("/restListeUtilisateurs")
    public ResponseEntity<List<Utilisateur>> restListeUtilisateurs() {

        return new ResponseEntity<>(utilisateurService.listUtilisateur(), HttpStatus.OK);

    }

    @PostMapping("/save")
    public String saveUser(Utilisateur utilisateur, RedirectAttributes redirectAttributes, @RequestParam("image")MultipartFile multipartFile) throws IOException {

        System.err.println(utilisateur);

        if(!multipartFile.isEmpty()){

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            utilisateur.setPhotos(fileName);

            Utilisateur saveUser = utilisateurService.saveUser(utilisateur);
            String uploadDir = "user-photos/"+saveUser.getId();
            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);

        }
        else {
            if (utilisateur.getPhotos().isEmpty()) utilisateur.setPhotos(null);
            utilisateurService.saveUser(utilisateur);
        }


//
        redirectAttributes.addFlashAttribute("message", "Opération réussie");
        return "redirect:/utilisateurs";
    }

    @ResponseBody
    @PostMapping("/check_mail")
    public String check_mail(@Param("email") String email, @Param("id") Integer id) {

        return utilisateurService.findUserByEmail(id, email) ? "OK" : "L'adresse mail existe deja";
    }


    @GetMapping("/edit/{id}")
    public String fdfdf(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Model model) {


        try {
            Utilisateur utilisateur = utilisateurService.get(id);

            model.addAttribute("utilisateur", utilisateur);
            List<Role> listRoles = utilisateurService.listRoles();
            model.addAttribute("listRoles", listRoles);

            model.addAttribute("pageTitle", "Gestion des utilisateurs | Modifier les informations de l'utilisateur : " + id);

            return "utilisateurForm";

        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/utilisateurs";

        }


    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Model model) {


        try {
            utilisateurService.delete(id);


            redirectAttributes.addFlashAttribute("message", "Utilisateur avec " + id + " a été supprimé pour les opérations");


        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());

        }
        return "redirect:/utilisateurs";


    }

    @GetMapping("/{id}/enabled/{status}")
    public String enabled(@PathVariable("id") Integer id, @PathVariable("status") boolean status, RedirectAttributes redirectAttributes, Model model) {

        System.err.println(status);

        utilisateurService.updateUserEnabledStatus(id, status);



        String enabled = status ? "actif" : "inactif";
        String message = "Utilisateur " + id + " est désormais " + enabled;
        redirectAttributes.addFlashAttribute("message", message);


        return "redirect:/utilisateurs";


    }
}
