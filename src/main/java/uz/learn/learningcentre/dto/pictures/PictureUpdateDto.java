package uz.learn.learningcentre.dto.pictures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.learn.learningcentre.dto.base.GenericDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PictureUpdateDto extends GenericDto {
    MultipartFile file;
}
