package com.jossemorales.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jossemorales.modelos.Artista;
import com.jossemorales.modelos.Cancion;
import com.jossemorales.servicios.ServicioArtistas;
import com.jossemorales.servicios.ServicioCanciones;

import jakarta.validation.Valid;

@Controller
public class ControladorCanciones {
	
	@Autowired
	private final ServicioCanciones servicioCanciones;
	private final ServicioArtistas servicioArtistas;
	
	public ControladorCanciones(ServicioCanciones servicioCanciones,
							    ServicioArtistas servicioArtistas) {
		this.servicioCanciones = servicioCanciones;
		this.servicioArtistas = servicioArtistas;
	}
	
	@GetMapping("/canciones")
	public String desplegarCanciones(Model modelo) {
		List<Cancion> canciones = this.servicioCanciones.obtenerTodasLasCanciones();
		modelo.addAttribute("canciones", canciones);
		return "canciones.jsp";
	}
	
	@GetMapping("/canciones/detalle/{idCancion}")
	public String desplegarDetalleCancion(@PathVariable Long idCancion, Model modelo) {
		Cancion cancion = servicioCanciones.obtenerCancionPorId(idCancion);
		
		if (cancion != null) {
			modelo.addAttribute("cancion", cancion);
			return "detalleCancion.jsp";
		} else {
			modelo.addAttribute("mensaje", "Canción no encontrada");
			return "detalleCancion.jsp";
		}
	}
	
	@GetMapping("/canciones/formulario/agregar/{idCancion}")
	public String formularioAgregarCancion(@ModelAttribute("cancion") Cancion cancion) {
		return "agregarCancion.jsp";
	}
	
	@GetMapping("/canciones/formulario/agregar")
	public String mostrarFormularioAgregarCancion(Model modelo) {
		List<Artista> artistas = this.servicioArtistas.obtenerTodosLosArtistas();
	    System.out.println("Número de artistas: " + artistas.size());
		modelo.addAttribute("idArtistas", artistas);
		modelo.addAttribute("cancion", new Cancion());
		return "agregarCancion.jsp";
	}
	
	@PostMapping("/canciones/procesa/agregar")
	public String procesarAgregarCancion(@Valid @ModelAttribute Cancion cancion, BindingResult validaciones) {
		
		if (validaciones.hasErrors()) {
			return "agregarCancion.jsp";
		}
		Long idArtista = cancion.getArtista().getId();
		Artista artista = this.servicioArtistas.obtenerArtistaPorId(idArtista);
		cancion.setArtista(artista);
		
		this.servicioCanciones.agregarCancion(cancion);
		return "redirect:/canciones";
	}
	
	@GetMapping("/canciones/formulario/editar/{idCancion}")
	public String formularioEditarCancion(@PathVariable Long idCancion, @ModelAttribute Cancion cancion, Model modelo) {
		Cancion editarCancion = this.servicioCanciones.obtenerCancionPorId(idCancion);
		modelo.addAttribute("cancion", editarCancion);
		
		return "editarCancion.jsp";
	}
	
	@PutMapping("/canciones/procesa/editar/{idCancion}")
	public String procesarEditarCancion(@Valid @ModelAttribute Cancion cancion, @PathVariable Long idCancion, BindingResult validaciones) {
		
		if(validaciones.hasErrors()) {
			return "editarCancion.jsp";
		}
		cancion.setId(idCancion);
		this.servicioCanciones.actualizarCancion(cancion);
		return "redirect:/canciones";
	}
	
	@DeleteMapping("/canciones/eliminar/{idCancion}")
	public String procesarEliminarCancion(@PathVariable Long idCancion) {
		this.servicioCanciones.eliminarCancion(idCancion);
		return "redirect:/canciones";
	}
}
