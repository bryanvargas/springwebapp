<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src='<c:url value="/res/js/jquery-1.12.0.min.js" />'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#target").click(function() {
			confirm("The paragraph was clicked.");
		});
	});
</script>
</head>
<body>
	<h1>Direccion.jsp</h1>

	<%-- 	"/direccion/save": esta es la peticion --%>
	<sf:form action="${pageContext.request.contextPath}/direccion/save"
		method="POST" commandName="direccion">
		<table>
			<tr>
				<td>Calle</td>
				<td><sf:input path="calle" type="text" /></td>
			</tr>
			<tr>
				<td>C.P.</td>
				<td><sf:input path="cp" type="text" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Guardar Cambios"></td>
			</tr>
		</table>
	</sf:form>

	<br /> Esto es el el Resultado:
	<c:out value="${resultado}"></c:out>
	<br />
	<br />

	<c:forEach items="${direcciones}" var="direccion">
		<c:out value="${direccion}"></c:out>
		<a id="target" href='<c:url  value="/direccion/${direccion.idDir}/delete" />'>eliminar</a>
		<br />
	</c:forEach>

</body>
</html>
