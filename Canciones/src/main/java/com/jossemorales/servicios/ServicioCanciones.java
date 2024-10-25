package com.jossemorales.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jossemorales.modelos.Cancion;
import com.jossemorales.repositorios.RepositorioCanciones;

@Service
public class ServicioCanciones {
	
	@Autowired
	private final RepositorioCanciones repositorioCanciones;
	
	public ServicioCanciones(RepositorioCanciones repositorioCanciones) {
		this.repositorioCanciones = repositorioCanciones;
	}
	
	public List<Cancion> obtenerTodasLasCanciones(){
		return repositorioCanciones.findAll();
	}
	
	public List<Cancion> obtenerCancionesPorArtista(Long idArtista) {
	    return this.repositorioCanciones.findByArtistaId(idArtista);
	}
	
	public Cancion obtenerCancionPorId(Long id) {
		Optional<Cancion> cancionOpcional = repositorioCanciones.findById(id);
		return cancionOpcional.orElse(null);
	}
	
	public Cancion agregarCancion(Cancion nuevaCancion) {
		return this.repositorioCanciones.save(nuevaCancion);
	}
	
	public Cancion actualizarCancion(Cancion cancion) {
		return this.repositorioCanciones.save(cancion);
	}
	
	public void eliminarCancion(Long id) {
		this.repositorioCanciones.deleteById(id);
	}
}
