package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;


public final class StringSchema extends BaseSchema {
    private List<String> check = new ArrayList<>();
    private List<String> dataForCheck = new ArrayList<>();
    private int minLength;

    public StringSchema required() {
        check.add("null");
        return this;
    }

    public StringSchema contains(String str) {
        check.add("contains");
        dataForCheck.add(str);
        return this;
    }

    public StringSchema minLength(int minLengthInn) {
        check.clear();
        check.add("minLength");
        this.minLength = minLengthInn;
        return this;
    }

    @Override
    public boolean isValid(Object obj) {
        for (String str : check) {
            if (str.equals("null")) {
                if (obj == null || obj.equals("")) {
                    return false;
                }

            }

            if (str.equals("contains")) {

                for (String strCheck : dataForCheck) {
                    if (!obj.toString().contains(strCheck)) {
                        return false;
                    }
                }
            }

            if (str.equals("minLength")) {

                if ((obj.toString().length() < minLength)) {
                    System.out.println(obj.toString().length());
                    return false;
                }
            }
        }

        return true;
    }


}


