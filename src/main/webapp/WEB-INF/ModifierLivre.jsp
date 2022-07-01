<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier le livre <c:out value="${livre.titre }"/></title>
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />
	<div class="form">
		<form method="post" action="<c:url value="/modifier-livre"><c:param name="id" value="${livre.id }"/></c:url>">
		
			<fieldset>
				<legend>Modifier <c:out value="${ livre.titre }"></c:out></legend>
                <c:import url="/WEB-INF/LivreFormFragment.jsp"></c:import>
			</fieldset>
		
			<input type="submit" value="Valider" />
			<input type="reset" value="Remettre à zéro" />
		</form>
	</div>
</body>
</html>