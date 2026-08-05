package com.gometro.service;

import com.gometro.entity.Payment;
import java.util.List;

public interface PaymentService {
    Payment create(Payment payment);
    List<Payment> getAll();
    Payment getById(Long id);
    Payment update(Long id, Payment paymentDetails);
    void delete(Long id);
}
