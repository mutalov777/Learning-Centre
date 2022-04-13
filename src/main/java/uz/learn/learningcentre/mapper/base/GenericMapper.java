package uz.learn.learningcentre.mapper.base;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface GenericMapper<E, D, CD, UD> extends BaseMapper {

    E fromDto(D dto);

    List<E> fromDto(List<D> dtos);

    D toDto(E entity);

    List<D> toDto(List<E> entities);

    E fromUpdateDto(UD ud);

    E fromUpdateDto(UD ud, @MappingTarget E e);

    List<E> fromUpdateDto(List<UD> ud);

    E fromCreateDto(CD cd);

    List<E> fromCreateDto(List<CD> cd);

}
