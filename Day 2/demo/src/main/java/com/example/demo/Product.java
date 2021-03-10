package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import javax.persistence.*;

//Aggregate Modeling
@Entity
//@Table(name = "t_product")
public class Product {
    @Id @GeneratedValue
    Long id;

//    @Column(name = "product-name")
    String name;
    int stock;

    //get, set method
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

//    @PostPersist{
//        //ProductChanged 이벤트를 객체 생성하고, Kafka에 publish
//        ProductChanged productChanged = new ProductChanged();
//        kafka.send(ProductChanged);
//    }

    //DB에 저장되고 난 이후에 실행
//    @PostPersist
//    public void eventPublish() {
//        ProductChanged productChanged = new ProductChanged();
//        productChanged.setProductId(this.getId());
//        productChanged.setProductName(this.getName());
//        productChanged.setProductStock(this.getStock());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = null;
//
//        try {
//            json = objectMapper.writeValueAsString(productChanged); //byte 타입을 json으로 변환해주는 method
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("JSON format exception", e);
//        }
//        System.out.println(json);
//    }

    @PreUpdate
    public void eventPublish() {
        ProductChanged productChanged = new ProductChanged();
        productChanged.setProductId(this.getId());
        productChanged.setProductName(this.getName());
        productChanged.setProductStock(this.getStock());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(productChanged); //byte 타입을 json으로 변환해주는 method
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }
//        System.out.println(json);

        Processor processor = DemoApplication.applicationContext.getBean(Processor.class);
        MessageChannel outputChannel = processor.output();

        outputChannel.send(MessageBuilder
                .withPayload(json)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}


