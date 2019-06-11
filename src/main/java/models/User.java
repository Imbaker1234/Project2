package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "USER")
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private int userID;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PASS")
    private String userPass;

    @Column(name = "USER_FIRST")
    private String userFirst;

    @Column(name = "USER_LAST")
    private String userLast;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_ROLE")
    private String userRole;
}
