package uz.learn.learningcentre.dto.mockExam;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.BaseDto;

import java.util.List;

@Getter
@Setter
public class MockExamCreateDto implements BaseDto {


    /**
     * Bu Mock Exam create bo'lganda home pagega elon chiqishi uchun
     */

    private List<String> speciality;

    private String studentName;

    private String phoneNumber;

    private Long mockId;

}
