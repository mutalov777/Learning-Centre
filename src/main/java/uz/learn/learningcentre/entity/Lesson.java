package uz.learn.learningcentre.entity;


import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.entity.base.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(schema = "grouping", name = "lesson")
public class Lesson extends Auditable {


    private String days;// hafta kunlari

    private LocalDate lessonBegin;

    private LocalDate lessonEnd;

    private Long groupId;// write relationship
}
