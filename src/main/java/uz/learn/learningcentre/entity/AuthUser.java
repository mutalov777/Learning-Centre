package uz.learn.learningcentre.entity;


import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.entity.base.Auditable;
import uz.learn.learningcentre.entity.base.BaseEntity;
import uz.learn.learningcentre.enums.AuthRole;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "auth", name = "auth_user")
public class AuthUser extends Auditable implements BaseEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column
    private Short experienceYear;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = "auth",
            name = "mentor_subject",
            joinColumns =  { @JoinColumn(name = "mentor_id") } ,
            inverseJoinColumns = { @JoinColumn(name = "subject_id") })
    private List<Subject> subjects;

    @Enumerated(EnumType.STRING)
    private AuthRole role;

    @Column
    private Double salary;


}
