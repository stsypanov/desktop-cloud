package ua.kpi.its.desktopcloud.database.entity;


import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 24.03.14
 * Time: 16:12
 */

@Entity
@Table(name = "ACTIVEUSERS")
public class ActiveUser {
//    @Id
//    @Column(name = "id")
//    private int id;

    @Id
    @Column(name = "username",  nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;


    public ActiveUser() {
    }

    public ActiveUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

//    public int getId() {
//        return id;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + userName + ", password: " + password;
    }
}
