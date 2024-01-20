package edu.miu.waa.demoinclasslab1.dto.response;

import edu.miu.waa.demoinclasslab1.entity.Post;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Data
@Getter
@Setter
public class ResUserDto{
    private long id;
    private String name;
    private List<ResPost> posts = new ArrayList<>();
}
