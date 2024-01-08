<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <title>Responsive Layout</title>

    <sitemesh:write property='head'/>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        nav {
            display: flex;
            justify-content: space-around;
            background-color: #444;
            padding: 10px;
        }

        nav a {
            color: #fff;
            text-decoration: none;
        }

        footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            background-color: #fff;
            box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
            justify-content: space-around;
            align-items: center;
        }

        .map {
            max-width: 600px;
            max-height: 600px;
            width: 100%;
            height: 100%;
            position: absolute;
            z-index: 1;
        }

        @media (min-width: 601px) {
            body {
                max-width: 600px;
                margin: 0 auto;
            }

            footer {
                max-width: 600px;
                margin: 0 auto;
            }
        }
    </style>
</head>

<body>

<%--헤더--%>
<header>
    <h1>Couple Compass</h1>
</header>

<%--메인--%>
<section>
    <sitemesh:write property='body'/>
</section>

<%--하단 네비게이션 바--%>
<footer>
    <nav>
        <a href="#">HOME</a>
        <a href="#">CHAT</a>
        <a href="#">LETTER</a>
        <a href="#">SETTING</a>
    </nav>
</footer>

</body>
</html>

<sitemesh:write property='script'/>