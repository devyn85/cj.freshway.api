package cjfw.core.web.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper {

    private ByteArrayOutputStream cachedByte;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (cachedByte == null) cacheInputStream();

        return new CachedServletInputStream(cachedByte.toByteArray());
    }

    @Override
    public BufferedReader getReader() throws IOException {

        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    private void cacheInputStream() throws IOException {
        cachedByte = new ByteArrayOutputStream();
        IOUtils.copy(super.getInputStream(), cachedByte);
        //log.info("cachedByte - {}", cachedByte);
    }

    public static class CachedServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream buffer;

        public CachedServletInputStream(byte[] contents) {
            this.buffer = new ByteArrayInputStream(contents);
        }

        @Override
        public int read() {

            return buffer.read();
        }

        @Override
        public boolean isFinished() {

            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {

            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {

            throw new RuntimeException("Not implemented"); 
        }
    }
}