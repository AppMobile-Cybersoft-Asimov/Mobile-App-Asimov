package com.example.asimov.data.model;

public class RegisterRequest {

    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String password;
    private String phone;
    private Integer directorId;
    private Integer point;

    public String getFirstName() {
        return firstName;
    }

    public RegisterRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisterRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public RegisterRequest setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegisterRequest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public RegisterRequest setDirectorId(Integer directorId) {
        this.directorId = directorId;
        return this;
    }

    public Integer getPoint() {
        return point;
    }

    public RegisterRequest setPoint(Integer point) {
        this.point = point;
        return this;
    }
}
