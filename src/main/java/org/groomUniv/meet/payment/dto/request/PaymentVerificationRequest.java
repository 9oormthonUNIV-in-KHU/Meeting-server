package org.groomUniv.meet.payment.dto.request;

import java.math.BigDecimal;

public class PaymentVerificationRequest {

    private String merchantUid;
    private String impUid;
    private BigDecimal amount; // 결제 요청 시 저장할 금액

    public String getMerchantUid() {
        return merchantUid;
    }
    public void setMerchantUid(String merchantUid) {
        this.merchantUid = merchantUid;
    }
    public String getImpUid() {
        return impUid;
    }
    public void setImpUid(String impUid) {
        this.impUid = impUid;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}