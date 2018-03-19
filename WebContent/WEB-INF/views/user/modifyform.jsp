<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

				<form id="join-form" name="joinForm" method="post" action="/mysite/user?cmd=modify">
				<input type="hidden" name="cmd" value="join">
				<input type="hidden" name="no" value="${authUser.no }">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${authUser.name }">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${authUser.email }" readonly="readonly">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					<label class="block-label">패스워드확인</label>
					<input name="passwordcheck" type="password" value="">
					
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${authUser.gender=="female" }'>
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male">	
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="female">
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:otherwise>
						</c:choose>
					</fieldset>
					<c:if test='${result == "fail" }'>
						<p>
						회원 수정에 실패 했습니다.
						</p>
					</c:if>
					<input type="submit" value="변경하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="user" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>