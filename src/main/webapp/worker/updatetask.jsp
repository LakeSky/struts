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
<script language="javascript" type="text/javascript"
	src="${ baseUrl }/My97DatePicker/WdatePicker.js"></script>

<script>
	$(".alert").alert();
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
				<h1>详细信息</h1>
				<div id="demo">
					<form id="updatertask" class="form-horizontal"
						action="${ baseUrl }/worker/updateitem" method="post">
						<fieldset>
							<div class="control-group">
								<label class="control-label" for="taskid"> 任务Id： </label>
								<div class="controls">
									<input id="taskid" class="xlarge" type="text" name="task.id"
										value="${task.id }" readonly="readonly" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="taskname"> 任务名称： </label>
								<div class="controls">
									<input id="taskname" class="xlarge" type="text"
										name="task.name" value="${task.name }" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="taskcontent"> 任务内容： </label>
								<div class="controls">
									<textarea id="taskcontent" class="input-xlarge" rows="3"
										name="task.content" readonly="readonly">${task.content}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="lasttime"> 截止日期： </label>
								<div class="controls">
									<input id="lasttime" class="Wdate" type="text"
										onfocus="WdatePicker()" name="task.lastTime"
										value="${task.lastTime }" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="fintime"> 完成日期： </label>
								<div class="controls">
										<input id="fintime" class="Wdate" type="text"
											onfocus="WdatePicker()" name="task.finTime"
											value="${task.finTime }" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="taskstatus"> 任务状态： </label>
								<div class="controls">
									<select id="taskstatus" name="task.status">
										<c:if test="${task.status == '未完成'}">
											<option value="未下达" selected="selected">未完成</option>
											<option value="已完成">已完成</option>
										</c:if>
										<c:if test="${task.status == '已完成'}">
											<option value="已完成" selected="selected">已完成</option>
										</c:if>
										<c:if test="${task.status == '已下达'}">
											<option value="已下达" selected="selected">已下达</option>
											<option value="未完成">未完成</option>
											<option value="已完成">已完成</option>
										</c:if>
									</select>
								</div>
							</div>
							<div style="padding-left: 160px;">
								<input class="btn btn-primary" type="submit" value="确认"
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
