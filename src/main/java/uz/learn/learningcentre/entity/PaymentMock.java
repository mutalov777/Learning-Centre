package uz.learn.learningcentre.entity;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.entity.base.Auditable;
import uz.learn.learningcentre.enums.PaymentType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(schema = "payment",name = "payment_mock")
public class PaymentMock extends Auditable {

    private Double price;

    private Long studentId;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType; // cash / card

    private boolean deleted;

    private boolean successful;

}
