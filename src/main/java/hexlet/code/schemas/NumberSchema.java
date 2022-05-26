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

        for (Enum node : getChecks()) {

            if (node.equals(Flags.RANGE)) {
                if ((Integer) obj < min || (Integer) obj > max) {
                    return false;
                }
            }
            if (node.equals(Flags.NULL)) {
                if (obj == null) {
                    return false;
                }
            }
            if (node.equals(Flags.INT)) {
                if (!(obj instanceof Integer)) {
                    return false;
                }
            }
            if (node.equals(Flags.POSITIVE)) {
                if (Objects.equals(obj, null)) {
                    return !getChecks().contains(Flags.NULL);
                }
                if ((Integer) obj < 1) {
                    return false;
                }
            }


        }

        return true;
    }
}

