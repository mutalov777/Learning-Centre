package uz.learn.learningcentre.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.learn.learningcentre.entity.News;
import uz.learn.learningcentre.repository.base.BaseRepository;


public interface NewsRepository extends JpaRepository<News, Long>, BaseRepository {

    @Query(value = "update news.news set tittle = :#{#news.tittle}, description = :#{#news.description}, picture_id = :#{#news.picture.id}", nativeQuery = true)
    News update(News news);

}
