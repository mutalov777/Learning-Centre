package uz.learn.learningcentre.dto.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public abstract class GenericDto {

    @NotBlank
    private Long id;

}
