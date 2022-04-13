package uz.learn.learningcentre.service.payment;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.payment.PaymentMockCriteria;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockCreateDto;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockDto;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockUpdateDto;
import uz.learn.learningcentre.entity.PaymentMock;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.mapper.payment.PaymentMockMapper;
import uz.learn.learningcentre.repository.payment.PaymentMockRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.payment.PaymentMockValidator;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMockService extends AbstractService<PaymentMockMapper, PaymentMockValidator, PaymentMockRepository>
        implements GenericCrudService<PaymentMockDto, PaymentMockCreateDto, PaymentMockUpdateDto>,
        GenericService<PaymentMockDto, PaymentMockCriteria> {

    public PaymentMockService(PaymentMockMapper mapper, PaymentMockValidator validator, PaymentMockRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(PaymentMockCreateDto paymentMockCreateDto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(PaymentMockUpdateDto paymentMockUpdateDto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            validator.validOnId(id);
            repository.softDelete(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.NO_CONTENT);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<PaymentMockDto>> get(Long id) {
        try {
            validator.validOnId(id);
            Optional<PaymentMock> optionalPaymentMock = repository.findById(id);
            return optionalPaymentMock
                    .map(paymentMock -> new ResponseEntity<>(new DataDto<>(mapper.toDto(paymentMock))))
                    .orElseGet(() -> new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Not Found"))));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, "Id invalid")));
        }

    }

    @Override
    public ResponseEntity<DataDto<List<PaymentMockDto>>> getAll(PaymentMockCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<PaymentMock> paymentMocks = repository.findAll(pageable).getContent();
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(paymentMocks)));
    }

    public ResponseEntity<DataDto<List<PaymentMockDto>>> getAllById(Long userId, PaymentMockCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<PaymentMock> paymentMocks = repository.findAllByStudentId(pageable, userId);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(paymentMocks)));
    }


    public boolean findByStudentId(Long studentId) {
        return repository.findPaymentMockByStudentId(studentId);
    }

}
