<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="../common/admin_include.jsp"%>
<script src="indexdata.js" type="text/javascript"></script>
<link href="${base}/css/admin_index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var zNodes = ${menus};//菜单json
</script>
  
<script type="text/javascript" src="${base }/js/admin/admin_index.js"></script>
<link rel="stylesheet" href="${base }/js/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base }/js/plugins/ztree/jquery.ztree.all-3.4.min.js"></script>
  
</head>
<body style="padding: 0px; background: #EAEEF5;">
  <div id="pageloading"></div>
  <div id="topmenu" class="l-topmenu">
    <div class="l-topmenu-logo">后台管理</div>
    <div class="l-topmenu-welcome">
      <a href="${base }/manage/userinfo" class="l-link2">admin</a> <span class="space">|</span> <a id="updatePassword" href="javascript:"
        class="l-link2">修改密码</a> <span class="space">|</span> <a href="${base }/manage/logout"
        class="l-link2">退出</a>
    </div>
  </div>
  <div id="layout1" style="width: 99.2%; margin: 0 auto; margin-top: 4px;">
    <div position="left" title="主要菜单" id="accordion1">
      <div title="功能列表" class="l-scroll">
        <ul id="tree1" style="margin-top: 3px;" class="ztree"></ul>
      </div>
    </div>
    <div position="center" id="framecenter">
      <div tabid="home" title="我的主页" style="height: 300px">
        <iframe frameborder="0" name="home" id="home" src="${base }/welcome.html"></iframe>
      </div>
    </div>
  </div>
  <div style="height: 32px; line-height: 32px; text-align: center;">Copyright 2012 <a href="http://blog.weirhp.com" target="_blank" title="访问我的博客">weirhp</a></div>
</body>
</html>
