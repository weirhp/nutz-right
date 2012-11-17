var tab = null;
var accordion = null;
var tree = null;
$(function() {
  // 布局
  $("#layout1").ligerLayout({
    leftWidth : 190,
    height : '100%',
    heightDiff : -34,
    space : 4,
    onHeightChanged : f_heightChanged
  });
  var height = $(".l-layout-center").height();
  // Tab
  $("#framecenter").ligerTab({
    height : height
  });
  // 面板
  $("#accordion1").ligerAccordion({
    height : height - 24,
    speed : null
  });
  $(".l-link").hover(function() {
    $(this).addClass("l-link-over");
  }, function() {
    $(this).removeClass("l-link-over");
  });
  // 树
  var setting = {
    data : {
      simpleData : {
        enable : true
      }
    },
    callback : {
      onClick : treeClick
    }
  };
  function treeClick(event, treeId, treeNode) {
    if (!treeNode.accessUrl)
      return;
    var tabid = treeNode.tabid;
    if (!tabid) {
      tabid = new Date().getTime();
      treeNode.tabid=tabid;
    }
    f_addTab(tabid, treeNode.name,basePath + treeNode.accessUrl);
  }
  
  $.fn.zTree.init($("#tree1"), setting, zNodes);

  tab = $("#framecenter").ligerGetTabManager();
  accordion = $("#accordion1").ligerGetAccordionManager();
  tree = $("#tree1").ligerGetTreeManager();
  $("#pageloading").hide();
  
  //修改密码
  $('#updatePassword').click(function(){
    f_addTab('updatePassword','修改密码',basePath + '/user/toUpdatePassword');
  });
  
  
});
function f_heightChanged(options) {
  if (tab)
    tab.addHeight(options.diff);
  if (accordion && options.middleHeight - 24 > 0)
    accordion.setHeight(options.middleHeight - 24);
}
function f_addTab(tabid, text, url) {
  tab.addTabItem({
    tabid : tabid,
    text : text,
    url : url
  });
}

