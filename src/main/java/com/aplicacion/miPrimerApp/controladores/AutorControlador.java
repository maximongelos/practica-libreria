package com.aplicacion.miPrimerApp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aplicacion.miPrimerApp.entidades.Autor;
import com.aplicacion.miPrimerApp.servicios.AutorServicio;

@Controller
@RequestMapping("/autores")
public class AutorControlador {

	@Autowired
	private AutorServicio autorServicio;

	@GetMapping()
	public String listarAutores(ModelMap modeloDeAutores) {
		List<Autor> listaAutores = autorServicio.listarTodos();
		modeloDeAutores.addAttribute("autores", listaAutores);
		return "lista-autor";
	}

	@GetMapping("/registro")
	public String crearAutor() {
		return "registro-autor";
	}

	@PostMapping("/registro")
	public String registrarAutor(@RequestParam String nombre) {
		try {
			autorServicio.guardar(nombre);
			return "redirect:/autores";
		} catch (Exception e) {
			return "redirect:/autores";
		}
	}

	@GetMapping("/alta/{id}")
	public String altaAutor(@PathVariable("id") String id) {
		try {
			autorServicio.alta(id);
			return "redirect:/autores";
		} catch (Exception e) {
			return "redirect:/autores";
		}	
	}

	@GetMapping("/baja/{id}")
	public String bajaAutor(@PathVariable("id") String id) {
		try {
			autorServicio.baja(id);
			return "redirect:/autores";
		} catch (Exception e) {
			return "redirect:/autores";
		}
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarAutor(@PathVariable("id") String id) {
		try {
			autorServicio.eliminar(id);
			return "redirect:/autores";
		} catch (Exception e) {
			return "redirect:/autores";
		}
	}

	@GetMapping("/editar/{id}")
	public String editarAutor(@PathVariable("id") String id) {
		try {
			autorServicio.editar(id);
			return "redirect:/autores";
		} catch (Exception e) {
			return "redirect:/autores";
		}
	}
}
