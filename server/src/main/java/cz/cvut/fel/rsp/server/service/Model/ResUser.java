package cz.cvut.fel.rsp.server.service.Model;

import cz.cvut.fel.rsp.server.Model.UnregisteredUser;
import cz.cvut.fel.rsp.server.Model.User;


public class ResUser {

    private String city;
    private String country;
    private String email;
    private String name;
    private String phoneNum;
    private Integer postalCode;
    private String streetName;

    public void setRegistered(User user) {
        city = user.getCity();
        country = user.getCountry();
        email = user.getEmail();
        name = user.getName();
        phoneNum = user.getPhoneNum();
        postalCode = user.getPostalCode();
        streetName = user.getStreetName();
    }

    public void setUnregistered(UnregisteredUser user) {
        city = user.getCity();
        country = user.getCountry();
        email = user.getEmail();
        name = user.getName();
        phoneNum = user.getPhoneNum();
        postalCode = user.getPostalCode();
        streetName = user.getStreetName();
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getStreetName() {
        return streetName;
    }
}
