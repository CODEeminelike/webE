<!DOCTYPE html>
<html>
<head>
    <title>S?a S?n Ph?m</title>
</head>
<body>
    <h1>S?a S?n Ph?m</h1>
    <form action="admin" method="post">
        <input type="hidden" name="action" value="updateProduct" />
        <input type="hidden" name="id" value="${product.id}" />

        <label for="name">Tên s?n ph?m:</label>
        <input type="text" id="name" name="name" value="${product.name}" required /><br/>

        <label for="descrip">Mô t?:</label>
        <textarea id="descrip" name="descrip" required>${product.descrip}</textarea><br/>

        <label for="category">Danh m?c:</label>
        <input type="text" id="category" name="category" value="${product.category}" required /><br/>

        <label for="price">Giá:</label>
        <input type="number" id="price" name="price" value="${product.price}" required /><br/>

        <input type="submit" value="C?p nh?t S?n Ph?m" />
    </form>
</body>
</html>
