package com.example.graduationbe.service.impl;

import com.example.graduationbe.config.JwtAuthenticationFilter;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.Cart;
import com.example.graduationbe.entities.commerce.TransactionDetails;
import com.example.graduationbe.repository.CartRepository;
import com.example.graduationbe.repository.UserRepository;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaypalService {
    private static final String CURRENCY = "USD";
    private final APIContext apiContext;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public TransactionDetails createPayment(String intent,
                                            String method,
                                            String description) throws PayPalRESTException {
        String username = JwtAuthenticationFilter.USER_CURRENT;
        User user = this.userRepository.findByUsername(username);
        List<Cart> carts = this.cartRepository.findByUser(user);
        double price = 0;
        for (Cart cart : carts) {
            price = cart.getProduct().getDiscountPrice() * cart.getQuantity();
        }
        Amount amount = new Amount();
        amount.setCurrency(CURRENCY);
        amount.setTotal(String.valueOf(price));
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        Payer payer = new Payer();
        payer.setPaymentMethod(method);
        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        Payment payment1 = payment.create(apiContext);
        TransactionDetails transactionDetails = prepareTransaction(payment1);
        return transactionDetails;
    }

    public TransactionDetails prepareTransaction(Payment payment) {
        String orderId = payment.getId();
        String state = payment.getState();

        List<Transaction> transactions = payment.getTransactions();
        TransactionDetails transactionDetails = new TransactionDetails(orderId, state, transactions);
        return transactionDetails;
    }
}
