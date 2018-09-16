package com.javagroup.kafkademo.consumer;

import java.time.Duration;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.javagroup.kafkademo.common.IKafkaConstants;

public class ConsumerApp {
	
	public static void main(String[] args) {
		init();
		
		String consumerGroup =  (args[0] != "") ? args[0] : "consumerGroup1";
	
		runConsumer(consumerGroup);
	}

	private static void init() {
		Logger.getRootLogger().setLevel(Level.INFO);
		BasicConfigurator.configure();
	}

	private static void runConsumer(String consumerGroup) {
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer(consumerGroup);

		executeConsumer(consumer);
		
		consumer.close();
	}

	private static void executeConsumer(Consumer<Long, String> consumer) {
		int noMessageFound = 0;

		while (true) {
			ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));

			if (consumerRecords.count() == 0) {
				noMessageFound++;

				System.out.println("No message found...");
				if (noMessageFound > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT) {
					break;
				} else {
					continue;
				}
			}

			System.out.println("Printing record...");
			printRecord(consumerRecords);
			
			consumer.commitAsync();
		}
	}

	private static void printRecord(ConsumerRecords<Long, String> consumerRecords) {
		consumerRecords.forEach(record -> {
			System.out.println("Record Key " + record.key());
			System.out.println("Record value " + record.value());
			System.out.println("Record partition " + record.partition());
			System.out.println("Record offset " + record.offset());
		});
	}
}
