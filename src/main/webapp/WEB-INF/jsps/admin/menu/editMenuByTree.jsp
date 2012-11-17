<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>添加/编辑菜单项</title>
<%@include file="../../common/admin_include.jsp"%>
<link rel="stylesheet" href="${base }/js/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base }/js/plugins/ztree/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript" src="${base }/js/admin/menu/editMenuByTree.js"></script>
</head>
<body>
  <div class="main">
    <div>
      <input id="btSave" value="保存" type="button" />
      <input id="btAddRoot" value="添加根菜单" type="button" />
      <input id="btRefresh" value="刷新" type="button" />
    </div>
    <div id="msg" class="error"></div>
    <div id="leftTree"  class="fl">
      <div class="tit">菜单数据</div>
      <ul id="menuTree" class="ztree"></ul>
    </div>
    <div id="rightCtrl" class="fl">
    <input id="nodeId" name="id" type="hidden" />
      <table class="datatable">
        <tr>
          <td>名称：</td>
          <td><input id="node_name" name="name" type="text" /></td>
        </tr>
        <tr>
          <td>地址：</td>
          <td><input id="nodeUrl" name="accessUrl" type="text" /></td>
        </tr>
         <tr>
          <td>显示：</td>
          <td><input id="nodeShow" name="show" type="checkbox" /></td>
        </tr>
        <tr id="trSetOperation" style="display: none;">
          <td colspan="2"><input id="btSetOperation"  value="设置关联的操作" type="button" /></td>
        </tr>
      </table>
    </div>
    <div class="clear"></div>
  </div>
</body>
</html>
