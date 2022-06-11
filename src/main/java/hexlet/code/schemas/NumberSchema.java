package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    public void required() {
        setPredicates(x -> x instanceof Integer);
    }

    public NumberSchema positive() {
        setPredicates(x -> Objects.equals(x, null) || x instanceof Integer && (Integer) x > 0);
        return this;
    }

    public void range(int min, int max) {
        setPredicates(x -> x instanceof Integer && (Integer) x >= min && (Integer) x <= max);
    }
}

