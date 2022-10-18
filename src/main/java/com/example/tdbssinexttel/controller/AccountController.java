package com.example.tdbssinexttel.controller;

import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.security.MyCompteDetails;
import com.example.tdbssinexttel.service.UtilisateurService;
import com.example.tdbssinexttel.utils.enums.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UtilisateurService utilisateurService;


    @GetMapping("")
    public String viewDetails(@AuthenticationPrincipal MyCompteDetails loggedUser, Model model){

        String email = loggedUser.getUsername();
        Utilisateur utilisateur = utilisateurService.getUserByEmail(email);
        model.addAttribute("utilisateur",utilisateur );
        model.addAttribute("pageTitle", "Profil utilisateur");

        return "account_form";

    }


    @PostMapping("/update")
    public String saveUserAccountParameter(Utilisateur utilisateur, RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile multipartFile) throws IOException {

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
            utilisateurService.updateAccount(utilisateur);
        }

        redirectAttributes.addFlashAttribute("message", "Les modifications ont été  prises en compte");
        return "redirect:/utilisateurs/account";

    }
}
