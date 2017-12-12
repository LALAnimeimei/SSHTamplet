<%--
  Created by IntelliJ IDEA.
  User: 390643
  Date: 17-12-6
  Time: 下午2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>Hello</title>
</head>
<script>
    function jump() {
        window.location.href="./querydata.do";
        <%--<%response.sendRedirect("./querydata.do");%>--%>
    }
</script>
<body>
<input type="button" value="Query" onclick="jump()"/>
<a href="./querydata.do" style="color: coral">Query</a>

</body>
</html>
