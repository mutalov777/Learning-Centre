package uz.learn.learningcentre.repository.speciality;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.learningcentre.entity.Speciality;
import uz.learn.learningcentre.repository.base.BaseRepository;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality, Long>, BaseRepository {

    List<Speciality> findAllBySubjectId(Long subjectId, Pageable pageable);

    List<Speciality> findAllByMain(boolean main, Pageable pageable);

    List<Speciality> findAllByMainAndSubjectId(boolean main, Long subjectId, Pageable pageable);

}
