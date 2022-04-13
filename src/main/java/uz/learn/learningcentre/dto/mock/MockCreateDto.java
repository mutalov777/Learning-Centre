package uz.learn.learningcentre.dto.mock;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.BaseDto;

@Getter
@Setter
public class MockCreateDto implements BaseDto {

    /**
     * Bu Mock Exam create bo'lganda home pagega elon chiqishi uchun
     */

    private String name;

    private String date;

    private String location;

    private String beginTime;

    private String endTime;

}