package uz.learn.learningcentre.repository.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.learningcentre.entity.Subject;
import uz.learn.learningcentre.repository.base.BaseRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long>, BaseRepository {

    Subject findByName( String name );
}
