package co.analisys.gimnasio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosEntrenamiento {
    private String miembroId;
    private String tipoEjercicio;
    private int duracionMinutos;
    private int caloriasQuemadas;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaEntrenamiento;
}