package uz.learn.learningcentre.repository.exam;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.learningcentre.entity.Exam;
import uz.learn.learningcentre.repository.base.BaseRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Long>, BaseRepository {
    List<Exam> findAllByGroupId(Long groupId, Pageable pageable);
}
