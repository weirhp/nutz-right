var menuTree = null;
var delMenus = [];
var newCount = 1;
// 树的默认配置
var setting = {
  view : {
    addHoverDom : addHoverDom,
    removeHoverDom : removeHoverDom,
    selectedMulti : false
  },
  edit : {
    enable : true,
    editNameSelectAll : true
  },
  data : {
    simpleData : {
      enable : true
    }
  },
  callback : {
    beforeDrag : beforeDrag,
    beforeDrop : beforeDrop,
    // beforeEditName : beforeEditName,
    beforeRemove : beforeRemove,
    beforeRename : beforeRename,
    onRemove : onRemove,
    onRename : onRename,
    onClick:treeClick
  }
};
// 单击树节点的处理
function treeClick(event, treeId, treeNode) {
  $('#node_name').val(treeNode.name);
  $('#nodeUrl').val(treeNode.accessUrl);
  $('#nodeId').val(treeNode.id);
  $('#nodeShow')[0].checked = (treeNode.showMenu==1)
  if (!(treeNode.children && treeNode.children.length)&&!("" + treeNode.id).startWith('new_')){
    $('#trSetOperation').show();
  }else{
    $('#trSetOperation').hide();
  }
}
// 拖动节点前的处理
function beforeDrag(treeId, treeNodes) {
  for ( var i = 0, l = treeNodes.length; i < l; i++) {
    if (treeNodes[i].drag === false) {
      return false;
    }
  }
  return true;
}
// 放下节点前的处理
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
  return targetNode ? targetNode.drop !== false : true;
}
// 删除节点前的处理
function beforeRemove(treeId, treeNode) {
  menuTree.selectNode(treeNode);
  if (treeNode.getParentNode() == null) {
    alert("根结点不能删除！");
    return false;
  }
  return confirm("确认删除 菜单[" + treeNode.name + "]吗？");
}
// 删除节点时的处理
function onRemove(e, treeId, treeNode) {
  fetchDeleteMenu(treeNode);
}
// 获取删除掉的原来的那些id
function fetchDeleteMenu(node) {
  if (/^\d+$/.test(node.id)) {
    delMenus.push(node.id);
    if (node.children && node.children.length) {
      for ( var i = 0; i < node.children.length; i++) {
        fetchDeleteMenu(node.children[i]);
      }
    }
  }
}
function beforeRename(treeId, treeNode, newName) {
  if (newName.trim().length == 0) {
    alert("节点名称不能为空.");
    setTimeout(function() {
      menuTree.editName(treeNode)
    }, 10);
    return false;
  }
  return true;
}

function onRename(e, treeId, treeNode) {
}
// 显示添加节点的按钮
function addHoverDom(treeId, treeNode) {
  var sObj = $("#" + treeNode.tId + "_span");
  if (treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0)
    return;
  var addStr = "<span class='button add' id='addBtn_" + treeNode.id
      + "' title='add node' onfocus='this.blur();'></span>";
  sObj.after(addStr);
  var btn = $("#addBtn_" + treeNode.id);
  if (btn)
    btn.bind("click", function() {
      menuTree.addNodes(treeNode, {
        id : ('new_' + newCount),
        pId : treeNode.id,
        name : "菜单" + (newCount++),
        showMenu : 1
      });
      return false;
    });
}

function removeHoverDom(treeId, treeNode) {
  $("#addBtn_" + treeNode.id).unbind().remove();
}

// 初始化树
function initTree() {
  newCount = 1;
  delMenus.length = 0;
  $('#rightCtrl :input:not(:button)').val('');
  $.getJSON(basePath + "/menu/menuJson?t=" + (new Date().getTime()), function(data) {
    menuTree = $.fn.zTree.init($("#menuTree"), setting, data);
  });
}

// 把节点数据保存为要提交的菜单数据
function covertNodeJson(node) {
  var nodeJson = {
    name : node.name,
    url : node.accessUrl,
    id : node.id,
    show : node.showMenu
  };
  if (node.children && node.children.length > 0) {
    nodeJson.children = [];
    for ( var i = 0; i < node.children.length; i++) {
      var newNodeJson = {};
      nodeJson.children.push(covertNodeJson(node.children[i]))
    }
  }
  return nodeJson;
}

$(function() {
  initTree();// 加载树
   //保存
  $("#btSave").click(function() {
    var nodes = menuTree.getNodes();
    var nodesJson = [];
    for ( var i = 0; i < nodes.length; i++) {
      nodesJson.push(covertNodeJson(nodes[i]));
    }
    var jsonStr = JSON.stringify(nodesJson);
    var delMenusStr = JSON.stringify(delMenus);
    $.post(basePath + '/menu/saveByTree', {
      menus : jsonStr,
      delMenus : delMenusStr
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
  
  $('#btSetOperation').click(function(){
    $.dialog({
      id:'selectRoles',
      fixed:true,
      width:600,
      height:300,
      content: 'url:'+basePath +'/operation/setOperationForMenu?menuId=' + $('#nodeId').val(),
      init: function(){
        
      }
    });
  });

  $('#btAddRoot').click(function() {
    menuTree.addNodes(null, {
      id : ('new_' + newCount),
      pId : '0',
      name : "菜单" + (newCount++),
      showMenu : 1
    });
  });
  //设置某个结点隐藏，并设置其相应的所有子结点也隐藏
  function setNodeHide(node){
    node.showMenu = 0;
    if(node.children&&node.children.length){
      for(var i=0;i<node.children.length;i++){
        setNodeHide(node.children[i]);
      }
    }
  }
  //设置某个结点显示，并设置其相应的父结点也显示
  function setNodeShow(node){
    var pnode = node.getParentNode();
    if(pnode){
      pnode.showMenu = 1;
      setNodeShow(pnode);
    }
  }
  
  $('#nodeShow').click(function(){
    var id = $('#nodeId').val();
    var selectedNode = menuTree.getSelectedNodes()[0];
    if (id != "") {
      selectedNode.showMenu = $(this)[0].checked?1:0;
      //如果不显示则设置所有的子也不显示
      if(selectedNode.showMenu == 0){
        setNodeHide(selectedNode);
      }else{
        setNodeShow(selectedNode);
      }
      if(selectedNode.children&&selectedNode.children.length){
        
      }
      //如果设置显示，设置所有父都显示
      
    }
  });
  
  $('#node_name').blur(function() {
    var name = $(this).val().trim();
    $(this).val(name);
    var id = $('#nodeId').val();
    var selectedNode = menuTree.getSelectedNodes()[0];
    if (id != "" && name == "") {
      $('#msg').html("菜单名称不能为空！");
      $(this).val(selectedNode.name);
    } else if (id != "") {
      selectedNode.name = name;
      menuTree.updateNode(selectedNode,false);
    }
  });
  $('#nodeUrl').blur(function() {
    var url = $(this).val().trim();
    $(this).val(url);
    var id = $('#nodeId').val();
    var selectedNode = menuTree.getSelectedNodes()[0];
    if (id != "" && url != "") {
      selectedNode.accessUrl = url;
    }
  });
  //刷新树
  $('#btRefresh').click(initTree);
});