<!DOCTYPE html>
<html lang="zh">
<head>
    <#include "../../common/head.ftl"/>
    <#include "../../common/form.ftl"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<section class="content-header">
    <ol class="breadcrumb">
        <li><a href="javascript:void(0)" onclick="top.location.href='${ctx}/'"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">接入管理</a></li>
        <li><a href="${ctx}/api/park/view">停车场接入管理</a></li>
        <li class="active">车场接入</li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
                <div class="box box-default">
                    <div class="box-header with-border">
                        <h3 class="box-title">车场系统信息</h3>
                        <div class="pull-right">
                            <button class="btn btn-box-tool" onclick="window.history.go(-1)"><i class="fa fa-reply"></i> </button>
                        </div>
                    </div>
                    <form id="inputForm" class="form-horizontal" action="${ctx}/api/park/save" method="post">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">车场名称</label>
                                <div class="col-sm-4">
                                    <input type="text" id="name" name="name" class="form-control" placeholder="车场名称" required value="${parkSystem.name!}"/>
                                </div>
                                <label for="name" class="col-sm-2 control-label">车场编号</label>
                                <div class="col-sm-4">
                                    <input type="text" id="parkNo" name="parkNo"  class="form-control" placeholder="请输入车场编号" required value="${parkSystem.parkNo!}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="url" class="col-sm-2 control-label">URL</label>
                                <div class="col-sm-4">
                                    <input type="url" id="url" name="url" placeholder="请输入URL" class="form-control" value="${parkSystem.url!}"/>
                                </div>
                                <label for="parkBrand" class="col-sm-2 control-label">品牌</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="parkBrand">
                                        <#list parkBrandList as brand>
                                            <option value="${brand}">${brand}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="remark" class="col-sm-2 control-label">备注:</label>
                                <div class="col-xs-10">
                                    <textarea id="remark" name="remark" class="form-control">${parkSystem.remark!}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary pull-right"><i class="fa fa-check-circle"></i> 提交</button>
                            <button type="reset" class="btn btn-default">重置</button>
                            <input type="hidden" name="id" class="form-control" value="${parkSystem.id!}"/>
                        </div>
                    </form>
                </div>
        </div>
    </div>
</section>
<script>
    let viewModel = {
    };
    $(function(){
        $('#inputForm').ajaxForm({
            dataType : 'json',
            success : function(data) {
                if (data.flag) {
                    layer.alert(data.content, function(index) {
                        //top.hideMyModal();
                        layer.close(index);
                        let url = "${ctx}/system/user/view";
                        if ($('input[name=pname]').val() !== '') {
                            url += '?groupId=' + $('#groupId').val();
                        }
                        window.location.href = url;
                    });
                } else {
                    layer.alert(data.content);
                }
            }
        });
        ko.applyBindings(viewModel);
    });
</script>
</body>
</html>
