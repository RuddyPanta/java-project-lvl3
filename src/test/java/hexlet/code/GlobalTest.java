package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

public class GlobalTest {

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
        results.add(schema.minLength(5).isValid("what does the fox say")); // true
        results.add(schema.minLength(50).isValid("what does the fox say")); // false

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
        results.add(schema.isValid(10)); // true
        results.add(schema.isValid("5")); // false

        results.add(schema.positive().isValid(10)); // true
        results.add(schema.isValid(-10)); // false

        schema.range(5, 10);

        results.add(schema.isValid(5)); // true
        results.add(schema.isValid(10)); // true
        results.add(schema.isValid(4)); // false
        results.add(schema.isValid(11)); // false

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

        Assertions.assertEquals(results, expected);

        System.out.println("Test NumberSchema is compliant");
    }
}
