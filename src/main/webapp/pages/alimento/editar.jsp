<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang="pt-br">
<head>
<%
pageContext.setAttribute("pageTitle", "Editar Alimento");
%>
<%@ include file="../../components/head.jsp"%>
</head>
<title>Editar Alimento</title>
</head>
<body style="background-color: #141A37;">
	<%
	pageContext.setAttribute("servletPath", "alimentos");
	%>
	<%@ include file="../../components/header.jsp"%>
	<main class=" mx-auto w-100 position-relative"
		style="z-index: 1; max-width: 1284px; padding-top: 10%;">
		<h2 style="color: #FFFFFF; font-weight: bold; font-size: 28px;">EDITAR
			ALIMENTO</h2>

		<hr style="background-color: #C6C2DE;" />

		<div class="mt-3 form-container px-3 py-3 text-white"
			style="max-height: 700px;">
			<form
				action="<%=request.getContextPath()%>/alimentos/editar?id=${food.getFoodId()}"
				method="post">
				<div class="row mb-3">
					<div class="col-12">
						<label class="mb-2">Alimento</label> <input type="text"
							class="form-control" name="foodName" id="foodName"
							placeholder="Nome do alimento" value="${food.getFoodName()}"
							required>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-6">
						<label class="mb-2">Descrição</label>
						<textarea rows="5" class="form-control" name="description"
							id="description" placeholder="Descreva o alimento">${food.getFoodDescription()}</textarea>
					</div>
					<div class="col-6">
						<div class="row">
							<div class="col-6">
								<label for="category" class="mb-2">Período</label> <select
									name="category" class="form-control">
									<option ${food.getHour() == null ? 'selected' : ''} value="">Selecione</option>
									<option ${food.getHour() == 'Matutino' ? 'selected' : ''}
										value="Matutino">Matutino</option>
									<option ${food.getHour() == 'Vespertino' ? 'selected' : ''}
										value="Vespertino">Vespertino</option>
									<option ${food.getHour() == 'Noturno' ? 'selected' : ''}
										value="Noturno">Noturno</option>
								</select>
							</div>
							<div class="col-6">
								<label class="mb-2">Calorias</label> <input type="number"
									class="form-control" name="calories" id="calories"
									value="${food.getQuantityCalories()}"
									placeholder="Digite a quantidade de calorias">
							</div>
						</div>
						<div class="col-12 mt-4">
							<label class="mb-2">Data</label> <input type="date"
								class="form-control" name="date" id="date"
								value="${food.getDate()}" required>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-between align-items-center mt-4">
					<a href="<%=request.getContextPath()%>/alimentos"
						class="btn btn-md btn-outline-secondary">Cancelar</a>
					<button type="submit" class="btn btn-md btn-success">Editar</button>
				</div>
			</form>
		</div>

	</main>
</body>
</html>