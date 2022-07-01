<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
				<label for="prenomAuteur">Prénom</label>
				<input type="text" value="<c:out value="${auteur.prenom}"/>" id="prenomAuteur" name="prenom" size="20" />
				<br/>
				
				<label for="nomAuteur">Nom</label>
				<input type="text" value=" <c:out value="${auteur.nom}"/>" id="nomAuteur" name="nom" size="20" />
				<br/>
				
				<label for="telephoneAuteur">Téléphone</label>
				<input type="text" value="<c:out value="${auteur.telephone}"/>" id="telephoneAuteur" name="telephone" size="10" />
				<br/>
				
				<label for="emailAuteur">Email</label>
				<input type="email" value="<c:out value="${auteur.email}"/>" id="emailAuteur" name="email" size="60" />
				<br/>