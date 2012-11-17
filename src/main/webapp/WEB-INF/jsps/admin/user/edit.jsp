<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>添加/编辑角色</title>
<%@include file="../../common/admin_include.jsp"%>
<script type="text/javascript">
  $(function() {
    var uId = '${user.id}';
    if(uId){
      $(':input[name="user.name"]').attr('readonly','readonly');
    }
    $('#btBack').click(function(){
      location.href = 'list';
    });
    $('#btSave').click(function(){
      if($(':input[name="user.name"]').val()!='' && $(':input[name="user.nickName"]').val()!=''){
        $('#userForm').submit();
      }
    });
  });
</script>
</head>
<body>
  <div class="main">
    <div id="msg" class="error">${message }</div>
    <form id="userForm" action="${base }/user/save" method="post" class="form-horizontal">
      <input type="hidden" name="user.id" value="${user.id }"/>
      <table>
        <tr>
          <td>用户名：</td>
          <td><input name="user.name" type="text" value="${user.name }" /></td>
        </tr>
        <tr>
          <td>昵称：</td>
          <td><input name="user.nickName" type="text" value="${user.nickName }"/></td>
        </tr>
        <c:if test="${empty user.id}">
        <tr>
          <td>用户密码：</td>
          <td><input name="user.password" type="text" value=""/></td>
        </tr>
        </c:if>
        <tr>
          <td>
            <input type="button" id="btSave"  value="保存" /> <input id="btBack" type="button" value="返回" />
          </td>
        </tr>
      </table>
    </form>
    <div class="clear"></div>
  </div>
</body>
</html>
