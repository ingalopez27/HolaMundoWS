package mx.com.naturgy.vass.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.naturgy.vass.bean.Usuario;

@Service
public class UsuarioDaoService {

	public static List<Usuario> usuario = new ArrayList<>();

	public List<Usuario> listaUser() {
		usuario.add(new Usuario(2839, "Alan", new Date()));
		usuario.add(new Usuario(5783, "Juan", new Date()));
		usuario.add(new Usuario(1111, "Francisco", new Date()));
		return usuario;
	}

}
