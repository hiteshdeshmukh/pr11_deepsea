package com.example.pr11_deepsea.Model;

public class UserModel {

    private String name, phoneNo, email, password;

    private String UserId;

    private String gender, location;
    private String age;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    //private String Thought, Location;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getProfilePhoto() {
        return ProfilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        ProfilePhoto = profilePhoto;
    }

    private String ProfilePhoto;
    public UserModel() {
    }

    public UserModel(String name, String phoneNo, String email, String password) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
