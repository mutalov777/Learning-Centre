package uz.learn.learningcentre.repository.authUser;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.learningcentre.entity.AuthUser;
import uz.learn.learningcentre.enums.AuthRole;
import uz.learn.learningcentre.repository.base.BaseRepository;

import java.util.List;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, BaseRepository {

    List<AuthUser> findAllByRole(AuthRole role, Pageable pageable);

    AuthUser findByFullName( String mentor );


    AuthUser findAuthUserByPhoneNumber(String phoneNumber);

}
