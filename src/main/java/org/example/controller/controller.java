package org.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@CrossOrigin (origins = {"http://192.168.1.5:4200","http://127.0.0.1:4200","*"})
public class controller {
    private Logger logger = LoggerFactory.getLogger(controller.class);

    @GetMapping(path = "/")
    private String hello() {
        return "Hello World";
    }

    @GetMapping(path = "/start")
    private String helloAll() {
        logger.error("Salam");
        return "Hello Damn World";
    }
    @GetMapping(path = "/hello/{pname}")
    private String sendnewpname(@PathVariable("pname") String name) {
        return "hello Dear " + name;
    }
    @GetMapping(path = "/hello_2/{email}/{pass}/{passC}")
    private String signupData(@PathVariable("email") String email,
                              @PathVariable("pass") String pass, @PathVariable("passC") String passC) {
        return "This  <" + email + ">" + " will be checked soon, Thanks for signing up.";
    }
    @GetMapping(path = "/hello")
    private String reqsendname(@RequestParam("pname") String name){
        logger.info(name);
        return "hello dear, " + name.toUpperCase(Locale.ROOT) +"\n\n" + "this is a test from our programm By Aniranmp" ;
    }
    @GetMapping(path = "/hello2")
    private String reqsendname2(@RequestParam("pname") String name, @RequestParam("age") Integer age){
        return "hello dear, " + name.toUpperCase(Locale.ROOT) +"\n\n" + "this is a test from our programm By Aniranmp" ;
    }
}
