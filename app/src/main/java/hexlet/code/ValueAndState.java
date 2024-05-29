package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValueAndState {

    public static final String EDITED = "edited";
    public static final String NOT_EDITED = "not edited";
    public static final String REMOVED = "removed";
    public static final String ADDED = "added";

    private String keyStatus;
    private Object oldValue;
    private Object newValue;
}
