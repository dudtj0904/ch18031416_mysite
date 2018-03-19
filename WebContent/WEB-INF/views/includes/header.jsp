<%@page import="com.cafe24.mysite.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="header">
	<h1>
		<a href="/mysite/main">MySite</a>
	</h1>
	<ul>
		<c:choose>
			<c:when test="${authUser == null }">
				<li><a href="/mysite/user?cmd=loginform">로그인</a></li>
				<li><a href="/mysite/user?cmd=joinform">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/mysite/user?cmd=modifyform">회원정보수정</a></li>
				<li><a href="/mysite/user?cmd=logout">로그아웃</a></li>
				<li>${authUser.getName() }님 안녕하세요 ^^;</li>
			</c:otherwise>
		</c:choose>

	</ul>
</div>