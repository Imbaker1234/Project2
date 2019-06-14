package com.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor //Lombok
@Entity //Lets Hibernate know that this class is an entity which will be mapped to a DB table.
@Table(name = "USERS") // Maps this class to the specified table.
//Specifies a counter sequence for this class.
@NamedQueries({
        @NamedQuery(name="findUserById", query="from User u where u.userID = :id"),
        @NamedQuery(name="findUserByName", query="from User u where u.userName = :name and u.userPass = :pass")
})

public class User {

    @Id //Specifies a primary key
    @GeneratedValue//Increments the ID
    @Column(name = "USER_ID")
    private int userID;

    @NonNull
    @Column(name = "USER_NAME", unique = true, nullable = false)
    private String userName;

    @NonNull
    @Column(name = "USER_PASS", nullable = false)
    private String userPass;

    @NonNull
    @Column(name = "USER_FIRST", nullable = false)
    private String userFirst;

    @NonNull
    @Column(name = "USER_LAST", nullable = false)
    private String userLast;

    @NonNull
    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    // 1) regular user
    // 2) artist
    // 3) moderator
    @NonNull
    @Column(name = "USER_ROLE")
    private int userRole = 1;
}

