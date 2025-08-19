package co.analisys.gimnasio.exception;

public class ClaseNoEncontrada extends RuntimeException {

    public ClaseNoEncontrada(Long claseId) {
        super("La clase con ID " + claseId + " no fue encontrada.");
    }
}
