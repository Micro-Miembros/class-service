package co.analisys.gimnasio.exception;

public class MiembroNoAsignado extends RuntimeException {

    public MiembroNoAsignado(Long miembroId, Long claseId) {
        super("El miembro con ID " + miembroId + " no está asignado a la clase con ID " + claseId + ".");
    }

}
