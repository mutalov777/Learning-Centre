package uz.learn.learningcentre.service.news;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.base.AbstractCriteria;
import uz.learn.learningcentre.dto.news.NewsCreateDto;
import uz.learn.learningcentre.dto.news.NewsDto;
import uz.learn.learningcentre.dto.news.NewsUpdateDto;
import uz.learn.learningcentre.entity.News;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.mapper.news.NewsMapper;
import uz.learn.learningcentre.repository.news.NewsRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.news.NewsValidator;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService extends AbstractService<NewsMapper, NewsValidator, NewsRepository>
        implements GenericCrudService<NewsDto, NewsCreateDto, NewsUpdateDto>,
        GenericService<NewsDto, AbstractCriteria> {


    public NewsService(NewsMapper mapper, NewsValidator validator, NewsRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(NewsCreateDto newsCreateDto) {

        try {
            validator.validOnCreate(newsCreateDto);
            News news = repository.save(mapper.fromCreateDto(newsCreateDto));
            return new ResponseEntity<>(new DataDto<>(news.getId()));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }

    }

    @Override
    public ResponseEntity<DataDto<Long>> update(NewsUpdateDto newsUpdateDto) {
        try {
            validator.validOnUpdate(newsUpdateDto);
            News news = repository.update(mapper.fromUpdateDto(newsUpdateDto));
            return new ResponseEntity<>(new DataDto<>(news.getId()));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            validator.validOnId(id);
            repository.deleteById(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<NewsDto>> get(Long id) {
        try {
            validator.validOnId(id);
            Optional<News> optionalNews = repository.findById(id);

            return optionalNews.map(news -> new ResponseEntity<>(new DataDto<>(mapper.toDto(news)))).orElseGet(() -> new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "NOT FOUND"))));

        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }

    }

    @Override
    public ResponseEntity<DataDto<List<NewsDto>>> getAll(AbstractCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<NewsDto> newsDto = mapper.toDto(repository.findAll(pageable).getContent());
        return new ResponseEntity<>(new DataDto<>(newsDto));
    }
}
