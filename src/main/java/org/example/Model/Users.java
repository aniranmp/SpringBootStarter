package org.example.Model;

import javax.persistence.*;

@Entity
@Table()
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = false)
    private String email;

    @Column(unique = true)
    private String username;

    @Column(unique = false)
    private String password;

    @Column(unique = false)
    private String phone;

    @Column(unique = false)
    private String nid;

    @Column(unique = false)
    private int age;

    @Column(unique = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getNid() {
        return nid;
    }

    public int getAge() {
        return age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }}