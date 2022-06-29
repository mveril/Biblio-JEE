<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav>
    <ul>
      	<li><a href="<c:url value="/" />">Accueil</a></li>
      	<li><a href="<c:url value="/liste-auteurs" />">Liste des auteurs</a></li>
      	<li><a href="<c:url value="/liste-livres" />">Liste des livres</a></li>
    </ul>
</nav>