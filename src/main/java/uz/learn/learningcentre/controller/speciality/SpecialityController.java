package uz.learn.learningcentre.controller.speciality;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.speciality.SpecialityCriteria;
import uz.learn.learningcentre.dto.speciality.SpecialityCreateDto;
import uz.learn.learningcentre.dto.speciality.SpecialityDto;
import uz.learn.learningcentre.dto.speciality.SpecialityUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.speciality.SpecialityService;

import java.util.List;

@RestController
@RequestMapping("/speciality")
public class SpecialityController extends AbstractController<SpecialityService>
        implements GenericCrudController<SpecialityCreateDto, SpecialityUpdateDto>, GenericController<SpecialityDto, SpecialityCriteria> {

    public SpecialityController(SpecialityService service) {
        super(service);
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(SpecialityCreateDto specialityCreateDto) {
        return service.create(specialityCreateDto);
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<DataDto<Long>> update(SpecialityUpdateDto specialityUpdateDto) {
        return service.update(specialityUpdateDto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<DataDto<SpecialityDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<DataDto<List<SpecialityDto>>> getAll(SpecialityCriteria specialityCriteria) {
        return service.getAll(specialityCriteria);
    }

}
