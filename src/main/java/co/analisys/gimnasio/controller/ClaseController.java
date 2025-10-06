package co.analisys.gimnasio.controller;

import co.analisys.gimnasio.model.Clase;
import co.analisys.gimnasio.service.ClaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/gimnasio")
public class ClaseController {
    @Autowired
    private ClaseService gimnasioService;

    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Clase programada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Programar clase de gimnasio", description = "Permite programar una nueva clase en el gimnasio")
    @PostMapping("/clases")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Clase programarClase(@RequestBody Clase clase) {
        return gimnasioService.programarClase(clase);
    }


    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de clases obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron clases"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Obtener todas las clases de gimnasio", description = "Permite obtener la lista de todas las clases programadas en el gimnasio")
    @GetMapping("/clases")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER', 'ROLE_MEMBER')")
    public List<Clase> obtenerTodasClases() {
        return gimnasioService.obtenerTodasClases();
    }

    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de clases del miembro obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron clases para el miembro"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Obtener clases de un miembro", description = "Permite obtener todas las clases en las que está inscrito un miembro específico")
    @GetMapping("/clases/miembro/{miembroId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER', 'ROLE_MEMBER')")
    public List<Clase> obtenerClasesPorMiembro(@PathVariable Long miembroId) {
        return gimnasioService.obtenerClasesPorMiembro(miembroId);
    }


    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Entrenador reservado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Clase o entrenador no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Reservar entrenador para clase", description = "Permite reservar un entrenador para una clase específica")
    @PostMapping("/clases/{claseId}/reservar-entrenador/{entrenadorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void reservarEntrenador(@PathVariable Long claseId, @PathVariable Long entrenadorId) {
        gimnasioService.reservarEntrenador(entrenadorId, claseId);
    }


    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reserva de entrenador cancelada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Clase o entrenador no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Cancelar reserva de entrenador para clase", description = "Permite cancelar la reserva de un entrenador para una clase específica")
    @PostMapping("/clases/{claseId}/cancelar-entrenador/{entrenadorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void cancelarReservaEntrenador(@PathVariable Long claseId, @PathVariable Long entrenadorId) {
        gimnasioService.cancelarEntrenador(entrenadorId, claseId);
    }


    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Equipo reservado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Clase o equipo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Reservar equipo para clase", description = "Permite reservar cierta cantidad de un equipo para una clase específica")
    @PostMapping("/clases/{claseId}/reservar-equipo/{equipoId}/cantidad/{cantidad}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public void reservarEquipo(@PathVariable Long claseId, @PathVariable Long equipoId, @PathVariable Long cantidad) {
        gimnasioService.reservarEquipo(equipoId, cantidad, claseId);
    }


    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Equipo devuelto exitosamente"),
        @ApiResponse(responseCode = "404", description = "Clase o equipo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Devolver equipo de clase", description = "Permite devolver cierta cantidad de un equipo para una clase específica")
    @PostMapping("/clases/{claseId}/devolver-equipo/{equipoId}/cantidad/{cantidad}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public void devolverEquipo(@PathVariable Long claseId, @PathVariable Long equipoId, @PathVariable Long cantidad) {
        gimnasioService.devolverEquipo(equipoId, cantidad, claseId);
    }


    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Miembro agregado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Clase o miembro no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Agregar miembro a clase", description = "Permite agregar un miembro a una clase específica")
    @PostMapping("/clases/{claseId}/agregar-miembro/{miembroId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public void añadirMiembro(@PathVariable Long claseId, @PathVariable Long miembroId) {
        gimnasioService.añadirMiembro(miembroId, claseId);
    }


    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Miembro eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Clase o miembro no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Eliminar miembro de clase", description = "Permite eliminar un miembro de una clase específica")
    @DeleteMapping("/clases/{claseId}/eliminar-miembro/{miembroId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public void eliminarMiembro(@PathVariable Long claseId, @PathVariable Long miembroId) {
        gimnasioService.eliminarMiembro(miembroId, claseId);
    }

    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Horario de clase cambiado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado - Solo administradores y entrenadores")
    })
    @Operation(
        summary = "Cambiar horario de clase", 
        description = "Permite cambiar el horario de una clase específica. Solo administradores y entrenadores pueden realizar esta acción. Se envía una notificación automática del cambio."
    )
    @PutMapping("/clases/{claseId}/cambiar-horario")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public void cambiarHorario(
        @Parameter(description = "ID de la clase a modificar", required = true)
        @PathVariable Long claseId, 
        @Parameter(description = "Nueva fecha y hora para la clase en formato ISO (ej: 2024-12-25T10:30:00)", required = true)
        @RequestParam String nuevaFechaHora) {
        
        LocalDateTime nuevoHorario = LocalDateTime.parse(nuevaFechaHora);
        gimnasioService.cambiarHorario(claseId, nuevoHorario);
    }
}