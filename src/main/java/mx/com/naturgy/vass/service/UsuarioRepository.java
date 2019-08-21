package mx.com.naturgy.vass.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.naturgy.vass.bean.Usuario;

@Repository
//Dentro de JpaRepository, se indica la clase bean y el tipo de dato
//de la llave primaria
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

}
