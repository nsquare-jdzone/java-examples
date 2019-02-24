package com.javadeveloperzone;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by JavaDeveloperZone on 2/5/2018.
 */
public class LongByteOperationUtil {

    public static void main(String[] args) throws IOException {
        LongByteOperationUtil byteOperationUtil = new LongByteOperationUtil();
        long number = 4444444L;
        System.out.println("***************************");
        byte [] intBytes = byteOperationUtil.longToByteArray(number);
        long num = byteOperationUtil.convertByteArrayToLong(intBytes);
        System.out.println("Num is :"+num);

        intBytes = byteOperationUtil.longToByteArray(number);
        num = byteOperationUtil.convertByteArrayToLong(intBytes);
        System.out.println("Num is :"+num);

        intBytes = byteOperationUtil.convertLongArrayToByteArray(new long[]{1,2,3});
        long [] longArray = byteOperationUtil.convertByteArrayToLongArray(intBytes);

        for(long l : longArray){
            System.out.println(l);
        }
        /*for(byte b : intBytes){
            System.out.println(b);
        }*/

    }

    private byte [] convertLongToByteArray(long number) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.putLong(number);
        return byteBuffer.array();
    }
    private static byte[] longtoBytes(long data) {
        return new byte[]{
                (byte) ((data >> 56) & 0xff),
                (byte) ((data >> 48) & 0xff),
                (byte) ((data >> 40) & 0xff),
                (byte) ((data >> 32) & 0xff),
                (byte) ((data >> 24) & 0xff),
                (byte) ((data >> 16) & 0xff),
                (byte) ((data >> 8) & 0xff),
                (byte) ((data >> 0) & 0xff),
        };
    }

    private byte[] longToByteArray ( final long i ) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeLong(i);
        dos.flush();
        return bos.toByteArray();
    }
    private long convertByteArrayToLong(byte[] longBytes){
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.put(longBytes);
        byteBuffer.flip();
        return byteBuffer.getLong();
    }
    private byte[] convertLongArrayToByteArray(long[] data) {
        if (data == null) return null;
        // ----------
        byte[] byts = new byte[data.length * Long.BYTES];
        for (int i = 0; i < data.length; i++)
            System.arraycopy(convertLongToByteArray(data[i]), 0, byts, i * Long.BYTES, Long.BYTES);
        return byts;
    }

    public long[] convertByteArrayToLongArray(byte[] data) {
        if (data == null || data.length % Long.BYTES != 0) return null;
        // ----------
        long[] longs = new long[data.length / Long.BYTES];
        for (int i = 0; i < longs.length; i++)
            longs[i] = ( convertByteArrayToLong(new byte[] {
                    data[(i*Long.BYTES)],
                    data[(i*Long.BYTES)+1],
                    data[(i*Long.BYTES)+2],
                    data[(i*Long.BYTES)+3],
                    data[(i*Long.BYTES)+4],
                    data[(i*Long.BYTES)+5],
                    data[(i*Long.BYTES)+6],
                    data[(i*Long.BYTES)+7],
            } ));
        return longs;
    }

    public long convertByteArrayToLongArrayByShift(byte[] data) {
        if (data == null || data.length % Long.BYTES != 0) return -1;
        // ----------
        return ( convertByteArrayToLong(new byte[] {
                data[(Long.BYTES)],
                data[(Long.BYTES)+1],
                data[(Long.BYTES)+2],
                data[(Long.BYTES)+3],
                data[(Long.BYTES)+4],
                data[(Long.BYTES)+5],
                data[(Long.BYTES)+6],
                data[(Long.BYTES)+7],
        } ));
    }

}
