package com.example.apiversiondemo.controller;

import com.example.apiversiondemo.model.Name;
import com.example.apiversiondemo.model.StudentV1;
import com.example.apiversiondemo.model.StudentV2;
import com.example.apiversiondemo.util.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    // Method - I (static versioning)
    @GetMapping(value = "/student/v1")
    public StudentV1 staticV1() {
        return new StudentV1("Bob Charlie");
    }

    @GetMapping(value = "/student/v2")
    public StudentV2 staticV2() {
        return new StudentV2(new Name("Bob", "Charlie"));
    }

    //Method - II (Passing version as request param)

    @GetMapping(value = "/student/param", params = "version=1")
    public StudentV1 paramV1() {
        return new StudentV1("Bob Charlie");
    }

    @GetMapping(value = "/student/param", params = "version=2")
    public StudentV2 paramV2() {
        return new StudentV2(new Name("Bob", "Charlie"));
    }

    //Method - III (Passing version as Header)

    @GetMapping(value = "/student/header", headers = "X-API-VERSION=1")
    public StudentV1 headerV1() {
        return new StudentV1("Bob Charlie");
    }

    @GetMapping(value = "/student/header", headers = "X-API-VERSION=2")
    public StudentV2 headerV2() {
        return new StudentV2(new Name("Bob", "Charlie"));
    }

    @GetMapping("/student/cleanTest/{name}")
    public StudentV2 testEsapi(@PathVariable String name) {

        StudentV2 studentV2 = new StudentV2(new Name("Bob", "Charlie"));

        String cleanString = SecurityUtil.cleanString(name);

        if (cleanString.equalsIgnoreCase("Bob")) {
            return studentV2;
        }

        return new StudentV2();
    }
}
