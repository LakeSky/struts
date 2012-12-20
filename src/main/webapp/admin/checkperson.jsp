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
			<h1 align="center">工作任务后台管理</h1>
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
									<li><a href="${ baseUrl }/admin/newdept">新增部门</a></li>
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
									<li><a href="#">查询员工</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="admin-accordion" href="#collapseFive"> 任务登记 </a>
						</div>
						<div id="collapseFive" class="accordion-body in collapse">
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
				<h1>人员列表</h1>
				<div id="demo">
					<form id="checkperson" class="form-horizontal"
						action="${ baseUrl }/admin/deleteemp" method="post">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="example" width="100%">
							<thead>
								<tr>
									<th>序号</th>
									<th>用户名</th>
									<th>所属部门</th>
									<th>职位</th>
									<th>是否删除</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${userinfo != null }">
									<c:forEach var="user" items="${ userinfo }" varStatus="stat">
										<tr class="gradeA">
											<td class="center">${user.id }</td>
											<td class="center"><a
												href="${ baseUrl }/admin/updateemp?Userid=${user.id }">${user.username
													}</a></td>
											<td class="center">${user.department.name }</td>
											<c:if test="${user.role == 'MANAGER'}">
												<td class="center">部门经理</td>
											</c:if>
											<c:if test="${user.role == 'USER'}">
												<td class="center">普通员工</td>
											</c:if>
											<c:if test="${user.role == 'ADMIN'}">
												<td class="center">总经理</td>
											</c:if>
											<td class="center"><input type="checkbox" name="userids"
												value="${user.id }"></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
						<div class="spacer"></div>
						<div style="padding-left: 200px;">
							<input class="btn btn-primary" type="submit" value="删除"
								style="margin-right: 124px;" />
							<button class="btn" type="reset">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
