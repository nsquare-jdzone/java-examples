package com.javadeveloperzone;

import java.nio.ByteBuffer;

/**
 * Created by JavaDeveloperZone on 2/5/2018.
 */
public class ByteOperationUtil {

    public static void main(String[] args) {
        ByteOperationUtil byteOperationUtil = new ByteOperationUtil();
        int number = 444;
        System.out.println("***************************");
        byte [] intBytes = byteOperationUtil.convertIntToByteArray(number);
        int num = byteOperationUtil.convertByteArrayToInt(intBytes);
        System.out.println("Num is :"+num);
        for(byte b : intBytes){
            System.out.println(b);
        }

        System.out.println("***************************");
        float floatNumber = 444.4f;
        intBytes = byteOperationUtil.convertFloatToByteArray(floatNumber);
        floatNumber = byteOperationUtil.convertByteArrayToFlat(intBytes);
        System.out.println("Num is :"+floatNumber);
        for(byte b : intBytes){
            System.out.println(b);
        }
        System.out.println("***************************");
        double doubleNumber = 888.4d;
        intBytes = byteOperationUtil.convertDoubleToByteArray(doubleNumber);
        doubleNumber = byteOperationUtil.convertByteArrayToDouble(intBytes);
        System.out.println("Num is :"+doubleNumber);
        for(byte b : intBytes){
            System.out.println(b);
        }

    }

    private byte [] convertIntToByteArray(int number) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(number);
        return byteBuffer.array();
    }

    private byte [] convertDoubleToByteArray(double number) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putDouble(number);
        return byteBuffer.array();
    }
    private byte [] convertFloatToByteArray(float number) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putFloat(number);
        return byteBuffer.array();
    }

    private int convertByteArrayToInt(byte[] intBytes){
        ByteBuffer byteBuffer = ByteBuffer.wrap(intBytes);
        return byteBuffer.getInt();
    }
    private byte[] convertIntArrayToByteArray(int[] data) {
        if (data == null) return null;
        // ----------
        byte[] byts = new byte[data.length * 4];
        for (int i = 0; i < data.length; i++)
            System.arraycopy(convertIntToByteArray(data[i]), 0, byts, i * 4, 4);
        return byts;
    }

    public int[] convertByteArrayToIntArray(byte[] data) {
        if (data == null || data.length % 4 != 0) return null;
        // ----------
        int[] ints = new int[data.length / 4];
        for (int i = 0; i < ints.length; i++)
            ints[i] = ( convertByteArrayToInt(new byte[] {
                    data[(i*4)],
                    data[(i*4)+1],
                    data[(i*4)+2],
                    data[(i*4)+3],
            } ));
        return ints;
    }

    private double convertByteArrayToDouble(byte[] doubleBytes){
        ByteBuffer byteBuffer = ByteBuffer.wrap(doubleBytes);
        return byteBuffer.getDouble();
    }
    private float convertByteArrayToFlat(byte[] floatBytes){
        ByteBuffer byteBuffer = ByteBuffer.wrap(floatBytes);
        return byteBuffer.getFloat();
    }
}
