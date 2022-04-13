package uz.learn.learningcentre.mapper.news;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.news.NewsCreateDto;
import uz.learn.learningcentre.dto.news.NewsDto;
import uz.learn.learningcentre.dto.news.NewsUpdateDto;
import uz.learn.learningcentre.entity.News;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface NewsMapper extends GenericMapper<News, NewsDto, NewsCreateDto, NewsUpdateDto> {


    @Override
    News fromDto(NewsDto dto);

    @Override
    List<News> fromDto(List<NewsDto> dtos);

    @Override
    NewsDto toDto(News entity);

    @Override
    List<NewsDto> toDto(List<News> entities);

    @Override
    News fromUpdateDto(NewsUpdateDto newsUpdateDto);

    @Override
    News fromUpdateDto( NewsUpdateDto newsUpdateDto, @MappingTarget News news);

    @Override
    List<News> fromUpdateDto(List<NewsUpdateDto> ud);

    @Override
    News fromCreateDto(NewsCreateDto newsCreateDto);

    @Override
    List<News> fromCreateDto(List<NewsCreateDto> cd);
}
