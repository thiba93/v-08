package org.akov.animal.controller.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class FileUploadController {

    @Value("${upload.path}") // récupérer la valeur à partir du fichier de config
    private String localPath;


    @PostMapping("/uploadFile")
    public List<String> uploadFile(@RequestParam MultipartFile file) {
        File directoryUpload = new File(localPath);

        SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/dd/");
        directoryUpload = new File(directoryUpload + dateFormat.format(new Date()));

        if (!directoryUpload.exists()) {
            directoryUpload.mkdirs();
        }

        UUID uuid = UUID.randomUUID();

        String fileName = file.getOriginalFilename();
        // toto.titi.png
        String[] listP = fileName.split("\\.");

        fileName = uuid.toString()+"."+listP[listP.length-1];

        try {
            file.transferTo(new File(directoryUpload, fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> list = new ArrayList<>();
        list.add(dateFormat.format(new Date()) +  fileName);
        return list;
    }

    @GetMapping("/file/display/{annee}/{mois}/{jour}/{fileName}")
    public ResponseEntity<?> displayFile(@PathVariable String fileName, @PathVariable String annee, @PathVariable String mois, @PathVariable String jour){
        // pathUploadFile => Chemin que l'image et sauvegarder
        File file = new File(localPath+annee+"/"+mois+"/"+jour+"/", fileName);
        if (!file.exists()){
            return null;
        }
        try{
            byte[] imageData = Files.readAllBytes(file.toPath());
            String meta = "image/png";
            return ResponseEntity.ok().contentType(MediaType.valueOf(meta)).body(imageData);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }



}


