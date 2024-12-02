package com.mikayelovich.interview;

/**
 * 1. FIFO
 * 2. Ring buffer, use all space in the buffer.
 * 3. Throw exception in corner cases:
 * a) no free space on put
 * b) no data on get
 * Example:
 * created empty buffer with size = 3   _,_,_
 * get -> no data, buffer is empty - exception
 * put 1 ->     1,_,_
 * put 2 ->     1,2,_
 * put 3 ->     1,2,3
 * put 4 -> buffer is full - exception
 * get -> 1     _,2,3
 * put 4 ->     4,2,3
 * get -> 2     4,_,3
 * 4. Do not change public interface
 * 5. Act by TDD.
 */
public class RingBuffer {

    private final Object[] data;
    private int pointerForPut;
    private int pointerForGet;
    public RingBuffer(int maxBufferSize) {
        data = new Object[maxBufferSize];
    }

    public void put(Object o) {
        if (pointerForPut == data.length) {
            throw new BufferIsFullException("buffer is full");
        }
        data[pointerForPut++] = o;
    }

    public Object get() {
        if (pointerForPut == 0 && pointerForGet == 0) {//no data at all
            throw new EmptyBufferException("no data");
        }
        if (pointerForPut == data.length) {
            pointerForPut = 0;
        }
        if (pointerForGet == data.length) {
            pointerForGet = 0;
        }
        return data[pointerForGet++];
    }
}