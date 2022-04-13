package uz.learn.learningcentre.mapper.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.learn.learningcentre.entity.Picture;
import uz.learn.learningcentre.mapper.base.BaseMapper;

import java.io.IOException;

@Component
@Mapper( componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface FileMapper extends BaseMapper {

    @Mapping( target = "content", source = "dto.bytes" )
    Picture fromDto( MultipartFile dto ) throws IOException;

    @Mapping( target = "content", source = "dto.bytes" )
    Picture fromDto( MultipartFile dto ,@MappingTarget Picture picture ) throws IOException;



}
