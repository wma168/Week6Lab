<%-- 
    Document   : shoppingList
    Created on : 23-Feb-2023, 9:52:47 AM
    Author     : xbali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <<body>
        <h1>Shopping List</h1>
        Hello, ${username} 
        <a href="shoppingList?action=logout">Logout</a><br>
        
        <h2>List</h2>
        <form action="shoppingList" method="post">
            Add Item: 
            <input type="text" name="itemToAdd"> 
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
            ${message}
        </form>
        <br>
        <form action="shoppingList" method="post">
            
             <c:forEach items="${items}" var="item">
            <input type="radio" name="foodDelete" value="${item}">
            ${item}
            <br>            
            
        </c:forEach>
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete"><br>
            
            ${noFoodDelete}
        </form>
       
         
            
    </body>
</html>