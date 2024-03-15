package org.akov.animal.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.akov.animal.model.Role;
import org.akov.animal.model.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, IOException {
        Users user = (Users) authentication.getPrincipal(); // on récupère l'utilisateur connecter

        String redirectUrl;


        for (Role role : user.getRoles()) {
            switch (role.getNom()) {
                case "ADMIN":
                    redirectUrl = "/dashboard";
                    break;
                case "USER":
                    redirectUrl = "/user";
                    break;
                default:
                    redirectUrl = "/login";
                    break;
            }
            response.sendRedirect(redirectUrl);
            return;
        }

    }
}
