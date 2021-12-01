<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang="pt-br">
<head>
<%
pageContext.setAttribute("pageTitle", "Adicionar Alimento");
%>
<%@ include file="../../components/head.jsp"%>
</head>
<title>Cadastrar Atividades</title>
</head>
<body style="background-color: #141A37;">
	<%
	pageContext.setAttribute("servletPath", "alimentos");
	%>
	<%@ include file="../../components/header.jsp"%>
	<main class=" mx-auto w-100 position-relative"
		style="z-index: 1; max-width: 1284px; padding-top: 10%;">
		<h2 style="color: #FFFFFF; font-weight: bold; font-size: 28px;">ADICIONAR
			ALIMENTO</h2>

		<hr style="background-color: #C6C2DE;" />

		<div class="mt-3 form-container px-3 py-3 text-white"
			style="max-height: 700px;">
			<form action="<%=request.getContextPath()%>/alimentos/criar"
				method="post">
				<div class="row mb-3">
					<div class="col-12">
						<label class="mb-2">Alimento</label> <input type="text"
							class="form-control" name="foodName" id="foodName"
							placeholder="Nome do alimento" required>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-6">
						<label class="mb-2">Descrição</label>
						<textarea rows="5" class="form-control" name="description"
							id="description" placeholder="Descreva o alimento"></textarea>
					</div>
					<div class="col-6">
						<div class="row">
							<div class="col-6">
								<label for="time" class="mb-2">Período</label> <select
									name="time" class="form-control">
									<option ${time == null ? 'selected' : ''} value="">Selecione</option>
									<option ${time == 'Matutino' ? 'selected' : ''} value="Matutino">Matutino</option>
									<option ${time == 'Vespertino' ? 'selected' : ''} value="Vespertino">Vespertino</option>
									<option ${time == 'Noturno' ? 'selected' : ''} value="Noturno">Noturno</option>
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
					<a href="<%=request.getContextPath()%>/alimentos"
						class="btn btn-md btn-outline-secondary">Cancelar</a>
					<button type="submit" class="btn btn-md btn-success">Criar</button>
				</div>
			</form>
		</div>
	</main>
</body>
</html>