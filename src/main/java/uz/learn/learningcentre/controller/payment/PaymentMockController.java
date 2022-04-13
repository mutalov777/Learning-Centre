package uz.learn.learningcentre.controller.payment;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.payment.PaymentMockCriteria;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockCreateDto;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockDto;
import uz.learn.learningcentre.dto.paymentMock.PaymentMockUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.payment.PaymentMockService;

import java.util.List;

@RestController
@RequestMapping(value = "/payment-mock/")
public class PaymentMockController extends AbstractController<PaymentMockService>
        implements GenericCrudController<PaymentMockCreateDto, PaymentMockUpdateDto>,
        GenericController<PaymentMockDto, PaymentMockCriteria> {


    public PaymentMockController(PaymentMockService service) {
        super(service);
    }

    @Override
    @GetMapping(value = "get/{id}")
    public ResponseEntity<DataDto<PaymentMockDto>> get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping(value = "list")
    public ResponseEntity<DataDto<List<PaymentMockDto>>> getAll(PaymentMockCriteria criteria) {
        return service.getAll(criteria);
    }


    @GetMapping(value = "list/{userId}")
    public ResponseEntity<DataDto<List<PaymentMockDto>>> getAllById(@PathVariable("userId") Long userId, PaymentMockCriteria criteria) {
        return service.getAllById(userId, criteria);
    }

    @Override
    @PostMapping(value = "create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody PaymentMockCreateDto paymentMockCreateDto) {
        return service.create(paymentMockCreateDto);
    }

    @Override
    @PutMapping(value = "update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody PaymentMockUpdateDto paymentMockUpdateDto) {
        return service.update(paymentMockUpdateDto);
    }

    @Override
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
