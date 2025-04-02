package com.heng.microservice.service.image;

import com.heng.microservice.dto.ImageDto;
import com.heng.microservice.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages( List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile files,Long imageId);




}
