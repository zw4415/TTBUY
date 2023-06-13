<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <img src="${pageContext.request.contextPath}/static/img/sanbao.png" class="img-fluid">
</div>
<footer class="mt-2 container-fluid">
    <div class="container text-center text-muted">
        <div class="mb-2">
            <a href="javascript:void(0);" class="text-muted"  data-toggle="modal" data-target="#myModal">联系客服</a>
            <a href="${pageContext.request.contextPath}/regist.jsp" class="text-muted">注册账号</a>
            <a href="${pageContext.request.contextPath}/" class="text-muted">网站首页</a>
            <a href="${pageContext.request.contextPath}/managerlogin.jsp" class="text-muted">后台登录</a>
        </div>
    </div>
</footer>