<!DOCTYPE html>
<html>
<head>
    <title>Th�m S?n Ph?m</title>
</head>
<body>
    <h1>Th�m S?n Ph?m M?i</h1>
    <form action="${pageContext.request.contextPath}/admin" method="post">
        <input type="hidden" name="action" value="addProduct" />
        <label for="name">T�n s?n ph?m:</label>
        <input type="text" id="name" name="name" required /><br/>

        <label for="descrip">M� t?:</label>
        <textarea id="descrip" name="descrip" required></textarea><br/>

        <label for="category">Danh m?c:</label>
        <input type="text" id="category" name="category" required /><br/>

        <label for="price">Gi�:</label>
        <input type="number" id="price" name="price" required /><br/>

        <input type="submit" value="Th�m S?n Ph?m" />
    </form>
</body>
</html>
