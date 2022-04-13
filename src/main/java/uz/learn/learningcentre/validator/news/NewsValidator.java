package uz.learn.learningcentre.validator.news;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.news.NewsCreateDto;
import uz.learn.learningcentre.dto.news.NewsDto;
import uz.learn.learningcentre.dto.news.NewsUpdateDto;
import uz.learn.learningcentre.entity.News;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;

@Component
public class NewsValidator implements GenericValidator<News, NewsDto, NewsCreateDto, NewsUpdateDto, Long> {

    @Override
    public void validOnUpdate(NewsUpdateDto newsUpdateDto) {
        if (Objects.isNull(newsUpdateDto)) {
            throw new BadRequestException("Dto is invalid");
        }
        validOnId(newsUpdateDto.getId());
    }


    @Override
    public void validOnCreate(NewsCreateDto newsCreateDto) {
        if (Objects.isNull(newsCreateDto)
                || Objects.isNull(newsCreateDto.getTittle())
                || Objects.isNull(newsCreateDto.getCreatedBy())) {
            throw new BadRequestException("Dto is invalid");
        }
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id) || id <= 0) throw new BadRequestException("ID IS INVALID");
    }
}
