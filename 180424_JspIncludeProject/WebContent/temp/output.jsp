<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
    // id=admin&mode=1
    // 흐름 제어 ==> 화면 변경
    String id=request.getParameter("id");
    String mode=request.getParameter("mode");
    int no=Integer.parseInt(mode);
    if(no==1)
    {
    	//sendRedirect
    	response.sendRedirect("result.jsp");
    	// output.jsp는 메모리에서 해제 (request가 초기화)
    }
    else if(no==2)
    {
    	// forward
        //pageContext.forward("result.jsp");
    	/*
    	    pageContext: page(jsp)와 page(jsp)
    	     = include() => <jsp:include>
    	     = forward() => <jsp:forward>
    	*/
%>
         <jsp:forward page="result.jsp"/>
<%
    }
%>






