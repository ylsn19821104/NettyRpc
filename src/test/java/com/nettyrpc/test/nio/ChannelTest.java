package com.nettyrpc.test.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by hongxp on 2017/9/27.
 */
public class ChannelTest {
    @Test
    public void test() {
        try {
            RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
            FileChannel fileChannel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(48);

            int count = fileChannel.read(buffer);
            while (count != -1) {
                System.err.println("Read " + count);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.err.println((char) buffer.get());
                }
                buffer.clear();
                count = fileChannel.read(buffer);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
