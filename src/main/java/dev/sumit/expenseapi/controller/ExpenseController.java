package dev.sumit.expenseapi.controller;


import dev.sumit.expenseapi.model.Expense;
import dev.sumit.expenseapi.repository.ExpenseRepository;

import dev.sumit.expenseapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ExpenseController {

    private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);
    @Autowired
    public ExpenseRepository expenseRepository;

    @Autowired
    public UserRepository userRepository;




    @CrossOrigin
    @GetMapping("/expenses")
    public ResponseEntity <List<Expense>> getExpense(){
        return  new ResponseEntity<List<Expense>>(expenseRepository.findAll(),HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Expense>> getOncExpense(@PathVariable String id){
        return new ResponseEntity<>(expenseRepository.findById(id),HttpStatus.OK) ;
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity <Expense> addExpense(@RequestBody Expense expense){
        try{

            return new ResponseEntity<>(expenseRepository.save(expense),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id){
        try {
            expenseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
