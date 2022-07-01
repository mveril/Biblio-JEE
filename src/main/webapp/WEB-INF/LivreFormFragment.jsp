<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				<label for="titreLivre">Titre</label>
				<input type="text" id="titreLivre" value="<c:out value="${livre.titre}"/>" name="titre" size="20" />
				<br/>
				
				<label for="nbPages">Nombre de pages</label>
				<input type="number" id="nbPagesLivre"  value="<c:out value="${livre.nbPages}"/>" min="1" name="nbPages" size="20" />
				<br/>
				
				<label for="categorieLivre">Categorie</label>
				<input type="text" id="categorieLivre" value="<c:out value="${livre.categorie}"/>" name="categorie" size="10" />
				<br/>
				
				<label for="categorieLivre">Auteur</label>
				<select id="auteurLIvre" name="auteurId">
				  <option value="" disabled >-- Choisir --</option>
				<c:forEach items="${auteurs }" var="auteur">
				  <option  ${ auteur.id == livre.auteur.id ? "selected" : "" } value="${auteur.id}"><c:out value="${auteur.prenom } ${auteur.nom }"/></option>
				</c:forEach>
				</select>
				<br/>