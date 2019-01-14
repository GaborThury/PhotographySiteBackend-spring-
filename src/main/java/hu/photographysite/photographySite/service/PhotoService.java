package hu.photographysite.photographySite.service;

import hu.photographysite.photographySite.model.Photo;
import hu.photographysite.photographySite.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    private PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo savePhoto(Photo entity) {
        return photoRepository.save(entity);
    }


}
