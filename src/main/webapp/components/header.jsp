<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div id="custom-navbar" class="position-fixed text-white vw-100 custom-navbar d-flex flex-row justify-content-between align-content-center" style="${servletPath == null ? 'justify-content: flex-end !important' : ''}">
		<c:if test="${servletPath != entrar && servletPath != cadastrar }">
			<div id="navbar-content" class="custom-navbar-content d-flex flex-row align-self-center">
				<a id="home" class="${servletPath == 'home' ? 'navbar-item-active' : ''}" href="<%=request.getContextPath()%>"><span>Home</span></a>
				<a id="alimentos" class="${servletPath == 'alimentos' ? 'navbar-item-active' : ''}" href="<%=request.getContextPath()%>/alimentos"><span>Alimentos</span></a>
				<a id="atividades" class="${servletPath == 'atividades' ? 'navbar-item-active' : ''}" href="<%=request.getContextPath()%>/atividades"><span>Atividades físicas</span></a>
				<a id="atividades" class="${servletPath == 'perfil' ? 'navbar-item-active' : ''}" href="<%=request.getContextPath()%>/perfil"><span>Perfil</span></a>
			</div>
			<i id="chevron" class="bi bi-chevron-compact-down custom-navbar-chevron align-self-center" onclick="openMenu()"></i>
			<a href="<%=request.getContextPath()%>/logout" style="align-self: center;"><i class="bi bi-power"></i></a>
		</c:if>
		<img src="<%=request.getContextPath()%>/assets/logo.svg" class="logo" />
	</div>
</header>