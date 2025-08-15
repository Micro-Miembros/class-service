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
}