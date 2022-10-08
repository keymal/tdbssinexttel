package com.example.tdbssinexttel.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * Auto update created date et last modify Date
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return Optional.ofNullable(((UserDetails) principal).getUsername());
        } else {
            return Optional.ofNullable(principal.toString());
        }

    }
}

