package org.akov.animal.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.akov.animal.model.Users;
import org.akov.animal.service.RoleService;
import org.akov.animal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsersService usersService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String index(HttpServletRequest req) {

        //TODO:Seulement pour les test
        Users user = usersService.byId(1);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return "redirect:/dashboard/animal";

/*
        Role roleAdmin = new Role();
        roleAdmin.setNom("ADMIN");
        this.roleService.save(roleAdmin);

        Users users = new Users();
        users.setNom("boumil");
        users.setPrenom("mounir");
        users.setEmail("toto@titi.fr");
        users.setActive(true);
        users.setMdp(bCryptPasswordEncoder.encode("1234"));
        users.setRoles(List.of(roleAdmin));
        this.usersService.save(users);*/

        //return "index";
    }

    @GetMapping("/login")
    public String login(ModelMap map) {
        map.put("user", new Users());
        return "login";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest req, @ModelAttribute Users user) {

        user.setActive(true);
        user.setRoles(List.of(roleService.findOrSave("ADMIN")));
        user.setMdp(bCryptPasswordEncoder.encode(user.getMdp()));
        this.usersService.save(user);


        // ajouter une authentification
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return "redirect:/dashboard";
    }
}
