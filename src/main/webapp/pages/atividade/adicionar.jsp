<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang="pt-br">
<head>
<%
pageContext.setAttribute("pageTitle", "Adicionar atividade");
%>
<%@ include file="../../components/head.jsp"%>
</head>
<title>Cadastrar Atividades</title>
</head>
<body style="background-color: #141A37;">
	<%
	pageContext.setAttribute("servletPath", "atividades");
	%>
	<%@ include file="../../components/header.jsp"%>
	<main class=" mx-auto w-100 position-relative"
		style="z-index: 1; max-width: 1284px; padding-top: 10%;">
		<h2 style="color: #FFFFFF; font-weight: bold; font-size: 28px;">CRIAR
			ATIVIDADE F�SICA</h2>

		<hr style="background-color: #C6C2DE;" />

		<div class="mt-3 form-container px-3 py-3 text-white"
			style="max-height: 700px;">
			<form action="<%=request.getContextPath()%>/atividades/criar"
				method="post">
				<div class="row mb-3">
					<div class="col-6">
						<label class="mb-2">Atividade</label> <input type="text"
							class="form-control" name="activity" id="activity"
							placeholder="Nome da atividade" required>
					</div>

					<div class="col-3">
						<label class="mb-2">Hor�rio do In�cio</label> <input type="time"
							class="form-control" name="startTime" id="startTime" required>
					</div>

					<div class="col-3">
						<label class="mb-2">Hor�rio do T�rmino</label> <input type="time"
							class="form-control" name="endTime" id="endTime" required>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-6">
						<label class="mb-2">Descri��o</label>
						<textarea rows="5" class="form-control" name="description"
							id="description" placeholder="Descreva a atividade"></textarea>
					</div>
					<div class="col-6">
						<div class="row">
							<div class="col-6">
								<label for="category" class="mb-2">Categoria</label> <select
									name="category" class="form-control">
									<option ${category == null ? 'selected' : ''} value="">Selecione</option>
									<option ${category == 'Aerobico' ? 'selected' : ''} value="1">Aer�bico</option>
									<option ${category == 'Anaerobico' ? 'selected' : ''} value="2">Anaer�bico</option>
								</select>
							</div>
							<div class="col-6">
								<label class="mb-2">Calorias</label> <input type="number"
									class="form-control" name="calories" id="calories"
									placeholder="Digite a quantidade de calorias">
							</div>
						</div>
						<div class="col-12 mt-4">
							<label class="mb-2">Data</label> <input type="date"
								class="form-control" name="date" id="date"
								placeholder="Selecione a data da atividade" required>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-between align-items-center mt-4">
					<a href="<%=request.getContextPath()%>/atividades"
						class="btn btn-md btn-outline-secondary">Cancelar</a>
					<button type="submit" class="btn btn-md btn-success">Criar</button>
				</div>
			</form>
		</div>

	</main>
</body>
</html>