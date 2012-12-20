<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<title>教师管理</title>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="首页" />
<script src="bootstrap/js/jquery-1.7.2.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script>
	$(function() {
		$('#fixed-nav li:last').addClass("active");
	});
</script>

</head>

<body>
	<div style="padding-top: 200px; padding-left: 30%;">
		<div class="span7">
			<form class="form-horizontal" action="login" method="post">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="nameInput"> 用户名： </label>
						<div class="controls">
							<input id="nameInput" class="xlarge" type="text"
								name="user.username" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="passInput"> 密&emsp;码： </label>
						<div class="controls">
							<input id="passInput" class="xlarge" type="password"
								name="user.password" />
						</div>
					</div>
					<div style="padding-left: 160px;">
						<input class="btn btn-primary" type="submit" value="登录"
							style="margin-right: 124px;" />
						<button class="btn" type="reset">取消</button>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>
