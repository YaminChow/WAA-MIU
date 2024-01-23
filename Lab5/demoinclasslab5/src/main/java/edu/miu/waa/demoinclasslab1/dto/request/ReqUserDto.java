package edu.miu.waa.demoinclasslab1.dto.request;

import edu.miu.waa.demoinclasslab1.entity.Post;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReqUserDto {
    private String name;
    private List<ReqPostDto> reqPosts = new ArrayList<>();
}
