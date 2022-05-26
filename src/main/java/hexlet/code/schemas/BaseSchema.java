package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSchema {
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

    private final List<Enum> checks = new ArrayList<>();

}

