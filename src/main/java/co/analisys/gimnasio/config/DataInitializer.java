package co.analisys.gimnasio.config;

import co.analisys.gimnasio.model.Clase;
import co.analisys.gimnasio.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public void run(String... args) throws Exception {
        if (claseRepository.count() == 0) {
            cargarDatosPrueba();
        }
    }

    private void cargarDatosPrueba() {

        Clase yoga = new Clase();
        yoga.setNombre("Yoga Matutino");
        yoga.setHorario(LocalDateTime.of(2024, 12, 1, 8, 0));
        yoga.setCapacidadMaxima(15);
        yoga.setTrainerId(1L);
        yoga.setEquipoId(1L);
        yoga.setCantidadEquipos(2L);
        yoga.setMiembroId(Arrays.asList(1L, 2L));
        claseRepository.save(yoga);

        
        Clase crossfit = new Clase();
        crossfit.setNombre("CrossFit Intensivo");
        crossfit.setHorario(LocalDateTime.of(2024, 12, 1, 18, 0));
        crossfit.setCapacidadMaxima(12);
        crossfit.setTrainerId(2L);
        crossfit.setEquipoId(2L);
        crossfit.setCantidadEquipos(5L);
        crossfit.setMiembroId(Arrays.asList(1L, 3L, 4L));
        claseRepository.save(crossfit);


        Clase pilates = new Clase();
        pilates.setNombre("Pilates");
        pilates.setHorario(LocalDateTime.of(2024, 12, 2, 10, 0));
        pilates.setCapacidadMaxima(10);
        pilates.setTrainerId(1L);
        pilates.setEquipoId(3L);
        pilates.setCantidadEquipos(1L);
        pilates.setMiembroId(Arrays.asList(2L, 5L));
        claseRepository.save(pilates);


        Clase spinning = new Clase();
        spinning.setNombre("Spinning");
        spinning.setHorario(LocalDateTime.of(2024, 12, 2, 19, 0));
        spinning.setCapacidadMaxima(20);
        spinning.setTrainerId(3L);
        spinning.setEquipoId(4L);
        spinning.setCantidadEquipos(15L);
        spinning.setMiembroId(Arrays.asList(3L, 5L));
        claseRepository.save(spinning);

        Clase aerobicos = new Clase();
        aerobicos.setNombre("Aeróbicos");
        aerobicos.setHorario(LocalDateTime.of(2024, 12, 3, 17, 0));
        aerobicos.setCapacidadMaxima(18);
        aerobicos.setTrainerId(2L);
        aerobicos.setEquipoId(1L);
        aerobicos.setCantidadEquipos(3L);
        aerobicos.setMiembroId(Arrays.asList(4L));
        claseRepository.save(aerobicos);

        System.out.println("Datos de prueba cargados para clases con inscripciones de miembros");
    }
}