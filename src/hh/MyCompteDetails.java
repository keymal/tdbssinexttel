package com.example.tdbssinexttel.security;




import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyCompteDetails implements UserDetails {

    private Utilisateur utilisateur;

    public MyCompteDetails(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = utilisateur.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNom()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return utilisateur.getPassword();
    }

    @Override
    public String getUsername() {
        return utilisateur.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return utilisateur.getStatus();
    }
}
