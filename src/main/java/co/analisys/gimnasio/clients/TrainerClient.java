package co.analisys.gimnasio.clients;

import org.springframework.cloud.openfeign.FeignClient; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
@FeignClient(name = "trainer-service", url = "http://trainer-service:8084") 
public interface TrainerClient { 
    @PostMapping("/api/gimnasio/entrenadores/{entrenadorId}/reservar") 
    Boolean reservarEntrenador(@PathVariable("entrenadorId") Long id); 

    @PostMapping("/api/gimnasio/entrenadores/{entrenadorId}/cancelar")
    Boolean cancelarEntrenador(@PathVariable("entrenadorId") Long id);
}