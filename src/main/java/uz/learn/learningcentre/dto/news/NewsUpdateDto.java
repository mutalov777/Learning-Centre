package uz.learn.learningcentre.dto.news;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;

@Getter
@Setter
public class NewsUpdateDto extends GenericDto {

    private Long updatedBy;

    private String tittle;

    private Long pictureId;

    private String description;


}
