package edu.miu.waa.demoinclasslab1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUpdatePost {
    private String title;
    private String content;
    private String author;
}
