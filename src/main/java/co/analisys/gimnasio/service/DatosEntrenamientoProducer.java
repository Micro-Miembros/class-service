package co.analisys.gimnasio.service;

import co.analisys.gimnasio.model.DatosEntrenamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DatosEntrenamientoProducer {
    
    @Autowired
    private KafkaTemplate<String, DatosEntrenamiento> kafkaTemplate;
    public void enviarDatosEntrenamiento(Long miembroId, String tipoEjercicio, int duracionMinutos, int caloriasQuemadas) {
        DatosEntrenamiento datos = new DatosEntrenamiento(miembroId.toString(), tipoEjercicio, duracionMinutos, caloriasQuemadas, LocalDateTime.now());
        kafkaTemplate.send("datos-entrenamiento", datos.getMiembroId(), datos);
    }
}