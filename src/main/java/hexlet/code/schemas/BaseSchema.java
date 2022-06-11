package hexlet.code.schemas;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class BaseSchema {
    public final boolean isValid(Object obj) {
        return predicates.stream().allMatch(x -> x.test(obj));
    }

    public final void setPredicates(Predicate<Object> predicate) {
        this.predicates.add(predicate);
    }

    public final void predicatesClear() {
        predicates.clear();
    }

    private final List<Predicate<Object>> predicates = new ArrayList<>();
}
