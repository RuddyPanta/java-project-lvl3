package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    private List<String> check = new ArrayList<>();
    private int min;
    private int max;

    public void required() {
        check.add("null");
        check.add("int");

    }

    public NumberSchema positive() {
        check.add("positive");
        return this;
    }

    public void range(int minInn, int maxInn) {
        check.add("range");
        this.min = minInn;
        this.max = maxInn;

    }


    @Override
    public boolean isValid(Object obj) {

        for (String str : check) {

            if (str.equals("range")) {
                if ((Integer) obj < min || (Integer) obj > max) {
                    return false;
                }
            }
            if (str.equals("null")) {
                if (obj == null) {
                    return false;
                }
            }
            if (str.equals("int")) {
                if (!(obj instanceof Integer)) {
                    return false;
                }
            }
            if (str.equals("positive")) {
                if (Objects.equals(obj, null)) {
                    return !check.contains("null");
                }
                if ((Integer) obj < 1) {
                    return false;
                }
            }


        }

        return true;
    }
}

