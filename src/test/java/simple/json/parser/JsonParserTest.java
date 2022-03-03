package simple.json.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    private final String jsonStringWithDoubleQuote = "{\"name\":\"John\", \"data\":[{\"id\" : 1, \"value\": \"abc\"},"
            + "{\"id\": 2, \"value\": \"def\"}]}";

    private final String jsonStringWithSingleQuote = "{'name':'John', 'data':[{'id' : 1, 'value': 'abc'}, " +
            "{'id': 2, 'value': 'def'}]}";

    @Test
    void testSimpleJsonWithDoubleQuote () {
        JsonObject jsonObject = new JsonObject(jsonStringWithDoubleQuote);
        assertEquals("John", jsonObject.getValue("name"));

        JsonArray jsonArray = jsonObject.getJsonArray("data");
        assertNotNull(jsonArray);

        JsonObject firstIndexObj = jsonArray.getJsonObject(0);
        assertNotNull(firstIndexObj);

        assertEquals(Integer.toString(1), firstIndexObj.getValue("id"));
        assertEquals("abc", firstIndexObj.getValue("value"));
    }

    @Test
    void testSimpleJsonWithSingleQuote () {
        JsonObject jsonObject = new JsonObject(jsonStringWithSingleQuote);
        assertEquals("John", jsonObject.getValue("name"));

        JsonArray jsonArray = jsonObject.getJsonArray("data");
        assertNotNull(jsonArray);

        JsonObject firstIndexObj = jsonArray.getJsonObject(0);
        assertNotNull(firstIndexObj);

        assertEquals(Integer.toString(1), firstIndexObj.getValue("id"));
        assertEquals("abc", firstIndexObj.getValue("value"));
    }

}
