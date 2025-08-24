package co.analisys.gimnasio.exception;

public class MiembroMembresiaNoActiva extends RuntimeException {

    public MiembroMembresiaNoActiva(Long miembroId) {
        super("La membresía del miembro con ID " + miembroId + " no está activa.");
    }

}
