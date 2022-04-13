package uz.learn.learningcentre.repository.payment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.learn.learningcentre.entity.PaymentMock;
import uz.learn.learningcentre.repository.base.BaseRepository;

import java.util.List;

public interface PaymentMockRepository extends JpaRepository<PaymentMock, Long>, BaseRepository {


    List<PaymentMock> findAllByStudentId(Pageable pageable, Long studentId);

    @Modifying
    @Transactional
    @Query(value = "update payment.payment_mock set deleted = true where id = :id", nativeQuery = true)
    void softDelete(Long id);


    boolean findPaymentMockByStudentId(Long studentId);


}
