package org.akov.animal.service;

import org.akov.animal.model.Animal;
import org.akov.animal.model.Photos;
import org.akov.animal.repository.AnimalRepository;
import org.akov.animal.repository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotosRepository photosRepository;

    public Photos save(Photos photos) {
        return photosRepository.save(photos);
    }

    public Photos byId(Integer id) {
        return photosRepository.findById(id).get();
    }
}
