package co.analisys.gimnasio.controller;

import co.analisys.gimnasio.model.Clase;
import co.analisys.gimnasio.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gimnasio")
public class ClaseController {
    @Autowired
    private ClaseService gimnasioService;
    
    @PostMapping("/clases")
    public Clase programarClase(@RequestBody Clase clase) {
        return gimnasioService.programarClase(clase);
    }

    @GetMapping("/clases")
    public List<Clase> obtenerTodasClases() {
        return gimnasioService.obtenerTodasClases();
    }

    @PostMapping("/clases/{claseId}/reservar-entrenador/{entrenadorId}")
    public void reservarEntrenador(@PathVariable Long claseId, @PathVariable Long entrenadorId) {
        gimnasioService.reservarEntrenador(entrenadorId, claseId);
    }

    @PostMapping("/clases/{claseId}/cancelar-entrenador/{entrenadorId}")
    public void cancelarReservaEntrenador(@PathVariable Long claseId, @PathVariable Long entrenadorId) {
        gimnasioService.cancelarEntrenador(entrenadorId, claseId);
    }

    @PostMapping("/clases/{claseId}/reservar-equipo/{equipoId}/cantidad/{cantidad}")
    public void reservarEquipo(@PathVariable Long claseId, @PathVariable Long equipoId, @PathVariable Long cantidad) {
        gimnasioService.reservarEquipo(equipoId, cantidad, claseId);
    }

    @PostMapping("/clases/{claseId}/devolver-equipo/{equipoId}/cantidad/{cantidad}")
    public void devolverEquipo(@PathVariable Long claseId, @PathVariable Long equipoId, @PathVariable Long cantidad) {
        gimnasioService.devolverEquipo(equipoId, cantidad, claseId);
    }
}