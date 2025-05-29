//package org.groomUniv.meet.payment.controller;
//
//import org.groomUniv.meet.payment.service.PaymentService;
//import org.groomUniv.meet.payment.dto.request.PaymentVerificationRequest;
//import org.groomUniv.meet.payment.dto.response.PaymentVerificationResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/payment")
//public class PaymentController {
//
//    private final PaymentService paymentService;
//
//    public PaymentController(PaymentService paymentService){
//        this.paymentService = paymentService;
//    }
//
//    // 결제 요청 시 DB에 저장하는 예제 엔드포인트 (클라이언트에서 별도로 호출하거나 결제 진행 시 함께 처리)
//    @PostMapping("/request")
//    public ResponseEntity<?> requestPayment(@RequestBody PaymentVerificationRequest request) {
//        paymentService.savePaymentRequest(request.getMerchantUid(), request.getAmount());
//        return ResponseEntity.ok("Payment request saved.");
//    }
//
//    // 결제 완료 후 검증 요청 엔드포인트
//    @PostMapping("/verify")
//    public ResponseEntity<PaymentVerificationResponse> verifyPayment(@RequestBody PaymentVerificationRequest request) {
//        boolean verified = paymentService.verifyPayment(request.getMerchantUid(), request.getImpUid());
//        if (verified) {
//            return ResponseEntity.ok(new PaymentVerificationResponse(true, "Payment verified successfully."));
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new PaymentVerificationResponse(false, "Payment verification failed."));
//        }
//    }
//}