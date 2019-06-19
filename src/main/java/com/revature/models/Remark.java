package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name="REMARKS")
@NamedQueries({
	@NamedQuery(name="findRemarksByArtId", query="from Remark r where r.remarkArtId = :id")
})
@SequenceGenerator(name="remark_seq", sequenceName="remark_seq", allocationSize=1)
public class Remark {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="remark_seq")
	@Column(name="remark_id")
	private int remarkId;
	
	@NonNull
	@Column(name="remark_art_id")
	private int remarkArtId;
	
	@NonNull
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User remarkAuthorId;
	
	@NonNull
	@Column(name="remark_description")
	private String remarkDescription;

}
