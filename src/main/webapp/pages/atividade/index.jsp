<%@ page import="java.util.*" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%pageContext.setAttribute("pageTitle", "Minhas Atividades");%>
<%@ include file="../../components/head.jsp"%>
</head>
<body>
	<%pageContext.setAttribute("servletPath", "atividades");%>
	<%@ include file="../../components/header.jsp"%>
	<div
		class="container-fluid vh-100 w-100 p-5 text-white bgc-purble-dark"
		style="z-index: 1; width: 100%; padding-top: 130px !important;">
		<div class="d-flex justify-content-between align-items-center">
			<h2>Atividades</h2>
			<a href="<%=request.getContextPath()%>/atividades/nova"
				class="btn btn-success" style="background-color: var(- -primary);">
				<i class="bi bi-plus-circle"></i> Adicionar atividade física
			</a>
		</div>

		<hr style="background-color: #C6C2DE;" />

		<span style="color: #6E6893; font-weight: bold; font-size: 14px;">REGISTRO
			DAS SUAS ATIVIDADES FÍSICAS</span>

		<table class="table text-white">
			<thead>
				<tr>
					<th scope="col">Atividade</th>
					<th scope="col">Calorias</th>
					<th scope="col">Início</th>
					<th scope="col">Término</th>
					<th scope="col">Categoria</th>
					<th scope="col">Descrição</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="physicalActivity" items="${physicalActivities}">
					<tr>
						<td>${physicalActivity.getActivityType()}</td>
						<td>${physicalActivity.getCalories()}</td>
						<td>${physicalActivity.getStartTime()}</td>
						<td>${physicalActivity.getEndTime()}</td>
						<td>${physicalActivity.getCategoryName()}</td>
						<td>${physicalActivity.getDescription()}</td>
						<td><a
							href="<%=request.getContextPath()%>/atividades/atividade?id=${physicalActivity.getUserActivityId()}">
								<i class="bi bi-pencil"></i>
						</a> <a href="<%=request.getContextPath()%>/atividades/excluir?id=${physicalActivity.getUserActivityId()}"> <i
								class="bi bi-trash"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>