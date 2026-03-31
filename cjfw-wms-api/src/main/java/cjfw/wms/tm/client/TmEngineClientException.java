package cjfw.wms.tm.client;

public class TmEngineClientException extends RuntimeException {
    public TmEngineClientException(String msg) {
        super(msg);
    }
    public TmEngineClientException(String msg, Throwable t) {
        super(msg, t);
    }
}
