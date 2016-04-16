<%@page contentType="text/html;charset=utf-8"%>
<%@include file="/taglib.jsp"%>

<html version="-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title>CA 证书管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <META http-equiv="x-ua-compatible" content="ie=EmulateIE7" />
    <script type="text/javascript">
        function init() {
            var svgflag=false;
            if(isSVG()){
                svgflag=true;
            }
            var a1 = document.getElementById("a1");
            if(svgflag==true){
                a1.innerHTML = " <iframe src='../../servlet/SvgServlet.svg' width='1366' height='768'></iframe>";
//                location.href="../../servlet/SvgServlet.svg";
            }else {
                a1.innerHTML ="你没有安装svg插件，请点击下面链接安装<br/><a href='../../servlet/DownloadFileServlet'>下载: Adobe SVG Viewer 3.0</a>";
            }

        }

        function isSVG(){ return ((window.ActiveXObject)?!!(new ActiveXObject("Adobe.SVGCtl")):true); }


    </script>
</head>

<body onload="init()" bottommargin="0" leftmargin="0" topmargin="0" scroll="no" rightmargin="0">
    <div id="a1" style="overflow:scroll;height:100%;width:100%">
        你没有安装svg插件，请点击下面链接安装<br/>
        <a href='../../servlet/DownloadFileServlet'>下载: Adobe SVG Viewer 3.0</a>
    </div>

    <%--<div id="a2" style="overflow:scroll;height:100%;width:100%">--%>
        <%--<iframe src='../../servlet/SvgServlet.svg' width='1366' height='768'>--%>
        <%--</iframe>--%>
    <%--</div>--%>

</body>

</html>