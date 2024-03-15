package org.akov.animal.service;

import org.akov.animal.model.Animal;
import org.akov.animal.model.Role;
import org.akov.animal.repository.AnimalRepository;
import org.akov.animal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal byId(Integer id) {
        return animalRepository.findById(id).get();
    }

    public List<Animal> byUser(Integer idUser) {
        return animalRepository.findByUsers_Id(idUser);
    }
}
