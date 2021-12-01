<%@ page import="java.util.*" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<%pageContext.setAttribute("pageTitle", "Cadaste-se");%>
	<%@ include file="../../components/head.jsp"%>
	<style>
	#cadastro-container {
		padding: 90px 100px 0px 100px;
		gap: 130px;
		min-height: 100vh;
	}
	
	#cadastro-container-content {
		max-width: 500px;
	}
	
	@media only screen and (max-width: 959px) {
		#cadastro-container {
			padding: 90px 20px 0px 20px;
			gap: 0;
		}
		#woman-walking {
			display: none;
		}
	}
	</style>
</head>
<body>
	<%@ include file="../../components/header.jsp"%>
	<div id="cadastro-container"
		class="container-fluid w-100 bgc-purble-dark d-flex justify-content-evenly align-items-center
		    ">
		<div id="woman-walking" class="col-md-5 col-sm-12 col-xs-12">
			<img src="<%=request.getContextPath()%>/assets/mulher-correndo.svg"
				class="mx-auto d-block img-fluid" />
		</div>
		<div id="cadastro-container-content"
			class=" col-md-7 col-sm-12 col-xs-12 container rounded p-5 text-white d-flex flex-column bgc-purble">
			<h5 class="align-self-center">CADASTRE-SE</h5>
			<form action="<%=request.getContextPath()%>/cadastrar" method="post">
				<fieldset>
					<div class="gap-2 d-grid">
						<div class="row">
				            <c:if test="${errorMessages != null }">
				              <c:forEach var="errorMessage" items="${errorMessages}">
				                 <div style="color: #FF0000;">${errorMessage}</div><br>
				              </c:forEach>
				            </c:if>
							<div class="col-12">
								<label for="name" class="form-label">Nome</label> <input
									required type="text" class="form-control" id="name" value="${name}"
									name="name" placeholder="Digite seu nome completo" />
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<label for="email" class="form-label">E-mail</label> <input
									required type="email" class="form-control" id="email" value="${email}"
									name="email" placeholder="Digite seu e-mail" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 col-sm-12">
								<label for="height" class="col-form-label">Altura</label> <input
									required type="number" class="form-control" id="height" step=0.1 value="${height}"									name="height" placeholder="1,60" />
							</div>
							<div class="col-md-3 col-sm-12">
								<label for="weight" class="col-form-label">Peso</label> <input
									required type="number" class="form-control" id="weight" step=0.1 value="${weight}"
									name="weight" placeholder="90" />
							</div>
							<div class="col-md-6 col-sm-12">
								<label for="gender" class="col-form-label">Sexo</label> <select
									class="form-select" id="gender" name="gender" required>
									<option ${gender == null ? 'selected' : ''} value="">Selecione</option>
									<option ${gender == 'M' ? 'selected' : ''} value="M">Masculino</option>
									<option ${gender == 'F' ? 'selected' : ''} value="F">Feminino</option>
									<option ${gender == 'NB' ? 'selected' : ''} value="NB">Não binário</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<label for="password" class="form-label">Senha</label> <input
									required type="password" class="form-control" id="password" minlength=4 value="${password}"
									name="password" placeholder="Digite sua senha" />
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<label for="confirm-password" class="form-label">Confirme sua senha</label> <input required type="password" class="form-control"
									id="confirm-password" name="confirmPassword" value="${confirmPassword}"
									placeholder="Digite novamente a sua senha" />
							</div>
						</div>
						<div class="row mt-5">
							<div class="d-grid col-lg-6 col-md-8 col-sm-8 col-xs-12 mx-auto">
								<button type="submit" class="btn btn-md btn-primary bgc-primary">CADASTRAR</button>
							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>