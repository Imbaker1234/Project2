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
//@NamedQueries(value = {
//        @NamedQuery(name = "findUserById", query = "from User u where u.userID = :id"),
//        @NamedQuery(name = "findUserByName", query = "from User u where u.userName = :name")
//})
public class User {

    @Id //Specifies a primary key
    @GeneratedValue//Increments the ID
    @Column(name = "USER_ID")
    private int userID;

    @NonNull
    @Column(name = "USER_NAME")
    private String userName;

    @NonNull
    @Column(name = "USER_PASS")
    private String userPass;

    @NonNull
    @Column(name = "USER_FIRST")
    private String userFirst;

    @NonNull
    @Column(name = "USER_LAST")
    private String userLast;

    @NonNull
    @Column(name = "USER_EMAIL")
    private String userEmail;

    @NonNull
    @Column(name = "USER_ROLE")
    private int userRole;
}

