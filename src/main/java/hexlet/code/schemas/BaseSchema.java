package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseSchema {

    private final List<Enum> checks = new ArrayList<>();

    public abstract boolean isValid(Object obj);

    public final List<Enum> getChecks() {
        return checks;
    }

    public final void setChecks(Enum check) {
        checks.add(check);
    }

    public final void clearChecks() {
        checks.clear();
    }

    public final boolean isTrueEnum(Enum value) {
        for (Enum check : checks) {
            if (Objects.equals(check, value)) {
                return true;
            }
        }

        return false;
    }


}

