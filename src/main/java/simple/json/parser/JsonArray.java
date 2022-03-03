package simple.json.parser;

import simple.json.parser.constants.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static simple.json.parser.constants.Constants.*;

public final class JsonArray {

    private final char special;
    private final char comma;
    private List<String> jsonObjects;

    public JsonArray(String jsonStr) {
        this.special = Constants.SPECIAL.charValue();
        this.comma = COMMA.charValue();
        this.getJsonObjects(jsonStr);
    }

    private void getJsonObjects(String arg) {

        jsonObjects = new ArrayList<>();

        if (arg.startsWith(SQUARE_OPEN.toString()) && arg.endsWith(SQUARE_CLOSE.toString())) {

            StringBuilder jsonBuilder = new StringBuilder(arg);

            jsonBuilder.deleteCharAt(0);
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);

            replaceCOMMA(jsonBuilder);

            Collections.addAll(jsonObjects, jsonBuilder.toString().split(COMMA.toString()));
        }
    }


    public void replaceCOMMA(StringBuilder jsonBuilder) {

        boolean isArray = false;
        int jsonBuilderLength = jsonBuilder.length();

        for (int i = 0; i < jsonBuilderLength; i++) {

            char ch = jsonBuilder.charAt(i);

            if (isArray) {

                if (String.valueOf(ch).equals(COMMA.toString())) {
                    jsonBuilder.setCharAt(i, special);
                }
            }

            if (String.valueOf(ch).equals(CURLY_OPEN.toString())) {
                isArray = true;
            }

            if (String.valueOf(ch).equals(CURLY_CLOSE.toString())) {
                isArray = false;
            }
        }
    }

    public String getObject(int index) {
        if (jsonObjects != null) {
            return jsonObjects.get(index).replace(special, comma);
        }
        return null;
    }

    public JsonObject getJsonObject(int index) {
        return jsonObjects != null ? new JsonObject(jsonObjects.get(index).replace(special, comma)) : null;
    }
}
