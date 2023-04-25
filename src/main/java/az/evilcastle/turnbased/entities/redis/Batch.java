package az.evilcastle.turnbased.entities.redis;


import az.evilcastle.turnbased.enums.BatchStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameSession {

    long id;
    BatchStatus batchStatus;
}
