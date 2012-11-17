<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>设置角色关联的菜单项</title>
<%@include file="../../common/admin_include.jsp"%>
<link rel="stylesheet" href="${base }/js/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base }/js/plugins/ztree/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript">
	var roleId = '${roleId}';
</script>
<script type="text/javascript" src="${base }/js/admin/menu/setMenuForRole.js"></script>
</head>
<body>
  <div class="main">
    <div>
      <input id="btSave" value="保存" type="button" />
      <input id="btRefresh" value="刷新" type="button" />
    </div>
    <div id="msg" class="error"></div>
    <div id="leftTree"  class="fl">
      <div class="tit">菜单数据</div>
      <ul id="menuTree" class="ztree"></ul>
    </div>
    <div class="clear"></div>
  </div>
</body>
</html>
