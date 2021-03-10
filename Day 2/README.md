### Modeling Structure

<img width="523" alt="스크린샷 2021-03-10 14 04 30" src="https://user-images.githubusercontent.com/56791347/110579163-8a9e0500-81a9-11eb-8487-417085f5af00.png">

##### 상품을 등록하고 싶을 때
```
http POST http://localhost:8080/products name=TV stock=30
```

##### 조회하고 싶을 때
```
http GET http://localhost:8080/products
```

##### 삭제하고 싶을 때
```
http DELETE http://localhost:8080/products/숫자
```

##### 변경하고 싶을 때
```
http PATCH http://localhost:8080/products/변경하고싶은곳 name=RADIO or stock=10 or name=RADIO stock=10
```