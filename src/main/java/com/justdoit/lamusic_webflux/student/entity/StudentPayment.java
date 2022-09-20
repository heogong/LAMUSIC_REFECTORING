package com.justdoit.lamusic_webflux.student.entity;

import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class StudentPayment {
    private String cashAmount;
    private String accountAmount;
    private String cardAmount;
    private String cardCompanyInfo;
    private String paymentMemo;
    private Date accountDate;
    private Date createDate;

    public static StudentPayment createStudentPayment(StudentDTO.StudentReq req) {
        return StudentPayment.builder()
                .cashAmount(req.getCashAmount())
                .accountAmount(req.getAccountAmount())
                .cardAmount(req.getCardAmount())
                .cardCompanyInfo(req.getCardCompanyInfo())
                .paymentMemo(req.getPaymentMemo())
                .accountDate(req.getAccountDate())
                .createDate(new Date())
                .build();
    }
}
