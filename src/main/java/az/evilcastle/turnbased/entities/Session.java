package az.evilcastle.turnbased.entities;


import az.evilcastle.turnbased.enums.BatchStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Session {

    long id;

    BatchStatus batchStatus;
}
