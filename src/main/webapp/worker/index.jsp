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

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable({
			"aaSorting" : [ [ 0, "asc" ] ]
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
			<h1 align="center">部门成员管理</h1>
		</div>
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
									<li><a href="#">系统提示</a></li>
									<li><a href="${ baseUrl }/worker/newpass">更改密码</a></li>
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
				<h1>任务列表</h1>
				<div id="demo">
					<table cellpadding="0" cellspacing="0" border="0" class="display"
						id="example" width="100%">
						<thead>
							<tr>
								<th>序号</th>
								<th>任务名称</th>
								<th>要求完成时间</th>
								<th>当前状态</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${tasks != null }">
								<c:forEach var="task" items="${ tasks }" varStatus="stat">
									<c:if test="${group[stat.count-1] == -1}">
										<tr class="gradeX">
									</c:if>
									<c:if test="${group[stat.count-1] == 0}">
										<tr class="gradeD">
									</c:if>
									<c:if test="${group[stat.count-1] == 1}">
										<tr class="gradeA">
									</c:if>
									<td class="center">${task.id }</td>
									<td class="center">${task.name }</td>
									<td class="center">${task.lastTime }</td>
									<td class="center">${task.status }</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<div class="spacer"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
