package org.example.controller;


import org.example.Model.Users;
import org.example.dao.UsersDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.security.*;

@RestController
@CrossOrigin (origins = {"http://192.168.1.5:4200","http://127.0.0.1:4200","*"})
public class UserController {
    byte salt[] = {   (byte) 0x43, (byte) 0x76, (byte) 0x95, (byte) 0xc7,
            (byte) 0x5b, (byte) 0xd7, (byte) 0x45, (byte) 0x17};
    @Autowired
    UsersDao usersDao;

    @GetMapping(path = "/api/user/test")
    private String test() {

        Users user = new Users();

        user.setEmail("Aniran.mp2@gmail.com");
        user.setUsername("Aniranmp2");
        user.setNid("3830306442");

        Users usersDb = usersDao.save(user);

        return "Id : " + usersDb.getId() + "";

    }

    @PostMapping(path = "/api/user/new")
    private Users newUser(@RequestBody Users user)  {

        String pass = encryptThisString(user.getPassword());
        user.setPassword(pass+user.getUsername());
        try {
            user = usersDao.save(user);
            user.setUsername(user.getUsername());
            return user;
        }catch (Exception e){
         System.out.println("this is the error"+ e.toString());
            return null;
        }

    }

    @PostMapping(path = "/api/user/auth")
    private Users userAuth(@RequestBody Users user)  {

       Users isUser =  usersDao.findByUsername(user.getUsername());
       if(isUser != null) {

           String pass = encryptThisString(user.getPassword()) + user.getUsername();

           if (isUser.getPassword().equalsIgnoreCase(pass)) {
               user.setUsername(isUser.getUsername());
               return user;
           }
           return null;
       }
       return null;
    }

    @GetMapping(path = "/api/user/all")
    private List<Users> allPeople() {
        return usersDao.findByOrderById();
    }

    @GetMapping(path = "/api/user/find")
    private List<Users> findPeople(@RequestParam("query") String query) {
        return usersDao.findByUsernameContaining(query);
    }

    @PostMapping(path = "/api/user/edit")
    private Users editPerson(@RequestBody Users user) {
        Users userDb = usersDao.findByUsername(user.getUsername());
        if (userDb != null) {
            user.setId(userDb.getId());
            return usersDao.save(user);
        }
        return null;
    }




    public static String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}









