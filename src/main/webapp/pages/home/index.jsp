<%@ page import="java.util.*" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%
pageContext.setAttribute("pageTitle", "Home");
%>
<%@ include file="../../components/head.jsp"%>
</head>
<body>
	<%
	pageContext.setAttribute("servletPath", "home");
	%>
	<%@ include file="../../components/header.jsp"%>
	<div
		class="
        container-fluid
        vh-100
        w-100
        d-flex
        bgc-purble-dark
        justify-content-center
        align-items-center
        flex-column
      "
		style="z-index: 7">
		<div class="d-flex flex-row bd-highlight mb-4">

			<div class="card bgc-primary me-4"
				style="width: 22rem; height: 100%;">
				<div class="card-body flex-column d-flex justify-content-center">
					<h1 class="card-title">Bem-vindo(a),</h1>
					<h1 class="card-title">${userName}</h1>
				</div>
			</div>

			<div class="card bgc-purble me-4" style="width: 22rem; height: 100%;">
				<div class="card-body d-flex flex-row">
					<div class="flex-column d-flex justify-content-center me-3">
						<img src="<%=request.getContextPath()%>/assets/activity.svg"
							style="width: 10rem;" />
					</div>
					<div class="flex-column">
						<h4 class="card-title mb-5">Atividades</h4>
						<h6 class="card-title">Última Atividade: ${lastActivity}</h6>
					</div>
				</div>
			</div>


			<div class="card bgc-purble" style="width: 14rem; height: 100%;">
				<div class="card-body flex-column ">
					<div class="flex-row d-flex justify-content-center">
						<img src="<%=request.getContextPath()%>/assets/imc.svg"
							style="width: 8rem;" />
					</div>
					<br />
					<div class="flex-row d-flex justify-content-center">
						<h4 id="imc-text" class="card-title"></h4>
						<script type="text/javascript">
							var imcText = document.getElementById('imc-text');
							imcText.innerHTML = "IMC: " + formatIMC(${imc}).toString();
						</script>
					</div>
				</div>
			</div>
		</div>


		<div class="row">

			<div class="col-md-3">

				<div class="card bgc-orange"
					style="width: 14rem; z-index: 1; height: 100%;">
					<div class="card-body flex-column text-center">
						<h4 class="card-title flex-row d-flex justify-content-center mb-4">
							Cadastro de Refeições</h4>
						<img src="<%=request.getContextPath()%>/assets/refeicao.svg"
							class="mx-auto mb-5" style="width: 10rem;" />
						<p class="card-text">Última Refeição: ${lastFood}</p>
					</div>
				</div>

			</div>
			<div class="col-md-9">
				<div class="row mb-4">
					<div class="col-md-12">
						<div class="card bgc-yellow"
							style="width: 100%; z-index: 1; height: 100%;">
							<div class="card-body d-flex flex-row justify-content-evenly">
								<div class="flex-column me-5">
									<h3 class="card-title">Seu Peso</h3>
									<h1 class="card-title">${weight}Kg</h1>
								</div>
								<div class="flex-column ">
									<h3 class="card-title">Sua Altura</h3>
									<h1 class="card-title">${height}M</h1>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="card bgc-purble" style="width: 45.5rem; height: 50%;">
					<div class="card-body d-flex justify-content-center">
						<img src="<%=request.getContextPath()%>/assets/chart.svg"
							style="width: 40rem;">
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../components/footer.jsp"%>
</body>
</html>