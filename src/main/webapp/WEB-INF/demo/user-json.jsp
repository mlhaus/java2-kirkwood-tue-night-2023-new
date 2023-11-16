<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <title>User Directory</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<header class="bg-dark">
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand text-light" href="#">User Directory</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <form class="d-flex pt-3" action="user-json" method="get">

                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="q" value="${q}">
                        <input type="hidden" name="sort" value="${s}">
                        <input type="hidden" name="state" value="${state}">
                        <button class="btn btn-light" type="submit">Search</button>

                </form>
                <form class="d-flex pt-3" action="user-json" method="get">

                    <select name="state" class="form-select" aria-label="Default select example">
                        <option value="">All</option>
                        <c:forEach items="${states}" var="s">
                        <option ${state == s ? 'selected': ''} value="${s}">${s}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="sort" value="${s}">
                    <input type="hidden" name="q" value="${q}">
                    <button class="btn btn-light" type="submit">Filter</button>

                </form>
                <div class="dropdown ms-3">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="sortDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                        Sort
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="sortDropdown">
                        <li><a class="dropdown-item" href="user-json?sort=az&q=${q}&state=${state}">A to Z</a></li>
                        <li><a class="dropdown-item" href="user-json?sort=za&q=${q}&state=${state}">Z to A</a></li>
                    </ul>
                </div>
                <div class="dropdown ms-3">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="sortDropdown2" data-bs-toggle="dropdown" aria-expanded="false">
                        Filter by state
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="sortDropdown2">
                        <li><a class="dropdown-item" href="user-json?sort=${s}&q=${q}&state=">All</a></li>
                        <c:forEach items="${states}" var="state">
                            <li><a class="dropdown-item" href="user-json?sort=${s}&q=${q}&state=${state}">${state}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>
<div class="container py-4">
    <div class="row">
        <c:forEach items="${users}" var="user">
            <div class="col-sm-6 col-md-4 col-lg-3 col-xl-2">
                <div class="card mb-4">
                    <img class="card-img-top" src="${user.picture.large}" alt="${user.name.first} ${user.name.last}">
                    <div class="card-body">
                        <h5 class="card-title">${user.name.first} ${user.name.last}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${user.location.city}, ${user.location.state}</h6>
                        <a href="mailto:${user.email}" class="card-link">Email</a>
                        <a href="tel:${user.phone}" class="card-link">Call</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">First</th>
                <th scope="col">Last</th>
                <th scope="col">Location</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user" varStatus="status">
            <tr>
                <th scope="row">${status.count}</th>
                <td>${user.name.first}</td>
                <td>${user.name.last}</td>
                <td>${user.location.city}, ${user.location.state}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
