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
    public ResponseEntity<UserExpense> addUserExpense(@RequestBody UserExpense userExpense){
        try {
            return new ResponseEntity<UserExpense>( userExpenseRepository.save(userExpense),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin
    @GetMapping("/get/userexpense")

    public  ResponseEntity<List<UserExpense>> getUserExpense(){

        try {
            return new ResponseEntity<>(userExpenseRepository.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin
    @GetMapping("/get/userexpense/{id}")
    public ResponseEntity<Optional<UserExpense>> getUserExpense(@PathVariable String id){
        try {
            return new ResponseEntity<>(userExpenseRepository.findById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin
    @PostMapping("/addexpense/{id}")
    public ResponseEntity<Optional<UserExpense>> updateExpense(@PathVariable String id, @RequestBody Expense expense){

        try {
            Query query = new Query(Criteria.where("id").is(id));
            Update update = new Update();
            mongoTemplate.updateFirst(query,update.push("expenses",expense),UserExpense.class);
            return new ResponseEntity<>(userExpenseRepository.findById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin
    @PutMapping("/deleteexpense/{id}/{taskid}")
    public ResponseEntity<String> deleteExpense(@PathVariable String id, @PathVariable String taskid){

        try {
            Query query = new Query(Criteria.where("id").is(id));

            Update update = new Update().pull("Expenses" , new BasicDBObject("id",taskid));

            mongoTemplate.updateFirst(query,update,UserExpense.class);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/updateexpense/{id}/{taskid}")
//    public UserExpense updateeexpense(@PathVariable String id,@PathVariable String taskid,@RequestBody Object requestBody){
//        Query query = new Query(new Criteria().andOperator(
//                Criteria.where("id").is(id),
//                Criteria.where("expenses").elemMatch(Criteria.where("id").is(taskid))
//        ));
//
//        Update update = new Update().set("expenses.$.id.amount", requestBody.amount);
//
//        mongoTemplate.updateMulti(query, update, "comment");
////        https://stackoverflow.com/questions/16066393/how-to-update-nested-objects-in-array-that-match-the-condition-in-spring-data
//    }
}