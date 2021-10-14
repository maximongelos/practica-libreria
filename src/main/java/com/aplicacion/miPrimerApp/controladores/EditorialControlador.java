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

import com.aplicacion.miPrimerApp.entidades.Editorial;
import com.aplicacion.miPrimerApp.servicios.EditorialServicio;

@Controller
@RequestMapping("/editoriales")
public class EditorialControlador {

	@Autowired
	private EditorialServicio editorialServicio;

	@GetMapping()
	public String listarEditoriales(ModelMap modelo) {
		List<Editorial> listaEditoriales = editorialServicio.listarTodos();
		modelo.addAttribute("editoriales", listaEditoriales);
		return "Editorial/lista-editorial";
	}

	@GetMapping("/registro")
	public String crearEditorial() {
		return "Editorial/registro-editorial";
	}

	@PostMapping("/registro")
	public String registrarEditorial(ModelMap modelo, @RequestParam String nombre) {
		try {
			editorialServicio.guardar(nombre);
			modelo.put("exito", "Se registro con exito");
			return "Editorial/registro-editorial";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "Editorial/registro-editorial";
		}
	}

	@GetMapping("/alta/{id}")
	public String altaEditorial(@PathVariable("id") String id) {
		try {
			editorialServicio.alta(id);
			return "redirect:/editoriales";
		} catch (Exception e) {
			return "redirect:/editoriales";
		}	
	}

	@GetMapping("/baja/{id}")
	public String bajaEditorial(@PathVariable("id") String id) {
		try {
			editorialServicio.baja(id);
			return "redirect:/editoriales";
		} catch (Exception e) {
			return "redirect:/editoriales";
		}
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarEditorial(@PathVariable("id") String id) {
		try {
			editorialServicio.eliminar(id);
			return "redirect:/editoriales";
		} catch (Exception e) {
			return "redirect:/editoriales";
		}
	}

	@GetMapping("/editar/{id}")
	public String editarEditorial(ModelMap modelo, @PathVariable("id") String id)  {
		try {
			Editorial editorial = editorialServicio.obtenerPorId(id);
			modelo.addAttribute("editorial", editorial);
			return "Editorial/editar-editorial";
		}catch(Exception e ) {
			return "redirect:/editoriales";
		}
	}
	
	@PostMapping("/editar/{id}")
	public String editarEditorial(@PathVariable("id") String id, @RequestParam String nombre) {
		try {
			editorialServicio.editar(id, nombre);
			return "redirect:/editoriales";
		} catch (Exception e) {
			return "redirect:/editoriales";
		}
	}
}
