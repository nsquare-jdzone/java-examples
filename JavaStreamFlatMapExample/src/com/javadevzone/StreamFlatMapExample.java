package com.javadevzone;

import java.util.*;
import java.util.stream.*;

/**
 * Created by java developer zone on 5/21/2017.
 */
public class StreamFlatMapExample {


    public void stringArrayToString(){

        String[][] data = new String[][]{{"java", "scala"}, {"python"}, {"C", "C++"}};

        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);

        //Stream<String>
        Stream<String> langStream = temp.flatMap(x -> Arrays.stream(x));

        langStream.forEach(System.out::println);
        /*Stream<String> stream = stringStream.filter(x -> "a".equals(x.toString()));

        stream.forEach(System.out::println);*/

		/*Stream<String> stream = Arrays.stream(data)
                .flatMap(x -> Arrays.stream(x))
                .filter(x -> "a".equals(x.toString()));*/
    }

    public void stringSetToString(){

        List<Employee> employeeList = Employee.getEmployee();

        List<String> collect =
                employeeList.stream()
                        .map(x -> x.getLanguages())      //Stream<Set<String>>
                        .flatMap(x -> x.stream())   //Stream<String>
                        .distinct()
                        .collect(Collectors.toList());

        collect.forEach(x -> System.out.println(x));
    }

    public void stringListToString(){

        List<List<String>> stringList = new ArrayList<>();

        List<String> jvmLanguage = new ArrayList<>();
        jvmLanguage.add("Java");
        jvmLanguage.add("Scala");

        List<String> otherLanguage = new ArrayList<>();
        otherLanguage.add("C++");
        otherLanguage.add("Ruby");

        stringList.add(jvmLanguage);
        stringList.add(otherLanguage);

        List<String> collect =
                stringList.stream()
                        .flatMap(x -> x.stream())   //Stream<String>
                        .collect(Collectors.toList());


        collect.forEach(x -> System.out.println(x));
    }

    public void intToStream(){

        int[] intArray = {101, 102, 103};

        //1. Stream<int[]>
        Stream<int[]> streamArray = Stream.of(intArray);

        //2. Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));

        intStream.forEach(x -> System.out.println(x));
    }

    public void longToStream(){

        long[] intArray = {1000001L, 1000002L, 1000003L};

        //1. Stream<long[]>
        Stream<long[]> streamArray = Stream.of(intArray);

        //2. Stream<long[]> -> flatMap -> LongStream
        LongStream longStream = streamArray.flatMapToLong(x -> Arrays.stream(x));

        longStream.forEach(x -> System.out.println(x));
    }

    public void doubleToStream(){

        double[] intArray = {101.1d, 102.2d, 103.3d};

        //1. Stream<double[]>
        Stream<double[]> streamArray = Stream.of(intArray);

        //2. Stream<double[]> -> flatMap -> DoubleStream
        DoubleStream doubleStream = streamArray.flatMapToDouble(x -> Arrays.stream(x));

        doubleStream.forEach(x -> System.out.println(x));
    }

    public static void main(String[] args) {
        StreamFlatMapExample streamFlatMapExample = new StreamFlatMapExample();
        //streamFlatMapExample.stringArrayToString();

        //streamFlatMapExample.stringSetToString();
        //streamFlatMapExample.stringListToString();
        streamFlatMapExample.intToStream();
        streamFlatMapExample.longToStream();
        streamFlatMapExample.doubleToStream();
    }
}
