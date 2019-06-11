package models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Data @NoArgsConstructor @AllArgsConstructor //Lombok
//@Entity @Table(name = "COMMENTS") //Hibernate
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private int commentId;

    @Column(name = "COMMENT_OBJECT_ID") //Will require additional annotation
    private int commentObjectId; //The ID for the art

    @Column(name = "COMMENT_AUTHOR_ID")
    private int commentAuthorId; //User ID of the author

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;
}
