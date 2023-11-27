package com.wy.yunoa;

import java.util.Optional;

/**
 * @Author: wy
 * @CreateTime: 2023-11-21  15:14
 * @Description: TODO
 * @Version: 1.0
 */
public class OptionalTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("wy");
        user.setAge(12);
        user.setGender("男");
        System.out.print("user=====>");
        System.out.println(user);
        Optional<User> user1 = Optional.of(user);
        User user2 = user1.get();
        System.out.print("user2=====>");
        System.out.println(user2);
        User user3 = null;
        Optional<User> user31 = Optional.ofNullable(user3);
        User user4 = user31.orElseGet(() -> new User("hk",12,"女"));
        System.out.print("user4=====>");
        System.out.println(user4);
    }
}

class User {
    private String name;
    private int age;
    private String gender;
    public User() {
    }
    public User(String name,int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
