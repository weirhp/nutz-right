<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>添加/编辑角色</title>
<%@include file="../../common/admin_include.jsp"%>
<script type="text/javascript">
  $(function() {
    $('#btBack').click(function(){
      location.href = 'list';
    });
    $('#btSave').click(function(){
      if($(':input[name="role.name"]').val()!='' && $(':input[name="role.code"]').val()!=''){
        $('#roleForm').submit();
      }
    });
  });
</script>
</head>
<body>
  <div class="main">
    <div id="msg" class="error">${message }</div>
    <form id="roleForm" action="${base }/role_mgr/save" method="post" class="form-horizontal">
      <input type="hidden" name="role.id" value="${role.id }"/>
      <table>
        <tr>
          <td>名称：</td>
          <td><input name="role.name" type="text" value="${role.name }"/></td>
        </tr>
        <tr>
          <td>编码：</td>
          <td><input name="role.code" type="text" value="${role.code }"/></td>
        </tr>
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
