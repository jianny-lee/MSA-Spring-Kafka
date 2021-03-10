package com.example.demo;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler {
    @StreamListener(Processor.INPUT)
    public void onProductChanged(@Payload ProductChanged productChanged){
        System.out.println(productChanged.getEventType());
        System.out.println(productChanged.getProductName());
        System.out.println(productChanged.getProductStock());

        //To-do
            //CJ 전문 교환
            //Customer SNS 발송
            //배송 어그리게잇에 정보 저장... >> Event Publish
    }
}
