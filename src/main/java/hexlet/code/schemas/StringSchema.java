package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema required() {
        setPredicates(x -> x != null && !x.toString().isEmpty());
        return this;
    }

    public StringSchema contains(String str) {
        setPredicates(x -> x == null || x.toString().contains(str));
        return this;
    }

    public void minLength(int minLength) {
        setPredicates(x -> x.toString().length() >= minLength);
    }
}


