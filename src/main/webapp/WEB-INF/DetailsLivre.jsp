<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail du livre : <c:out value="${ livre.titre }"></c:out> </title>
</head>
<body>
  <table>
    <tbody>
      <tr>
        <th>Auteur</th>
        <td><c:out value="${ livre.auteur.prenom } ${ livre.auteur.nom }"/></td>
      </tr>
      <tr>
         <th>Nombre de pages</th>
         <td><c:out value="${ livre.nbPages }"/></td>
      </tr>
      <tr>
         <th>Catégorie</th>
         <td><c:out value="${ livre.categorie }"/></td>
      </tr>
    </tbody>
  </table>
  
  <a href="<c:url value="/liste-livres" />">Voir toute la liste des livres</a>
</body>
</html>