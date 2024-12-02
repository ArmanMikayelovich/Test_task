package com.mikayelovich.interview;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
class RingBufferTest {

    @Test
    void createEmptyRingBuffer_getElement_shouldThrowException() {
        RingBuffer ringBuffer = new RingBuffer(3);
        assertThrows(EmptyBufferException.class, ringBuffer::get);
    }


    @Test
    void createEmptyRingBuffer_putElement_ElementShouldBeAtFirstIndex() {
        RingBuffer ringBuffer = new RingBuffer(3);
        Object firstElement = new Object();
        ringBuffer.put(firstElement);
        assertEquals(firstElement, ringBuffer.get());
    }

    @Test
    void createEmptyRingBuffer_putTwoElement_GetTwoElementsInWayWePut() {
        RingBuffer ringBuffer = new RingBuffer(3);
        int count = 2;
        Object[] elements = new Object[count];
        for (int x = 0; x < elements.length; x++) {
            Object o = new Object();
            elements[x] = o;
            ringBuffer.put(o);
        }

        Object[] result = new Object[count];
        for (int x = 0; x < elements.length; x++) {
            result[x] = ringBuffer.get();
        }
        assertArrayEquals(elements, result);
    }

    @Test
    void createEmptyRingBuffer_putThreeElement_GetThreeElementsInWayWePut() {
        RingBuffer ringBuffer = new RingBuffer(3);
        int count = 3;
        String[] elements = new String[count];
        for (int x = 0; x < elements.length; x++) {
            String value = String.valueOf(x);
            elements[x] = value;
            ringBuffer.put(value);
        }
        String[] result = new String[count];
        for (int x = 0; x < elements.length; x++) {
            result[x] = (String) ringBuffer.get();
        }
        assertArrayEquals(elements, result);
    }


    @Test
    void createEmptyRingBuffer_putMorElementsThatBufferCanHold_ShouldThrowBufferIsFullException() {
        int bufferSize = 3;
        RingBuffer ringBuffer = new RingBuffer(bufferSize);
        ringBuffer.put(new Object());
        ringBuffer.put(new Object());
        ringBuffer.put(new Object());
        assertThrows(BufferIsFullException.class, () -> ringBuffer.put(new Object()));
    }

    @Test
    void createEmptyRingBuffer_putThreeElement_GetFirstElementShouldEqualFirstPutElement() {
        RingBuffer ringBuffer = new RingBuffer(3);
        int count = 3;
        String[] elements = new String[count];
        for (int x = 0; x < elements.length; x++) {
            String value = String.valueOf(x);
            elements[x] = value;
            ringBuffer.put(value);
        }
        assertEquals(elements[0], ringBuffer.get());
        ringBuffer.put("4");
        assertEquals(elements[1], ringBuffer.get());
    }

    //put 3 elements
    //getOne
    //putOne
    //getTwo
    //putTwo
    //getThree
    @Test
    void createRingBuffer_checkAllFunctionality() {
        RingBuffer ringBuffer = new RingBuffer(3);

        ringBuffer.put("1");
        ringBuffer.put("2");
        ringBuffer.put("3");


        assertEquals("1", ringBuffer.get());

        ringBuffer.put("4");
        assertEquals("2", ringBuffer.get());
        assertEquals("3", ringBuffer.get());

        ringBuffer.put("5");
        ringBuffer.put("6");

        assertEquals("4", ringBuffer.get());
        assertEquals("5", ringBuffer.get());
        assertEquals("6", ringBuffer.get());

    }
}