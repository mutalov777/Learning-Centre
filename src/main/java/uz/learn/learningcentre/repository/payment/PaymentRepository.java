package uz.learn.learningcentre.repository.payment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.learn.learningcentre.entity.Payment;
import uz.learn.learningcentre.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long>, BaseRepository {

    @Query(value = "update payment.payment set deleted  = true where id = :id", nativeQuery = true)
    Boolean softDelete(Long id);


    Optional<Payment> findPaymentByIdAndDeletedFalse(Long id);

    List<Payment> findAllByDeletedFalse(Pageable pageable);

    List<Payment> findAllByDeletedFalseAndAndStudentId(Long id, Pageable pageable);

}
