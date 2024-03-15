package org.akov.animal.controller.dashboard;

import org.akov.animal.model.Animal;
import org.akov.animal.model.Photos;
import org.akov.animal.service.AnimalService;
import org.akov.animal.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhotosController {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private AnimalService animalService;


    @PostMapping("/dashboard/photos/save")
    public Photos savePhoto(@ModelAttribute Photos photos) {
        return photoService.save(photos);
    }

    @PostMapping("/dashboard/photos/animal/save")
    public List<Photos> savePhoto(@RequestParam Integer idAnimal, @RequestParam Integer idPhoto) {
        Animal animal = animalService.byId(idAnimal);
        animal.getPhotos().add(photoService.byId(idPhoto));
        animalService.save(animal);
        return animal.getPhotos();
    }

    @GetMapping("/dashboard/photos/animal")
    public List<Photos> byAnimal(@RequestParam Integer idAnimal) {
        return animalService.byId(idAnimal).getPhotos();
    }
}
