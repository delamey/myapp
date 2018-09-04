package com.example.delamey.myapplication5.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class young {
    @Id
    private Long id;
    private String name;
    private Integer age;
    @Generated(hash = 1286592332)
    public young(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 1873701544)
    public young() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return this.age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
