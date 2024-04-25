package dev.sumit.expenseapi.controller;

import dev.sumit.expenseapi.model.UserExpense;
import dev.sumit.expenseapi.repository.UserExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserExpenseController {

    @Autowired
    UserExpenseRepository userExpenseRepository;

//    @PostMapping("/add/userexpense/{id}")
//    public UserExpense addUserExpense(@PathVariable String id){
//        return userExpenseRepository.save(id);
//    }
}
