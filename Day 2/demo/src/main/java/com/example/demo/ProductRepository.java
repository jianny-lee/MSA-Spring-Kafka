package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> { //product는 데이터 모델, Long은 PK의 타입

}
