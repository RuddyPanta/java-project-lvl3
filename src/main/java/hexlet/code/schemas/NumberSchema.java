package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    private int min;
    private int max;

    public void required() {
        setChecks(Flags.NULL);
        setChecks(Flags.INT);

    }

    public NumberSchema positive() {
        setChecks(Flags.POSITIVE);
        return this;
    }

    public void range(int minInn, int maxInn) {
        setChecks(Flags.RANGE);
        this.min = minInn;
        this.max = maxInn;

    }

    public boolean isValid(Object obj) {

        if (isTrueEnum(Flags.RANGE) && ((Integer) obj < min || (Integer) obj > max)) {
            return false;
        }

        if (isTrueEnum(Flags.NULL) && (Objects.equals(obj, null))) {
            return false;
        }

        if (isTrueEnum(Flags.INT) && (!(obj instanceof Integer))) {
            return false;
        }

        if (isTrueEnum(Flags.POSITIVE) && (Objects.equals(obj, null))) {
            return !getChecks().contains(Flags.NULL);
        } else if (isTrueEnum(Flags.POSITIVE) && ((Integer) obj < 1)) {
            return false;
        }

        return true;
    }
}

