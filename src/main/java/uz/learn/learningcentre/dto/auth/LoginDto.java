package uz.learn.learningcentre.dto.auth;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    private String phoneNumber;

    private String password;
}
