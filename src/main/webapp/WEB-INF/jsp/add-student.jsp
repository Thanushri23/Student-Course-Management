<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; margin: 0; padding: 20px; }
        .container { max-width: 600px; margin: 40px auto; background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
        h1 { color: #333; margin-top: 0; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; font-weight: 600; color: #555; }
        input[type="text"], input[type="email"], select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 16px; }
        .btn { padding: 10px 15px; border-radius: 5px; text-decoration: none; color: #fff; display: inline-block; border: none; cursor: pointer; font-size: 16px; }
        .btn-primary { background-color: #28a745; }
        .btn-primary:hover { background-color: #218838; }
        .btn-secondary { background-color: #6c757d; margin-left: 10px; }
        .btn-secondary:hover { background-color: #5a6268; }
        .error-msg { color: #dc3545; background-color: #f8d7da; padding: 10px; border-radius: 4px; margin-bottom: 20px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add New Student</h1>
        
        <c:if test="${not empty error}">
            <div class="error-msg">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/students" method="post">
            <div class="form-group">
                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" required value="${student.name}">
            </div>
            
            <div class="form-group">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" required value="${student.email}">
            </div>
            
            <div class="form-group">
                <label for="course">Enroll in Course</label>
                <select id="course" name="course.id" required>
                    <option value="" disabled selected>Select a Course</option>
                    <c:forEach var="course" items="${courses}">
                        <option value="${course.id}">${course.title} (${course.credits} Credits)</option>
                    </c:forEach>
                </select>
            </div>
            
            <button type="submit" class="btn btn-primary">Save Student</button>
            <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>
