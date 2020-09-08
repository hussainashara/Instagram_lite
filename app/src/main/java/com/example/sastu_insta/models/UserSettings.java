package com.example.sastu_insta.models;

public class UserSettings {
    private User user;
    private UserAccountSett userAccountSett;

    public UserSettings() {
    }

    public UserSettings(User user, UserAccountSett userAccountSett) {
        this.user = user;
        this.userAccountSett = userAccountSett;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAccountSett getUserAccountSett() {
        return userAccountSett;
    }

    public void setUserAccountSett(UserAccountSett userAccountSett) {
        this.userAccountSett = userAccountSett;
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "user=" + user +
                ", userAccountSett=" + userAccountSett +
                '}';
    }
}
