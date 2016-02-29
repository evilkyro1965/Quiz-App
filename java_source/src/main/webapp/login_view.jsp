<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz App</title>

    <!-- Bootstrap -->
    <link href="pages/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="pages/css/font-awesome.min.css" rel="stylesheet">
    <link href="pages/css/general.css" rel="stylesheet">
    <link href="pages/css/style.css" rel="stylesheet">
</head>
<body>

<h1 class="pageHeader">Login</h1>

<ul class="horizontalFormList">
    <form action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
        <c:if test="${param.error ne null}">
            <div class="alert-danger">Invalid username and password.</div>
        </c:if>
        <c:if test="${param.logout ne null}">
            <div class="alert-normal">You have been logged out.</div>
        </c:if>
        <li class="fullInput">
            <span class="labelWrapper"><label>Username</label></span>
            <span class="inputWrapper">
                <span class="inputInner">
                    <input type="text" class="inputText" name="username"
                           placeholder="User Name" />
                </span>
            </span>
            <div class="clear"></div>
        </li>
        <li class="fullInput">
            <span class="labelWrapper"><label>Password</label></span>
            <span class="inputWrapper">
                <span class="inputInner">
                    <input type="password" class="inputText" name="password"
                           placeholder="Password" />
                </span>
            </span>
            <div class="clear"></div>
        </li>
        <li class="fullInput">
            <span class="labelWrapper"><label>&nbsp;</label></span>
            <span class="inputWrapper">
                <span class="inputInner">
                    <input class="btn btn-default submit" type="submit" value="Sign In"></input>
                </span>
            </span>
            <div class="clear"></div>
        </li>
    </form>
</ul>

</body>
</html>