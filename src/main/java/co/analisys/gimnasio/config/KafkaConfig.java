package co.analisys.gimnasio.config;
 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.kafka.core.KafkaTemplate; 
import org.springframework.kafka.core.ProducerFactory;

import co.analisys.gimnasio.model.DatosEntrenamiento;
import co.analisys.gimnasio.model.OcupacionClase; 
 
@Configuration 
public class KafkaConfig { 
    @Bean 
    public KafkaTemplate<String, OcupacionClase> ocupacionClaseKafkaTemplate(ProducerFactory<String, OcupacionClase> producerFactory) { 
        return new KafkaTemplate<>(producerFactory); 
    } 

    @Bean 
    public KafkaTemplate<String, DatosEntrenamiento> datosEntrenamientoKafkaTemplate(ProducerFactory<String, DatosEntrenamiento> producerFactory) { 
        return new KafkaTemplate<>(producerFactory); 
    } 
} 