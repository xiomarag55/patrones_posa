package org.reportes.broker;

import org.springframework.stereotype.Component;

@Component
public class ReportPublisher {
    /*private final RabbitTemplate rabbitTemplate;

    public ReportPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("reportesQueue", message);
        System.out.println("Mensaje enviado a Broker: " + message);
    }*/
}
