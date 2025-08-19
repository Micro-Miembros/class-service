package co.analisys.gimnasio.exception;

public class EquipoNoDisponible extends RuntimeException {

    public EquipoNoDisponible(Long equipoId) {
        super("El equipo con ID " + equipoId + " no está disponible o no dispone de suficientes unidades.");
    }
}
