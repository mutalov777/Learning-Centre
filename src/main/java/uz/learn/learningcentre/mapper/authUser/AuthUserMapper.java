package uz.learn.learningcentre.mapper.authUser;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.auth.AuthUserCreateDto;
import uz.learn.learningcentre.dto.auth.AuthUserDto;
import uz.learn.learningcentre.dto.auth.AuthUserUpdateDto;
import uz.learn.learningcentre.entity.AuthUser;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;

@Component
@Mapper( componentModel = "spring" )
public interface AuthUserMapper extends GenericMapper<AuthUser, AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto> {

    @Override
    AuthUser fromDto( AuthUserDto dto );

    @Override
    List<AuthUser> fromDto( List<AuthUserDto> dtos );

    @Override
    AuthUserDto toDto( AuthUser entity );

    @Override
    List<AuthUserDto> toDto( List<AuthUser> entities );

    @Override
    @Mapping( target = "subjects", ignore = true )
    AuthUser fromUpdateDto( AuthUserUpdateDto authUserUpdateDto );

    @Override
    List<AuthUser> fromUpdateDto( List<AuthUserUpdateDto> ud );

    @Override
    @Mapping( target = "subjects", ignore = true )
    AuthUser fromCreateDto( AuthUserCreateDto authUserCreateDto );

    @Override
    @Mapping( target = "subjects", ignore = true )
    AuthUser fromUpdateDto( AuthUserUpdateDto authUserUpdateDto ,@MappingTarget AuthUser authUser );

    @Override
    List<AuthUser> fromCreateDto( List<AuthUserCreateDto> cd );
}
