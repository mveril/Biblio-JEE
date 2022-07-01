<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout auteur</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />
	<div class="form">
		<form method="post" action="<c:url value="/ajouter-auteur" />">
		
			<fieldset>
				<legend>Créer un auteur</legend>
				
                <c:import url="/WEB-INF/AuteurFormFragment.jsp"></c:import>
			
			</fieldset>
		
			<input type="submit" value="Valider" />
			<input type="reset" value="Remettre à zéro" />
		</form>
	</div>
</body>
</html>