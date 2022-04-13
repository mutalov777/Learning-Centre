package uz.learn.learningcentre.validator.authUser;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.auth.AuthUserCreateDto;
import uz.learn.learningcentre.dto.auth.AuthUserDto;
import uz.learn.learningcentre.dto.auth.AuthUserUpdateDto;
import uz.learn.learningcentre.entity.AuthUser;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;

@Component
public class AuthUserValidator implements GenericValidator<AuthUser, AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto, Long> {

    @Override
    public void validOnUpdate(AuthUserUpdateDto authUserUpdateDto) {
        validOnId(authUserUpdateDto.getId());
    }

    @Override
    public void validOnCreate(AuthUserCreateDto authUserCreateDto) {
        if (Objects.isNull(authUserCreateDto.getFullName())) {
            throw new BadRequestException("FULL NAME IS NULL");
        }
        if (Objects.isNull(authUserCreateDto.getPassword())) {
            throw new BadRequestException("PASSWORD IS NULL");
        }
        if (Objects.isNull(authUserCreateDto.getPhoneNumber())) {
            throw new BadRequestException("PASSWORD IS NULL");
        }
        if (Objects.isNull(authUserCreateDto.getRole())) {
            throw new BadRequestException("ROLE IS NULL");
        }
        if (Objects.isNull(authUserCreateDto.getSalary())) {
            throw new BadRequestException("SALARY IS NULL");
        }
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id) || id <= 0) throw new BadRequestException("ID IS INVALID");
    }
}
