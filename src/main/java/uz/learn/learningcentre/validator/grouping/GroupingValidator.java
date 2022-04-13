package uz.learn.learningcentre.validator.grouping;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.grouping.GroupingCreateDto;
import uz.learn.learningcentre.dto.grouping.GroupingDto;
import uz.learn.learningcentre.dto.grouping.GroupingUpdateDto;
import uz.learn.learningcentre.entity.Grouping;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;

@Component
public class GroupingValidator implements GenericValidator<Grouping, GroupingDto, GroupingCreateDto, GroupingUpdateDto, Long> {
    @Override
    public void validOnUpdate( GroupingUpdateDto groupingUpdateDto ) {
        validOnId( groupingUpdateDto.getId() );
    }

    @Override
    public void validOnCreate( GroupingCreateDto groupingCreateDto ) {
        if ( Objects.isNull( groupingCreateDto.getMentor() ) ) {
            throw new BadRequestException( "MENTOR IS NULL" );
        }
        if ( Objects.isNull( groupingCreateDto.getSubject() ) ) {
            throw new BadRequestException( "SUBJECT IS NULL" );
        }
        if ( Objects.isNull( groupingCreateDto.getName() ) ) {
            throw new BadRequestException( "NAME IS NULL" );
        }

    }

    @Override
    public void validOnId( Long id ) {

    }
}
