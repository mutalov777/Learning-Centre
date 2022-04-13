package uz.learn.learningcentre.criteria.speciality;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.criteria.base.AbstractCriteria;

@Getter
@Setter
public class SpecialityCriteria extends AbstractCriteria {
    private Long subjectId;
    private boolean main = true;
}
