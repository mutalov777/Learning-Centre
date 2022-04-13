package uz.learn.learningcentre.service.grouping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.grouping.GroupingCriteria;
import uz.learn.learningcentre.dto.grouping.GroupingCreateDto;
import uz.learn.learningcentre.dto.grouping.GroupingDto;
import uz.learn.learningcentre.dto.grouping.GroupingUpdateDto;
import uz.learn.learningcentre.entity.Grouping;
import uz.learn.learningcentre.exceptions.NotFoundException;
import uz.learn.learningcentre.mapper.grouping.GroupingMapper;
import uz.learn.learningcentre.repository.grouping.GroupingRepository;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.grouping.GroupingValidator;

import java.util.List;
import java.util.Optional;

@Service
public class GroupingService extends AbstractService<GroupingMapper, GroupingValidator, GroupingRepository>
        implements GenericCrudService<GroupingDto, GroupingCreateDto, GroupingUpdateDto>,
        GenericService<GroupingDto, GroupingCriteria> {

    @Autowired
    public GroupingService( @Qualifier( "groupingMapperImpl" ) GroupingMapper mapper , GroupingValidator validator , GroupingRepository repository ) {
        super( mapper , validator , repository );
    }

    @Override
    public ResponseEntity<DataDto<Long>> create( GroupingCreateDto groupingCreateDto ) {
        validator.validOnCreate( groupingCreateDto );
        Grouping grouping = mapper.fromCreateDto( groupingCreateDto );
        grouping.setSubjectId( groupingCreateDto.getSubject() );
        grouping.setMentorId( groupingCreateDto.getMentor() );
        Grouping save = repository.save( grouping );
        return new ResponseEntity<>( new DataDto<>( save.getId() ) );
    }

    @Override
    @CachePut(value ="group",key = "#groupingUpdateDto")
    public ResponseEntity<DataDto<Long>> update( GroupingUpdateDto groupingUpdateDto ) {
        validator.validOnUpdate( groupingUpdateDto );
        Grouping groupingById = repository.findByIdWithoutStudent( groupingUpdateDto.getId() ).orElseThrow( () -> {
            throw new NotFoundException( "Group not found" );
        } );
        Grouping grouping = mapper.fromUpdateDto( groupingUpdateDto , groupingById );
        Grouping save = repository.save( grouping );
        return new ResponseEntity<>( new DataDto<>( save.getId() ) );
    }

    public ResponseEntity<DataDto<Boolean>> setStudent( Long groupId , Long studentId ) {
        repository.setStudent( studentId , groupId );
        return new ResponseEntity<>( new DataDto<>( true ) );
    }

    @Override
    @CacheEvict(value ="group",key = "#id")
    public ResponseEntity<DataDto<Boolean>> delete( Long id ) {
        validator.validOnId( id );
        Optional<Grouping> byId = repository.findById( id );
        byId.ifPresent( repository::delete );
        return new ResponseEntity<>( new DataDto<>( true ) );
    }

    @Override
    @Cacheable(value = "group" ,key = "#id")
    public ResponseEntity<DataDto<GroupingDto>> get( Long id ) {
            validator.validOnId( id );
            Grouping groupingById = repository.findById( id ).orElseThrow( () -> {
                throw new NotFoundException( "Group not found" );
            } );
            GroupingDto groupingDto = mapper.toDto( groupingById );
            return new ResponseEntity<>( new DataDto<>( groupingDto ) );
     }

    @Override
    public ResponseEntity<DataDto<List<GroupingDto>>> getAll( GroupingCriteria criteria ) {
        Pageable pageable = PageRequest.of( criteria.getPage() , criteria.getSize() , Sort.by( "price" ).descending() );
        Page<Grouping> all = repository.findAll( pageable );
        List<Grouping> list = all.stream().toList();
        List<GroupingDto> dtos = mapper.toDto( list );
        return new ResponseEntity<>( new DataDto<>( dtos ) );
    }

}
