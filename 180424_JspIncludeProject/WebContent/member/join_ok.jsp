<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.member.dao.*"%>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<jsp:useBean id="dao" class="com.sist.member.dao.MemberDAO"/>
<jsp:useBean id="vo" class="com.sist.member.dao.MemberVO">
	<jsp:setProperty name="vo" property="*"/> <!-- 넘어오는 모든값을 받아준다  vo안에채운다 -->
</jsp:useBean>
<%
	dao.memberJoin(vo);
	response.sendRedirect("../member/welcome.jsp");
%>
