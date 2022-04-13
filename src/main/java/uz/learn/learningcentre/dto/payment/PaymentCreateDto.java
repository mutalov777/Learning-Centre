package uz.learn.learningcentre.dto.payment;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.BaseDto;
import uz.learn.learningcentre.enums.PaymentType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class PaymentCreateDto implements BaseDto {

    private Double price;

    private Long studentId;

    private Long subjectId;// write relationship

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType; // cash / card

}
