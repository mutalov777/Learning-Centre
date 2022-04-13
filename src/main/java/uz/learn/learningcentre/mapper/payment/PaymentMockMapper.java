package uz.learn.learningcentre.mapper.payment;

import org.mapstruct.Mapper;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockCreateDto;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockDto;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockUpdateDto;
import uz.learn.learningcentre.entity.PaymentMock;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMockMapper extends GenericMapper<PaymentMock, PaymentMockDto, PaymentMockCreateDto, PaymentMockUpdateDto> {


    @Override
    PaymentMock fromDto(PaymentMockDto dto);

    @Override
    List<PaymentMock> fromDto(List<PaymentMockDto> dtos);

    @Override
    PaymentMockDto toDto(PaymentMock entity);

    @Override
    List<PaymentMockDto> toDto(List<PaymentMock> entities);

    @Override
    PaymentMock fromUpdateDto(PaymentMockUpdateDto paymentMockUpdateDto);


    @Override
    List<PaymentMock> fromUpdateDto(List<PaymentMockUpdateDto> ud);

    @Override
    PaymentMock fromCreateDto(PaymentMockCreateDto paymentMockCreateDto);

    @Override
    List<PaymentMock> fromCreateDto(List<PaymentMockCreateDto> cd);
}
