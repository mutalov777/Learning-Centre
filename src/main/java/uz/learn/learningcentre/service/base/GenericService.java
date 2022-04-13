package uz.learn.learningcentre.service.base;

import uz.learn.learningcentre.criteria.base.BaseCriteria;
import uz.learn.learningcentre.dto.base.GenericDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;

import java.util.List;

public interface GenericService<D extends GenericDto, C extends BaseCriteria> {

    ResponseEntity<DataDto<D>> get(Long id);

    ResponseEntity<DataDto<List<D>>> getAll(C criteria);


}
