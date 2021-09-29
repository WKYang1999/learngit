<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="neu" uri="http://java.sun.com/jsp/jstl/neu" %>
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
            <h1>UP审批<small>查询\添加\修改</small>
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
                                                <option value="applyName">名字</option>
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
                                <button type="button" data-ope="delete" class="btn bg-maroon btn-flat">不通过</button>
                                <button type="button" data-ope="update" class="btn bg-olive btn-flat">通过</button>
                            </div>
                            <table id="apply_table"></table>
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
    $(function(){
        var $applyTable;
        var queryURL="${pageContext.request.contextPath}/applys/xx";
        $applyTable = $('#apply_table').bootstrapTable({
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
                    field: 'userName',
                    title:'申请人'
                },{
                    field: 'roleName',
                    title:'当前角色'
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
            $applyTable.bootstrapTable('refresh');
            return false;
        });

        $("#toolbar").on("click","button",function(){

            var $it = $(this);
            var ope = $it.data("ope");
            if(ope=="delete"){
                //获取选中的
                var idArray = $applyTable.bootstrapTable('getAllSelections');
                if(idArray.length==0){
                    alert("至少选择一个");
                    return;
                }
                idArray = idArray.map(function(item,index){
                    return item.id;
                });
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/applys/"+idArray.join(),
                    data:"_method=delete",
                    success:function(respData){
                        if(respData.code==200){
                            alert(respData.msg);
                            $applyTable.bootstrapTable('refresh');
                        }else{
                            alert(respData.msg);
                        }
                    }
                });
            }else if(ope=="update"){
                //获取选中的
                var idArray = $applyTable.bootstrapTable('getAllSelections');
                if(idArray.length==0){
                    alert("至少选择一个");
                    return;
                }
                idArray = idArray.map(function(item,index){
                    return item.id;
                });
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/applys/delete/"+idArray.join(),
                    data:"_method=delete",
                    success:function(respData){
                        if(respData.code==200){
                            alert(respData.msg);
                            $applyTable.bootstrapTable('refresh');
                        }else{
                            alert(respData.msg);
                        }
                    }
                });
            }
        });
    });
</script>
</body>
</html>
