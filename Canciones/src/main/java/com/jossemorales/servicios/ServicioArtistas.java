package com.jossemorales.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jossemorales.modelos.Artista;
import com.jossemorales.repositorios.RepositorioArtistas;

@Service
public class ServicioArtistas {
	@Autowired
	private final RepositorioArtistas repositorioArtista;
	
	public ServicioArtistas(RepositorioArtistas repositorioArtista) {
		this.repositorioArtista = repositorioArtista;
	}
	
	public List<Artista> obtenerTodosLosArtistas() {
		return this.repositorioArtista.findAll();
	}
	
	public Artista obtenerArtistaPorId(Long id_artista) {
		return this.repositorioArtista.findById(id_artista).orElse(null);
	}
	
	public Artista agregarArtista(Artista nuevoArtista) {
		return this.repositorioArtista.save(nuevoArtista);
	}
	
}
