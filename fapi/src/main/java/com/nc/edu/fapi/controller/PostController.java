//package com.nc.edu.fapi.controller;
//
//
//import com.nc.edu.fapi.models.Post;
//import com.nc.edu.fapi.models.User;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
//@RestController
//@RequestMapping("/api")
//public class PostController {
//    @GetMapping("/posts")
//    public String getPostByPageable(Model model, @RequestParam(defaultValue = "0") int page) {
//        model.addAttribute("data", new PageRequest())
//
//    }
//}
