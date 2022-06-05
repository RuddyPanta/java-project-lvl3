package hexlet.code.schemas;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema {
    public abstract boolean isValid(Object obj);

    public final List<Predicate<Object>> getPredicates() {
        return predicates;
    }

    public final void setPredicates(Predicate<Object> predicate) {
        this.predicates.add(predicate);
    }

    public final void predicatesClear() {
        predicates.clear();
    }

    private List<Predicate<Object>> predicates = new ArrayList<>();
}
