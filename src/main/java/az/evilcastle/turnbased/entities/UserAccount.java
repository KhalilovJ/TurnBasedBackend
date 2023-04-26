package az.evilcastle.turnbased.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String username;
    String email;
    String password;

//    @ManyToOne
//    @JoinColumn(name = "active_session_id")
//    Session activeSession;
}
