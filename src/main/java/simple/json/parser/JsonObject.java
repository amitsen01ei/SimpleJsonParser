package simple.json.parser;

import java.util.HashMap;
import java.util.Map;

import static simple.json.parser.constants.Constants.*;

public final class JsonObject {

    private final char special;
    private final char comma;
    private Map<String, String> jsonObjects;

    public JsonObject(String jsonStr) {
        this.special = SPECIAL.charValue();
        this.comma = COMMA.charValue();
        this.getJSONObjects(jsonStr);
    }

    private void getJSONObjects(String jsonStr) {

        jsonObjects = new HashMap<>();

        if (jsonStr.startsWith(CURLY_OPEN.toString()) && jsonStr.endsWith(CURLY_CLOSE.toString())) {

            StringBuilder jsonBuilder = new StringBuilder(jsonStr);
            jsonBuilder.deleteCharAt(0);
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);

            replaceCOMMA(jsonBuilder);

            for (String jsonObject : jsonBuilder.toString().split(COMMA.toString())) {

                String[] objectValue = jsonObject.split(COLON.toString(), 2);

                if (objectValue.length == 2)
                    this.jsonObjects.put(objectValue[0]
                                    .replace("'", "")
                                    .replace("\"", "").trim(),
                            objectValue[1]
                                    .replace("'", "")
                                    .replace("\"", "").trim());
            }
        }
    }

    public void replaceCOMMA(StringBuilder jsonBuilder) {

        boolean isJsonArray = false;
        int jsonBuilderLength = jsonBuilder.length();

        for (int i = 0; i < jsonBuilderLength; i++) {

            char ch = jsonBuilder.charAt(i);

            if (isJsonArray) {

                if (String.valueOf(ch).equals(COMMA.toString())) {
                    jsonBuilder.setCharAt(i, special);
                }
            }

            if (String.valueOf(ch).equals(SQUARE_OPEN.toString())) {
                isJsonArray = true;
            }

            if (String.valueOf(ch).equals(SQUARE_CLOSE.toString())) {
                isJsonArray = false;
            }
        }
    }

    public String getValue(String key) {
        if (jsonObjects != null) {
            return jsonObjects.get(key).replace(special, comma);
        }
        return null;
    }

    public JsonArray getJsonArray(String key) {
        return jsonObjects != null ? new JsonArray(jsonObjects.get(key).replace(special, comma)) : null;
    }
}
