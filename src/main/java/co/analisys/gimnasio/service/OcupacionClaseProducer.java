package co.analisys.gimnasio.service;

import co.analisys.gimnasio.model.OcupacionClase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OcupacionClaseProducer {
    
    @Autowired
    private KafkaTemplate<String, OcupacionClase> kafkaTemplate;
    public void actualizarOcupacion(String claseId, String claseName, int ocupacionActual, int capacidadMaxima) {
        OcupacionClase ocupacion = new OcupacionClase(claseId, claseName, ocupacionActual, capacidadMaxima, LocalDateTime.now());
        kafkaTemplate.send("ocupacion-clases", claseId, ocupacion);
    }
}