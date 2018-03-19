<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">

			<fmt:parseNumber var="fmtboardcount1" value="${boardCount/10}"
				integerOnly="true" />
			<fmt:parseNumber var="fmtboardcount2" value="${boardCount%10}"
				integerOnly="true" />
			<c:choose>
				<c:when test="${fmtboardcount2 != 0 }">
					<c:set var="pagecount" value="${fmtboardcount1+1}" />
				</c:when>
				<c:otherwise>
					<c:set var="pagecount" value="${fmtboardcount1}" />
				</c:otherwise>
			</c:choose>

			<!-- board 리스트 시작 -->
			<div id="board">
				<form id="search_form" action="/mysite/board?cmd=list" method="post">
					<input type="text" id="kwd" name="kwd" value="${kwd }"> <input
						type="submit" value="찾기">
				</form>
				<!-- table -->
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="listsize" value="${boardCount }" />
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${listsize-(page-1)*10-status.index }</td>
							<td style="text-align:left; padding-left:${20*vo.depth}px">
								<c:if test="${vo.depth != 0 }">
									<img src="/mysite/assets/images/reply.png" />
								</c:if> <a href="/mysite/board?cmd=view&no=${vo.no }">${vo.title }</a>
							</td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:if test="${vo.userNo==sessionScope.authUser.no }">
									<a href="/mysite/board?cmd=delete&no=${vo.no }" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>

				</table>
				<!-- table end -->
				<!-- page -->
				<div class="pager">

					<ul>
						<li><a href="/mysite/board?cmd=list&page=${page-1 }&kwd=${kwd }">◀</a></li>
						<c:choose>
							<c:when test="${page % 5 == 0 }">
								<!-- page가 5,10,15.. -->

								<c:forEach begin="${page-4 }" end="${page }" step="1" var="i">
									<c:choose>
										<c:when test="${i==page }">
											<li class="selected">${i }</li>
										</c:when>
										<c:when test="${i > pagecount }">
											<li>${i }</li>
										</c:when>
										<c:otherwise>
											<li><a href="/mysite/board?cmd=list&page=${i }&kwd=${kwd }">${i }</a></li>
										</c:otherwise>
									</c:choose>

								</c:forEach>

							</c:when>

							<c:otherwise>
								<fmt:parseNumber var="fmtNumber" value="${page/5}"
									integerOnly="true" />
								<c:forEach begin="${(fmtNumber+1)*5-4 }"
									end="${(fmtNumber+1)*5 }" step="1" var="i">
									<c:choose>
										<c:when test="${i==page }">
											<li class="selected">${i }</li>
										</c:when>
										<c:when test="${i > pagecount }">
											<li>${i }</li>
										</c:when>
										<c:otherwise>
											<li><a href="/mysite/board?cmd=list&page=${i }&kwd=${kwd }">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<li><a href="/mysite/board?cmd=list&page=${page+1 }&kwd=${kwd }">▶</a></li>
					</ul>


				</div>
				<!-- page end -->

				<div class="bottom">
					<a href="/mysite/board?cmd=writeform" id="new-book">글쓰기</a>
				</div>
			</div>
			<!-- board end -->
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>