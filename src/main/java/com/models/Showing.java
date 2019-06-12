package com.models;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHOWS")
//@NamedQueries(value = {
//        @NamedQuery(name = "findShowById", query = "from SHOWS s where s.showId = :id"),
//        @NamedQuery(name = "findShowByHostId", query = "from SHOWS s where s.hostId = :id"),
//        @NamedQuery(name = "findShowByPlace", query = "from SHOWS s where s.place like :address")
//})

public class Showing {

    @Id
    @GeneratedValue
    @Column(name = "SHOW_ID")
    int showId; //Primary Key

    @NonNull
    @Column(name = "SHOW_PLACE")
    String address; //Address of current event

    @NonNull
    @Column(name = "SHOW_TIME")
    String time; //Date of the event

    @NonNull
    @Column(name = "SHOW_HOST_ID")
    int hostId; //the UserId of the hosting artist

    @NonNull
    @Column(name = "SHOW_DESCRIPTION")
    String description; //Details about the event.

}
