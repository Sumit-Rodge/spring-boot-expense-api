package dev.sumit.expenseapi.repository;

import dev.sumit.expenseapi.model.UserExpense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExpenseRepository extends MongoRepository<UserExpense,String> {
//    UserExpense findByEmail(String email);
}
