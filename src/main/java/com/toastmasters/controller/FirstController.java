package com.toastmasters.controller;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping(value= "/")
public class FirstController {
 
    // Method to serve the guest page!
    @GetMapping(value= "toastmasters")
    public ResponseEntity<String> guest() {
        return new ResponseEntity<String>("Hello! This is an application being developed for Toastmasters group!!!", HttpStatus.OK);
    }
}