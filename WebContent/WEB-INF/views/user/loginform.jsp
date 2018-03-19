<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- jstl 안쓰는 경우
	/* redirect방식
	String result = request.getParameter("result"); */
	
	/* forward 방식 */
	String result = (String) request.getAttribute("result");
	String email = (String) request.getAttribute("email");
	
--%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="/mysite/user">
				<input type="hidden" name="cmd" value="login">
					<label class="block-label" for="email">이메일</label>
					<c:choose>
						<c:when test="${email == null }">
							<input id="email" name="email" type="text" value="">
						</c:when>
						<c:otherwise>
							<input id="email" name="email" type="text" value="${email }">	
						</c:otherwise>
					</c:choose>
					<label class="block-label" >패스워드</label>
					<input name="password" type="password" value="">
					<c:if test='${result == "fail" }'>
						<p>
						로그인이 실패 했습니다.
					</p>
					</c:if>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>