package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {

    private Map<String, BaseSchema> schemas = new HashMap<>();

    private int size;

    public void required() {
        setPredicates(x -> !Objects.equals(x, null) && x instanceof Map<?, ?>);
    }

    public void sizeof(int sizeInn) {
        setPredicates(x -> {
            HashMap<Object, Object> xInn = (HashMap<Object, Object>) x;
            return xInn.size() == size;
        });
        this.size = sizeInn;
    }

    public void shape(Map<String, BaseSchema> schemasInn) {

        setPredicates(x -> {

            HashMap<Object, Object> xInn = (HashMap<Object, Object>) x;

            StringSchema tempClassStringSchema = (StringSchema) schemas.get("name");
            NumberSchema tempClassNumberSchema = (NumberSchema) schemas.get("age");

            return tempClassStringSchema.isValid(xInn.get("name"))
                    && tempClassNumberSchema.isValid(xInn.get("age"));
        });
        this.schemas = schemasInn;
    }

    public boolean isValid(Object obj) {
        return getPredicates().stream().allMatch(x -> x.test(obj));
    }
}
