package model.account;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private boolean status;

    private Set<Role> roles;

    public User() {
    }

    public User(int id, String name, String username, String password, String email, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public User(int id, String newName, String newEmail, String password) {
        this.id = id;
        this.name = newName;
        this.password = password;
        this.email = newEmail;

    }

    public RoleName getRoleNameOfUser(){
        for (Role role : roles){
            if (role.getRoleName() == RoleName.ADMIN) return  RoleName.ADMIN;
            if (role.getRoleName() == RoleName.PM) return  RoleName.PM;
            if (role.getRoleName() == RoleName.USER) return  RoleName.USER;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return  "    ID = " + id + "\n" +
                "Name = " + name  + "                 Username = " + username  +"\n"+
                "Password = " + password + "             Email = " + email +"\n"+
                "Status = " + (status ? "BLOCKED" : "NOT BLOCKED") + "        Roles = " + roles +"\n";
    }
}
