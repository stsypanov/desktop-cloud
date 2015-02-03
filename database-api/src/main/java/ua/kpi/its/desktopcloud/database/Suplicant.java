package ua.kpi.its.desktopcloud.database;


import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: stsypanov
 * Date: 24.03.14
 * Time: 16:12
 */

@Entity
@Table(name = "ACTIVEUSERS")
public class Suplicant {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;


    public Suplicant() {
    }

    public Suplicant(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
