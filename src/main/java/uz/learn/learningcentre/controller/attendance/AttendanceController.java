package uz.learn.learningcentre.controller.attendance;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.attendance.AttendanceCriteria;
import uz.learn.learningcentre.dto.attendance.AttendanceCreateDto;
import uz.learn.learningcentre.dto.attendance.AttendanceDto;
import uz.learn.learningcentre.dto.attendance.AttendanceUpdateDto;
import uz.learn.learningcentre.entity.AttendanceContainer;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.attendance.AttendanceService;

import java.util.List;

@RestController
@RequestMapping(value = "/attendance/")
public class AttendanceController extends AbstractController<AttendanceService>
        implements GenericCrudController<AttendanceCreateDto, AttendanceUpdateDto>,
        GenericController<AttendanceDto, AttendanceCriteria> {


    public AttendanceController(AttendanceService service) {
        super(service);
    }

    @Override
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<AttendanceDto>> get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @Override
    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<AttendanceDto>>> getAll(AttendanceCriteria criteria) {
        return service.getAll(criteria);
    }


    @RequestMapping(value = "list/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<AttendanceContainer>>> getOneGroupAttendanceContainer(@PathVariable("groupId") Long groupId) {
        return service.getOneGroupAttendanceContainer(groupId);
    }

    @RequestMapping(value = "list/{studentId}{groupId}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<AttendanceDto>>> getStudentAttendance(@PathVariable("studentId") Long studentId, @PathVariable("groupId") Long groupId) {
        return service.getStudentAttendance(studentId, groupId);
    }

    @Override
    @PostMapping(value = "create/")
    public ResponseEntity<DataDto<Long>> create(@RequestBody AttendanceCreateDto attendanceCreateDto) {
        return service.create(attendanceCreateDto);
    }

    @Override
    @PutMapping(value = "update/")
    public ResponseEntity<DataDto<Long>> update(@RequestBody AttendanceUpdateDto attendanceUpdateDto) {
        return service.update(attendanceUpdateDto);
    }

    @Override
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
