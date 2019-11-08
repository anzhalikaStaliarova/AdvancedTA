package com.epam.rp.com.epam.rp.pages;
public class UserDetails {

private String firstName;
private String lastName;
private String loginName;


public UserDetails() {
        super();
        }

public String getFirstName() {
        return firstName;
        }

public String getFirstNameSpaceLastName() {
        return getFirstName() + " " + getLastName();
        }

public String getFullName() {
        return getLastName() + ", " + getFirstName();
        }

public String getLastName() {
        return lastName;
        }

public String getLoginName() {
        return loginName;
        }

public UserDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
        }

public UserDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
        }

public UserDetails setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
        }
        }
