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
            <h1>部门管理<small>查询\添加\修改</small>
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

                                        <input type="hidden" name="parentId">

                                        <div class="form-group">
                                            <select class="form-control" name="name">
                                                <option value="">请选组</option>
                                                <option value="name">名字</option>
                                                <option value="num">编号</option>
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
                                <a href="${pageContext.request.contextPath}/depts/add" class="btn bg-maroon btn-flat">添加</a>
                                <button type="button" data-ope="delete" class="btn bg-olive btn-flat">删除</button>
                                <button type="button" data-ope="update" class="btn bg-blue btn-flat">编辑</button>
                            </div>
                            <table id="dept_table"></table>
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
        var $deptTable;
        var queryURL="${pageContext.request.contextPath}/depts/xx";
        $deptTable = $('#dept_table').bootstrapTable({
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
            pageSize:2,                     //每页显示的数量
            pageList: [5, 10, 20, 50, 100],  //设置每页显示的数量

            queryParams : function (params) {
                params.pageSize=params.limit;
                delete params.limit;

                var parentId = $("#searchForm input[name='parentId']").val();
                if(parentId){
                    params.parentId = parentId;
                }
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
                    sortable: true,
                    formatter : function(value,row,index){
                        if(row.childCount>0){
                            return "<a href='javascript:;' name='child' data-id='"+row.id+"'>"+value+"</a>";
                        }else{
                            return value;
                        }
                    }
                },{
                    field: 'num',
                    title:'编号'
                }
            ],
            onLoadSuccess: function () {
            },
            onLoadError: function () {
                alert("数据加载失败！");
            },
            responseHandler: function(res) {  //数据响应回来会触发该方法
                 //如果是server分页,数据比如按照如下格式
                 //{“rows”:数据,”total”:总的记录数}
                return {
                    "total": res.count,//总页数
                    "rows": res.data  //数据
                };
            }
        });


        $('#dept_table').on("click","a[name='child']",function(){
            var parentId = $(this).data("id");
            $("#searchForm input[name='parentId']").val(parentId);
            $deptTable.bootstrapTable('refresh');
        });

        $("#searchForm").on("submit",function(){
            $deptTable.bootstrapTable('refresh');
            return false;
        });

        $("#toolbar").on("click","button",function(){

            var $it = $(this);
            var ope = $it.data("ope");
            if(ope=="delete"){
                //获取选中的
                var idArray = $deptTable.bootstrapTable('getAllSelections');
                idArray = idArray.map(function(item,index){
                    return item.id;
                });
                // delete  /depts/1,2,3,4
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/depts/"+idArray.join(),
                    data:"_method=delete",
                    success:function(respData){
                        if(respData.code==200){
                            $deptTable.bootstrapTable('refresh');
                        }else{
                            alert(respData.msg);
                        }
                    }
                });
            }else if(ope=="update"){
                var idArray = $deptTable.bootstrapTable('getAllSelections');
                if(idArray.length==0){
                    alert("请选择一条记录");
                    return;
                }
                if(idArray.length>1){
                    alert("只能选择一条记录");
                    return;
                }

                window.location.href="${pageContext.request.contextPath}/depts/"+idArray[0].id+"/edit";
            }
        });
    });
</script>
</body>
</html>
