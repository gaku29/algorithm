package com.gaku.netty;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {

    public static void main(String[] args) {

        IntBuffer buffer = IntBuffer.allocate(8);
//        ByteBuffer buffer1 = ByteBuffer.allocate(10);
//        ByteBuffer buffer2 = ByteBuffer.allocateDirect(10);

        for(int i = 0; i < buffer.capacity(); i++){
            int j = 2 * (i + 1);
            buffer.put(j);
        }

        buffer.flip();

        while (buffer.hasRemaining()){
            int j = buffer.get();
            System.out.println(j);
        }

    }
}
