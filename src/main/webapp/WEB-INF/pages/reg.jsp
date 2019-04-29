<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<!----------- Styles Block -------------->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<title>Регистрация</title>
	</head>
	<body>
		<div class="reg-form" align="center">
			<a class="welcome" align="center">Welcome to Electronic Journal</a>
			<br>
			<br>
			<br>
			<form action="/register" method="post">
				<div>
					Login
				<br>
				<input type="text" name="name">
				</div>
				<br>
				<div>
					FIO
				<br>
				<input type="text" name="fio">
				</div>
				<br>
				<div>
					Student ticket
				<br>
				<input type="text" name="ticket">
				</div>
				<br>
				<div>
					Password
				<br>
				<input type="password" name="password">
				</div>
				<br>
				<div>
					Repeat password
				<br>
				<input type="password" name="repeatPassword">
				</div>
				<br>
				<input class="send" type="submit" value="Send">

			</form>
		</div>
	</body>
</html>