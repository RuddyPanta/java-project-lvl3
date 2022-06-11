package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class StringSchema extends BaseSchema {
     public StringSchema required() {
        setPredicates(x -> x != null && !x.toString().isEmpty());
        return this;
    }

    public StringSchema contains(String str) {

        setPredicates(x -> x == null || x.toString().contains(str));
        return this;
    }

    public StringSchema minLength(int minLength) {
        predicatesClear();
        setPredicates(x -> x.toString().length() >= minLength);
        return this;
    }
}


