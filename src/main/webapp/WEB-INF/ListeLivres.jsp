<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Liste de livre</title>
</head>
<body>
  <c:import url="/WEB-INF/menu.jsp" />
  <c:choose>
    <c:when test="${ empty livres }">
       <p>Aucun livre trouvé…</p>
    </c:when>
    <c:otherwise>
       <table>
         <thead>
           <th>Auteur</th>
           <th>Titre</th>
           <th>nbPages</th>
           <th>catégorie</th>
         </thead>
         <tbody>
            <c:forEach items="${ livres }" var="livre">
                <tr>
                    <td><c:out value="${livre.auteur.prenom }"/> <c:out value="${livre.auteur.nom }"/></td>
                    <td><c:out value="${livre.titre }"/></td>
                    <td><c:out value="${livre.nbPages }"/></td>
                    <td><c:out value="${livre.categorie }"/></td>
                    <td>
                       <a href="<c:url value="/liste-livres">
                          <c:param name="id" value="${livre.id }"/>
                        </c:url>">Voir les détails du livre
                       </a>
                    </td>
                </tr>
            </c:forEach>

         </tbody>
       </table>
    </c:otherwise>
  </c:choose>
</body>
</html>