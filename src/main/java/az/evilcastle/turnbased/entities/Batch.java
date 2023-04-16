package az.evilcastle.turnbased.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

//    @OneToMany
//    List<UserAccount> players;
}
