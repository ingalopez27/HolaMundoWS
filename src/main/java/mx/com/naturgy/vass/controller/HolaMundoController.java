package mx.com.naturgy.vass.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import mx.com.naturgy.vass.bean.HolaMundo;
import mx.com.naturgy.vass.bean.Usuario;
import mx.com.naturgy.vass.exception.CustomerResponseEntityExceptionHandler;
import mx.com.naturgy.vass.exception.DatoNotFoundException;
import mx.com.naturgy.vass.service.HolaMundoService;
import mx.com.naturgy.vass.service.UsuarioDaoService;
import mx.com.naturgy.vass.service.UsuarioRepository;

@RestController
public class HolaMundoController {
	@Autowired
	HolaMundoService hola;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UsuarioDaoService usuarioDao;
	@Autowired
	private UsuarioRepository usuarioRepository;


	@GetMapping("/v2/jpa-usuario/{id}")
	public List<Usuario> recuperaUsuarios(@PathVariable("id") int id){
		return usuarioRepository.findAll();
	}	
	
	@PostMapping("/v2/jpa/usuario")
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
	return usuarioRepository.save(usuario);	
	}
	
	
	
	@GetMapping("/hola-mundo-internacional")
	public String holaMundoInter(@RequestHeader(name = "Accept-Language" , required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	@GetMapping("/v2/jpa-usuario")
	public List<Usuario> devuelveUsuario(){
		return usuarioDao.listaUser();
	}
	@RequestMapping(method = RequestMethod.POST, path = "/holamundo/{id}")
	public HolaMundo dameDatos(@PathVariable int id, @Valid @RequestBody HolaMundo holaMundo) {

		return hola.devuelveDatos();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hola")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hola/{id}")
	@ResponseBody
	public ResponseEntity<String> hello2(@PathVariable("id") int id) {
		return new ResponseEntity<>("La solicitud fue erronea", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/hola/{id}")

	public ResponseEntity<String> createCustomer(UriComponentsBuilder builder, @PathVariable("id") int id) {
		return null;

	}

	@PostMapping("/v1/hola")
	public ResponseEntity postController(@RequestBody HolaMundo holaMundo) {
		System.out.println(holaMundo.getNombre());
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/v1")
	@ResponseBody
	public HolaMundo postResponseController(@RequestBody HolaMundo holaMundo) {
		return new HolaMundo("Alan", "Lopez", "Hola a todos");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/v1/{id}")
	public ResponseEntity conexion(@PathVariable("id") int id) throws IOException {
		URL url = new URL("https://google.com.mx");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		System.out.println("La conexion se establecio de manera exitosa");
		con.setRequestMethod("GET");
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}

	@RequestMapping("/v1/test")
	public ResponseEntity<String> handleRequest(RequestEntity<String> requestEntity) {
		System.out.println("request body : " + requestEntity.getBody());
		HttpHeaders headers = requestEntity.getHeaders();
		System.out.println("request headers : " + headers);
		HttpMethod method = requestEntity.getMethod();
		System.out.println("request method : " + method);
		System.out.println("request url: " + requestEntity.getUrl());

		ResponseEntity<String> responseEntity = new ResponseEntity<>("my response body", HttpStatus.OK);
		return responseEntity;
	}

	// Insercion de un parametro
	@GetMapping("/v1/api/foos")
	@ResponseBody
	public String getFoos(@RequestParam String id) {

		return "ID: " + id;
	}

	// Insercion de 2 parametros
	@PostMapping("/api/foos")
	@ResponseBody
	public String addFoo(@RequestParam(name = "id") String fooId, @RequestParam String name) {
		return "ID: " + fooId + " Name: " + name;
	}

	// Listado de parametros
	@GetMapping("/api/foos")
	@ResponseBody
	public String getFoos(@RequestParam List<String> id) {
		return "IDs are " + id;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/v2/token")
	public Response authenticate(@HeaderParam("authorization") String token) {
		return Response.ok("token=" + token).build();
	}

	@GetMapping("/v2/{id}")
	public ResponseEntity<String> usingResponseEntityBuilderAndHttpHeaders(@PathVariable("id") int id,
			@RequestParam(name = "name") String name) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Baeldung-Example-Header", "Value-ResponseEntityBuilderWithHttpHeaders");
		responseHeaders.set("Content-Type", "application/json");

		return ResponseEntity.ok().headers(responseHeaders).body("Response with header using ResponseEntity");
	}

	// Creacion de una URI
	@PostMapping("/v2/{id}")
	public ResponseEntity<Object> crearMundo(@PathVariable("id") int id, @RequestParam("dato") String dato) {
		HolaMundo mundo = hola.devuelveDatos();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/token")
				.buildAndExpand(mundo.getApellido()).toUri();
		System.out.println("Se creo la uri " + location);
		return ResponseEntity.created(location).build();
	}

	// Creacion de una URI
	@PostMapping("/v2/test/{id}")
	public HolaMundo crearExcepcion(@PathVariable("id") int id, @RequestParam("dato") String dato) {
		HolaMundo mundo = hola.devuelveDatos();
		if (dato == null) {
			throw new DatoNotFoundException("dato-" + dato);

		}
		return mundo;
	}
}