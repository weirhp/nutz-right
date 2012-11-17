<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>添加/编辑菜单项</title>
<%@include file="../../common/admin_include.jsp"%>
<script type="text/javascript">
  $(function() {
  });
</script>
</head>
<body>
  <div class="main">
    <form action="${base }/menu/list" method="post" class="form-horizontal">
      <input type="hidden" name="menu.id" value="${menu.id }"/>
      <table>
        <tr>
          <td>菜单名：</td>
          <td><input name="menu.name" type="text" value="${menu.name }"/></td>
        </tr>
        <tr>
          <td>链接：</td>
          <td><input name="menu.url" type="text" value="${menu.url }"/></td>
        </tr>
        <tr>
          <td>父ID：</td>
          <td><input name="menu.parentId" type="text" value="${menu.parentId }"/></td>
        </tr>
        <tr>
          <td>排序值：</td>
          <td><input name="menu.indexs" type="text" value="${menu.indexs }"/></td>
        </tr>
        <tr>
          <td>
            <input type="button" value="保存" />
          </td>
        </tr>
      </table>
    </form>
    <div class="clear"></div>
  </div>
</body>
</html>
