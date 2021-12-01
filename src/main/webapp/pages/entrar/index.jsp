<%@ page import="java.util.*" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<%pageContext.setAttribute("pageTitle" , "Bem-vindo");%>
 	<%@ include file="../../components/head.jsp" %>
</head>
<body>
	<%@ include file="../../components/header.jsp" %>
	<div class="container-fluid vh-100 w-100 d-flex justify-content-center align-items-center bgc-purble-dark flex-column">
	  <div class="col-sm-12 col-md-8 col-lg-6 col-xl-4 col-xxl-3 container rounded p-5 text-white d-flex flex-column bgc-purble" style="z-index: 2; box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25)">
	    <h5 class="align-self-center">LOGIN</h5>
	    <form action="<%=request.getContextPath()%>/entrar" method="post">
	      <fieldset>
	        <div class="row">
	          <c:if test="${errorMessage != null }">
	        	  <div style="color: #FF0000;">${errorMessage}</div><br>
	          </c:if>
	          <div class="col-12">
	            <label for="email" class="form-label">E-mail</label>
	            <input required type="email" class="form-control" id="email" name="email" value="${email}"
	              placeholder="Digite seu e-mail" />
	          </div>
	        </div>
	        <div class="row mt-sm-2">
	          <div class="col-12">
	            <label for="password" class="form-label">Senha</label>
	            <input required type="password" class="form-control" id="password" name="password" value="${password}"
	              placeholder="Digite sua senha" />
	          </div>
	        </div>
	      </fieldset>
	      <div class="row mt-sm-2 mt-lg-4">
	        <div class="d-grid col-md-8 col-xs-12 mx-auto">
	          <a href="<%=request.getContextPath()%>/cadastro" class="btn btn-link text-white" role="button" style="box-shadow: none !important">
	            Não tenho conta
	          </a>
	        </div>
	      </div>
	      <div class="row mt-sm-2 mt-lg-4">
            <div class="d-grid col-md-8 col-sm-12 mx-auto">
              <button type="submit" class="btn btn-md btn-primary bgc-primary">
                Entrar
              </button>
            </div>
	      </div>
	    </form>
	  </div>
	</div>
	<%@ include file="../../components/footer.jsp" %>
</body>
</html>