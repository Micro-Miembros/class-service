package co.analisys.gimnasio.clients;

import org.springframework.cloud.openfeign.FeignClient; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
@FeignClient(name = "member-service", url = "http://member-service:8081") 
public interface MemberClient { 
    @GetMapping("/api/gimnasio/miembros/{miembroId}/activa") 
    Boolean isMiembroActivo(@PathVariable("miembroId") Long id); 
}