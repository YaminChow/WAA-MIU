package edu.miu.waa.demoinclasslab1.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDtos {
    private long id;
    private String title;
    private String author;
}
