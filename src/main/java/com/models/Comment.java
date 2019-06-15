package com.models;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component("myComment")
@Scope("singleton")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor //Lombok
@Entity
@Table(name = "COMMENTS") //Hibernate
//@NamedQueries(value = {
//        @NamedQuery(name="findCommentById", query="from COMMENTS c where c.commentId = :id"),
//        @NamedQuery(name="findCommentByAuthorId", query="from COMMENTS c where c.authorId = :id"),
//        @NamedQuery(name="findCommentByArtId", query="from COMMENTS c where c.ObjectId like :address")
//})
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private int commentId;

    @NonNull
    @Column(name = "COMMENT_ART_ID") //Will require additional annotation
    private int commentArtId; //The ID for the art

    @NonNull
    @Column(name = "COMMENT_AUTHOR_ID")
    private int commentAuthorId; //User ID of the author

    @NonNull
    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    public void test() {
        this.getCommentAuthorId();
    }

}
