package uz.learn.learningcentre.entity;


import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(schema = "exam", name = "exam")
public class Exam extends Auditable {

    @Column
    private Integer ball;

    @Column
    private String title; // qanaqa imtihonligi

    @Column
    private Long groupId;

    @Column
    private Long studentId;

}
