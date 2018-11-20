<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../fragment/head.jsp" />
<title>Stock form</title>
<style type="text/css">
.errorMessage {
	color: red;
	list-style-type: none;
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<div class="container pt-5 pb-5">
		<h1>Form Kho hàng</h1>
		<hr>
		<s:fielderror />
		<s:form action="save" namespace="/stock">
			<s:hidden name="stockBean.id" />
			<div class="form-group">
				<label>Mã kho hàng</label>
				<s:textfield name="stockBean.code" cssClass="form-control" />
			</div>
			<div class="form-group">
				<label>Tên kho hàng</label>
				<s:textfield name="stockBean.name" cssClass="form-control" />
			</div>
			<div class="form-group">
				<s:submit value="Lưu" cssClass="btn btn-primary" />
			</div>
		</s:form>
	</div>
	<jsp:include page="../fragment/js.jsp" />
</body>
</html>