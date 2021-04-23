package org.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@RestController
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
        return "hello doche " + name;
    }

    @GetMapping(path = "/hello")
    private String reqsendname(@RequestParam("pname") String name){
        logger.info(name);
        return "hello dear, " + name.toUpperCase(Locale.ROOT) +"\n\n" + "this is a test from our programm helped by Eng Ali Azizipour" ;
    }

    @GetMapping(path = "/hello2")
    private String reqsendname2(@RequestParam("pname") String name, @RequestParam("age") Integer age){
        return "hello dear, " + name.toUpperCase(Locale.ROOT) +"\n\n" + "this is a test from our programm helped by Eng Ali Azizipour" ;
    }

}
