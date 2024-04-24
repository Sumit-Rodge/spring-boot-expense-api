package dev.sumit.expenseapi.controller;


import dev.sumit.expenseapi.model.Expense;
import dev.sumit.expenseapi.repository.ExpenseRepository;
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

@RestController
public class ExpenseController {

    private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);
    @Autowired
    public ExpenseRepository expenseRepository;

    @Autowired
    MongoOperations mongoOperations;

//    @GetMapping("/")
//    public ResponseEntity <List<Expense>> getData(){
//        return new ResponseEntity<List<Expense>>(expenseRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/")
    public List<Expense> heii(){
        return expenseRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity <Expense> addExpense(@RequestBody Expense expense){
        try{

            return new ResponseEntity<>(expenseRepository.save(expense),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

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
