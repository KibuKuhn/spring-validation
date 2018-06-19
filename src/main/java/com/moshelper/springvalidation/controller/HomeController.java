package com.moshelper.springvalidation.controller;

import com.moshelper.springvalidation.model.Student;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @PostMapping("/post")
    public Student postStudent(@Valid @RequestBody Student student){
        return student;
    }

}
