package uz.learn.learningcentre.repository.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.learn.learningcentre.entity.Lesson;
import uz.learn.learningcentre.repository.base.BaseRepository;


public interface LessonRepository extends JpaRepository<Lesson, Long>, BaseRepository {

    @Query(value = "update grouping.lesson set days = :#{#updateEntity.days},  lesson_begin = :#{#updateEntity.lessonBegin},  lesson_end = :#{#updateEntity.lessonEnd}", nativeQuery = true)
    Lesson update(Lesson updateEntity);

}
