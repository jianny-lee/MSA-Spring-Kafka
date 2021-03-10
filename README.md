## 카프카 실행
### 주키퍼 서버 실행
터미널을 생성해서 카프카 홈에서 주키퍼 서버를 실행한다.
 - bin/zookeeper-server-start.sh config/zookeeper.properties

### 카프카 서버(Broker) 실행
새로운 터미널을 하나 더 생성해서 카프카 서버를 실행한다.
 - bin/kafka-server-start.sh config/server.properties

## 토픽(topic)
### 토픽 생성
새로운 터미널을 하나 더 생성해서 토픽을 생성한다.
 - bin/kafka-topics.sh -create -zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic {TopicName}

생성된 토픽 리스트 확인
bin/kafka-topics.sh --list --zookeeper localhost:2181

토픽 삭제
bin/kafka-topics.sh --delete --zookeeper localhost:2181 --topic {TopicName}

### 메세지 전송
#### producer
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic {TopicName}
> {message}

#### consumer
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic {TopicName} --from-beginning
{message}
