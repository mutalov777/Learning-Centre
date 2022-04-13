package uz.learn.learningcentre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.dto.pictures.PictureCreateDto;
import uz.learn.learningcentre.dto.pictures.PictureUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.file.FileUploadService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping( value = "/picture/" )
public class PictureController extends AbstractController<FileUploadService> {

    @Autowired
    public PictureController( FileUploadService service ) {
        super( service );
    }

    @PostMapping( value = "create/" )
    public ResponseEntity<DataDto<Long>> create( @RequestBody MultipartFile file ) {
        PictureCreateDto dto = new PictureCreateDto( file );
        return service.create( dto );
    }

    @PostMapping( value = "update/{id}" )
    public ResponseEntity<DataDto<Long>> update( @PathVariable Long id , @RequestBody MultipartFile file ) {
        PictureUpdateDto dto = new PictureUpdateDto( file );
        dto.setId( id );
        return service.update( dto );
    }

    @GetMapping( value = "get/{id}" )
    public ResponseEntity<DataDto<?>> get( @PathVariable Long id , HttpServletResponse response ) throws IOException {
        return service.getPicture( id , response );
    }

    @DeleteMapping( value = "delete/{id}" )
    public ResponseEntity<DataDto<Boolean>> delete( @PathVariable Long id ) {
        return service.delete( id );
    }
}
