package dev.sumit.expenseapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "userexpense")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expense {

    @Id
    public String id;
    public String description;
    public String amount;
    public String category;


}
