package co.analisys.gimnasio.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import co.analisys.gimnasio.model.Clase;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
}
