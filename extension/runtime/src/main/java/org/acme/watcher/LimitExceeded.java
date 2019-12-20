package org.acme.watcher;

public class LimitExceeded {

    public final long time;
    public final String methodInfo;

    public LimitExceeded(long time, String methodInfo) {
        this.time = time;
        this.methodInfo = methodInfo;
    }

}
