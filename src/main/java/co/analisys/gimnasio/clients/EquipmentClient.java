package co.analisys.gimnasio.clients;

import org.springframework.cloud.openfeign.FeignClient; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 

@FeignClient(name = "equipment-service", url = "http://localhost:8082") 
public interface EquipmentClient { 
    @PostMapping("/api/gimnasio/equipos/{equipoId}/reservar/{cantidad}") 
    Boolean reservarEquipo(@PathVariable("equipoId") Long id, @PathVariable("cantidad") Long cantidad);

    @PostMapping("/api/gimnasio/equipos/{equipoId}/devolver/{cantidad}")
    Boolean devolverEquipo(@PathVariable("equipoId") Long id, @PathVariable("cantidad") Long cantidad);
}