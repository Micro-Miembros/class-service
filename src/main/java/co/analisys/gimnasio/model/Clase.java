package co.analisys.gimnasio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDateTime horario;
    private int capacidadMaxima;
    private Long trainerId;
    private Long equipoId;
    private Long cantidadEquipos;
    
    public void setEntrenadorId(Long entrenadorId) {
        this.trainerId = entrenadorId;
    }

    public void setEquipoId(Long equipoId) {
        this.equipoId = equipoId;
    }

    public void setCantidadEquipos(Long cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }
}