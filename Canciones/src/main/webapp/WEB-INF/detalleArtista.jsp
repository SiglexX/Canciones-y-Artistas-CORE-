<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1> Detalles sobre el Artista </h1>
		
		<h2> Nombre: ${artista.nombre} ${artista.apellido} </h2>
		
		<p> ID del Artista: ${artista.id} </p>
		<p> Biografía: ${artista.biografia} </p>
		
		<h3> Sus canciones: </h3>
		<ul>
			<c:forEach var="cancion" items="${artista.canciones}">
				<li> ${cancion.titulo} </li>
			</c:forEach>
		</ul>
		
		<a href="/artistas"> Volver a la lista de artistas </a>
	</body>
</html>