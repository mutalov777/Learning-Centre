package uz.learn.learningcentre.entity;


import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.entity.base.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(schema = "auth", name = "auth_picture")
public class Picture extends Auditable {

    private String contentType;

    private byte[] content;

    private String name;

    private Integer size;

}
