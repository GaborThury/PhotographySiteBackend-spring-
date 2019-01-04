package hu.photographysite.photographySite.resource;

import hu.photographysite.photographySite.model.Photo;
import hu.photographysite.photographySite.repository.PhotoRepository;
import hu.photographysite.photographySite.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhotoResource {

    @Autowired
    private PhotoRepository photoRepository;

    private PhotoService photoService = new PhotoService();

    @GetMapping("/")
    private List<Photo> getAll() {
        return photoRepository.findAll();
    }


}
