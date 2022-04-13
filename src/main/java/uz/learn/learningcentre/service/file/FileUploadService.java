package uz.learn.learningcentre.service.file;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.base.AbstractCriteria;
import uz.learn.learningcentre.dto.pictures.PictureCreateDto;
import uz.learn.learningcentre.dto.pictures.PictureDto;
import uz.learn.learningcentre.dto.pictures.PictureUpdateDto;
import uz.learn.learningcentre.entity.Picture;
import uz.learn.learningcentre.exceptions.NotFoundException;
import uz.learn.learningcentre.mapper.file.FileMapper;
import uz.learn.learningcentre.repository.FileRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.FileValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class FileUploadService extends AbstractService<FileMapper, FileValidator, FileRepository>
        implements GenericCrudController<PictureCreateDto, PictureUpdateDto>, GenericService<PictureDto, AbstractCriteria> {


    public FileUploadService( @Qualifier( "fileMapperImpl" ) FileMapper mapper , FileValidator validator , FileRepository repository ) {
        super( mapper , validator , repository );
    }

    @Override
    public ResponseEntity<DataDto<Long>> create( PictureCreateDto pictureCreateDto ) {
        try {
            Picture picture = mapper.fromDto( pictureCreateDto.getFile() );
            Picture save = repository.save( picture );
            return new ResponseEntity<>( new DataDto<>( save.getId() ) );
        } catch ( IOException e ) {
            return new ResponseEntity<>( new DataDto<>( new AppErrorDto( HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage() ) ) );
        }
    }

    @Override
    public ResponseEntity<DataDto<Long>> update( PictureUpdateDto pictureUpdateDto ) {
        Picture pictureById = repository.findById( pictureUpdateDto.getId() ).orElseThrow(
                () -> {
                    throw new NotFoundException( "Picture not found" );
                } );
        try {
            Picture picture = mapper.fromDto( pictureUpdateDto.getFile() , pictureById );
            Picture save = repository.save( picture );
            return new ResponseEntity<>( new DataDto<>( save.getId() ) );
        } catch ( IOException e ) {
            return new ResponseEntity<>( new DataDto<>( new AppErrorDto( HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage() ) ) );
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete( Long id ) {
        repository.deleteById( id );
        return new ResponseEntity<>( new DataDto<>( true ) );
    }

    @Override
    public ResponseEntity<DataDto<PictureDto>> get( Long id ) {

        return new ResponseEntity<>( new DataDto<>( true ) );
    }

    public ResponseEntity<DataDto<?>> getPicture( Long id , HttpServletResponse response ) throws IOException {
            Picture pictureById = repository.findById( id ).orElseThrow( () -> {
                throw new NotFoundException( "Picture not found " );
            } );
            String contentType = pictureById.getContentType();
            response.setContentType( contentType );
            response.setHeader( HttpHeaders.CONTENT_DISPOSITION , "attachment;filename=\"" + pictureById.getName() + "\"" );
            FileCopyUtils.copy( pictureById.getContent() , response.getOutputStream() );
        return null;
    }


    @Override
    public ResponseEntity<DataDto<List<PictureDto>>> getAll( AbstractCriteria criteria ) {
        return new ResponseEntity<>( new DataDto<>( true ) );
    }

}