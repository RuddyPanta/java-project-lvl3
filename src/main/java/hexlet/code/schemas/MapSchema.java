package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private List<String> check = new ArrayList<>();
    private Map<String, BaseSchema> schemas = new HashMap<>();

    private int size;

    public void required() {
        check.add("map");
        check.add("null");
    }

    public void sizeof(int sizeInn) {
        check.add("size");
        this.size = sizeInn;
    }

    public void shape(Map<String, BaseSchema> schemasInn) {
        check.add("schemas");
        this.schemas = schemasInn;
    }

    @Override
    public boolean isValid(Object obj) {

        for (String str : check) {

            if (str.equals("null")) {
                if (obj == null) {
                    return false;
                }
            }

            if (str.equals("map")) {
                if (!(obj instanceof Map<?, ?>)) {
                    return false;
                }
            }

            if (str.equals("size")) {
                HashMap<Object, Object> temp = (HashMap<Object, Object>) obj;
                if (!(temp.size() == size)) {
                    return false;
                }
            }

            if (str.equals("schemas")) {
                StringSchema tempClassStringSchema = (StringSchema) schemas.get("name");
                NumberSchema tempClassNumberSchema = (NumberSchema) schemas.get("age");
                HashMap tempObj = (HashMap) obj;
                tempClassStringSchema.isValid(tempObj.get("name"));
                tempClassNumberSchema.isValid(tempObj.get("age"));
                return tempClassStringSchema.isValid(tempObj.get("name"))
                        && tempClassNumberSchema.isValid(tempObj.get("age"));

            }
        }

        return true;
    }
}
