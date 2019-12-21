package com.nc.edu.fapi.service;

import com.nc.edu.fapi.dto.PostPageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageService {
    @Value("${backend.server.url}")
    private String backendServerUrl;


    public ResponseEntity<byte[]> getImage(String name) {

        RestTemplate restTemplate = new RestTemplate();
        byte[] image= restTemplate.getForObject(backendServerUrl + "/api/photo/"+name, byte[].class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
