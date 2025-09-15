package com.bank.tsehay.wikitsehay.utils;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class MediaTypes {

    public static MediaType getMediaType(MultipartFile file) {
        if (file == null || file.getOriginalFilename() == null) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }

        String filename = file.getOriginalFilename().toLowerCase();

        if (filename.endsWith(".pdf")) {
            return MediaType.APPLICATION_PDF;
        } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else if (filename.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
