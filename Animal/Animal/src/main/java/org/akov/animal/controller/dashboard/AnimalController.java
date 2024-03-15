package org.akov.animal.controller.dashboard;

import org.akov.animal.model.Animal;
import org.akov.animal.model.Users;
import org.akov.animal.service.AnimalService;
import org.akov.animal.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    @GetMapping("/dashboard/animal")
    public String index(ModelMap map,@AuthenticationPrincipal Users users,@RequestParam(required = false) Integer id) {

        if (id != null) { // update
            map.put("animal", animalService.byId(id));
        }else{ // ajout
            map.put("animal", new Animal());
        }

        map.put("listAnimal", animalService.byUser(users.getId()));

        return "dashboard/animal";
    }

    @PostMapping("/dashboard/animal")
    public String save(@ModelAttribute Animal animal,@AuthenticationPrincipal Users users) {
        animal.setUsers(users);
        animalService.save(animal);
        return "redirect:/dashboard/animal";
    }


}
