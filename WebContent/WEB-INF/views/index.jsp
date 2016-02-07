<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hola mundo</h1>
	<br>
	<a href='<c:url value="/about" />'>Acarca de</a> <b/>
	<a href='<c:url value="/admin" />'>Gestionar administradores</a>
	
<%-- 	Atributos del Modelo: <c:out value="${mensaje}" /> <br/>	 --%>
<%-- 	Atributos en Session 111<c:out value="${sessionScope.resultado}"/> --%>

	<br>
	<c:out value="${adminForm}"/> <br>
	Esto es el Resultado: <c:out value="${resultado}"/> <br>

</body>
</html>