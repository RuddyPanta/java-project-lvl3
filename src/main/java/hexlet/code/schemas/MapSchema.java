package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;

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

        for (Enum note : getChecks()) {

            if (note.equals(Flags.NULL)) {
                if (obj == null) {
                    return false;
                }
            }

            if (note.equals(Flags.MAP)) {
                if (!(obj instanceof Map<?, ?>)) {
                    return false;
                }
            }

            if (note.equals(Flags.SIZE)) {
                //  HashMap<Object, Object> temp = (HashMap<Object, Object>) obj;
                if (!(tempObj.size() == size)) {
                    return false;
                }
            }

            if (note.equals(Flags.SCHEMAS)) {

                StringSchema tempClassStringSchema = (StringSchema) schemas.get("name");
                NumberSchema tempClassNumberSchema = (NumberSchema) schemas.get("age");

                return tempClassStringSchema.isValid(tempObj.get("name"))
                        && tempClassNumberSchema.isValid(tempObj.get("age"));

            }
        }

        return true;
    }
}
