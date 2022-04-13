package uz.learn.learningcentre.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import uz.learn.learningcentre.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(schema = "attendance", name = "attendance_container")
public class AttendanceContainer implements BaseEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP default NOW()")
    private LocalDate createdAt;

    private Long groupId;

//    @Column(columnDefinition = "boolean default false")
//    private boolean enabled;//

}
