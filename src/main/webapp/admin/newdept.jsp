<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${ pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
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

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable({
			"aaSorting" : [ [ 4, "desc" ] ]
		});
	});
</script>

<script>
	$(".alert").alert();
</script>
</head>

<body id="dt_example">
	<div class="container">
		<div class="row well">
			<h1 align="center">工作任务后台管理</h1>
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
									<li><a href="${ baseUrl }/admin/index">系统提示</a></li>
									<li><a href="${ baseUrl }/admin/newpass">更改密码</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="admin-accordion" href="#collapseTwo"> 部门管理 </a>
						</div>
						<div id="collapseTwo" class="accordion-body in collapse">
							<div class="accordion-inner">
								<ul>
									<li><a href="#">新增部门</a></li>
									<li><a href="${ baseUrl }/admin/checkdept">查询部门</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="admin-accordion" href="#collapseThree"> 人员管理 </a>
						</div>
						<div id="collapseThree" class="accordion-body in collapse">
							<div class="accordion-inner">
								<ul>
									<li><a href="${ baseUrl }/admin/newperson">新增员工</a></li>
									<li><a href="${ baseUrl }/admin/checkperson">查询员工</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="admin-accordion" href="#collapseFive"> 任务登记 </a>
						</div>
						<div id="collapseFive" class="accordion-body collapse">
							<div class="accordion-inner">
								<ul>
									<li><a href="${ baseUrl }/admin/newtask">新增任务</a></li>
									<li><a href="${ baseUrl }/admin/checktask">查询任务</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="admin-accordion" href="#collapseSix"> 报表打印 </a>
						</div>
						<div id="collapseSix" class="accordion-body collapse">
							<div class="accordion-inner">
								<ul>
									<li><a href="${ baseUrl }/admin/print">报表打印</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" href="${ baseUrl }/index">退出</a>
						</div>
					</div>
				</div>
			</div>
			<div class="span9" style="padding-left: 20px;">
				<h1>新增部门</h1>
				<div id="demo">
					<form id="newDept" class="form-horizontal"
						action="${ baseUrl }/admin/adddept" method="post">
						<fieldset>
							<div class="control-group">
								<label class="control-label" for="deptname"> 部门名称： </label>
								<div class="controls">
									<input id="deptname" class="xlarge" type="text"
										name="dept.name" />
								</div>
							</div>
							<div style="padding-left: 160px;">
								<input class="btn btn-primary" type="submit" value="添加"
									style="margin-right: 124px;" />
								<button class="btn" type="reset">取消</button>
							</div>
						</fieldset>
					</form>
					<div class="spacer"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
