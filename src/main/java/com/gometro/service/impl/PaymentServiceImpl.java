package com.gometro.service.impl;

import com.gometro.entity.Payment;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.PaymentRepository;
import com.gometro.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "id", id));
    }

    @Override
    public Payment update(Long id, Payment paymentDetails) {
        Payment existingPayment = getById(id);
        if (paymentDetails.getPaymentStatus() != null) existingPayment.setPaymentStatus(paymentDetails.getPaymentStatus());
        if (paymentDetails.getAmount() != null) existingPayment.setAmount(paymentDetails.getAmount());
        if (paymentDetails.getPaymentMethod() != null) existingPayment.setPaymentMethod(paymentDetails.getPaymentMethod());
        return paymentRepository.save(existingPayment);
    }

    @Override
    public void delete(Long id) {
        Payment payment = getById(id);
        paymentRepository.delete(payment);
    }
}
