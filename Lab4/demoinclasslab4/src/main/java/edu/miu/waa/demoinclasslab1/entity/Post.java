package edu.miu.waa.demoinclasslab1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private String author;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="post_id")
    private List<Commet> commets;

//    @ManyToOne
//    private User user;

    public Post(String title, String content, String author, List<Commet> comments){
        this.title = title;
        this.content = content;
        this.author = author;
        this.commets = comments;
    }
}
