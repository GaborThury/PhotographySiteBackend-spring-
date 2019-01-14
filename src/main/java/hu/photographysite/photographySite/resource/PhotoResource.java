package hu.photographysite.photographySite.resource;

import hu.photographysite.photographySite.model.Photo;
import hu.photographysite.photographySite.service.PhotoService;
import net.bytebuddy.utility.RandomString;
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

@RestController
public class PhotoResource {

    private String uploadDirectory = "C:\\Programming\\+project\\PhotographySiteBackend-spring-\\src\\main\\resources\\static\\";
    private PhotoService photoService;

    public PhotoResource(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    private List<Photo> getAll() {
        return photoService.getAllPhotos();
    }


    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String category) throws IOException {
        return saveFile(file, category);
    }

    private ResponseEntity saveFile(MultipartFile file, String category) throws IOException {
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

        Photo entity = new Photo(originalFileName, filename + "." + fileExtension, category);
        photoService.savePhoto(entity);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

}
