package co.analisys.gimnasio.dto;

import java.time.LocalDateTime;
import java.util.List;

public class HorarioClaseMessage {
    private Long claseId;
    private String nombreClase;
    private LocalDateTime nuevaFechaHora;
    private List<Long> miembrosIds;

    public HorarioClaseMessage() {}

    public HorarioClaseMessage(Long claseId, String nombreClase, LocalDateTime nuevaFechaHora, List<Long> miembrosIds) {
        this.claseId = claseId;
        this.nombreClase = nombreClase;
        this.nuevaFechaHora = nuevaFechaHora;
        this.miembrosIds = miembrosIds;
    }

    public Long getClaseId() {
        return claseId;
    }

    public void setClaseId(Long claseId) {
        this.claseId = claseId;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public LocalDateTime getNuevaFechaHora() {
        return nuevaFechaHora;
    }

    public void setNuevaFechaHora(LocalDateTime nuevaFechaHora) {
        this.nuevaFechaHora = nuevaFechaHora;
    }

    public List<Long> getMiembrosIds() {
        return miembrosIds;
    }

    public void setMiembrosIds(List<Long> miembrosIds) {
        this.miembrosIds = miembrosIds;
    }

    @Override
    public String toString() {
        return "HorarioClaseMessage{" +
                "claseId=" + claseId +
                ", nombreClase='" + nombreClase + '\'' +
                ", nuevaFechaHora=" + nuevaFechaHora +
                ", miembrosIds=" + miembrosIds +
                '}';
    }
}
