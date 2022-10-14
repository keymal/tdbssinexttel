package com.example.tdbssinexttel.security;




import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CompteDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findUtilisateurByEmailIgnoreCase(s);

        if (user == null) {
            throw new UsernameNotFoundException("Impossible de trouver l'utilisateur " +s);
        }
        return new MyCompteDetails(user);
    }
}
