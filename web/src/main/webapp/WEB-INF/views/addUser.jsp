<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Регистрация</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
  <link rel="stylesheet" href="reg.css">
</head>
<body>
<header>
  <nav class="navbar navbar-default">
    <div class="container-fluid" >
      <div class="col-md-8">
        <div class="navbar-header">
          <a href="${pageContext.request.contextPath}/home" class="navbar-brand">It-Academy</a>
        </div>
      </div>
      <div>
        <ul class="nav navbar-nav">
          <li class="active"><a href="${pageContext.request.contextPath}/home">Главная</a> </li>
          <li ><a href="#">О нас</a> </li>
          <li><a href="#">Обратная связь</a> </li>
          <li><a href="#">Registration</a> </li>
        </ul>
      </div>
    </div>
  </nav>
</header>
<div class="container-fluid">
  <div class="row">
    <div class="col-md-offset-3 col-md-6">
      <c:if test="${error!=null}">
        <h4 style=" color: red" >${error}</h4>
      </c:if>
      <form:form method="post" class="form-horizontal" action="addUser" id="userForm" modelAttribute="userForm">
        <small>
          <span class="heading ">Registration</span>
        </small>
        <div class="form-group">
          <form:input type="text" class="form-control" name="userName"  placeholder="Login" path="userName"/>
          <i class="fa fa-user"></i>
        </div>
        <div class="form-group help">
          <form:input type="password" class="form-control" name="password"  placeholder="Password" path="password"/>
          <i class="fa fa-lock"></i>
        </div>
        <div class="form-group help">
          <form:input type="password" class="form-control" name="password"  placeholder="Password" path="confirmPassword" />
          <i class="fa fa-lock"></i>
        </div>
        <div class="form-group">
          <form:input type="text" class="form-control" name="email"  placeholder="E-mail" path="email" />
          <i class="fa fa-user"></i>
        </div>
        <div class="form-group">
          <form:button type="submit" class="btn btn-default" onclick="${pageContext.request.contextPath}/addUser'">
            Далее
          </form:button>
        </div>
      </form:form>
    </div>

  </div>
</div>
</body>
</html>
