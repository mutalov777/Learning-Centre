package uz.learn.learningcentre.entity;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.entity.base.Auditable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(schema = "news", name = "news")
public class News extends Auditable {

    private String tittle;

    @OneToOne
    private Picture picture;

    private String description;


}
