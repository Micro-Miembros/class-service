package co.analisys.gimnasio.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    public static final String HORARIO_QUEUE = "horario.cambio.queue";
    public static final String HORARIO_EXCHANGE = "horario.exchange";
    public static final String HORARIO_ROUTING_KEY = "horario.cambio";
    
    @Bean
    public Queue horarioCambioQueue() {
        return new Queue(HORARIO_QUEUE, true);
    }

    @Bean
    public TopicExchange horarioExchange() {
        return new TopicExchange(HORARIO_EXCHANGE);
    }

    @Bean
    public Binding horarioBinding(Queue horarioCambioQueue, TopicExchange horarioExchange) {
        return BindingBuilder.bind(horarioCambioQueue).to(horarioExchange).with(HORARIO_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
