package com.hzjava.monitorcenter.svgmap;

import com.hzjava.monitorcenter.domain.EquipmentLine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 12-12-25
 * Time: 下午2:31
 * To change this template use File | Settings | File Templates.
 */

public class SvgServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Refresh","60");
        MyEquipmentDao equipmentDao = new MyEquipmentDao();
        ArrayList<EquipmentVo> elist = equipmentDao.findAllEquipmentVos();
        ArrayList<EquipmentLine> linelist = equipmentDao.findAllEquipmentLines();
        response.setContentType("text/svg+xml");
        PrintWriter out = response.getWriter();
        out.println("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>\n" +
                "<svg width=\"1366\" height=\"768\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" onload=\"init(evt)\" onmousedown=\"downline(evt)\" onmousemove=\"moveline(evt)\" onmouseup=\"upline()\">\n" +
                "\t<defs>\n" +
                "\t\t<linearGradient id=\"linearGradient\">\n" +
                "\t\t\t<stop offset=\"10%\" stop-color=\"rgb(255,0,0)\" stop-opacity=\"1\" />\n" +
                "\t\t\t<stop offset=\"50%\" stop-color=\"rgb(255,255,0)\" stop-opacity=\"1\" />\n" +
                "\t\t\t<stop offset=\"90%\" stop-color=\"rgb(255,0,0)\" stop-opacity=\"1\" />\n" +
                "\t\t</linearGradient>\n" +
                "\t\t<linearGradient id=\"linearGradient1\">\n" +
                "\t\t\t<stop offset=\"10%\" stop-color=\"rgb(255,0,0)\" stop-opacity=\"1\" />\n" +
                "\t\t\t<stop offset=\"50%\" stop-color=\"rgb(255,255,0)\" stop-opacity=\"1\" />\n" +
                "\t\t\t<stop offset=\"90%\" stop-color=\"rgb(255,0,0)\" stop-opacity=\"1\" />\n" +
                "\t\t</linearGradient>\n" +
                "\t</defs>\n" +
                "\t<script type=\"text/javascript\">\n" +
                "var tsline=2;" +
                "var domnmove=2;" +
                "var lineid=0;\n"+
                "var objline=null;\n" +
                "\t\t\tvar svgdoc=null;\n" +
                "\t\t\tvar lmove=2;\n" +
                "\t\t\tvar isMove=false;\n" +
                "\t\t\tvar xt1=0;\n" +
                "\t\t\tvar yt1=0;\n" +
                "\t\t\tfunction init(){\n" +
                "\t\tsvgdoc=evt.target.ownerDocument;\n" +
                "\t\tisMove=false;\n" +
                "\t\t\n" +
                "\t\t\t}\n" +

                "function setlmovenull() {\n" +
//                "\t\t\t\tlmove=2;\n" +
                "\t\t\t}\n"+

                "function downline(evt){\n" +
                "\t\t\t\tif(lmove!=2) {\n" +
                "\t\t\t\t\tlmove=1;\n" +
                "\t\t\t\t\tvar group=document.getElementById(\"g5\");\n" +
                "\t\t\t\t\tobjline =document.createElement(\"line\");\n" +
                "objline.setAttribute(\"id\",\"vvvvv\"+lineid);" +
                "\t \t\t\t\tobjline.setAttribute(\"x1\",evt.clientX);\n" +
                "\t\t\t    objline.setAttribute(\"y1\",evt.clientY);\n" +
                "\t\t\t    objline.setAttribute(\"x2\",evt.clientX);\n" +
                "\t\t\t    objline.setAttribute(\"y2\",evt.clientY);\n" +
                "\t\t\t    objline.setAttribute(\"style\",\"stroke:rgb(99,99,99);stroke-width:1\");\n" +
                "objline.setAttribute(\"onmousedown\", \"setlmovenull()\");\n" +
                "objline.setAttribute(\"onmousemove\", \"tslxx(evt)\");\n" +
                "objline.setAttribute(\"onmouseout\", \"tslout()\");"+
                "\t\t    \tobjline.setAttribute(\"onclick\", \"javascript:if(tsline==0){" +
                    "var conf = confirm('删除此连线？');" +
                    "if(conf==true) {" +
                        "var el = document.getElementById('vvvvv\"+lineid+\"');" +
                        "var lineatt=el.getAttribute('x1')+'_'+el.getAttribute('y1')+'_'+el.getAttribute('x2')+'_'+el.getAttribute('y2');"+
                        "var httpRequest;" +
                        "if(window.XMLHttpRequest){ " +
                        "httpRequest=new XMLHttpRequest(); " +
                        "}else if(window.ActiveXObject){   " +
                        "httpRequest=new ActiveXObject('Microsoft.XMLHTTP');" +
                        "}else{" +
                        "alert('浏览器版本太低，先升级再访问');" +
                        "} " +
                        "httpRequest.open('GET','/cms/servlet/SvgDelLineServlet?lineatt='+lineatt,true);" +
                        "httpRequest.onreadystatechange=function(){" +
                        "   if(httpRequest.readyState==4){ " +
                        "                    if(httpRequest.status==200){" +
//                        "                      alert('获取响应数据');" +
                        "                    }" +
                        "                " +
                        "                }" +
                        "           " +
                        "           };" +
                        "httpRequest.send(null);"+
                        "el.parentNode.removeChild(el);}" +
                    "tslout();}\"" +
                ");\n"+
                "\t\t\t    group.appendChild(objline);\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "function moveline(evt){\n" +
                "\t\t\t\tif(lmove==1) {\n" +
                "\t\t\t\t\t\tobjline.setAttribute(\"x2\",evt.clientX);\n" +
                "\t\t    \t\tobjline.setAttribute(\"y2\",evt.clientY);\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +

                "function upline() {\n" +
                "if(lmove==1){\n"+
                "lmove=0;\n" +
                "\t\t\t\tvar conf = confirm(\"是否保存此线\")\n" +
                "\t\t  \tif(conf==true) {\n" +
                "lineid++;\n"+
                "var lineatt=objline.getAttribute(\"x1\")+\"_\"+objline.getAttribute(\"y1\")+\"_\"+objline.getAttribute(\"x2\")+\"_\"+objline.getAttribute(\"y2\");\n"+
                "var httpRequest;\n" +
                "if(window.XMLHttpRequest){ \n" +
                "httpRequest=new XMLHttpRequest(); \n" +
                "}else if(window.ActiveXObject){   \n" +
                "httpRequest=new ActiveXObject(\"Microsoft.XMLHTTP\");\n" +
                "}else{\n" +
                "alert(\"浏览器版本太低，先升级再访问\");\n" +
                "} \n" +
                "httpRequest.open(\"GET\",\"/cms/servlet/SvgLineServlet?lineatt=\"+lineatt,true);\n" +
                "httpRequest.onreadystatechange=function(){\n" +
                "   if(httpRequest.readyState==4){ \n" +
                "                    if(httpRequest.status==200){\n" +
//                "                      alert(\"获取响应数据\");\n" +
                "                    }\n" +
                "                \n" +
                "                }\n" +
                "           \n" +
                "           };\n" +
                "httpRequest.send(null);\n"+
                "\t\t  \t}else{\n" +
                "\t\t  \t\tobjline.parentNode.removeChild(objline);\n" +
                "\t\t  \t\tobjline=null;\n" +
                "\t\t  \t}\n" +
                "}\n"+
                "\t\t\tsvgdoc=null;\n" +
                "\t\t\tisMove=false;\n" +
                "\t\t\txt1=0;\n" +
                "\t\t\tyt1=0;\n" +
//                "lmove=2;\n" +
//                "document.getElementById(\"recttjlx\").setAttribute(\"fill\", \"#FA5C46\");" +
                "\t\t\t}\n"+

                "function setxy(xt2,yt2,obj){\n" +
                "\t\tobj.setAttribute(\"x\",parseInt(obj.getAttribute(\"x\"))+xt2-xt1);\n" +
                "\t\tobj.setAttribute(\"y\",parseInt(obj.getAttribute(\"y\"))+yt2-yt1);\n" +
                "\t\t\t}\n");
        for (EquipmentLine l : linelist)  {
            out.println("function "+"clickline"+l.getId()+"(evt) {\n" +
                    "if(tsline==0){" +
                    "var conf = confirm(\"删除此连线？\")\n" +
                    "\t\t\t  \tif(conf==true) {\n" +
                    "\t\t\t\tvar el = document.getElementById(\"line"+l.getId()+"\");\n" +
                    "\t\t\t\t\t\tel.parentNode.removeChild(el);\n" +
                    "var httpRequest;\n" +
                    "if(window.XMLHttpRequest){ \n" +
                    "httpRequest=new XMLHttpRequest(); \n" +
                    "}else if(window.ActiveXObject){   \n" +
                    "httpRequest=new ActiveXObject(\"Microsoft.XMLHTTP\");\n" +
                    "}else{\n" +
                    "alert(\"浏览器版本太低，先升级再访问\");\n" +
                    "} \n" +
                    "var lineatt="+l.getId()+"+\"\"\n"+
                    "httpRequest.open(\"GET\",\"/cms/servlet/SvgLineServlet?lineatt=\"+lineatt,true);\n" +
                    "httpRequest.onreadystatechange=function(){\n" +
                    "   if(httpRequest.readyState==4){ \n" +
                    "                    if(httpRequest.status==200){\n" +
//                    "                      alert(\"删除成功\");\n" +
                    "                    }\n" +
                    "                \n" +
                    "                }\n" +
                    "           \n" +
                    "           };\n" +
                    "httpRequest.send(null);\n"+
                    "\t\t\t  \t}\n" +
                    "tslout();\n"+
                    "\t\t\t}\n" +
                    "}");
        }

        for(EquipmentVo e:elist) {
            out.println("\t\t\tfunction downtext"+e.getId()+"(evt) {\n" +
                    "if(domnmove==0){" +
//                    "lmove=2;\n"+
                    "\t\tisMove=true;\n" +
                    "\t\txt1=parseInt(evt.clientX);\n" +
                    "\t\tyt1=parseInt(evt.clientY);\n" +
                    "\t\t\n" +
                    "\t\t\tvar obj = document.getElementById(\"ea"+e.getId()+"\");\n" +
                    "var opa=obj.parentNode;\n" +
                    "\t\t\topa.removeChild(obj);\n" +
                    "\t\t\topa.appendChild(obj);" +
                    "\t\t\tobj = document.getElementById(\"eb"+e.getId()+"\");\n" +
                    "opa=obj.parentNode;\n" +
                    "\t\t\topa.removeChild(obj);\n" +
                    "\t\t\topa.appendChild(obj);" +
                    "\t\t\tobj = document.getElementById(\"ec"+e.getId()+"\");\n" +
                    "opa=obj.parentNode;\n" +
                    "\t\t\topa.removeChild(obj);\n" +
                    "\t\t\topa.appendChild(obj);" +
                    "\t\t\t}\n" +
                    "}\n" +

                    "function me"+e.getId()+"(evt){\n" +
//                    "tslxx2(evt);\n" +
                    "\t\tif(isMove==true){\n" +
                    "\t\t\tvar xt2 = evt.clientX;\n" +
                    "\t\t\tvar yt2 = evt.clientY;\n" +
                    "\t\t\tvar obj = document.getElementById(\"ea"+e.getId()+"\");\n" +
                    "\t\t\tsetxy(xt2,yt2,obj);\n" +
                    "\t\t\tobj = document.getElementById(\"eb"+e.getId()+"\");\n" +
                    "\t\t\tsetxy(xt2,yt2,obj);\n" +
                    "\t\t\tobj = document.getElementById(\"ec"+e.getId()+"\");\n" +
                    "\t\t\tsetxy(xt2,yt2,obj);\n" +
                    "\t\t\txt1=xt2;\n" +
                    "\t\t\tyt1=yt2;\n" +
                    "\t\t}\n" +
                    "}\n" +
                    "\n" +
                    "function upe"+e.getId()+"(){\n" +
//                    "lmove=0;\n"+
                    "isMove=false;\n" +
                    "var obj = document.getElementById(\"ec"+e.getId()+"\");\n" +
                    "var id="+e.getId()+";\n"+
                    "var equx = obj.getAttribute(\"x\");\n" +
                    "var equy = obj.getAttribute(\"y\");\n" +
                    "var stt=id+'_'+equx+'_'+equy;\n"+
                    "var httpRequest;\n" +
                    "if(window.XMLHttpRequest){ \n" +
                    "httpRequest=new XMLHttpRequest(); \n" +
                    "}else if(window.ActiveXObject){   \n" +
                    "httpRequest=new ActiveXObject(\"Microsoft.XMLHTTP\");\n" +
                    "}else{\n" +
                    "alert(\"浏览器版本太低，先升级再访问\");\n" +
                    "} \n" +
                    "httpRequest.open(\"GET\",\"/cms/servlet/SvgJspServlet?stt=\"+stt,true);\n" +
                    "httpRequest.onreadystatechange=function(){\n" +
                    "   if(httpRequest.readyState==4){ \n" +
                    "                    if(httpRequest.status==200){\n" +
//                    "                      alert(httpRequest.responseText);\n" +
                    "                    }\n" +
                    "                \n" +
                    "                }\n" +
                    "           \n" +
                    "           };\n" +
                    "httpRequest.send(null);\n" +
                    "}\n" +
                    
                    "function pingIp"+e.getId()+"() {" +
                    "var pingip=\""+e.getIp()+"_"+e.getNetStation()+"\"\n"+
                    "var httpRequest;\n" +
                    "if(window.XMLHttpRequest){ \n" +
                    "httpRequest=new XMLHttpRequest(); \n" +
                    "}else if(window.ActiveXObject){   \n" +
                    "httpRequest=new ActiveXObject(\"Microsoft.XMLHTTP\");\n" +
                    "}else{\n" +
                    "alert(\"浏览器版本太低，先升级再访问\");\n" +
                    "} \n" +
                    "httpRequest.open(\"GET\",\"/cms/servlet/AjaxPingIpServlet?pingip=\"+pingip,true);\n" +
                    "httpRequest.onreadystatechange=function(){\n" +
                    "   if(httpRequest.readyState==4){ \n" +
                    "                    if(httpRequest.status==200){\n" +
                    "var imaf = httpRequest.responseText;\n" +
//                    "alert(imaf);"+
                    "\t\t\tvar objim = document.getElementById(\"eb"+e.getId()+"\");\n" +
                    "objim.setAttribute(\"xlink:href\", \"../img/icon/\"+imaf);" +
                    "setTimeout('pingIp"+e.getId()+"()',15000); " +
                    "                    }\n" +
                    "                \n" +
                    "                }\n" +
                    "           \n" +
                    "           };\n" +
                    "httpRequest.send(null);\n"+
                    "}" +
                    "setTimeout('pingIp"+e.getId()+"()',1000); ");
        }

        out.println("function tslxx(evt) {\n" +
                "if(tsline==0){" +
                "\t\t\tvar tsct = document.getElementById(\"textscts\");\t\n" +
                "\t\t\ttsct.setAttribute(\"x\", evt.clientX); \n" +
                "\t\t\ttsct.setAttribute(\"y\", evt.clientY); \t\n" +
                "\t\t}\n" +
                "}" +
                "\t\t\t\n" +
                "\t\tfunction tslout() {\n" +
                "\t\t\tvar tsct = document.getElementById(\"textscts\");\t\n" +
                "\t\t\ttsct.setAttribute(\"x\", \"-10\"); \n" +
                "\t\t\ttsct.setAttribute(\"y\", \"-10\"); \t\n" +
                "\t\t}\n" +
//                "function tslxx2(evt) {\n" +
//                "\t\t\tvar tsct = document.getElementById(\"texttptdts\");\t\n" +
//                "\t\t\ttsct.setAttribute(\"x\", evt.clientX); \n" +
//                "\t\t\ttsct.setAttribute(\"y\", evt.clientY); \t\n" +
//                "\t\t}\n" +
//                "\t\t\t\n" +
//                "\t\tfunction tslout2() {\n" +
//                "\t\t\tvar tsct = document.getElementById(\"texttptdts\");\t\n" +
//                "\t\t\ttsct.setAttribute(\"x\", \"-40\"); \n" +
//                "\t\t\ttsct.setAttribute(\"y\", \"-10\"); \t\n" +
//                "\t\t}\n" +

                "function addline() {\n" +
                "\t\t\tlmove=0;\n" +
                "\t\t\tdocument.getElementById(\"recttjlx\").setAttribute(\"xlink:href\", \"../img/other/huax2.png\");\n" +
                "\t\t\tdomnmove=2;\n" +
                "\t\t\tdocument.getElementById(\"recttdsb\").setAttribute(\"xlink:href\", \"../img/other/tuod.png\");\n" +
                "\t\t\ttsline=2;\n" +
                "\t\t\tdocument.getElementById(\"rectsclx\").setAttribute(\"xlink:href\", \"../img/other/shanx.png\");\n" +
                "\t\t}\n" +
                "\t\t\n" +
                "\t\tfunction tdequ() {\n" +
                "\t\t\tdomnmove=0\n" +
                "\t\t\tdocument.getElementById(\"recttdsb\").setAttribute(\"xlink:href\", \"../img/other/tuod2.png\");\n" +
                "\t\t\tlmove=2;\n" +
                "\t\t\tdocument.getElementById(\"recttjlx\").setAttribute(\"xlink:href\", \"../img/other/huax.png\");\n" +
                "\t\t\ttsline=2;\n" +
                "\t\t\tdocument.getElementById(\"rectsclx\").setAttribute(\"xlink:href\", \"../img/other/shanx.png\");\n" +
                "\t\t}\n" +
                "\t\t\n" +
                "\t\tfunction delline() {\n" +
                "\t\t\ttsline=0;\n" +
                "\t\t\tdocument.getElementById(\"rectsclx\").setAttribute(\"xlink:href\", \"../img/other/shanx2.png\");\n" +
                "\t\t\tlmove=2;\n" +
                "\t\t\tdocument.getElementById(\"recttjlx\").setAttribute(\"xlink:href\", \"../img/other/huax.png\");\n" +
                "\t\t\tdomnmove=2;\n" +
                "\t\t\tdocument.getElementById(\"recttdsb\").setAttribute(\"xlink:href\", \"../img/other/tuod.png\");\n" +
                "\t\t}");

        out.println("</script>");
        out.println("<rect id='BackDrop' x='-10%' y='-10%' width='110%' height='110%'\n" +
                "fill='none' pointer-events='all' />\n" +
//                "<text id=\"texttptdts\" x=\"-40\" y=\"-10\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\" baseline-shift=\"baseline\" transform=\"matrix(1,0,0,1,-15,0)\">\n" +
//                "\t可拖动</text>\n" +
                "<text id=\"textscts\" x=\"-10\" y=\"-10\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\" baseline-shift=\"baseline\">\n" +
                "\t删除此线</text>\n");
//        out.println("<image id=\"ima3\" xlink:href=\"../img/equ/3002.PNG\" x=\"54\" y=\"370\" height=\"45px\" width=\"50px\" transform=\"matrix(1, 0, 0, 1, 1, -20)\" />\n" +
//                "\t<text x=\"37\" y=\"414\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\">\n" +
//                "\t笔记本终端</text>\n" +
//                "\t<image id=\"ima4\" xlink:href=\"../img/equ/3003.PNG\" x=\"31\" y=\"439\" width=\"35px\" height=\"50px\" transform=\"matrix(1, 0, 0, 1, 1, -20)\"/>\n" +
//                "\t<text x=\"29\" y=\"482\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\" baseline-shift=\"baseline\">\n" +
//                "\tPDA终端</text>\n" +
//                "\t<text x=\"153\" y=\"447\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\" baseline-shift=\"baseline\">\n" +
//                "\tCMET</text>");
        for(EquipmentVo e:elist){
//            String[][] params = new String[][] {
//                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
//                    {"Command","ping"},
//                    { "deviceip", e.getIp() }
//            };
//            String ipflag = Test.callService(params).getData();
//            String im = "off.gif";
//            if("true".equals(ipflag)){
//                im = "ok.png";
//            }
            String im = "off.gif";

            String ss ="<image onmousedown=\"downtext"+e.getId()+"(evt)\" onmousemove=\"me"+e.getId()+"(evt)\" onmouseup=\"upe"+e.getId()+"()\" id=\"ea"+e.getId()+"\" xlink:href=\"../img/equ/"+e.getImag()+".PNG\" x=\""+e.getEx()+"\" y=\""+e.getEy()+"\" height=\"50px\" width=\"45px\" transform=\"matrix(1,0,0,1,1,-55)\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns=\"http://www.w3.org/2000/svg\" />\n" +
                    "<image onmousedown=\"downtext"+e.getId()+"(evt)\" onmousemove=\"me"+e.getId()+"(evt)\" onmouseup=\"upe"+e.getId()+"()\" id=\"eb"+e.getId()+"\" xlink:href=\"../img/icon/"+im+"\" x=\""+e.getEx()+"\" y=\""+e.getEy()+"\" height=\"16px\" width=\"16px\" transform=\"matrix(1,0,0,1,45,-55)\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns=\"http://www.w3.org/2000/svg\" />\n"+
                    "<text onmousedown=\"downtext"+e.getId()+"(evt)\" onmousemove=\"me"+e.getId()+"(evt)\" onmouseup=\"upe"+e.getId()+"()\" id=\"ec"+e.getId()+"\" x=\""+e.getEx()+"\" y=\""+e.getEy()+"\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\" baseline-shift=\"baseline\">\n" +
                    e.getName()+"</text>\n";
            out.println(ss);
        }

        for (EquipmentLine l : linelist){
            String ll = "<line onmousemove=\"tslxx(evt)\" onmouseout=\"tslout()\" onmousedown=\"setlmovenull()\" onclick=\"clickline"+l.getId()+"(evt)\" id=\"line"+l.getId()+"\" x1=\""+l.getX1()+"\" y1=\""+l.getY1()+"\" x2=\""+l.getX2()+"\" y2=\""+l.getY2()+"\" style=\"stroke:rgb(99,99,99);stroke-width:1\" xmlns=\"http://www.w3.org/2000/svg\" />\n";
            out.println(ll);
        }

//        out.println("<text id=\"text21\" x=\"1272\" y=\"393\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\" baseline-shift=\"baseline\">\n" +
//                "\t公安网</text>\n" );
        out.println("\t<line id=\"line1\" x1=\"208\" y1=\"5\" x2=\"208\" y2=\"767\" style=\"stroke:rgb(99,99,99);stroke-width:1\" stroke-dasharray=\"10,2,10\" fill=\"#F72517\" stroke=\"#CC3333\" />\n" +
                "\t<line id=\"line2\" x1=\"700\" y1=\"5\" x2=\"700\" y2=\"767\" style=\"stroke:rgb(99,99,99);stroke-width:1\" stroke-dasharray=\"10,2,10\" fill=\"url(#linearGradient)\" stroke=\"#FF0033\" />\n" +
                "\t<line id=\"line3\" x1=\"855\" y1=\"5\" x2=\"855\" y2=\"767\" style=\"stroke:rgb(99,99,99);stroke-width:1\" stroke-dasharray=\"10,2,10\" fill=\"url(#linearGradient)\" stroke=\"#FF0033\" />\n" +
                "\t<text x=\"15\" y=\"110\" space=\"preserve\" font-family=\"宋体\" font-size=\"36\" fill=\"#F72517\" stroke=\"#F59244\" >\n" +
                "\t外网接入区</text>\n" +
                "\t<text x=\"370\" y=\"110\" space=\"preserve\" font-family=\"宋体\" font-size=\"36\" fill=\"#F72517\" stroke=\"#F59244\" >\n" +
                "\t安全接入区</text>\n" +
                "\t<text x=\"720\" y=\"110\" space=\"preserve\" font-family=\"宋体\" font-size=\"36\" fill=\"#F72517\" stroke=\"#F59244\" >\n" +
                "\t隔离区</text>\n" +
                "\t<text x=\"922\" y=\"110\" space=\"preserve\" font-family=\"宋体\" font-size=\"36\" fill=\"#F72517\" stroke=\"#F59244\" >\n" +
                "\t公安信息网</text>\n" +
                "<text x=\"915\" y=\"648\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#330099\">\n" +
                "<tspan>\n" +
                "操作说明：1、拖动：点击拖动按钮，按钮变灰色,鼠标按住设备可以拖动，</tspan>\n" +
                "<tspan x=\"915\" dy=\"13.6875\">\n" +
                "             拖动速度不要太快，否者容易出错，有可能会造成别的设备的漂移等问题，</tspan>\n" +
                "<tspan x=\"915\" dy=\"13.6875\">\n" +
                "             重新刷新界面即可。</tspan>\n" +
                "<tspan x=\"915\" dy=\"13.6875\">\n" +
                "          2、删除连线：点击删除按钮，按钮变灰色, 把鼠标放到连线上会有“删除此线”</tspan>\n" +
                "<tspan x=\"915\" dy=\"13.6875\">\n" +
                "             提示，单击鼠标左键删除即可，</tspan>\n" +
                "<tspan x=\"915\" dy=\"13.6875\">\n" +
                "          3、画线：点击画线设备按钮，按钮变灰色, 把鼠标放在空白位置并按住左键拖拽</tspan>\n" +
                "<tspan x=\"915\" dy=\"13.6875\">\n" +
                "          即可画线。</tspan>\n" +
                "<tspan x=\"915\" dy=\"13.6875\">\n" +
                "          （注意：如果在画图中出现错误，刷新界面即可）</tspan>\n" +
                "</text>\n" +
                "<a id=\"tjlx\" onclick=\"addline()\"  >    \n" +
                "\t<image id=\"recttjlx\" xlink:href=\"../img/other/huax.png\" x=\"10\" y=\"18\" width=\"85px\" height=\"35px\" />\n" +
                "</a>\n" +
                "\n" +
                "<a id=\"tdsb\" onmousedown=\"tdequ()\">  \n" +
                "    <image id=\"recttdsb\" xlink:href=\"../img/other/tuod.png\" x=\"97\" y=\"18\" width=\"85px\" height=\"35px\" />\n" +
                "</a>\n" +
                "\n" +
                "<a id=\"sclx\" onmousedown=\"delline()\">  \n" +
                "    <image id=\"rectsclx\" xlink:href=\"../img/other/shanx.png\" x=\"185\" y=\"18\" width=\"85px\" height=\"35px\" />\n" +
                "</a>"+
//                "\t<text x=\"50\" y=\"685\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\">\n" +
//                "\tx:</text>\n" +
//                "\t<text id=\"xtext\" x=\"65\" y=\"685\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
//                "</text>\n" +
//                "\t<text x=\"100\" y=\"685\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\">\n" +
//                "\ty:</text>\n" +
//                "\t<text id=\"ytext\" x=\"115\" y=\"685\" space=\"preserve\" font-family=\"宋体\" font-size=\"12\" fill=\"#FFFFFF\" stroke=\"#000000\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
//                "</text>\n" +
                "<g id=\"g5\">\n" +
                "</g>\n"+
                "</svg>");
        out.flush();
    }
}
