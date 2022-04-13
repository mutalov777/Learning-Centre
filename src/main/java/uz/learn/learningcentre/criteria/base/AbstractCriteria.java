package uz.learn.learningcentre.criteria.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractCriteria implements BaseCriteria {
    protected Integer size;
    protected Integer page;

    public AbstractCriteria(Integer size, Integer page) {
        this.size = size;
        this.page = page;
    }

    public AbstractCriteria(Integer page) {
        this.size = 20;
        this.page = page;
    }

}
