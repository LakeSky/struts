<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>

<title>信息管理</title>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="信息管理" />
<link rel="shortcut icon" type="image/ico"
	href="http://www.datatables.net/favicon.ico" />
<style type="text/css" title="currentStyle">
@import "${ baseUrl }/bootstrap/css/demo_page.css";

@import "${ baseUrl }/bootstrap/css/demo_table.css";
</style>
<script src="${ baseUrl }/bootstrap/js/jquery-1.7.2.js"></script>
<script src="${ baseUrl }/bootstrap/js/jquery.dataTables.js"></script>
<script src="${ baseUrl }/bootstrap/js/bootstrap.js"></script>
<link href="${ baseUrl }/bootstrap/css/bootstrap.css" rel="stylesheet" />

<script>
	$(".alert").alert();
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#passAction').submit(function() {
			var oldpass = $('#oldpass').val();
			if (oldpass == null || oldpass == '') {
				alert('请输入原密码');
				return false;
			}
			var newpass = $('#newpass').val();
			if (newpass == null || newpass == '') {
				alert('请输入新密码');
				return false;
			}
			var ensurepass = $('#ensurepass').val();
			if (ensurepass == null || ensurepass == '') {
				alert('请输入确认密码');
				return false;
			}
			if (ensurepass != newpass) {
				alert('新密码与确认密码不符');
				return false;
			}
			return true;
		});
	});
</script>
</head>

<body id="dt_example">
	<div class="container">
		<div class="row well">
			<h1 align="center">部门成员管理</h1>
		</div>
		<c:if test="${tips != null}">
			<div class="alert alert-${alertType}">
				<a class="close" data-dismiss="alert">×</a> ${tips}
			</div>
		</c:if>
		<div class="row">
			<div class="span2">
				<div class="accordion" id="admin-accordion">
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="admin-accordion" href="#collapseOne"> 个人管理 </a>
						</div>
						<div id="collapseOne" class="accordion-body in collapse">
							<div class="accordion-inner">
								<ul>
									<li><a href="${ baseUrl }/worker/index">系统提示</a></li>
									<li><a href="#">更改密码</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="admin-accordion" href="#collapseFive"> 任务管理 </a>
						</div>
						<div id="collapseFive" class="accordion-body in collapse">
							<div class="accordion-inner">
								<ul>
									<li><a href="${ baseUrl }/worker/checktask">查询分配</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" href="${ baseUrl }/logoutAction">退出</a>
						</div>
					</div>
				</div>
			</div>
			<div class="span9" style="padding-left: 20px;">
				<h1>修改密码</h1>
				<div id="demo">
					<form id="passAction" class="form-horizontal"
						action="${ baseUrl }/updatepass" method="post">
						<fieldset>
							<div class="control-group">
								<label class="control-label" for="oldpass"> 原密码： </label>
								<div class="controls">
									<input id="oldpass" class="xlarge" type="password"
										name="user.password" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="newpass"> 新密码： </label>
								<div class="controls">
									<input id="newpass" class="xlarge" type="password"
										name="newpass" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="ensurepass"> 确认密码： </label>
								<div class="controls">
									<input id="ensurepass" class="xlarge" type="password"
										name="ensurepass" />
								</div>
							</div>
							<div style="padding-left: 160px;">
								<input class="btn btn-primary" type="submit" value="修改"
									style="margin-right: 124px;" />
								<button class="btn" type="reset">取消</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
