# kafkademo

Requirements:
- Apache zookeeper 3.4.12
- Apache kafka 2.11-2.0.0

Instructions:
1) Install zookeeper;

2) Install kafka;

3) Start zookeeper: 
 3.1) ./zkServer.sh start;

4) Start kafka: 
 4.1) ./bin/kafka-server-start.sh config/server.properties;

5) Create the kafka topic:
 5.1) ./bin/kafka-topics.sh --list --zookeeper localhost:2181
 5.2) ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 100 --topic demotopic
 5.3) ./bin/kafka-topics.sh --describe --topic demotopic --zookeeper localhost:2181

6) Instantiate the consumer from the Consumer folder. 
 - e.g.: java -cp target/kafkademo.consumer-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.javagroup.kafkademo.consumer.ConsumerApp -consumerGroup2

7) Instantiate the Producer from the kafkademo-producer folder
 - e.g.: java -cp target/kafkademo.producer-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.javagroup.kafkademo.producer.ProducerApp

