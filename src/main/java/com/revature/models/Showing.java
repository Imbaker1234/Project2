package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHOWINGS")
@SequenceGenerator(name="showing_seq", sequenceName="showing_seq", allocationSize=1)
public class Showing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="showing_seq")
	@Column(name="show_id")
	private int showId;
	
	@NonNull
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User showArtist;
	
	@NonNull
	@Column(name="show_description")
	private String showDescription;
	
	@NonNull
	@Column(name="show_place")
	private String showPlace;
	
	@NonNull
	@Column(name="show_time")
	private String showTime;
	
	@Column(name="show_status_id")
	private int showStatusId = 1;
	
}
