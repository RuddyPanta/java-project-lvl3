package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        setPredicates(x -> x instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        setPredicates(x -> Objects.equals(x, null) || x instanceof Integer && (Integer) x > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        setPredicates(x -> x instanceof Integer && (Integer) x >= min && (Integer) x <= max);
        return this;
    }
}

