package uz.learn.learningcentre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.learn.learningcentre.entity.Picture;
import uz.learn.learningcentre.repository.base.BaseRepository;

@Repository
public interface FileRepository extends JpaRepository<Picture,Long>, BaseRepository {
}
