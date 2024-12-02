package com.mikayelovich.interview;

public class BufferIsFullException  extends RuntimeException{
    public BufferIsFullException(String message) {
        super(message);
    }
}
