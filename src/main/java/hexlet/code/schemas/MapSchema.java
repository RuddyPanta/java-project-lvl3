package hexlet.code.schemas;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {


    public MapSchema required() {
        setPredicates(x -> !Objects.equals(x, null) && x instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int sizeInn) {
        setPredicates(x -> ((HashMap<Object, Object>) x).size() == sizeInn);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemasInn) {

        setPredicates(x -> {

            List<Boolean> b = new ArrayList<>();
            schemasInn.forEach((k, v) -> {
                b.add(v.isValid(((HashMap<?, ?>) x).get(k)));
            });

            return !b.contains(false);
        });
        return this;
    }
}
