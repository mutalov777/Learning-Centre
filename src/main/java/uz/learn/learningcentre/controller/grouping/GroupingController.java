package uz.learn.learningcentre.controller.grouping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.grouping.GroupingCriteria;
import uz.learn.learningcentre.dto.grouping.GroupingCreateDto;
import uz.learn.learningcentre.dto.grouping.GroupingDto;
import uz.learn.learningcentre.dto.grouping.GroupingUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.grouping.GroupingService;

import java.util.List;

@RestController
@RequestMapping(value = "/grouping")
public class GroupingController extends AbstractController<GroupingService>
        implements GenericCrudController<GroupingCreateDto, GroupingUpdateDto>, GenericController<GroupingDto, GroupingCriteria> {

    @Autowired
    public GroupingController( GroupingService service ) {
        super( service );
    }


    @Override
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create( GroupingCreateDto groupingCreateDto ) {
        return service.create( groupingCreateDto );
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<DataDto<Long>> update( GroupingUpdateDto groupingUpdateDto ) {
        return service.update( groupingUpdateDto );
    }

    @PostMapping("/SetStudent/{group}/{student}")
    public ResponseEntity<DataDto<Boolean>> setStudent(@PathVariable Long group,@PathVariable Long student) {
        return service.setStudent( group, student );
    }

    @Override
    @GetMapping("/delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable Long id ) {

        return service.delete( id );
    }


    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<GroupingDto>> get(@PathVariable Long id ) {
        return service.get( id );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<DataDto<List<GroupingDto>>> getAll( GroupingCriteria criteria ) {
        return service.getAll( criteria );
    }
}
