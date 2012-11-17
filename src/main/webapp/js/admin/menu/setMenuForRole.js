var menuTree = null;
// 树的默认配置
var setting = {
  view : {
    selectedMulti : false
  },
  check: {
    enable: true,
    autoCheckTrigger: true
  },
  data : {
    simpleData : {
      enable : true
    }
  }
};
// 单击树节点的处理
function treeClick(event, treeId, treeNode) {
}

// 初始化树
function initTree() {
  $.getJSON(basePath + "/menu/menuJson?t=" + (new Date().getTime()), function(data) {
    menuTree = $.fn.zTree.init($("#menuTree"), setting, data);
    //set checkbox
    $.getJSON(basePath + "/menu/menuJsonForRole?roleId="+ roleId +"&t=" + (new Date().getTime()), function(menuJson) {
      if(menuJson&&menuJson.length){
        for(var i=0;i<menuJson.length;i++){
          var tmpMenu = menuJson[i];
          var tmpNode = menuTree.getNodesByFilter(function(node){return node.id==tmpMenu.id;},true);
          if(!tmpNode.children||!tmpNode.children.length){
            menuTree.checkNode(tmpNode, true, true, true);
          }
        }
      }
    });
  });
}

$(function() {
  initTree();// 加载树
   //保存
  $("#btSave").click(function() {
    var nodes = menuTree.getNodesByFilter(function(node){return node.checked||node.halfChecked;});
    var nodesJson = [];
    for ( var i = 0; i < nodes.length; i++) {
      nodesJson.push(nodes[i].id);
    }
    $.post(basePath + '/menu/saveRoleMenus', {
      menuIds : JSON.stringify(nodesJson),
      roleId : roleId
    }, function(data) {
      var json = data.json();
      if (json.success) {
        initTree();// 加载树
        $('#msg').html('保存成功！');
      } else {
        $('#msg').html('保存失败！');
      }
    });
  });

  //刷新树
  $('#btRefresh').click(initTree);
});