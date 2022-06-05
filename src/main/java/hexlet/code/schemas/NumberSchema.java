package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    private int min;
    private int max;

    public void required() {
        setPredicates(x -> x instanceof Integer);
    }

    public NumberSchema positive() {
        setPredicates(x -> Objects.equals(x, null) || x instanceof Integer && (Integer) x > 0);
        return this;
    }

    public void range(int minInn, int maxInn) {
        setPredicates(x -> x instanceof Integer && (Integer) x >= this.min && (Integer) x <= this.max);
        this.max = maxInn;
        this.min = minInn;
    }

    @Override
    public boolean isValid(Object obj) {
        return getPredicates().stream().allMatch(x -> x.test(obj));
    }
}

