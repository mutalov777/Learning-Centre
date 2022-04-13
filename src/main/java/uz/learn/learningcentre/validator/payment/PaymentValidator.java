package uz.learn.learningcentre.validator.payment;


import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.payment.PaymentCreateDto;
import uz.learn.learningcentre.dto.payment.PaymentDto;
import uz.learn.learningcentre.dto.payment.PaymentUpdateDto;
import uz.learn.learningcentre.entity.Payment;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;

@Component
public class PaymentValidator implements GenericValidator<Payment, PaymentDto, PaymentCreateDto, PaymentUpdateDto, Long> {


    @Override
    public void validOnUpdate(PaymentUpdateDto paymentUpdateDto) {

        if (Objects.isNull(paymentUpdateDto)) {
            throw new BadRequestException("Dto is invalid");
        }
        validOnId(paymentUpdateDto.getId());
    }

    @Override
    public void validOnCreate(PaymentCreateDto paymentCreateDto) {
        if (Objects.isNull(paymentCreateDto)
                || Objects.isNull(paymentCreateDto.getStudentId())
                || Objects.isNull(paymentCreateDto.getSubjectId())
                || Objects.isNull(paymentCreateDto.getPaymentType())
                || Objects.isNull(paymentCreateDto.getPrice())) {
            throw new BadRequestException("Dto is invalid");
        }
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id) || id <= 0) throw new BadRequestException("ID IS INVALID");
    }
}
