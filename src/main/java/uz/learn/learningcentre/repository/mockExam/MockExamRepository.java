package uz.learn.learningcentre.repository.mockExam;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.learningcentre.entity.MockExam;
import uz.learn.learningcentre.repository.base.BaseRepository;

public interface MockExamRepository extends JpaRepository<MockExam, Long>, BaseRepository {

//    @Query(value = "update Mock set enabled = false where enabled is true ", nativeQuery = true)
//    void updateEnabled();
}
