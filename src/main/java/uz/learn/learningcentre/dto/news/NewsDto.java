package uz.learn.learningcentre.dto.news;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;

@Getter
@Setter
public class NewsDto extends GenericDto {

    private String tittle;

    private Long pictureId;

    private String description;

}
