package com.javadevzone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by java developer zone on 5/21/2017.
 */
public class Employee implements Comparable<Employee>{
    private int no;
    private String name;
    private String designation;
    private String gender;
    private Set<String> languages;

    public Set<String> getLanguages() {
        return languages;
    }

    public Employee(int no, String name, String designation, String gender , String [] languages) {
        this.no = no;
        this.name = name;

        this.designation = designation;
        this.gender = gender;
        this.languages = new HashSet<>(Arrays.asList(languages));
    }

    public void addLanguage(String language){
        this.languages.add(language);
    }

    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return this.no + " : " + this.name + " : " + gender + " : " + designation;
    }

    @Override
    public int hashCode() {
        int hashno = 7;
        hashno = 13 * hashno + (name == null ? 0 : name.hashCode());
        return hashno;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final Employee std = (Employee) obj;
        if (this == std) {
            return true;
        } else {
            return (this.name.equals(std.name)
                    && (this.no == std.no)) && (this.designation.equals(std.designation))
                    && (this.gender.equals(std.gender));
        }
    }

    public static java.util.List<Employee> getEmployee() {
        java.util.List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Bob", "Developer", "Male",new String[]{"java","scala"}));
        employees.add(new Employee(2, "Joy", "Sr. Developer", "Male",new String[]{"java"}));
        employees.add(new Employee(3, "John", "CEO", "Male",new String[]{"python","ruby"}));
        employees.add(new Employee(4, "Bat", "Developer", "Male",new String[]{"scala"}));
        employees.add(new Employee(5, "Jolly", "Developer", "Female",new String[]{"C","C++"}));
        employees.add(new Employee(6, "Bobby", "Developer", "Female",new String[]{".Net","VB"}));
        return employees;
    }

    @Override
    public int compareTo(Employee o) {
        return name.compareTo(o.getName());
    }

}