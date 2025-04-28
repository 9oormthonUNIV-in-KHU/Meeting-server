package org.groomUniv.meet.payment.dto.response;

public class PaymentVerificationResponse {
    private boolean verified;
    private String message;

    public PaymentVerificationResponse(boolean verified, String message) {
        this.verified = verified;
        this.message = message;
    }

    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}