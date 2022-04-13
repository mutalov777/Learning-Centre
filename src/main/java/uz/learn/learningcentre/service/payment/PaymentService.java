package uz.learn.learningcentre.service.payment;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.base.AbstractCriteria;
import uz.learn.learningcentre.dto.payment.PaymentCreateDto;
import uz.learn.learningcentre.dto.payment.PaymentDto;
import uz.learn.learningcentre.dto.payment.PaymentUpdateDto;
import uz.learn.learningcentre.entity.Payment;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.mapper.payment.PaymentMapper;
import uz.learn.learningcentre.repository.payment.PaymentRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.payment.PaymentValidator;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService extends AbstractService<PaymentMapper, PaymentValidator, PaymentRepository>
        implements GenericCrudService<PaymentDto, PaymentCreateDto, PaymentUpdateDto>,
        GenericService<PaymentDto, AbstractCriteria> {


    public PaymentService(PaymentMapper mapper, PaymentValidator validator, PaymentRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(PaymentCreateDto paymentCreateDto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(PaymentUpdateDto paymentUpdateDto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            validator.validOnId(id);
            repository.softDelete(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).build()));
        }
    }

    @Override
    public ResponseEntity<DataDto<PaymentDto>> get(Long id) {
        try {
            validator.validOnId(id);
            Optional<Payment> optionalPayment = repository.findPaymentByIdAndDeletedFalse(id);
            return optionalPayment.map(payment -> new ResponseEntity<>(new DataDto<>(mapper.toDto(payment)))).orElseGet(() -> new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "NOT FOUND"))));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<List<PaymentDto>>> getAll(AbstractCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<PaymentDto> paymentDtos = mapper.toDto(repository.findAllByDeletedFalse(pageable));
        return new ResponseEntity<>(new DataDto<>(paymentDtos));
    }


    public ResponseEntity<DataDto<List<PaymentDto>>> getStudentsAllPayment(Long studentId, AbstractCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Payment> payments = repository.findAllByDeletedFalseAndAndStudentId(studentId, pageable);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(payments)));
    }


}
