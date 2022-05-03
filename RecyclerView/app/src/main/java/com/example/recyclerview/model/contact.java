package com.example.recyclerview.model;

public class contact
{
    // MIGHT BE CONSIDERED AS A ROW STRUCTURE TO BE ADDED IN THE TABLE - MODEL

    private int ID ;
    private String name ;
    private String phone_no ;

    // CONSTRUCTORS

    public contact(int ID, String name, String phone_no) {
        this.ID = ID;
        this.name = name;
        this.phone_no = phone_no;
    }

    public contact(String name, String phone_no) {
        this.name = name;
        this.phone_no = phone_no;
    }

    public contact()
    {

    }

    // GETTERS AND SETTERS

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
