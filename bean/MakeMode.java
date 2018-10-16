package com.example.delamey.myapplication5.bean;

public class MakeMode {
    private  int id;
    private  String name;
    private  int age;
    private  String career;
    private  int  salary;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCareer() {
        return career;
    }

    public int getSalary() {
        return salary;
    }



    public  static class builder{
        private MakeMode makeMode=new MakeMode();
        public builder setId(int id) {
            makeMode.id = id;
            return this;
        }

        public builder setName(String name) {
            makeMode.name = name;
            return this;
        }

        public builder setAge(int age) {
            makeMode.age = age;
            return this;
        }

        public builder setCareer(String career) {
            makeMode.career = career;
            return this;
        }

        public builder setSalary(int salary) {
            makeMode.salary = salary;
            return this;
        }

        public MakeMode  build() {
            return makeMode;
        }
    }
}
