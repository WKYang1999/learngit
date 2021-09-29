<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>

    <%@include file="/WEB-INF/jsp/common/base.jsp"%>

    <!--引入table插件资源-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/table/bootstrap-table.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/table/bootstrap-table.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/table/bootstrap-table-zh-CN.js"></script>

    <!--引入ztree-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/ztree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/ztree/js/jquery.ztree.excheck.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/ztree/css/zTreeStyle/zTreeStyle.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!--头部-->
    <%@include file="/WEB-INF/jsp/common/top.jsp"%>


    <!--菜单-->
    <%@include file="/WEB-INF/jsp/common/left.jsp"%>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content-header">
            <h1>角色管理<small>查询\添加\修改</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Layout</a></li>
                <li class="active">Fixed</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-success">

                        <div class="box-body">

                            <!-- 添加搜索条件 -->
                            <div class="box-body">
                                <div class="row">
                                    <form id="searchForm" action="" onsubmit="return false;" class="form-inline">
                                        <div class="form-group">
                                            <select class="form-control" name="name">
                                                <option value="">请选组</option>
                                                <option value="name">名字</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="keyWords" class="form-control" placeholder="关键字">
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn bg-olive btn-flat">查询</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div id="toolbar">
                                <a href="${pageContext.request.contextPath}/roles/add" class="btn bg-maroon btn-flat">添加</a>
                                <button type="button" data-ope="delete" class="btn bg-olive btn-flat">删除</button>
                                <button type="button" data-ope="update" class="btn bg-blue btn-flat">编辑</button>
                            </div>
                            <table id="role_table"></table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!--底部-->
    <%@include file="/WEB-INF/jsp/common/foot.jsp"%>

    <!--设置-->
    <%@include file="/WEB-INF/jsp/common/setting.jsp"%>
</div>

<script type="text/javascript">

    var menuId=[];
    var $treeObj;
    $(function(){
        var $roleTable;
        var queryURL="${pageContext.request.contextPath}/roles/xx";
        $roleTable = $('#role_table').bootstrapTable({
            url: queryURL,
            method: 'GET',
            // 绑定ID，不显示
            uniqueId: 'id',
            //是否显示行间隔色
            striped: true,
           //是否使用缓存，默认为true
            cache: false,
            //是否启用排序
            sortable: true,
            //排序方式
            sortOrder: "asc",
            //分页方式：client客户端分页，server服务端分页（*）
            sidePagination: "server",
            undefinedText: '--',

            // 工具条
            toolbar: '#toolbar',
            pagination: true,                //是否显示分页

            pageNumber:1,                   //初始化加载第一页，默认第一页,并记录
            pageSize:10,                     //每页显示的数量
            pageList: [10, 20, 50, 100],  //设置每页显示的数量

            queryParams : function (params) {
                params.pageSize=params.limit;
                delete params.limit;
                params.pageNum=params.offset/params.pageSize+1;

                delete params.offset;

                //拼接上查询条件
                var paramName = $("#searchForm select[name='name']").val();
                var paramValue = $("#searchForm input[name='keyWords']").val();

                if(paramName && paramValue){
                    params[paramName]=paramValue;
                }
                return params;
            },
            columns: [
                {
                    checkbox: true
                },{
                    field: 'name',
                    title:'名字',
                    sortable: true
                },{
                    field: 'xx',
                    title:'操作',
                    formatter:function(value,row,index){
                        return '<button type="button" name="btn_auth" data-role="'+row.id+'" class="btn btn-success btn-flat btn-xs">授权</button>';
                    }
                }
            ],
            onLoadSuccess: function () {
            },
            onLoadError: function () {
                alert("数据加载失败！");
            },
            responseHandler: function(res) {
                return {
                    "total": res.count,//总页数
                    "rows": res.data  //数据
                };
            }
        });

        $("#searchForm").on("submit",function(){
            $roleTable.bootstrapTable('refresh');
            return false;
        });

        $("#toolbar").on("click","button",function(){
            var $it = $(this);
            var ope = $it.data("ope");
            if(ope=="delete"){
                //获取选中的
                var idArray = $roleTable.bootstrapTable('getAllSelections');
                idArray = idArray.map(function(item,index){
                    return item.id;
                });
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/roles/"+idArray.join(),
                    data:"_method=delete",
                    success:function(respData){
                        if(respData.code==200){
                            $roleTable.bootstrapTable('refresh');
                        }else{
                            alert(respData.msg);
                        }
                    }
                });
            }else if(ope=="update"){
                var idArray = $roleTable.bootstrapTable('getAllSelections');
                if(idArray.length==0){
                    alert("请选择一条记录");
                    return;
                }
                if(idArray.length>1){
                    alert("只能选择一条记录");
                    return;
                }
                window.location.href="${pageContext.request.contextPath}/roles/"+idArray[0].id+"/edit";
            }
        });

        $('#role_table').on("click","button[name='btn_auth']",function () {
            var roleId = $(this).data("role");
            $('#modal_menu').data("role",roleId);

            //获取roleId的所有权限
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/roles/"+roleId+"/auth",
                success:function(respData){
                    menuId=respData.data;
                    console.log(menuId);
                }
            })

            //打开一个对话框
            $('#modal_menu').modal("show");

            //ztree配置信息
            var setting = {
                async : {
                    enable : true,
                    type : "get",
                    url : "${pageContext.request.contextPath}/menus", //数据地址
                    autoParam : [ "id=parentId" ],
                    dataFilter : filter
                },
                view: {
                    fontCss : {"color":"red"}
                },
                check: {
                    enable: true,
                    chkboxType: {"Y":"ps","N":"s"}
                },
                callback: {
                    onNodeCreated: zTreeOnNodeCreated  //当节点创建的时候触发
                }
            };
            function filter(treeId, parentNode, childNodes) {
                var data = childNodes.data;
                $.each(data, function(index, item) {
                    item.isParent = !item.leaf;  //isParent代表是否父节点
                });
                return data;
            }
            //创建ztree
            $treeObj = $.fn.zTree.init($("#tree_menu"), setting);
        });

        $("#modal_menu button[id='save']").on("click",function(){
           var roleId =  $(this).closest("div[id='modal_menu']").data("role");

            //选中的菜单id
            var menuIdArray = $treeObj.getCheckedNodes(true);

            menuIdArray = menuIdArray.map(function(item,index){
                return item.id;
            });

            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/roles/"+roleId+"/auth",
                data:"_method=put&menuId="+menuIdArray.join("&menuId="),
                success:function(respData){
                    if(respData.code==200){
                        //关闭modal
                        $('#modal_menu').modal("hide");
                    }else{
                        alert(respData.msg);
                    }
                }
            });
        });
    });


    //节点创建的时候触发
    function zTreeOnNodeCreated(event, treeId, treeNode) {

        //menuId 代表当前角色的所属权限
        $.each(menuId,function(index,value){
            if(treeNode.id==value){
                //把这个节点勾选
                $treeObj.checkNode(treeNode,true,false);
            }
        });
    };
</script>

<div class="modal fade" id="modal_menu" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">权限分配</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <ul id="tree_menu" class="ztree"></ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="save" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
