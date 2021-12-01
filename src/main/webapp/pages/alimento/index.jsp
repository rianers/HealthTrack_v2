<%@ page import="java.util.*" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%pageContext.setAttribute("pageTitle", "Minha Alimentação");%>
<%@ include file="../../components/head.jsp"%>
</head>
<body>
	<%pageContext.setAttribute("servletPath", "alimentos");%>
	<%@ include file="../../components/header.jsp"%>
	<div
		class="container-fluid vh-100 w-100 p-5 text-white bgc-purble-dark"
		style="z-index: 1; width: 100%; padding-top: 130px !important;">
		<div class="d-flex justify-content-between align-items-center">
			<h2>Alimentação</h2>
			<a href="<%=request.getContextPath()%>/alimentos/nova"
				class="btn btn-success" style="background-color: var(- -primary);">
				<i class="bi bi-plus-circle"></i> Adicionar alimento
			</a>
		</div>

		<hr style="background-color: #C6C2DE;" />

		<span style="color: #6E6893; font-weight: bold; font-size: 14px;">REGISTRO
			DE SEUS ALIMENTOS</span>

		<table class="table text-white">
			<thead>
				<tr>
					<th scope="col">Alimento</th>
					<th scope="col">Calorias</th>
					<th scope="col">Período</th>
					<th scope="col">Descrição</th>
					<th scope="col">Data</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="food" items="${foods}">
					<tr>
						<td>${food.getFoodName()}</td>
						<td>${food.getQuantityCalories()}</td>
						<td>${food.getHour()}</td>
						<td>${food.getFoodDescription()}</td>
						<td>${food.getDate()}</td>
						<td><a
							href="<%=request.getContextPath()%>/alimentos/alimento?id=${food.getFoodId()}">
								<i class="bi bi-pencil"></i>
						</a> <a href="<%=request.getContextPath()%>/alimentos/excluir?id=${food.getFoodId()}"> <i
								class="bi bi-trash"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>