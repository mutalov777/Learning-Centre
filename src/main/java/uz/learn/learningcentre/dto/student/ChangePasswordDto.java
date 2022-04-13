package uz.learn.learningcentre.dto.student;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ChangePasswordDto {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(min = 4, max = 15, message = "length of password should be between {min} and {max}")
    private String oldPassword;

    @NotBlank
    @Size(min = 4, max = 15, message = "length of password should be between {min} and {max}")
    private String newPassword;

    @NotBlank
    @Size(min = 4, max = 15, message = "length of password should be between {min} and {max}")
    private String confirmedPassword;
}
