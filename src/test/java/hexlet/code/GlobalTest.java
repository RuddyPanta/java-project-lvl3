package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalTest {

    static final int NUM_MINUS_10 = -10;
    static final int NUM_MINUS_5 = -5;
    static final int NUM_4 = 4;
    static final int NUM_5 = 5;
    static final int NUM_6 = 6;
    static final int NUM_10 = 10;
    static final int NUM_11 = 11;
    static final int NUM_50 = 50;
    static final int NUM_100 = 100;

    @Test
    void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        List<Boolean> results = new ArrayList<>();

        results.add(schema.isValid("")); //true
        results.add(schema.isValid(null)); //true

        schema.required();

        results.add(schema.isValid("what does the fox say")); //true
        results.add(schema.isValid("hexlet")); // true
        results.add(schema.isValid(null)); // false
        results.add(schema.isValid("")); // false

        results.add(schema.contains("what").isValid("what does the fox say")); // true
        results.add(schema.contains("whatthe").isValid("what does the fox say")); // false

        results.add(schema.isValid("what does the fox say")); // false
        results.add(schema.minLength(NUM_5).isValid("what does the fox say")); // true
        results.add(schema.minLength(NUM_50).isValid("what does the fox say")); // false

        List<Boolean> expected = new ArrayList<>();
        expected.add(true);
        expected.add(true);
        expected.add(true);
        expected.add(true);
        expected.add(false);
        expected.add(false);
        expected.add(true);
        expected.add(false);
        expected.add(false);
        expected.add(true);
        expected.add(false);

        Assertions.assertEquals(results, expected);

        System.out.println("Test StringSchema is compliant");

    }

    @Test
    void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        List<Boolean> results = new ArrayList<>();

        results.add(schema.isValid(null)); // true

        schema.required();

        results.add(schema.isValid(null)); // false
        results.add(schema.isValid(NUM_10)); // true
        results.add(schema.isValid("5")); // false

        results.add(schema.positive().isValid(NUM_10)); // true
        results.add(schema.isValid(NUM_MINUS_10)); // false

        schema.range(NUM_5, NUM_10);

        results.add(schema.isValid(NUM_5)); // true
        results.add(schema.isValid(NUM_10)); // true
        results.add(schema.isValid(NUM_4)); // false
        results.add(schema.isValid(NUM_11)); // false

        schema.range(NUM_6, NUM_10);

        results.add(schema.isValid(NUM_5)); // false

        List<Boolean> expected = new ArrayList<>();
        expected.add(true);
        expected.add(false);
        expected.add(true);
        expected.add(false);
        expected.add(true);
        expected.add(false);
        expected.add(true);
        expected.add(true);
        expected.add(false);
        expected.add(false);
        expected.add(false);

        Assertions.assertEquals(results, expected);

        System.out.println("Test NumberSchema is compliant");
    }

    @Test
    void testMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        List<Boolean> results = new ArrayList<>();

        results.add(schema.isValid(null)); // true

        schema.required();

        results.add(schema.isValid(null)); // false
        results.add(schema.isValid(new HashMap())); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        results.add(schema.isValid(data)); // true

        schema.sizeof(2);

        results.add(schema.isValid(data));  // false
        data.put("key2", "value2");
        results.add(schema.isValid(data)); // true

        List<Boolean> expected = new ArrayList<>();
        expected.add(true);
        expected.add(false);
        expected.add(true);
        expected.add(true);
        expected.add(false);
        expected.add(true);

        Assertions.assertEquals(results, expected);

        System.out.println("Test MapSchema is compliant");
    }

    @Test
    void testMapSchemaComplicated() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        List<Boolean> results = new ArrayList<>();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", NUM_100);
        results.add(schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null); // true
        results.add(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        results.add(schema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", NUM_MINUS_5);
        results.add(schema.isValid(human4)); // false

        List<Boolean> expected = new ArrayList<>();
        expected.add(true);
        expected.add(true);
        expected.add(false);
        expected.add(false);

        Assertions.assertEquals(results, expected);

        System.out.println("Test MapSchemaComplicated is compliant");
    }
}
