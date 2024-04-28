package dev.sumit.expenseapi.controller;

import com.mongodb.BasicDBObject;
import dev.sumit.expenseapi.model.Expense;
import dev.sumit.expenseapi.model.User;
import dev.sumit.expenseapi.model.UserExpense;
import dev.sumit.expenseapi.repository.UserExpenseRepository;
import dev.sumit.expenseapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserExpenseController {

    @Autowired
    UserExpenseRepository userExpenseRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRepository userRepository;



    @CrossOrigin
    @PostMapping("/add/userexpense")
    public UserExpense addUserExpense(@RequestBody UserExpense userExpense){
        return userExpenseRepository.save(userExpense);
    }

    @CrossOrigin
    @GetMapping("/get/userexpense")
    public List<UserExpense> getUserExpense(){
        return userExpenseRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/get/userexpense/{id}")
    public Optional<UserExpense> getUserExpense(@PathVariable String id){

        return userExpenseRepository.findById(id);
    }

    @CrossOrigin
    @PostMapping("/addexpense/{id}")
    public Optional<UserExpense> updateExpense(@PathVariable String id, @RequestBody Expense expense){
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        mongoTemplate.updateFirst(query,update.push("expenses",expense),UserExpense.class);
        return userExpenseRepository.findById(id);
    }

    @CrossOrigin
    @PutMapping("/deleteexpense/{id}/{taskid}")
    public ResponseEntity<String> deleteExpense(@PathVariable String id, @PathVariable String taskid){

        Query query = new Query(Criteria.where("id").is(id));

        Update update = new Update().pull("Expenses" , new BasicDBObject("id",taskid));

        mongoTemplate.updateFirst(query,update,UserExpense.class);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}