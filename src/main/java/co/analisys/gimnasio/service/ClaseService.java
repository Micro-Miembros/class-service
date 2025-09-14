package co.analisys.gimnasio.service;

import co.analisys.gimnasio.clients.EquipmentClient;
import co.analisys.gimnasio.clients.TrainerClient;
import co.analisys.gimnasio.clients.MemberClient;
import co.analisys.gimnasio.exception.ClaseNoEncontrada;
import co.analisys.gimnasio.exception.EntrenadorNoDisponible;
import co.analisys.gimnasio.exception.EquipoNoDisponible;
import co.analisys.gimnasio.exception.MiembroMembresiaNoActiva;
import co.analisys.gimnasio.exception.MiembroNoAsignado;
import co.analisys.gimnasio.model.Clase;
import co.analisys.gimnasio.repository.ClaseRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClaseService {
    @Autowired
    private ClaseRepository claseRepository;
    
    @Autowired
    private NotificationService notificationService;

    public Clase programarClase(Clase clase) {
        return claseRepository.save(clase);
    }

    public List<Clase> obtenerTodasClases() {
        return claseRepository.findAll();
    }

    @Autowired
    private EquipmentClient equipmentClient;

    @Autowired
    private TrainerClient trainerClient;

    @Autowired
    private MemberClient memberClient;

    @Transactional
    public void reservarEntrenador(Long entrenadorId, Long claseId) {
        Boolean entrenadorDisponible = trainerClient.reservarEntrenador(entrenadorId);

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
        Boolean entrenadorCancelado = trainerClient.cancelarEntrenador(entrenadorId);

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
        Boolean equipoDisponible = equipmentClient.reservarEquipo(equipoId, cantidad);

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
        Boolean equipoDevuelto = equipmentClient.devolverEquipo(equipoId, cantidad);

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

    public void añadirMiembro(Long miembroId, Long claseId) {
        boolean revisarMembresia = memberClient.isMiembroActivo(miembroId);

        if (!revisarMembresia) {
            throw new MiembroMembresiaNoActiva(miembroId);
        }

        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new ClaseNoEncontrada(claseId));
        clase.setMiembroId(miembroId);
        claseRepository.save(clase);
    }

    public void eliminarMiembro(Long miembroId, Long claseId) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new ClaseNoEncontrada(claseId));

        if (!clase.getMiembroId().contains(miembroId)) {
            throw new MiembroNoAsignado(miembroId, claseId);
        }

        clase.getMiembroId().remove(miembroId);
        claseRepository.save(clase);
    }

    public void cambiarHorario(Long claseId, LocalDateTime nuevoHorario) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new ClaseNoEncontrada(claseId));
        
        clase.setHorario(nuevoHorario);
        Clase claseActualizada = claseRepository.save(clase);
        
        notificationService.enviarNotificacionCambioHorario(claseActualizada);
    }
}