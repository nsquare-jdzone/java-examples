package com.javadeveloperzone;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListDuplicateObjectExample {

    public static void main(String[] args) {
        ListDuplicateObjectExample listDuplicateObjectExample = new ListDuplicateObjectExample();
//        listDuplicateObjectExample.duplicateUsingSet();
//        listDuplicateObjectExample.duplicateUsingHashMap();
//        listDuplicateObjectExample.duplicateUsingBruteForce();
        listDuplicateObjectExample.duplicateUserDefindObjects();
    }

    public void duplicateUsingSet(){
        java.util.List<String> duplicates = Arrays.asList("A", "B", "B", "C", "D", "D", "Z", "E", "E");
        java.util.HashSet<String> unique=new HashSet();
        System.out.println("Java Find duplicate objects in list using Set");
        for (String s:duplicates){
            if(!unique.add(s)){   //  // java.util.Set only unique object so if object will not bee add in Set it will return false so can consider it as Duplicate
                System.out.println(s);
            }
        }
    }

    public void duplicateStreamGroupBy(){
        java.util.List<String> list = Arrays.asList("A", "B", "B", "C", "D", "D", "Z", "E", "E");
        list.stream().collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()))                                             // perform group by count
                .entrySet().stream()
                .filter(e -> e.getValue() > 1L)                                        // using take only those element whose count is greater than 1
                .map(e -> e.getKey())                                                  // using map take only value
                .collect(Collectors.toList())               // convert group by result to list
                .forEach(System.out::println);
    }

    public void duplicateUsingHashMap(){
        java.util.List<String> list = Arrays.asList("A", "B", "B", "C", "D", "D", "Z", "E", "E");
        Map<String,Integer> valueCounter = new HashMap<>();
        for(String str : list){
            Integer val = valueCounter.get(str);
            if(valueCounter.get(str)==null){
                valueCounter.put(str,1);
            }else{
                val = val+1;//val++, ++val
                valueCounter.put(str,val);
            }
        }
        Set<Map.Entry<String, Integer>> entrySet = valueCounter.entrySet();
        for(Map.Entry<String,Integer> entry : entrySet){
            if(entry.getValue()>1){
                System.out.println(entry.getKey()+"=>"+entry.getValue());
            }
        }
        /*valueCounter.entrySet().stream() //we can also use stream api to achieve the same
                .filter(map -> map.getValue()>1)
                .forEach(map -> System.out.println(map.getKey()+"=>"+map.getValue()));*/
    }
    
    public void duplicateUsingLoops(){
        java.util.List<String> list = Arrays.asList("A", "B", "B", "C", "D", "D", "Z", "E", "E");
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j <list.size() ; j++) {
                if(list.get(i).equals(list.get(j))){
                    System.out.println(list.get(i));
                }
            }
        }
    }

    public void duplicateUserDefindObjects(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"John","street 1,"));
        employees.add(new Employee(2,"Frank","Nr. Cosmos,"));
        employees.add(new Employee(3,"Danyy","street 101,Washington DC,"));
        employees.add(new Employee(1,"John","street 1,"));
        employees.add(new Employee(2,"Frank","Nr. Cosmos,"));

        employees.stream().collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1L)
                .map(e -> e.getKey())
                .collect(Collectors.toList())
                .forEach(employee -> System.out.println(employee.getEmpId()+"==>"+employee.getEmpName()));
    }
}
