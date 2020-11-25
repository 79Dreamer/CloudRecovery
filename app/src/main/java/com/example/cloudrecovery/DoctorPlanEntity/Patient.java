package com.example.cloudrecovery.DoctorPlanEntity;

public class Patient {
    private String name;
    private String sex;
    private String nickname;

    public Patient(String name, String sex, String nickname){
        this.name = name;
        this.sex = sex;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
