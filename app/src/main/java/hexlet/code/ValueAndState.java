package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValueAndState {

    private String keyStatus;
    private Object oldValue;
    private Object newValue;
}
