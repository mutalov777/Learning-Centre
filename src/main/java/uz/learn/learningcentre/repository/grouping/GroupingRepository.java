package uz.learn.learningcentre.repository.grouping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.learn.learningcentre.entity.Grouping;
import uz.learn.learningcentre.repository.base.BaseRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface GroupingRepository extends JpaRepository<Grouping, Long>, BaseRepository {

    @Query( value = "select * from grouping.grouping t where t.id=:group_id", nativeQuery = true )
    Optional<Grouping> findByIdWithoutStudent( @Param( "group_id" ) Long id );

    @Transactional
    @Modifying
    @Query( value = "insert into student.student_group(group_id, student_id) values (:grouping_id,:stu_id)", nativeQuery = true )
    void setStudent( Long stu_id , Long grouping_id );

/*
    @Transactional
    @Query( value = "select t.*,s.* from grouping.grouping t inner join student.student_group sg on sg.group_id = t.id inner join auth.student s on s.id=sg.student_id    where t.id=:group_id;", nativeQuery = true )
    Optional<Grouping> findByIdWithStudent( Long groupId);*/
}
