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
@Table(schema = "attendance", name = "attendance")
public class Attendance extends Auditable {

    @Column
    private Long studentId;

    @Column
    private Long groupId;

    private Boolean here;// null if here / true if reasonable / false if reason not found(send message)


    private Long attendanceContainerId;

}
