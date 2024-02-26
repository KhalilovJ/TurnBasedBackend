package az.evilcastle.turnbased.mappers;

import az.evilcastle.turnbased.enums.GameActionType;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class GameActionTypeDeserializer extends JsonDeserializer<GameActionType> {
    @Override
    public GameActionType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String value = jsonParser.getText();
        // Implement your logic to convert the string to enum
        return GameActionType.valueOf(value); // This is a simple example; you may need additional error handling.

    }
}
