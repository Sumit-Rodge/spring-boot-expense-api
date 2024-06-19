package dev.sumit.expenseapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "userexpense")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserExpense {

    public String email;
    public String id;
    public List<Expense> expenses;
}
