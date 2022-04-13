package uz.learn.learningcentre.repository.mock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.learn.learningcentre.entity.Mock;
import uz.learn.learningcentre.repository.base.BaseRepository;

import java.util.Optional;

public interface MockRepository extends JpaRepository<Mock, Long>, BaseRepository {

    @Transactional
    @Modifying
    @Query(value = "update exam.mock set enabled = false where enabled = true ", nativeQuery = true)
    void updateEnabled();


    @Query(value = "select id from exam.mock where enabled is true order by id desc limit 1", nativeQuery = true)
    Optional<Long> findId();

}
