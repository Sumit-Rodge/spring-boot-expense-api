package dev.sumit.expenseapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    public String id;
    public String firstname;
    public String lastname;
    public String username;
    public String email;
    public String password;

}
