package uz.learn.learningcentre.mapper.payment;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.payment.PaymentCreateDto;
import uz.learn.learningcentre.dto.payment.PaymentDto;
import uz.learn.learningcentre.dto.payment.PaymentUpdateDto;
import uz.learn.learningcentre.entity.Payment;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface PaymentMapper extends GenericMapper<Payment, PaymentDto, PaymentCreateDto, PaymentUpdateDto> {

    @Override
    Payment fromDto(PaymentDto dto);

    @Override
    List<Payment> fromDto(List<PaymentDto> dtos);

    @Override
    PaymentDto toDto(Payment entity);

    @Override
    List<PaymentDto> toDto(List<Payment> entities);

    @Override
    Payment fromUpdateDto(PaymentUpdateDto paymentUpdateDto);

    @Override
    Payment fromUpdateDto(PaymentUpdateDto paymentUpdateDto,@MappingTarget Payment payment);

    @Override
    List<Payment> fromUpdateDto(List<PaymentUpdateDto> ud);

    @Override
    Payment fromCreateDto(PaymentCreateDto paymentCreateDto);

    @Override
    List<Payment> fromCreateDto(List<PaymentCreateDto> cd);
}
