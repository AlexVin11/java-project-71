package hexlet.code;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValueState {

    public static final String EDITED = "edited";
    public static final String NOT_EDITED = "not edited";
    public static final String REMOVED = "removed";
    public static final String ADDED = "added";

    private String keyStatus;
    private Object oldValue;
    private Object newValue;

    ValueState(String keyStatus, Object oldValue, Object newValue) {
        keyStatus = keyStatus;
        oldValue = oldValue;
        newValue = newValue;
    }
}
