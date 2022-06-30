<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  	<c:import url="/WEB-INF/menu.jsp" />

	<div class="form">
		<form method="post" action="<c:url value="/ajouter-livre" />">
		
			<fieldset>
				<legend>Créer un livre</legend>
				
				<label for="titreLivre">Titre</label>
				<input type="text" id="titreLivre" name="titre" size="20" />
				<br/>
				
				<label for="nbPages">Nombre de pages</label>
				<input type="number" id="nbPagesLivre" min="1" name="nbPages" size="20" />
				<br/>
				
				<label for="categorieLivre">Categorie</label>
				<input type="text" id="categorieLivre" name="categorie" size="10" />
				<br/>
				
				<label for="categorieLivre">Auteur</label>
				<select id="auteurLIvre" name="auteurId">
				  <option value="" disabled selected>-- Choisir --</option>
				<c:forEach items="${auteurs }" var="auteur">
				  <option value="${auteur.id }"><c:out value="${auteur.prenom } ${auteur.nom }"/></option>  
				</c:forEach>
				</select>
				<br/>
			
			</fieldset>
		
			<input type="submit" value="Valider" />
			<input type="reset" value="Remettre à zéro" />
		</form>
	</div>
</body>
</html>