//package org.groomUniv.meet.payment.service;
//
//import com.siot.IamportRestClient.IamportClient;
//import com.siot.IamportRestClient.response.IamportResponse;
//import org.groomUniv.meet.payment.entity.Payment;
//import org.groomUniv.meet.payment.repository.PaymentRepository;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//public class PaymentService {
//
//    private final PaymentRepository paymentRepository;
//    private final IamportClient iamportClient;
//
//    public PaymentService(PaymentRepository paymentRepository, IamportClient iamportClient) {
//        this.paymentRepository = paymentRepository;
//        this.iamportClient = iamportClient;
//    }
//
//    // 결제 요청 시 DB에 결제 정보를 저장
//    public Payment savePaymentRequest(String merchantUid, BigDecimal amount) {
//        Payment payment = new Payment();
//        payment.setMerchantUid(merchantUid);
//        payment.setAmount(amount);
//        payment.setStatus("PENDING");
//        payment.setRequestTime(LocalDateTime.now());
//        return paymentRepository.save(payment);
//    }
//
//    // 결제 검증 메서드
//    public boolean verifyPayment(String merchantUid, String impUid) {
//        // DB에서 해당 주문 번호로 저장된 결제 정보 조회
//        Optional<Payment> optPayment = paymentRepository.findByMerchantUid(merchantUid);
//        if (!optPayment.isPresent()) {
//            System.out.println("해당하는 Payment 못 찾음");
//            return false;
//        }
//        Payment payment = optPayment.get();
//        System.out.println("payment.get() : " + payment);
//
//        try {
//            // 실제 일어난 결제 정보(iamport API를 통해 결제 상세정보 조회)
//            IamportResponse<com.siot.IamportRestClient.response.Payment> response = iamportClient.paymentByImpUid(impUid);
//            com.siot.IamportRestClient.response.Payment paymentData = response.getResponse();
//
//            // 결제 신청 금액과 결제가 된 금액을 비교
//            if (paymentData.getAmount().compareTo(payment.getAmount()) == 0) {
//                payment.setStatus("VERIFIED");
//                payment.setImpUid(impUid);
//                payment.setVerifiedTime(LocalDateTime.now());
//                paymentRepository.save(payment);
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 검증에 실패하면 상태 업데이트 (원하는 로직에 맞게 처리)
//        payment.setStatus("FAILED");
//        paymentRepository.save(payment);
//        return false;
//    }
//}
//
