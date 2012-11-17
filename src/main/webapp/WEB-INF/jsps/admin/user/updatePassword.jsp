<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>修改密码</title>
<%@include file="../../common/admin_include.jsp"%>
<script type="text/javascript">
  $(function() {
	$('#btSave').click(function(){
	  if($(':password[name=oldPass]').val()==''||$(':password[name=newPass]').val()==''){
	    $('#msg').html('密码不能为空！');
	    return false;
	  }
	  if($(':password[name=newPass2]').val()!=$(':password[name=newPass]').val()){
	    $('#msg').html('两次输入的新密码不一致！');
	    return false;
	  }
	  $.post('${base }/user/updatePassword',$('#userForm').serialize(),function(data){
	    if(data.json().success){
	      $('#msg').html('修改成功！');
	      $(':password').val('');
	    }
	  });
	});
  });
</script>
</head>
<body>
  <div class="main">
  <div id="msg" class="error"></div>
    <form id="userForm" action="${base }/user/updatePassword" method="post" class="form-horizontal">
      <table>
        <tr>
          <td>原密码：</td>
          <td><input name="oldPass" type="password" value="" /></td>
        </tr>
        <tr>
          <td>新密码：</td>
          <td><input name="newPass" type="password" value=""/></td>
        </tr>
        <tr>
          <td>确认密码：</td>
          <td><input name="newPass2" type="password" value=""/></td>
        </tr>
        <tr>
          <td>
            <input type="button" id="btSave"  value="保存" />
          </td>
        </tr>
      </table>
    </form>
    <div class="clear"></div>
  </div>
</body>
</html>
