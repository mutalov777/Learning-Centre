package uz.learn.learningcentre.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;
import uz.learn.learningcentre.enums.AuthRole;

import java.util.List;

@Getter
@Setter
public class AuthUserUpdateDto extends GenericDto {
    private String fullName;

    private String phoneNumber;

    private String password;

    private Short experienceYear;

    private List<Long> subjects;

    private AuthRole role;

    private Double salary;
}
