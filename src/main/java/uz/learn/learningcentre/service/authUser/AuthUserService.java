package uz.learn.learningcentre.service.authUser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.authUser.AuthUserCriteria;
import uz.learn.learningcentre.dto.auth.AuthUserCreateDto;
import uz.learn.learningcentre.dto.auth.AuthUserDto;
import uz.learn.learningcentre.dto.auth.AuthUserUpdateDto;
import uz.learn.learningcentre.entity.AuthUser;
import uz.learn.learningcentre.entity.Subject;
import uz.learn.learningcentre.enums.AuthRole;
import uz.learn.learningcentre.exceptions.NotFoundException;
import uz.learn.learningcentre.mapper.authUser.AuthUserMapper;
import uz.learn.learningcentre.repository.authUser.AuthUserRepository;
import uz.learn.learningcentre.repository.subject.SubjectRepository;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.authUser.AuthUserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthUserService extends AbstractService<AuthUserMapper, AuthUserValidator, AuthUserRepository> implements GenericCrudService<AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto>, GenericService<AuthUserDto, AuthUserCriteria> {

    private final SubjectRepository subjectRepository;

    public AuthUserService( @Qualifier( "authUserMapperImpl" ) AuthUserMapper mapper , AuthUserValidator validator , AuthUserRepository repository , SubjectRepository subjectRepository ) {
        super( mapper , validator , repository );
        this.subjectRepository = subjectRepository;
    }

    @Override
    public ResponseEntity<DataDto<Long>> create( AuthUserCreateDto authUserCreateDto ) {
        validator.validOnCreate( authUserCreateDto );
        List<Subject> subjects = findSubjects( authUserCreateDto.getSubjects() );
        AuthUser authUser = mapper.fromCreateDto( authUserCreateDto );
        authUser.setSubjects( subjects );
        AuthUser save = repository.save( authUser );
        return new ResponseEntity<>( new DataDto<>( save.getId() ) );
    }

    private List<Subject> findSubjects( List<Long> subjects ) {
        List<Subject> s = new ArrayList<>();
        for ( Long subject: subjects ) {
            s.add( subjectRepository.findById( subject ).orElseThrow( () -> {
                throw new NotFoundException( "Subject not found" );

            } ) );
        }
        return s;
    }

    @Override
    public ResponseEntity<DataDto<Long>> update( AuthUserUpdateDto authUserUpdateDto ) {
        validator.validOnUpdate( authUserUpdateDto );
        AuthUser auth = repository.findById( authUserUpdateDto.getId() ).orElseThrow( () -> {
            throw new NotFoundException( "User not found" );
        } );
        AuthUser authUser = mapper.fromUpdateDto( authUserUpdateDto , auth );
        AuthUser save = repository.save( authUser );
        return new ResponseEntity<>( new DataDto<>( save.getId() ) );
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete( Long id ) {
        validator.validOnId( id );
        repository.deleteById( id );
        return new ResponseEntity<>( new DataDto<>( Boolean.valueOf( true ) ) );
    }

    @Override
    public ResponseEntity<DataDto<AuthUserDto>> get( Long id ) {
        validator.validOnId( id );
        AuthUser authUser = repository.findById( id ).orElseThrow( () -> {
            throw new NotFoundException( "user not found" );
        } );
        AuthUserDto authUserDto = mapper.toDto( authUser );
        return new ResponseEntity<>( new DataDto<>( authUserDto ) );
    }

    @Override
    public ResponseEntity<DataDto<List<AuthUserDto>>> getAll( AuthUserCriteria criteria ) {
        Pageable pageable = PageRequest.of( criteria.getPage() , criteria.getSize() );
        List<AuthUser> authUserList;
        if ( Objects.nonNull( criteria.getRole() ) && AuthRole.isAuthRole( criteria.getRole() ) ) {
            authUserList = repository.findAllByRole( criteria.getRole() , pageable );
        } else {
            authUserList = repository.findAll( pageable ).getContent();
        }
        List<AuthUserDto> authUserDtoList = mapper.toDto( authUserList );
        return new ResponseEntity<>( new DataDto<>( authUserDtoList ) );
    }

}
