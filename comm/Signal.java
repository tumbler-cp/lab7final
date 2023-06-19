package comm;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public enum Signal implements Serializable {
    COMMAND,
    TEXT,
    FILE,
    LOGIN,
    ACCEPT,
    DECLINE,
    REG,
    TEMP_CONN,
    CLOSING;
}
