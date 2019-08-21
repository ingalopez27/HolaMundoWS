package mx.com.naturgy.vass.bean;	

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Se muestran los detalles del usuario")
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Size(min = 2, message = "nombre del usuario")
	@ApiModelProperty(notes = "Aqui va el nombre del usuario")
	private String nombre;
	private Date date;

	public Usuario(Integer id, @Size(min = 2, message = "nombre del usuario") String nombre, Date date) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
