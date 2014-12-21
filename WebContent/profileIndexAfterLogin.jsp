<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="headerAfterLogin.jsp"/>
<%
			Object obj = request.getParameter("sec");
			if (obj != null) {
				String sec = obj.toString();
				if (sec.equals("aboutUs")) {
					%> <jsp:include page="aboutUs.jsp" /> <%
				} else if (sec.equals("home")) {
					%> <jsp:include page="userProfile.jsp" /> <%
				} else if (sec.equals("contactUs")) {
					%> <jsp:include page="contactUs.jsp" /> <%
				} else if (sec.equals("newsLetter")) {
					%> <jsp:include page="newsLetter.jsp" /> <%
				}else if (sec.equals("logOut")) {
					request.getSession().invalidate();
					%> <jsp:forward page="indexPage.jsp" /> <%
				}
			}else{ %>
				<jsp:include page="userProfile.jsp" />
			<% }
				%>
 	
</body>
</html>