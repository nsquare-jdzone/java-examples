package com.javadevzone;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by java developer zone on 5/21/2017.
 */
public class StreamSortingExample {

    public void primitiveSort(){

        //Build IntStream
        IntStream intStream = IntStream.of(3, 2, 1, 2, 5);
        System.out.println("Sort IntStream");
        intStream.sorted().forEach(x -> System.out.print(x+","));//natural order sort & print

        //Build list of numbers
        List<Integer> numbers = Arrays.asList(3, 2, 1, 2, 5);
        System.out.println(System.lineSeparator()+"Sort list of int numbers");
        numbers.stream().sorted().forEach(x -> System.out.print(x+",")); //natural order sort & print


    }

    public void reverseSorting(){

        //Build list of numbers
        List<Integer> numbers = Arrays.asList(3, 2, 1, 2, 5);
        System.out.println(System.lineSeparator()+"Sort list of int numbers");
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(x -> System.out.print(x+",")); //reverse order sort & print
    }

    public static void main(String[] args) {
        StreamSortingExample streamSortingExample = new StreamSortingExample();
        //streamSortingExample.primitiveSort();
        //streamSortingExample.reverseSorting();

        //streamSortingExample.listSorting();
        //streamSortingExample.setSorting();
        //streamSortingExample.MapSorting();
        streamSortingExample.userDefindObjectSorting();

    }

    public void userDefindObjectSorting(){
        List<Employee> employeeList = Employee.getEmployee();
        System.out.println("---Natural Sorting by No---");
        List<Employee> slist = employeeList.stream().sorted(Comparator.comparing(Employee::getNo)).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getNo()+", Name: "+e.getName()));
    }

    public void listSorting(){
        List<Employee> employeeList = Employee.getEmployee();

        System.out.println("---Sorting using Comparator by Gender--");
        List<Employee> slist = employeeList.stream().sorted(Comparator.comparing(Employee::getGender)).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getNo()+", Name: "+e.getName()+", Gender:"+e.getGender()));
    }

    public void setSorting(){
        HashSet<Employee> employeeSet = new HashSet<>(Employee.getEmployee());

        System.out.println("---Sorting using Comparator by Designation---");
        List<Employee> slist= employeeSet.stream().sorted(Comparator.comparing(Employee::getDesignation)).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getNo()+", Name: "+e.getName()+", Designation:"+e.getDesignation()));
    }

    public void MapSorting(){
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "John");
        map.put(1, "gim");
        map.put(3, "orton");

        System.out.println("---Sort by Map Value---");
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(e -> System.out.println("Key: "+ e.getKey() +", Value: "+ e.getValue()));

        System.out.println("---Sort by Map Key---");
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(e -> System.out.println("Key: "+ e.getKey() +", Value: "+ e.getValue()));
    }
}
