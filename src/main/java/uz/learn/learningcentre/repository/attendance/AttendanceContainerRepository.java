package uz.learn.learningcentre.repository.attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.learningcentre.entity.AttendanceContainer;
import uz.learn.learningcentre.repository.base.BaseRepository;

import java.util.List;

public interface AttendanceContainerRepository extends JpaRepository<AttendanceContainer, Long>, BaseRepository {

    List<AttendanceContainer> findAllByGroupId(Long groupId);

}
