package comm;

import java.io.Serializable;

public enum CommType implements Serializable {
    HELP,
    SHOW,
    INSERT,
    REMOVE_KEY,
    CLEAR,
    REMOVE_LOWER_KEY,
    UPDATE,
    REPLACE_IF_GREATER,
    INFO,
    PRINT_ASCENDING,
    FILTER_CONTAINS_NAME,
    PRINT_FIELD_ASCENDING_COLOR,
    EXECUTE_SCRIPT
}
