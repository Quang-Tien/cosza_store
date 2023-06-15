package com.cybersoft.cozastore.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Vui lòng nhập username")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Vui lòng nhập password")
    @NotNull(message = "Password not null")
    @Length(min = 4)
    @Column(name = "password")
    private String password;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Vui lòng nhập email")
    @NotNull(message = "Email not null")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<BlogEntity> blogs;

    @OneToMany(mappedBy = "user")
    private Set<OrderEntity> orders;

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BlogEntity> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<BlogEntity> blogs) {
        this.blogs = blogs;
    }
}
