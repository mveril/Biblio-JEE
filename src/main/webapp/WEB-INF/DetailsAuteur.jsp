<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail de l'auteur <c:out value="${auteur.prenom } ${auteur.nom }"/></title>
</head>
<body>
  <h1>Détail de l'auteur <c:out value="${auteur.prenom } ${auteur.nom }"/></h1>
  <table>
    <tbody>
      <tr>
        <th>📞</th>
        <td><c:out value="${ auteur.telephone }"/></td>
      </tr>
      <tr>
         <th>✉️</th>
         <td><c:out value="${ auteur.email }"/></td>
      </tr>
    </tbody>
    
  </table>
  <a href="<c:url value="/liste-auteurs" />">Voir toute la liste des auteurs</a>
</body>
</html>