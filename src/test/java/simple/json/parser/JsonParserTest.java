package simple.json.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonParserTest {

    private final String jsonString = "{\"name\":\"John\", \"data\":[{\"id\" : 1, \"value\": \"abc\"}, " +
            "{\"id\": 2, \"value\": \"def\"}]}";

    @Test
    void testSimpleJson () {
        JsonObject jsonObject = new JsonObject(jsonString);
        Assertions.assertEquals("John", jsonObject.getValue("name"));

        JsonArray jsonArray = jsonObject.getJsonArray("data");
        Assertions.assertNotNull(jsonArray);

        JsonObject firstIndexObj = jsonArray.getJsonObject(0);
        Assertions.assertNotNull(firstIndexObj);

        Assertions.assertEquals(Integer.toString(1), firstIndexObj.getValue("id"));
        Assertions.assertEquals("abc", firstIndexObj.getValue("value"));
    }

}
