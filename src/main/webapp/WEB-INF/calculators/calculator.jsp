<%-- These are called directives --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- HTML comment -->
<html>
<head>
    <title>My Addition App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<div class="container my-4">
    <div class="row">
        <div class="col-6">
            <h1>My Addition App</h1>
            <p class="lead">Enter two numbers and press submit to calculate the sum.</p>
            <form method="POST" action="calculator">
                <div class="form-group mb-2">
                    <label for="firstNumber">First Number:</label>
                    <input name="firstNum" value="${results.get("num1")}" type="text" class="form-control" id="firstNumber">
                </div>
                <div class="form-group mb-2">
                    <label for="secondNumber">Second Number:</label>
                    <input name="secondNum" value="${results.get("num2")}" type="text" class="form-control" id="secondNumber">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

            <c:if test="${results.containsKey('sum')}">
                <p>${results.get("sum")}</p>
            </c:if>
            <c:if test="${results.containsKey('invalidNumber')}">
                <p>${results.get("invalidNumber")}</p>
            </c:if>
            
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
