package edu.miu.waa.demoinclasslab1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private long id;
    private String title;
    private String content;
    private String author;
}
