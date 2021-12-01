<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${successMessages != null}">
	<div class="alert alert-success" role="alert">
		<h5 class="alert-heading">${successMessagesTitle}</h5>
		<c:forEach var="successMessage" items="${successMessages}">
			<label>${successMessage}</label>
			<br>
		</c:forEach>
	</div>
</c:if>
<c:if test="${errorMessages != null }">
	<div class="alert alert-danger" role="alert">
		<h5 class="alert-heading">${errorMessagesTitle}</h5>
		<c:forEach var="errorMessage" items="${errorMessages}">
			<label>${errorMessage}</label>
			<br>
		</c:forEach>
	</div>
</c:if>