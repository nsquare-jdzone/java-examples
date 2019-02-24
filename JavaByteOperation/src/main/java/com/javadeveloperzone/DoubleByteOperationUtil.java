package com.javadeveloperzone;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by JavaDeveloperZone on 2/5/2018.
 */
public class DoubleByteOperationUtil {

    public static void main(String[] args) throws IOException {
        DoubleByteOperationUtil byteOperationUtil = new DoubleByteOperationUtil();
        double number = 4444444.44d;
        System.out.println("***************************");
        byte [] doubleBytes = byteOperationUtil.doubleToByteArray(number);
        double num = byteOperationUtil.convertByteArrayToDouble(doubleBytes);
        System.out.println("Num is :"+num);

        doubleBytes = byteOperationUtil.doubleToByteArray(number);
        num = byteOperationUtil.convertByteArrayToDouble(doubleBytes);
        System.out.println("Num is :"+num);

        doubleBytes = byteOperationUtil.convertDoubleArrayToByteArray(new double[]{1.1,2.2,3.3});
        double [] longArray = byteOperationUtil.convertByteArrayToDoubleArray(doubleBytes);

        for(double l : longArray){
            System.out.println(l);
        }
        /*for(byte b : intBytes){
            System.out.println(b);
        }*/

    }

    private byte [] convertDoubleToByteArray(double number) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Double.BYTES);
        byteBuffer.putDouble(number);
        return byteBuffer.array();
    }
    private static byte[] doubletoBytes(double dblValue) {
        long data = Double.doubleToRawLongBits(dblValue);
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

    private byte[] doubleToByteArray ( final double i ) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeDouble(i);
        dos.flush();
        return bos.toByteArray();
    }
    private double convertByteArrayToDouble(byte[] doubleBytes){
        ByteBuffer byteBuffer = ByteBuffer.allocate(Double.BYTES);
        byteBuffer.put(doubleBytes);
        byteBuffer.flip();
        return byteBuffer.getDouble();
    }
    private byte[] convertDoubleArrayToByteArray(double[] data) {
        if (data == null) return null;
        // ----------
        byte[] byts = new byte[data.length * Double.BYTES];
        for (int i = 0; i < data.length; i++)
            System.arraycopy(convertDoubleToByteArray(data[i]), 0, byts, i * Double.BYTES, Double.BYTES);
        return byts;
    }

    public double[] convertByteArrayToDoubleArray(byte[] data) {
        if (data == null || data.length % Double.BYTES != 0) return null;
        // ----------
        double[] doubles = new double[data.length / Double.BYTES];
        for (int i = 0; i < doubles.length; i++)
            doubles[i] = ( convertByteArrayToDouble(new byte[] {
                    data[(i*Double.BYTES)],
                    data[(i*Double.BYTES)+1],
                    data[(i*Double.BYTES)+2],
                    data[(i*Double.BYTES)+3],
                    data[(i*Double.BYTES)+4],
                    data[(i*Double.BYTES)+5],
                    data[(i*Double.BYTES)+6],
                    data[(i*Double.BYTES)+7],
            } ));
        return doubles;
    }

    public double convertByteArrayToDoubleShiftOpr(byte[] data) {
        if (data == null || data.length % Double.BYTES != 0) return Double.NaN;
        // ----------

        return ( convertByteArrayToDouble(new byte[]{
                        data[(Double.BYTES)],
                        data[(Double.BYTES) + 1],
                        data[(Double.BYTES) + 2],
                        data[(Double.BYTES) + 3],
                        data[(Double.BYTES) + 4],
                        data[(Double.BYTES) + 5],
                        data[(Double.BYTES) + 6],
                        data[(Double.BYTES) + 7],
                }
        ));
    }
}
