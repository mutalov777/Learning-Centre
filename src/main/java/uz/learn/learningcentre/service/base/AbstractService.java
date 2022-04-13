package uz.learn.learningcentre.service.base;


import lombok.RequiredArgsConstructor;
import uz.learn.learningcentre.mapper.base.BaseMapper;
import uz.learn.learningcentre.repository.base.BaseRepository;
import uz.learn.learningcentre.validator.base.BaseValidator;

@RequiredArgsConstructor
public abstract class AbstractService<M extends BaseMapper, V extends BaseValidator, R extends BaseRepository> implements BaseService {

    protected final M mapper;
    protected final V validator;
    protected final R repository;

}
