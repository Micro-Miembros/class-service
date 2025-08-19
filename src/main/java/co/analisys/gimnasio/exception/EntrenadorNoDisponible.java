package co.analisys.gimnasio.exception;

public class EntrenadorNoDisponible extends RuntimeException {

    public EntrenadorNoDisponible(Long entrenadorId) {
        super("El entrenador con ID " + entrenadorId + " no está disponible.");
    }
}
