package elekuvanje_mutualauth.demo.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_DOCTOR,ROLE_PATIENT,ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
