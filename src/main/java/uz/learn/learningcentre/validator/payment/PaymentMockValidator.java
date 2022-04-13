package uz.learn.learningcentre.validator.payment;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockCreateDto;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockDto;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockUpdateDto;
import uz.learn.learningcentre.entity.PaymentMock;
import uz.learn.learningcentre.validator.base.GenericValidator;

@Component
public class PaymentMockValidator implements GenericValidator<PaymentMock, PaymentMockDto, PaymentMockCreateDto, PaymentMockUpdateDto, Long> {

    @Override
    public void validOnUpdate(PaymentMockUpdateDto paymentMockUpdateDto) {

    }

    @Override
    public void validOnCreate(PaymentMockCreateDto paymentMockCreateDto) {

    }

    @Override
    public void validOnId(Long id) {

    }
}
