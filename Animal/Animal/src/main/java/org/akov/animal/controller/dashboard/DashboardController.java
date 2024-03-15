package org.akov.animal.controller.dashboard;

import org.akov.animal.model.Users;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String index() {
        return "dashboard/index";
    }

}
