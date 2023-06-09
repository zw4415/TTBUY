<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录界面</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/userlogin.css">
    <script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
    <style>
        input[type=text],input[type=password]{
            border: 1px solid darkgray;
            outline: none;
        }
    </style>
</head>
<body>
<div id="all">
    <div id="background">
        <div id="login">
            <div class="second warn">

            </div>
            <div class="login-tab login-tab-l">
                <a id="user-login">账户登录</a>
            </div>

            <div id="loginFoot">

                <%--用户登录部分--%>
                <div id="input" class="div-input">
                    <div id="prompt" class="msg-error">

                        <div class="loginDivError"></div>
                    </div>
                    <form id="loginForm">
                        <input type="hidden" name="action" value="login"/>
                        <div class="item loginDiv3Fisrt">
                            <div>
                                <label for="username" class="login-label name-label"></label>
                            </div>
                            <div>
                                <input id="username" class="input" type="text" name="username" placeholder="用户名">
                            </div>
                        </div>
                        <div class="item loginDiv3Fisrt">
                            <div>
                                <label for="password" class="login-label pwd-label"></label>
                            </div>
                            <div>
                                <input id="password" class="input" type="password" name="password" placeholder="密码">
                            </div>
                        </div>
                        <div class="forgetMima "><a class="forgetA" href="">忘记密码</a></div>
                        <input class="btn btn btn-primary" type="button" onclick="login()" style="height: 40px; width: 320px;" value="登&nbsp;&nbsp;&nbsp;&nbsp;录">
<%--                        <a href=${pageContext.request.contextPath}/checkLoginServlet onclick="checkUser();" id="tripe">登&nbsp;&nbsp;&nbsp;&nbsp;录</a>--%>
                    </form>
                </div>


                <div id="tubiao">
                    <ul id="biao" style="list-style-type: none;">
                        <b></b>
                        <li>
                            <img id="qq" src=${pageContext.request.contextPath}/static/img/qq.PNG>
                            <a href="#"
                               class="pdl">
                                <b class="QQ-icon"></b><span style="font-size: 12px;">QQ</span>
                            </a>
                            <span class="line">|</span>
                        </li>
                        <li>
                            <img id="weixin" src=${pageContext.request.contextPath}/static/img/weixin.PNG>
                            <a href="#"

                               class="pd">
                                <b class="weixin-icon"></b><span style="font-size: 12px;">微信</span>
                            </a>
                        </li>

                        <li class="extra-r">
                            <div class="regist-link">
                                <a href="${pageContext.request.contextPath}/regist.jsp" ><b></b>立即注册</a>
                            </div>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="end">
        <div id="footer-2013">
            <div class="links" style="font-size: 12px;">
                <a rel="nofollow" target="_blank" href="http://www.wal-martchina.com/walmart/index.htm">
                    关于我们
                </a>
                |
                <a rel="nofollow" target="_blank" href="http://www.wal-martchina.com/promotion/promotion.htm">
                    促销活动
                </a>
                |
                <a target="_blank" href="http://www.wal-martchina.com/contact/contactus.htm">
                    联系我们
                </a>

            </div>
        </div>
    </div>

    <script>
        /*轮播图*/
        let background;
        const imgs = ["${pageContext.request.contextPath}/static/img/background2.PNG","${pageContext.request.contextPath}/static/img/background3.PNG"
            ,"${pageContext.request.contextPath}/static/img/background3.PNG"];
        let i = 0;
        let img;
        window.onload = function () {
            background = document.getElementById("background");
            img = window.setInterval(change, 3000);
        }

        function change() {
            i++;

            // 超过2则取余数，保证循环在0、1、2之间
            i = i % 3;
            background.style.background = "url(" + imgs[i] + ")";
        }

        $(function () {
            $("#background").hover(function () {

                window.clearInterval(img);
            }, function () {
                img = window.setInterval(change, 5000);
            })
        });

        /*扫码登录与用户名登录切换*/
        $(function(){
            $("#user-login").click(function(){
                $("#input").show();
                $("#maLogin").hide();
            });
            $("#ma-login").click(function(){
                $("#input").hide();
                $("#maLogin").show();
            });
        });

        function login() {
            //登录逻辑
            let index = layer.load(1, {
                shade: [0.3,'#fff'] //0.1透明度的白色背景
            });
            let username = $("#username").val();
            let password = $("#password").val();
            if(username===""||password===""){
                layer.msg("请输入！");
            }
            $.post("/login/userlogin", {
                    username,
                    password,
                },
                function (data) {
                    layer.close(index)
                    console.log(data)
                    if (data.status === 200) {
                        layer.msg("登陆成功！")
                        location.href = "/";
                    } else {
                        layer.alert("登陆失败");
                    }
                }
            );
        }
    </script>

</div>
</body>
</html>