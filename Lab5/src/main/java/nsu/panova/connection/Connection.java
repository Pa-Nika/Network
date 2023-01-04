package nsu.panova.connection;


import nsu.panova.handler.Handler;

import java.nio.ByteBuffer;

public abstract class Connection implements Handler {
    private boolean disconnect = false;

    abstract void linkBuffer(ByteBuffer clientBuffer);

    public void setDisconnect() {
        disconnect = true;
    }

    public boolean getDisconnect() {
        return disconnect;
    }
}
