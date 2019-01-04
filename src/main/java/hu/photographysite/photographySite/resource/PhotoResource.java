package hu.photographysite.photographySite.resource;

import hu.photographysite.photographySite.model.Photo;
import hu.photographysite.photographySite.repository.PhotoRepository;
import hu.photographysite.photographySite.service.PhotoService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
public class PhotoResource {

    private String uploadDirectory = "C:\\Programming\\+project\\PhotographySiteBackend-spring-\\src\\main\\resources\\static\\";

    @Autowired
    private PhotoRepository photoRepository;

    private PhotoService photoService = new PhotoService();

    @GetMapping("/")
    private List<Photo> getAll() {
        return photoRepository.findAll();
    }


    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return saveFile(file);
    }

    private ResponseEntity saveFile(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String filename = RandomString.make(12);
        String[] splittedFileName = Objects.requireNonNull(file.getOriginalFilename()).split("[.]");
        String fileExtension = splittedFileName[splittedFileName.length - 1];
        if (!((fileExtension.equals("jpg")) || (fileExtension.equals("jpeg")) || (fileExtension.equals("png")))) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
        byte[] bytes = file.getBytes();

        Path path = Paths.get(uploadDirectory + filename + "." + fileExtension);
        Files.write(path, bytes);

         return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

}
