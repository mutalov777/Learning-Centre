package uz.learn.learningcentre.service.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.dto.student.ChangePasswordDto;
import uz.learn.learningcentre.dto.student.StudentCreateDto;
import uz.learn.learningcentre.dto.student.StudentDto;
import uz.learn.learningcentre.dto.student.StudentUpdateDto;
import uz.learn.learningcentre.entity.Student;
import uz.learn.learningcentre.enums.StudyType;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.mapper.student.StudentMapper;
import uz.learn.learningcentre.repository.student.StudentRepository;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.validator.student.StudentValidator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService extends AbstractService<StudentMapper, StudentValidator, StudentRepository> {

    public StudentService(StudentMapper mapper, StudentValidator validator, StudentRepository repository) {
        super(mapper, validator, repository);
    }

    public ResponseEntity<DataDto<Long>> create(StudentCreateDto studentCreateDto) {
        Student student = mapper.fromCreateDto(studentCreateDto);
        Student save = repository.save(student);
        return new ResponseEntity<>(new DataDto<>(save.getId()));
    }

    public ResponseEntity<DataDto<Long>> update(StudentUpdateDto studentUpdateDto) {
        Long id = studentUpdateDto.getId();
        String fullName = studentUpdateDto.getFullName();
        String entranceYear = studentUpdateDto.getEntranceYear();
        String type = studentUpdateDto.getStudyType();
        StudyType studyType = StudyType.valueOf(type);
        String phoneNumber = studentUpdateDto.getPhoneNumber();
        String fathersNumber = studentUpdateDto.getFathersNumber();
        String mothersNumber = studentUpdateDto.getMothersNumber();
        Optional<Student> byId = repository.findById(id);

        if (byId.isPresent()) {
            Student student = byId.get();
            student.setFullName(fullName);
            student.setEntranceYear(entranceYear);
            student.setStudyType(studyType);
            student.setPhoneNumber(phoneNumber);
            student.setFathersNumber(fathersNumber);
            student.setMothersNumber(mothersNumber);

            repository.save(student);
            return new ResponseEntity<>(new DataDto<>(id));
        } else {

            throw new BadRequestException("user not found");
        }

    }

    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(new DataDto<>(true));
    }


    public ResponseEntity<DataDto<StudentDto>> get(Long id) {
        Optional<Student> byId = repository.findById(id);

        if (byId.isPresent()) {
            Student student = byId.get();
            StudentDto studentDto = mapper.toDto(student);

            return new ResponseEntity<>(new DataDto<>(studentDto));
        } else {

            throw new BadRequestException("user not found");
        }

    }

    public ResponseEntity<DataDto<List<StudentDto>>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> all = repository.findAll(pageable);

        if (all.isEmpty()) {
            throw new BadRequestException("page not found");
        }

        List<StudentDto> studentDtos = mapper.toDto(all.get().toList());
        long totalCount = studentDtos.size();

        return new ResponseEntity<>(new DataDto<>(studentDtos, totalCount));
    }

    public ResponseEntity<DataDto<Long>> changePassword(ChangePasswordDto password) {
        Optional<Student> byId = repository.findById(password.getId());

        if (byId.isPresent()) {
            Student student = byId.get();

            if (student.getPassword().equals(password.getOldPassword())
                    && password.getNewPassword().equals(password.getConfirmedPassword())) {
                student.setPassword(password.getConfirmedPassword());
                repository.save(student);
                return new ResponseEntity<>(new DataDto<>(student.getId()));
            } else {
                throw new BadRequestException("bad credentials");
            }

        }

        throw new BadRequestException("User not found");
    }

    public List<StudentDto> getRandomList(Integer count) {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        String s = String.valueOf(year);

        Optional<List<Student>> list = repository.findAllByEntranceYearAndStudyType(s, StudyType.GRANT);

        if (list.isPresent()) {
            List<Student> students = list.get();

            if (students.size() > count) {
                Collections.shuffle(students);
                List<Student> shuffledList = students.subList(0, count);

                return mapper.toDto(shuffledList);
            } else {

                return mapper.toDto(students);
            }

        } else {

            throw new RuntimeException("Students not found");
        }

    }
}
