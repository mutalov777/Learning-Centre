package uz.learn.learningcentre.service.attendance;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.attendance.AttendanceCriteria;
import uz.learn.learningcentre.dto.attendance.AttendanceCreateDto;
import uz.learn.learningcentre.dto.attendance.AttendanceDto;
import uz.learn.learningcentre.dto.attendance.AttendanceUpdateDto;
import uz.learn.learningcentre.entity.Attendance;
import uz.learn.learningcentre.entity.AttendanceContainer;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.mapper.attendance.AttendanceMapper;
import uz.learn.learningcentre.repository.attendance.AttendanceContainerRepository;
import uz.learn.learningcentre.repository.attendance.AttendanceRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.attendance.AttendanceValidator;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService extends AbstractService<AttendanceMapper, AttendanceValidator, AttendanceRepository>
        implements GenericCrudService<AttendanceDto, AttendanceCreateDto, AttendanceUpdateDto>,
        GenericService<AttendanceDto, AttendanceCriteria> {

    private final AttendanceContainerRepository containerRepository;

    public AttendanceService(AttendanceMapper mapper, AttendanceValidator validator, AttendanceRepository repository, AttendanceRepository attendanceRepository, AttendanceContainerRepository containerRepository) {
        super(mapper, validator, repository);
        this.containerRepository = containerRepository;
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(AttendanceCreateDto attendanceCreateDto) {
        validator.validOnCreate(attendanceCreateDto);

        AttendanceContainer attendanceContainer = new AttendanceContainer();
        attendanceContainer.setGroupId(attendanceCreateDto.getGroupId());

        containerRepository.save(attendanceContainer);

        Attendance attendance = repository.save(mapper.fromCreateDto(attendanceCreateDto));
        return new ResponseEntity<>(new DataDto<>(attendance.getId()));
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(AttendanceUpdateDto attendanceUpdateDto) {
        validator.validOnUpdate(attendanceUpdateDto);
        Attendance attendance = repository.save(mapper.fromUpdateDto(attendanceUpdateDto));
        return new ResponseEntity<>(new DataDto<>(attendance.getId()));
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            validator.validOnId(id);
            repository.deleteById(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }

    }

    @Override
    public ResponseEntity<DataDto<AttendanceDto>> get(Long id) {
        try {
            validator.validOnId(id);

            Optional<Attendance> attendance = repository.findById(id);
            if (attendance.isEmpty()) {
                return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Attendance not found")));
            }

            AttendanceDto attendanceDto = mapper.toDto(attendance.get());
            return new ResponseEntity<>(new DataDto<>(attendanceDto));

        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }

    }

    @Override
    public ResponseEntity<DataDto<List<AttendanceDto>>> getAll(AttendanceCriteria criteria) {
        return null;
    }


    //show one group attendance by groupId and teacherId
    public ResponseEntity<DataDto<List<AttendanceContainer>>> getOneGroupAttendanceContainer(Long groupId) {
        List<AttendanceContainer> attendanceContainerList = containerRepository.findAllByGroupId(groupId);
        return new ResponseEntity<>(new DataDto<>(attendanceContainerList));
    }


    //show all attendances of student (inside the group)
    public ResponseEntity<DataDto<List<AttendanceDto>>> getStudentAttendance(Long studentId, Long groupId) {
        List<AttendanceDto> attendanceDtos = mapper.toDto(repository.findStudentAttendance(studentId, groupId));
        return new ResponseEntity<>(new DataDto<>(attendanceDtos));
    }

}
