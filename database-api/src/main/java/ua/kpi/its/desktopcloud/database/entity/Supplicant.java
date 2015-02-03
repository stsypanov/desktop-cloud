package ua.kpi.its.desktopcloud.database.entity;

import ua.kpi.its.desktopcloud.database.utils.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 15.04.2014
 * Time: 13:57
 */
@Entity
@Table(name = "radcheck")
public class Supplicant {
    @Id
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "username", nullable = false)
    private String userName;
    @Column(name = "attribute", nullable = false)
    private String attribute;
    @Column(name = "op", nullable = false)
    private String op;
    @Column(name = "value", nullable = false)
    private String value;

    public Supplicant(String userName, String password, String op, String value) {
        this.userName = userName;
        this.attribute = password;
        this.op = op;
        this.value = value;
    }

    public Supplicant() {
    }

    public Supplicant(String userName, String password) {
        this.userName = userName;
        this.attribute = StringUtils.CLEAR_TEXT_PASSWORD_ATTRIBUTE;
        this.op = StringUtils.EQUALS_SIGN;
        this.value = password;
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

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return userName + " " + attribute + " " + op + " " + value;
    }
}
