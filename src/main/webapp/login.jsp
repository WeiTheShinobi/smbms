<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系統登錄 - 超市訂單管理系統</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <script type="text/javascript">
	/* if(top.location!=self.location){
	      top.location=self.location;
	 } */
    </script>
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>超市訂單管理系統</h1>
        </header>
        <section class="loginCont">
	        <form class="loginForm" action="${pageContext.request.contextPath }/login.do"  name="actionForm" id="actionForm"  method="post" >
				<div class="info">${error}</div>
				<div class="inputbox">
                    <label for="userCode">使用者：</label>
					<input type="text" class="input-text" id="userCode" name="userCode" placeholder="請輸入使用者ID" required/>
				</div>	
				<div class="inputbox">
                    <label for="userPassword">密碼：</label>
                    <input type="password" id="userPassword" name="userPassword" placeholder="請輸入密碼" required/>
                </div>	
				<div class="subBtn">
					
                    <input type="submit" value="登錄"/>
                    <input type="reset" value="清空"/>
                </div>	
			</form>
        </section>
    </section>
</body>
</html>
