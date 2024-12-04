<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="appUtil.DBUtil" %>  <!-- CHÚ Ý IMPORTTTTTTTT-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Check Database Connection</title>
</head>
<body>
    <h1>Database Connection Status</h1>

    <%
        // L?y k?t n?i t? DBUtil
        Connection conn = DBUtil.getConnection();
        if (conn != null) {
    %>
            <p style="color:green;">K?t n?i MySQL thành công!</p>
    <%
        } else {
    %>
            <p style="color:red;">K?t n?i MySQL th?t b?i!</p>
    <%
        }
        // ?óng k?t n?i
        DBUtil.closeConnection();
    %>

</body>
</html>
