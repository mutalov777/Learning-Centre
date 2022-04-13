package uz.learn.learningcentre.service.base;

import uz.learn.learningcentre.dto.base.BaseDto;
import uz.learn.learningcentre.dto.base.GenericDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;

public interface GenericCrudService<D extends GenericDto, CD extends BaseDto, UD extends GenericDto> {


    ResponseEntity<DataDto<Long>> create(CD cd);

    ResponseEntity<DataDto<Long>> update(UD ud);

    ResponseEntity<DataDto<Boolean>> delete(Long id);


}
