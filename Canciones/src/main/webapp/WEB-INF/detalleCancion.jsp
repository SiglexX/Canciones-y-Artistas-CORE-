<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Detalles de la Canción</title>
	</head>
	<body>
		<div>
			<h1>Detalles de la Canción</h1>
			
		    <h2>Título: ${cancion.titulo}</h2>
		    <p>Autor: ${cancion.artista.nombre}</p>
		    <p>Álbum: ${cancion.album}</p>
		    <p>Género: ${cancion.genero}</p>
		    <p>Idioma: ${cancion.idioma}</p>
		
		    <a href="/canciones">Volver a lista de canciones</a>
		    
		    <c:if test="${not empty mensaje}">
		    	<p>${mensaje}</p>
			</c:if>
		</div>
		<div>
			<form action="/canciones/formulario/editar/${cancion.id}">
				<button>
					Editar Cancion
				</button>
			</form>
			<form action="/canciones/eliminar/${cancion.id}" method="POST">
			<input type="hidden" name="_method" value="DELETE"/>
				<button>
					Eliminar Cancion
				</button>
			</form>
		</div>
	</body>
</html>