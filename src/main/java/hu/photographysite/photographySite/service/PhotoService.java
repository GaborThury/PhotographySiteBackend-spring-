package hu.photographysite.photographySite.service;

import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    public PhotoService() {

    }

    public String getAllPhotos() {
        return "all the photos from the service";
    }

    public String uploadPhoto() {
        return "upload successful (service)";
    }

}
