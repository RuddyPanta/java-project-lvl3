package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class StringSchema extends BaseSchema {
    private final List<String> dataForCheck = new ArrayList<>();
    private int minLength;



    public StringSchema required() {
        setPredicates(x -> !Objects.equals(x, null) && !Objects.equals(x, ""));
        return this;
    }

    public StringSchema contains(String str) {

        setPredicates(x -> {
            if (!Objects.equals(x, null)) {
                for (String strCheck : dataForCheck) {
                    if (!x.toString().contains(strCheck)) {
                        return false;
                    }
                }
            }
            return true;
        });
        dataForCheck.add(str);
        return this;
    }

    public StringSchema minLength(int minLengthInn) {
        predicatesClear();
        setPredicates(x -> x.toString().length() >= this.minLength);
        this.minLength = minLengthInn;
        return this;
    }

    public boolean isValid(Object obj) {
        return getPredicates().stream().allMatch(x -> x.test(obj));
    }

}


