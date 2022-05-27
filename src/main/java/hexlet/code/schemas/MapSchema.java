package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {

    private Map<String, BaseSchema> schemas = new HashMap<>();

    private int size;

    public void required() {
        setChecks(Flags.MAP);
        setChecks(Flags.NULL);
    }

    public void sizeof(int sizeInn) {
        setChecks(Flags.SIZE);
        this.size = sizeInn;
    }

    public void shape(Map<String, BaseSchema> schemasInn) {
        setChecks(Flags.SCHEMAS);
        this.schemas = schemasInn;
    }

    public boolean isValid(Object obj) {
        HashMap<Object, Object> tempObj = (HashMap<Object, Object>) obj;

        if (isTrueEnum(Flags.NULL) && Objects.equals(obj, null)) {
            return false;
        }

        if (isTrueEnum(Flags.MAP) && (!(obj instanceof Map<?, ?>))) {
            return false;
        }

        if (isTrueEnum(Flags.SIZE) && (!(tempObj.size() == size))) {
            return false;
        }

        if (isTrueEnum(Flags.SCHEMAS)) {
            StringSchema tempClassStringSchema = (StringSchema) schemas.get("name");
            NumberSchema tempClassNumberSchema = (NumberSchema) schemas.get("age");

            return tempClassStringSchema.isValid(tempObj.get("name"))
                    && tempClassNumberSchema.isValid(tempObj.get("age"));
        }

        return true;
    }
}
