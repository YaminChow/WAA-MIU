package edu.miu.waa.demoinclasslab1.dto.request;

import edu.miu.waa.demoinclasslab1.entity.Commet;
import lombok.Data;

import java.util.List;

@Data
public class ReqPostDto {
    private String title;
    private String content;
    private String author;
    private List<ReqCommetDto> commets;
}
