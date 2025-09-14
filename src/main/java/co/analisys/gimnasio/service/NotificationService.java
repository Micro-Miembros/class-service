package co.analisys.gimnasio.service;

import co.analisys.gimnasio.config.RabbitMQConfig;
import co.analisys.gimnasio.dto.HorarioClaseMessage;
import co.analisys.gimnasio.model.Clase;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void enviarNotificacionCambioHorario(Clase clase) {
        try {
            HorarioClaseMessage mensaje = new HorarioClaseMessage(
                clase.getId(),
                clase.getNombre(),
                clase.getHorario(),
                clase.getMiembroId()
            );
            
            rabbitTemplate.convertAndSend(
                RabbitMQConfig.HORARIO_EXCHANGE,
                RabbitMQConfig.HORARIO_ROUTING_KEY,
                mensaje
            );
            
            System.out.println("Mensaje de cambio de horario enviado a RabbitMQ para clase: " + clase.getNombre());
        } catch (Exception e) {
            System.err.println("Error enviando mensaje a RabbitMQ para clase: " + clase.getNombre());
            e.printStackTrace();
        }
    }
}
