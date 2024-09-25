package infrastructure.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.entities.UsuarioEntity;




@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

}
