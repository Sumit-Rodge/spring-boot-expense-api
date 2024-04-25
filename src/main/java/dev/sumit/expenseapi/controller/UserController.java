package dev.sumit.expenseapi.controller;

import dev.sumit.expenseapi.model.User;
import dev.sumit.expenseapi.model.UserExpense;
import dev.sumit.expenseapi.repository.UserExpenseRepository;
import dev.sumit.expenseapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserExpenseRepository userExpenseRepository;

    @CrossOrigin
    @PostMapping("/user/add")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK) ;
    }

    @CrossOrigin
    @PostMapping("/login/{email}")
    public User verifyUser(@PathVariable String email){
        return userRepository.findByEmail(email);
    }
}
