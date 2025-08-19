package co.analisys.gimnasio.service;

import co.analisys.gimnasio.exception.ClaseNoEncontrada;
import co.analisys.gimnasio.exception.EntrenadorNoDisponible;
import co.analisys.gimnasio.exception.EquipoNoDisponible;
import co.analisys.gimnasio.model.Clase;
import co.analisys.gimnasio.repository.ClaseRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClaseService {
    @Autowired
    private ClaseRepository claseRepository;

    public Clase programarClase(Clase clase) {
        return claseRepository.save(clase);
    }

    public List<Clase> obtenerTodasClases() {
        return claseRepository.findAll();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public void reservarEntrenador(Long entrenadorId, Long claseId) {
    Boolean entrenadorDisponible = restTemplate.postForObject(
        "http://localhost:8080/api/gimnasio/entrenadores/" + entrenadorId + "/reservar",
        null,
        Boolean.class);

        if (entrenadorDisponible != null && entrenadorDisponible) {
            Clase clase = claseRepository.findById(claseId)
                    .orElseThrow(() -> new ClaseNoEncontrada(claseId));
            clase.setEntrenadorId(entrenadorId);
            claseRepository.save(clase);
        } else {
            throw new EntrenadorNoDisponible(entrenadorId);
        }
    }

    @Transactional
    public void cancelarEntrenador(Long entrenadorId, Long claseId) {
    Boolean entrenadorCancelado = restTemplate.postForObject(
        "http://localhost:8080/api/gimnasio/entrenadores/" + entrenadorId + "/cancelar",
        null,
        Boolean.class);

        if (entrenadorCancelado != null && entrenadorCancelado) {
            Clase clase = claseRepository.findById(claseId)
                    .orElseThrow(() -> new ClaseNoEncontrada(claseId));
            clase.setEntrenadorId(null);
            claseRepository.save(clase);
        } else {
            throw new EntrenadorNoDisponible(entrenadorId);
        }
    }

    @Transactional
    public void reservarEquipo(Long equipoId, Long cantidad, Long claseId) {
    Boolean equipoDisponible = restTemplate.postForObject(
        "http://localhost:8082/api/gimnasio/equipos/" + equipoId + "/reservar/" + cantidad,
        null,
        Boolean.class);

        if (equipoDisponible != null && equipoDisponible) {
            Clase clase = claseRepository.findById(claseId)
                    .orElseThrow(() -> new ClaseNoEncontrada(claseId));

            clase.setEquipoId(equipoId);
            clase.setCantidadEquipos(cantidad);

            claseRepository.save(clase);
        } else {
            throw new EquipoNoDisponible(equipoId);
        }
    }

    @Transactional
    public void devolverEquipo(Long equipoId, Long cantidad, Long claseId) {
    Boolean equipoDevuelto = restTemplate.postForObject(
        "http://localhost:8082/api/gimnasio/equipos/" + equipoId + "/devolver/" + cantidad,
        null,
        Boolean.class);

        if (equipoDevuelto != null && equipoDevuelto) {
            Clase clase = claseRepository.findById(claseId)
                    .orElseThrow(() -> new ClaseNoEncontrada(claseId));

            clase.setEquipoId(null);
            clase.setCantidadEquipos(0L);

            claseRepository.save(clase);
        } else {
            throw new EquipoNoDisponible(equipoId);
        }
    }

}