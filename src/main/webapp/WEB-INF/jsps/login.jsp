<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户登录</title>
<%@include file="common/admin_include.jsp"%>
<script type="text/javascript">
  $(function() {
    $('#btLogin').click(function() {
      if (check()) {
        $.post('${base}/llogin', $('#loginForm').serialize(), function(data) {
          var json = data.json();
          if (json.success) {
            location.href = '${base}/manage/index';
          } else {
            $('#msg').html(json.message);
          }
        });
      }
    });

    function check() {
      var isValid = false;
      if ($(':input[name="user.name"]').val().trim() == "") {
        $('#msg').html('用户名不能为空！');
      } else if ($(':input[name="user.password"]').val() == "") {
        $('#msg').html('密码不能为空！');
      } else {
        isValid = true;
      }
      return isValid;
    }
  });

</script>
<style type="text/css">
  .main{margin: 30px auto;width: 300px;}
  .inp{height: 20px;width: 150px; margin: 2px;}
  .btn{padding: 2px;}
</style>
</head>
<body>
  <div class="main">
    <form id="loginForm" action="">
      <table>
        <tr>
          <td colspan="2"><h2>nutz功能权限系统</h2></td>
        </tr>
        <tr>
          <td>用户名：</td>
          <td><input name="user.name" class="inp" type="text" /></td>
        </tr>
        <tr>
          <td>密码：</td>
          <td><input name="user.password" class="inp" type="password" /></td>
        </tr>
        <tr>
          <td colspan="2" align="right"><input id="btLogin" class="btn" type="button" value="登录" /></td>
        </tr>
        <tr>
          <td colspan="2"><div class="error" id="msg"></div></td>
        </tr>
      </table>
    </form>
    <div class="clear"></div>
  </div>
</body>
</html>
