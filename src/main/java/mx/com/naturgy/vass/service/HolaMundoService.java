package mx.com.naturgy.vass.service;

import org.springframework.stereotype.Service;

import mx.com.naturgy.vass.bean.HolaMundo;

@Service
public class HolaMundoService {

	public HolaMundo devuelveDatos() {
		HolaMundo holaMundo=new HolaMundo("Alan","Lopez", "Hola mundo");
	 return holaMundo;
	}
	
}
