package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;


public final class StringSchema extends BaseSchema {
    private final List<String> dataForCheck = new ArrayList<>();
    private int minLength;

    public StringSchema required() {
        setChecks(Flags.NULL);
        return this;
    }

    public StringSchema contains(String str) {
        setChecks(Flags.CONTAINS);
        dataForCheck.add(str);
        return this;
    }

    public StringSchema minLength(int minLengthInn) {
        clearChecks();
        setChecks(Flags.MIN_LENGTH);
        this.minLength = minLengthInn;
        return this;
    }

    public boolean isValid(Object obj) {

        for (Enum anEnum : getChecks()) {
            if (anEnum.equals(Flags.NULL)) {
                if (obj == null || obj.equals("")) {

                    return false;
                }
            }

            if (anEnum.equals(Flags.CONTAINS)) {

                for (String strCheck : dataForCheck) {
                    if (!obj.toString().contains(strCheck)) {
                        return false;
                    }
                }
            }

            if (anEnum.equals(Flags.MIN_LENGTH)) {

                if ((obj.toString().length() < minLength)) {

                    return false;
                }
            }
        }

        return true;
    }


}


