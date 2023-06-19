package comm;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public record Request(Signal signal, Object object) implements Serializable {
    public byte[] bytes() {
        return SerializationUtils.serialize(this);
    }
}
