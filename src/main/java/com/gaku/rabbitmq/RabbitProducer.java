package com.gaku.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;


public class RabbitProducer {

    private static final String IP_ADDRESS = "localhost";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);


    }



}
