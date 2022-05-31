package com.example.customlistview;

public class student
{
    private String Name ;
    private String Age ;
    private String Title ;
    private int ImageId ;

    public student(String name, String age, String title, int imageId)
    {
        Name = name;
        Age = age;
        Title = title;
        ImageId = imageId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}
