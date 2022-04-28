<#assign ctx=context.contextPath/>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <link rel="icon" href="${ctx}/res/img/favicon.ico">
  <title>东龙优易系统接口管理平台</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${ctx}/res/lib/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="${ctx}/res/lib/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="${ctx}/res/lib/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="${ctx}/res/adminLTE/dist/css/AdminLTE.css">
  <link rel="stylesheet" href="${ctx}/res/adminLTE/dist/css/skins/_all-skins.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <#--<a href="#" class="logo">
      <span class="logo-mini">东龙优易</span>
      <span class="logo-lg"><b>Sprout</b>管理平台</span>
    </a>-->
    <a href="#" class="logo">
      <span class="logo-mini"><img src="${ctx}/res/img/dept-logo.png"></span>
      <span class="logo-lg"><img src="${ctx}/res/img/dept-logo.png">统一接口管理平台</span>
    </a>
    <nav class="navbar navbar-static-top">
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
      </div>
    </nav>
  </header>
  <aside class="main-sidebar">
    <section class="sidebar">
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">导航栏</li>
        <li class="active treeview">
          <a href="#">
            <i class="fa fa-clone"></i>
            <span>接口接入管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#" class="menuBtn" url="${ctx}/api/park/view"><i class="fa fa-car"></i> 停车场接入管理</a></li>
          </ul>
        </li>
      </ul>
    </section>
  </aside>
  <div class="content-wrapper">
    <iframe src="${ctx}/api/park/view" width="100%" height="800px"  id="content" name="myframe" frameborder="no" border="0"></iframe>
  </div>
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0.0
    </div>
    <strong>Copyright &copy; 2021 <a href="https://zdlonghu.com">东龙优易</a>.</strong>
  </footer>

  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<script src="${ctx}/res/lib/jquery/dist/jquery.min.js"></script>
<script src="${ctx}/res/lib/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/res/adminLTE/dist/js/adminlte.min.js"></script>
<script src="${ctx}/res/layer/layer.js"></script>
<script src="${ctx}/res/lib/knockout/knockout-3.5.0.js"></script>
<script src="${ctx}/res/lib/underscore/underscore-min.js"></script>
<script>
  let viewModel = {
    todoList: ko.observableArray([]),
    noticeList: ko.observableArray([])
  }
  $(function(){
    $(".menuBtn").click(function(){
      let $this = $(this);
      $(".sidebar-menu").find(".active").each(function(idx,el) {
        $(el).removeClass("active");
      });
      $this.parent().addClass("active");
      $this.parent().parent().parent().addClass("active");
      document.getElementById("content").src = $(this).attr("url");
    });
    $(".menuBtn").eq(0).click();
    ko.applyBindings(viewModel);
  });

  let myModel = {};

  function showMyModel(url, title, width, height, callBack) {
    myModel.id = layer.open({
      //skin: 'layui-layer-lan',
      type: 2,
      title: title,
      //shadeClose: false,
      shade: 0.5,
      area: [width, height],
      content: url
    });
    myModel.callBack = callBack;
  }

  function hideMyModal() {
    if (myModel.callBack != null) {
      myModel.callBack.apply(this, arguments);
    }
    layer.close(myModel.id);
  }

</script>
</body>
</html>
