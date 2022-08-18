package com.example.database_student_crud;

public class Student {

    private int Id ;
    private String Name ;
    private int Age;
    private int ImageId ;

    public Student(String name, int age, int imageId) {
        Name = name;
        Age = age;
        ImageId = imageId;
    }

    public Student(int id, String name, int age, int imageId) {
        Id=id;
        Name = name;
        Age = age;
        ImageId = imageId;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "Id=" + Id +
//                ", Name='" + Name + '\'' +
//                ", Age=" + Age +
//                ", ImageId=" + ImageId +
//                '}';
//    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}
