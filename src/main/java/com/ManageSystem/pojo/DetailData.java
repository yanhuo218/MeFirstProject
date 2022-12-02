package com.ManageSystem.pojo;

public class DetailData {
    private int id;
    private String portrait;
    private String Nicknames;
    private int sex;
    private int age;

    public DetailData() {
    }

    public DetailData(Integer id, String portrait, String Nicknames, Integer sex, Integer age) {
        this.id = id;
        this.portrait = portrait;
        this.Nicknames = Nicknames;
        this.sex = sex;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNicknames() {
        return Nicknames;
    }

    public void setNicknames(String Nicknames) {
        this.Nicknames = Nicknames;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DetailData{" +
                "id=" + id +
                ", portrait='" + portrait + '\'' +
                ", Nicknames='" + Nicknames + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
