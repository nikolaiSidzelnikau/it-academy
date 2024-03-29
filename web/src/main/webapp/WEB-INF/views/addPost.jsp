<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Post</title>
    <link rel="stylesheet"
          href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/> "/>
    <link rel="stylesheet"
          href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/> "/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/reg.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/user.css'/>"/>
</head>
<br>
<header>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="col-md-8">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand">It-Academy</a>
                </div>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="#">О нас</a></li>
                    <li><a href="#">Обратная связь</a></li>
                </ul>
            </div>
            <div class="userinfo">
                <div class="user">
                    <ul>
                        <li>
                            <c:if test="${userForm.userName!=null}">
                                <c:if test="${userForm.photo!=null}">
                                    <img src="${pageContext.request.contextPath}/image" title="user-name"/>
                                </c:if>
                                <c:if test="${userForm.photo==null}">
                                    <img src="<c:url value='/resources/image/smail.jfif'/>" title="user-name"/>
                                </c:if>
                                <span>Hello ${userForm.userName}</span>
                            </c:if>
                        </li>
                    </ul>
                </div>
            </div>
            <div>
            </div>
        </div>
    </nav>
</header>
<div class="container-fluid">
    <div id="men-left" class="rad col-md-2"></div>
    <div class="col-md-2"></div>
    <div class=" col-md-4">
        <div>
            <h1 id="h1r" class=" text-center">Your posts</h1>
            <form:form method="post" action="addPost" modelAttribute="postForm">
                <table>
                    <tr>
                        <td>Post Name</td>
                        <td>
                            <form:input path="idTopic" type="hidden"/>
                            <form:input type="text" name="postName" required="required" path="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Text</td>
                        <td><form:textarea path="text" name="textPost" required="required" rows="5" cols="100"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="submit">
                        </td>
                    </tr>
                </table>
            </form:form>
            <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary">welcome</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">logout</a>
        </div>
    </div>
    <div class="col-md-2"></div>
    <div class="col-md-2"></div>
</div>
</body>
</html>