package com.jossemorales.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jossemorales.modelos.Artista;
import com.jossemorales.modelos.Cancion;
import com.jossemorales.servicios.ServicioArtistas;
import com.jossemorales.servicios.ServicioCanciones;

import jakarta.validation.Valid;

@Controller
public class ControladorArtistas {
	
	@Autowired
	private final ServicioArtistas servicioArtistas;
	private final ServicioCanciones servicioCanciones;
	
	public ControladorArtistas(ServicioArtistas servicioArtista,
						       ServicioCanciones servicioCanciones) {
		this.servicioArtistas = servicioArtista;
		this.servicioCanciones = servicioCanciones;
	}
	
	@GetMapping("/artistas")
	public String desplegarArtistas(Model modelo) {
		List<Artista> artista = this.servicioArtistas.obtenerTodosLosArtistas();
		 modelo.addAttribute("artistas", artista);
		return "artistas.jsp";
	}
	
	@GetMapping("/artistas/detalle/{idArtista}")
	public String desplegarDetalleArtista(@PathVariable("idArtista") Long idArtista, Model modelo) {
		Artista artista = this.servicioArtistas.obtenerArtistaPorId(idArtista);
		
			if(artista != null) {
				
				List<Cancion> canciones = this.servicioCanciones.obtenerCancionesPorArtista(idArtista);
				artista.setCanciones(canciones);
				
				modelo.addAttribute("artista", artista);
				return "detalleArtista.jsp";
			} else {
				modelo.addAttribute("mensaje", "Artista no encontrado");
				return "detalleArtista.jsp";
		}
	}
	
	@GetMapping("/artistas/formulario/agregar/{idArtista}")
	public String formularioAgregarArtista(@ModelAttribute("artista") Artista artista) {
		return "agregarArtista.jsp";
	}
	
	@PostMapping("/artistas/procesa/agregar")
	public String procesarAgregarArtista(@Valid @ModelAttribute Artista artista, BindingResult validaciones) {
		
		if (validaciones.hasErrors()) {
			return "agregarArtista.jsp";
		}
		
		this.servicioArtistas.agregarArtista(artista);
		return "redirect:/artistas";
	}
}
