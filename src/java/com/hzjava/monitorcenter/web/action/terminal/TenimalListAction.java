package com.hzjava.monitorcenter.web.action.terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;
import cn.collin.commons.utils.DateUtils;
import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.CardType;
import com.hzjava.monitorcenter.domain.SysTerminalStatus;
import com.hzjava.monitorcenter.domain.TenimalList;
import com.hzjava.monitorcenter.domain.TenimalOperationAudit;
import com.hzjava.monitorcenter.service.TerminalService;
import com.hzjava.monitorcenter.utils.ServiceResponse;
import com.hzjava.monitorcenter.utils.ServiceUtil;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * date:2012-03-16 author:qxp function:终端管理的'全部用户列表'
 * 
 * @author qxp
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/tenimalListAction" validate="false"
 *                parameter="m"
 * @struts.action-forward name="index"
 *                        path=""
 * @struts.action-forward name="cardTypeJSON"
 *                        path=""
 */
public class TenimalListAction extends DispatchActionSupport {
	private static Logger logger = Logger.getLogger(TenimalListAction.class);
	private TerminalService terminalService;

	public void setTerminalService(TerminalService terminalService) {
		this.terminalService = terminalService;
	}
	/**
	 * 
	 * 读取全部列表
	 */
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		// 获取卡类型的ID号
		String cardType = ServletRequestUtils.getStringParameter(request,"cardType");
		// 获取当前状态的ID号，1在线，0离线
		String state = ServletRequestUtils.getStringParameter(request, "state");
		// 获取阻断状态的编码
		String ifblock = ServletRequestUtils.getStringParameter(request,"ifblock");
		// 警员编号code和名字name的标识，单选按钮组
		String radioPolice = ServletRequestUtils.getStringParameter(request,"RadioPolice");
		// 获取警号或警官名字内容
		String police = ServletRequestUtils.getStringParameter(request,	"police");
		
		String[][] params = new String[][] {
				{ "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
				{ "Command", "allvpn" },
				{ "beginno", String.valueOf(pageIndex) },
				{ "endno", String.valueOf(pageIndex+pageLength)},
				{ "pagesize",String.valueOf(pageLength)},
				{ "cardType",cardType==null?"All":cardType},
				{ "state", state==null?"All":state}, 
				{ "ifblock", ifblock==null?"All":ifblock}, 
				{ "policeValue",police==null?"":police},
				{ "policeKey",radioPolice==null?"":radioPolice}
		};
		ServiceResponse sResponse = ServiceUtil.callService(params);
		logger.info(sResponse.getCode());
		String msg = null;
		String data = null;
		String json = null;
		if(sResponse.getCode() == 404 || sResponse.getCode() == 403){
			msg = "服务没有开通";
		}else if(sResponse.getCode()==200){
			msg = "服务连接成功";
			data = sResponse.getData();
		}else if(sResponse.getCode()==500){
			msg = "服务出错";
		}			
		json = setJson(data);
		logger.info(msg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.close();
		return mapping.findForward("index");
	}
	
	/**
	 *  
	 *  date:2012-030-20 author:qxp
	 *  读取在线列表
	 */
	public ActionForward indexOnline(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		
		// 获取卡类型的ID号
		String cardType = ServletRequestUtils.getStringParameter(request,"cardType");
		// 警员编号code和名字name的标识，单选按钮组
		String radioPolice = ServletRequestUtils.getStringParameter(request,"RadioPolice");
		// 获取警号或警官名字内容
		String police = ServletRequestUtils.getStringParameter(request,	"police");
		
		String[][] params = new String[][] {
				{ "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
				{ "Command", "onlinevpn" },
				{ "beginno", String.valueOf(pageIndex) },
				{ "endno", String.valueOf(pageIndex+pageLength)},
				{ "pagesize",String.valueOf(pageLength)},
				{ "cardType",cardType==null?"All":cardType},
				{ "policeValue",police==null?"":police},
				{ "policeKey",radioPolice==null?"":radioPolice}
		};
		ServiceResponse sResponse = ServiceUtil.callService(params);
		logger.info(sResponse.getCode());
		String msg = null;
		String data = null;
		String json = null;
		if(sResponse.getCode() == 404 || sResponse.getCode() == 403){
			msg = "服务没有开通";
		}else if(sResponse.getCode()==200){
			msg = "服务连接成功";
			data = sResponse.getData();
		}else if(sResponse.getCode()==500){
			msg = "服务出错";
		}			
		json = setJson(data);
		logger.info(msg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.close();
		return mapping.findForward("indexOnline");
	}

	private String setJson(String data) {
		if(data == null){
			data = "[" +
			"{status:'0',ifcancel:false,ip:'192.168.1.11',ifblock:false,cardtype:'001',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:'10101',policename:'test6',idno:'110108197903034913',org:'组织1',depart:'koal',region:'上海市',logindate:'2012-03-14 01:32:38',onlinetime:'01:32:38',createdate:'2012-03-27 10:15:21'},"+
			"{status:'1',ifcancel:false,ip:'192.168.1.11',ifblock:false,cardtype:'002',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:'10102',policename:'test7',idno:'110108197903034914',org:'组织1',depart:'koal',region:'上海市',logindate:'2012-03-14 01:32:38',onlinetime:'01:32:38',createdate:'2012-03-14 07:09:18'},"+
			"{status:'0',ifcancel:false,ip:'192.168.1.11',ifblock:true,cardtype:'003',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:'10103',policename:'test5',idno:'110108197903034915',org:'组织1',depart:'koal',region:'重庆市',logindate:'2012-03-14 01:32:38',onlinetime:'01:32:38',createdate:'2012-03-14 02:39:17'},"+
			"{status:'1',ifcancel:false,ip:'192.168.1.11',ifblock:true,cardtype:'001',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:'10104',policename:'test4',idno:'110108197903034916',org:'组织1',depart:'koal',region:'重庆市',logindate:'2012-03-14 01:32:38',onlinetime:'01:32:38',createdate:'2012-03-14 02:36:57'},"+
			"{status:'0',ifcancel:false,ip:'192.168.1.11',ifblock:false,cardtype:'002',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:'10105',policename:'test3',idno:'110108197903034917',org:'组织1',depart:'koal',region:'重庆市',logindate:'2012-03-14 01:32:38',onlinetime:'01:32:38',createdate:'2012-03-14 02:23:39'},"+
			"{status:'1',ifcancel:false,ip:'192.168.1.11',ifblock:false,cardtype:'001',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:'10106',policename:'test3',idno:'110108197903034918',org:'组织1',depart:'koal',region:'重庆市',logindate:'2012-03-14 01:32:38',onlinetime:'01:32:38',createdate:'2012-03-14 02:18:28'},"+
			"{status:'0',ifcancel:false,ip:'192.168.1.11',ifblock:false,cardtype:'002',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:'10107',policename:'test2',idno:'110108197903034919',org:'组织1',depart:'koal',region:'重庆市',logindate:'2012-03-14 01:32:38',onlinetime:'01:32:38',createdate:'2012-03-14 02:16:44'},"+
			"{status:'1',ifcancel:true,ip:'192.168.1.11',ifblock:false,cardtype:'003',cardmodel:'200',cardver:'v1.0',policecate:'1',policeno:'10108',policename:'test1',idno:'110108197903034920',org:'组织1',depart:'koal',region:'上海市',logindate:'2012-03-14 01:32:38',onlinetime:'01:32:38',createdate:'2012-03-14 01:32:38'},"+
			"{total:8,beginno:null,engno:null,pagesize:null}" +
			"]";
		}
		String rows = data.split("\\[")[1].split(",\\{total:")[0];
		int total = Integer.parseInt(data.split(",\\{total:")[1].split(",beginno:")[0]);
		String json = "{success:true,amount:"+total+",results:["+rows+"]}";
		return json;
	}

	/*
	 * date:2012-03-16 author:crj function:显示详细信息
	 */
	@SuppressWarnings("rawtypes")
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = terminalService.loadById(id);
		request.setAttribute("model", model);
		return mapping.findForward("index");
	}

	/*
	 * date:2012-03-16 author:crj function:显示出所有的安全卡类型信息
	 */
	public ActionForward showCardType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<CardType> list = terminalService.showCardType();
		request.setAttribute("list", list);
		return mapping.findForward("cardTypeJSON");
	}

	/*
	 * date:2012-03-16 author:crj function:恢复接入操作
	 */
	public ActionForward releaseBlock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String policeno = ServletRequestUtils.getStringParameter(request, "policeno");
		String policeName = ServletRequestUtils.getStringParameter(request, "policename");
		String cardType = ServletRequestUtils.getStringParameter(request, "cardtype");
		String cardId = ServletRequestUtils.getStringParameter(request, "cardmodel");
		String idno = ServletRequestUtils.getStringParameter(request, "idno");
		String ip = ServletRequestUtils.getRequiredStringParameter(request, "ip");
		TenimalList tenimal = new TenimalList();
		tenimal.setPoliceId(policeno);
		tenimal.setPoliceName(policeName);
		tenimal.setCardType(cardType);
		tenimal.setCardId(cardId);
		tenimal.setCertId(idno);
		aduit(request,tenimal, "D");
		String msg = null;
        String[][] params = null;
        if(policeno!=null&&idno!=null){
            params = new String[][] {
                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                    { "Command", "noblock" },
                    { "pliceno",  policeno},
                    { "idno", idno },
                    { "ip",ip}
            };
        } else if(policeno!=null&&idno==null){
            params = new String[][] {
                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                    { "Command", "noblock" },
                    { "pliceno",  policeno},
                    { "idno", "" },
                    { "ip",ip}
            };
        } else if(policeno==null&&idno!=null){
            params = new String[][] {
                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                    { "Command", "noblock" },
                    { "pliceno",  ""},
                    { "idno", idno },
                    { "ip",ip}
            };
        } else if(policeno==null&&idno==null){
            msg = "警号和身份证号都是空值,无法恢复接入";
        }
        String data = "恢复接入失败";
        if(params!=null){
            ServiceResponse sResponse = ServiceUtil.callService(params);
            logger.info(sResponse.getCode());
            if(sResponse.getCode() == 404 || sResponse.getCode() == 403){
                msg = "服务没有开通";
            }else if(sResponse.getCode()==200){
                msg = "恢复接入成功";
                data = sResponse.getData();
            }else if(sResponse.getCode()==500){
                msg = "服务出错";
            }
        }
		logger.info(msg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"',data:'"+ data +"'}");
		writer.close();

		return mapping.findForward("releaseBlock");
	}

	/*
	 * date:2012-03-16 author:crj function:阻断操作
	 */
	public ActionForward showBlock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String policeno = ServletRequestUtils.getStringParameter(request, "policeno");
		String block = ServletRequestUtils.getStringParameter(request, "block");
		String hour = ServletRequestUtils.getStringParameter(request, "hour");
		// 长久阻断value1 阻断value2
		String policeName = ServletRequestUtils.getStringParameter(request, "policename");
		String cardType = ServletRequestUtils.getStringParameter(request, "cardtype");
		String cardId = ServletRequestUtils.getStringParameter(request, "cardmodel");
		String idno = ServletRequestUtils.getStringParameter(request, "idno");
		String ip = ServletRequestUtils.getStringParameter(request, "ip");
		TenimalList tenimal = new TenimalList();
		tenimal.setPoliceId(policeno);
		tenimal.setPoliceName(policeName);
		tenimal.setCardType(cardType);
		tenimal.setCardId(cardId);
		tenimal.setCertId(idno);
		String json = null;
		boolean isLogin = aduit(request,tenimal, "B");
		if(isLogin){
			String data = null;
            String msg = null;
            String[][] params = null;
            if (block.equals("value1")) {
                data = "长久";
                if(policeno!=null&&idno!=null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "block" },
                            { "pliceno",  policeno},
                            { "idno", idno },
                            { "ip",ip}
                    };
                } else if(policeno!=null&&idno==null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "block" },
                            { "pliceno",  policeno},
                            { "idno", "" },
                            { "ip",ip}
                    };
                } else if(policeno==null&&idno!=null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "block" },
                            { "pliceno",  ""},
                            { "idno", idno },
                            { "ip",ip}
                    };
                } else if(policeno==null&&idno==null){
                    msg = "警号和身份证号都是空值,无法"+data+"阻断";
                }
            }
            if (block.equals("value2")) {
                data = "临时";
                if(policeno!=null&&idno!=null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "tempblock" },
                            { "pliceno",  policeno},
                            { "ip",ip},
                            { "idno", idno },
                            { "timelength",String.valueOf(hour)}
                    };
                } else if(policeno==null&&idno!=null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "tempblock" },
                            { "pliceno",  ""},
                            { "ip",ip},
                            { "idno", idno },
                            { "timelength",String.valueOf(hour)}
                    };
                }  else if(policeno!=null&&idno==null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "tempblock" },
                            { "pliceno",  policeno},
                            { "ip",ip},
                            { "idno", "" },
                            { "timelength",String.valueOf(hour)}
                    };
                } else if(policeno==null&&idno==null){
                    msg = "警号和身份证号都是空值,无法"+data+"阻断";
                }
            }
            data += "阻断失败";
			if(params!=null){
                ServiceResponse sResponse = ServiceUtil.callService(params);
                logger.info(sResponse.getCode());
                if(sResponse.getCode() == 404 || sResponse.getCode() == 403){
                    msg = "服务没有开通";
                }else if(sResponse.getCode()==200){
                    msg = "阻断成功";
                    data = sResponse.getData();
                }else if(sResponse.getCode()==500){
                    msg = "服务出错";
                }
            }
			logger.info(msg);
			json = "{success:true,msg:'"+msg+"',data:'"+ data +"'}";
		}else{
			json = "{success:true,msg:'',data:'您还没有登录'}";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return mapping.findForward("showBlock");
	}

	/*
	 * date:2012-03-16 author:crj function:吊销证件操作
	 */
	public ActionForward revokeCert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String policeno = ServletRequestUtils.getStringParameter(request, "policeno");
		String policeName = ServletRequestUtils.getStringParameter(request, "policename");
		String cardType = ServletRequestUtils.getStringParameter(request, "cardtype");
		String cardId = ServletRequestUtils.getStringParameter(request, "cardmodel");
		String idno = ServletRequestUtils.getStringParameter(request, "idno");
		String ip = ServletRequestUtils.getStringParameter(request, "ip");
		TenimalList tenimal = new TenimalList();
		tenimal.setPoliceId(policeno);
		tenimal.setPoliceName(policeName);
		tenimal.setCardType(cardType);
		tenimal.setCardId(cardId);
		tenimal.setCertId(idno);
		String json = null;
		boolean isLogin = aduit(request,tenimal, "C");
		if(isLogin){			
			String msg = null;
			String[][] params = null;
            if(policeno!=null&&idno!=null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "revokecert" },
                        { "policeno", policeno },
                        { "idno", idno },
                        { "ip",ip}
                };
            } else if(policeno!=null&&idno==null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "revokecert" },
                        { "pliceno",  policeno},
                        { "idno", "" },
                        { "ip",ip}
                };
            } else if(policeno==null&&idno!=null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "revokecert" },
                        { "pliceno",  ""},
                        { "idno", idno },
                        { "ip",ip}
                };
            } else if(policeno==null&&idno==null){
                msg = "警号和身份证号都是空值,无法吊销证件";
            }
            String data = "吊销失败";
            if(params!=null){
                ServiceResponse sResponse = ServiceUtil.callService(params);
                logger.info(sResponse.getCode());
                if(sResponse.getCode() == 404 || sResponse.getCode() == 403){
                    msg = "服务没有开通";
                }else if(sResponse.getCode()==200){
                    msg = "吊销证件成功";
                    data = sResponse.getData();
                }else if(sResponse.getCode()==500){
                    msg = "服务出错";
                }
            }
			logger.info(msg);
			json = "{success:true,msg:'"+msg+"',data:'"+ data +"'}";
		}else{
			json = "{success:true,msg:'',data:'您还没有登录'}";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return mapping.findForward("revokeCert");
	}
	
	/**
	 * date:2012-03-30 author:qxp function:在线恢复接入
	 */
	public ActionForward releaseBlockOnline(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String policeno = ServletRequestUtils.getStringParameter(request, "policeno");
		String policeName = ServletRequestUtils.getStringParameter(request, "policename");
		String cardType = ServletRequestUtils.getStringParameter(request, "cardtype");
		String cardId = ServletRequestUtils.getStringParameter(request, "cardmodel");
		String idno = ServletRequestUtils.getStringParameter(request, "idno");
		String ip = ServletRequestUtils.getStringParameter(request, "ip");
		TenimalList tenimal = new TenimalList();
		tenimal.setPoliceId(policeno);
		tenimal.setPoliceName(policeName);
		tenimal.setCardType(cardType);
		tenimal.setCardId(cardId);
		tenimal.setCertId(idno);
		String json = null;
		boolean isLogin = aduit(request,tenimal, "D");
		if(isLogin){
			String msg = null;
			String[][] params = null;
            if(policeno!=null&&idno!=null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "noblock" },
                        { "pliceno",  policeno},
                        { "idno", idno },
                        { "ip",ip}
                };
            } else if(policeno!=null&&idno==null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "noblock" },
                        { "pliceno",  policeno},
                        { "idno", "" },
                        { "ip",ip}
                };
            } else if(policeno==null&&idno!=null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "noblock" },
                        { "pliceno",  ""},
                        { "idno", idno },
                        { "ip",ip}
                };
            } else if(policeno==null&&idno==null){
                msg = "警号和身份证号都是空值,无法恢复接入";
            }
            String data = "恢复接入失败";
            if(params!=null){
                ServiceResponse sResponse = ServiceUtil.callService(params);
                logger.info(sResponse.getCode());
                if(sResponse.getCode() == 404 || sResponse.getCode() == 403){
                    msg = "服务没有开通";
                }else if(sResponse.getCode()==200){
                    msg = "恢复接入成功";
                    data = sResponse.getData();
                }else if(sResponse.getCode()==500){
                    msg = "服务出错";
                }
            }
			logger.info(msg);
			json = "{success:true,msg:'"+msg+"',data:'"+ data +"'}";
		}else{
			json = "{success:true,msg:'',data:'您还没有登录'}";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return mapping.findForward("releaseBlock");
	}
	
	/**
	 * date:2012-03-30 author:qxp function:在线阻断
	 */
	public ActionForward showBlockOnline(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String policeno = ServletRequestUtils.getStringParameter(request, "policeno");
		String block = ServletRequestUtils.getStringParameter(request, "block");
		String hour = ServletRequestUtils.getStringParameter(request, "hour");
		// 长久阻断value1 阻断value2
		String policeName = ServletRequestUtils.getStringParameter(request, "policename");
		String cardType = ServletRequestUtils.getStringParameter(request, "cardtype");
		String cardId = ServletRequestUtils.getStringParameter(request, "cardmodel");
		String idno = ServletRequestUtils.getStringParameter(request, "idno");
		String ip = ServletRequestUtils.getStringParameter(request, "ip");
		TenimalList tenimal = new TenimalList();
		tenimal.setPoliceId(policeno);
		tenimal.setPoliceName(policeName);
		tenimal.setCardType(cardType);
		tenimal.setCardId(cardId);
		tenimal.setCertId(idno);
		String json = null;
        boolean isLogin = aduit(request,tenimal, "B");
        if(isLogin){
            String data = null;
            String msg = null;
            String[][] params = null;
            if (block.equals("value1")) {
                data = "长久";
                if(policeno!=null&&idno!=null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "block" },
                            { "pliceno",  policeno},
                            { "idno", idno },
                            { "ip",ip}
                    };
                } else if(policeno!=null&&idno==null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "block" },
                            { "pliceno",  policeno},
                            { "idno", "" },
                            { "ip",ip}
                    };
                } else if(policeno==null&&idno!=null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "block" },
                            { "pliceno",  ""},
                            { "idno", idno },
                            { "ip",ip}
                    };
                } else if(policeno==null&&idno==null){
                    msg = "警号和身份证号都是空值,无法"+data+"阻断";
                }
            }
            if (block.equals("value2")) {
                data = "临时";
                if(policeno!=null&&idno!=null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "tempblock" },
                            { "pliceno",  policeno},
                            { "ip",ip},
                            { "idno", idno },
                            { "timelength",String.valueOf(hour)}
                    };
                } else if(policeno!=null&&idno==null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "tempblock" },
                            { "pliceno",  policeno},
                            { "ip",ip},
                            { "idno", "" },
                            { "timelength",String.valueOf(hour)}
                    };
                } else if(policeno==null&&idno!=null){
                    params = new String[][] {
                            { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                            { "Command", "tempblock" },
                            { "pliceno",  ""},
                            { "ip",ip},
                            { "idno", idno },
                            { "timelength",String.valueOf(hour)}
                    };
                } else if(policeno==null&&idno==null){
                    msg = "警号和身份证号都是空值,无法"+data+"阻断";
                }
            }
            data += "阻断失败";
			if(params!=null){
                ServiceResponse sResponse = ServiceUtil.callService(params);
                logger.info(sResponse.getCode());
                if(sResponse.getCode() == 404 || sResponse.getCode() == 403){
                    msg = "服务没有开通";
                }else if(sResponse.getCode()==200){
                    msg = "阻断成功";
                    data = sResponse.getData();
                }else if(sResponse.getCode()==500){
                    msg = "服务出错";
                }
            }
			logger.info(msg);
			json = "{success:true,msg:'"+msg+"',data:'"+ data +"'}";
		}else{
			json = "{success:true,msg:'',data:'您还没有登录'}";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return mapping.findForward("showBlock");
	}
	
	/**
	 * date:2012-03-30 author:qxp function:在线吊销证件
	 */
	public ActionForward revokeCertOnline(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String policeno = ServletRequestUtils.getStringParameter(request, "policeno");
		String policeName = ServletRequestUtils.getStringParameter(request, "policename");
		String cardType = ServletRequestUtils.getStringParameter(request, "cardtype");
		String cardId = ServletRequestUtils.getStringParameter(request, "cardmodel");
		String idno = ServletRequestUtils.getStringParameter(request, "idno");
		String ip = ServletRequestUtils.getStringParameter(request, "ip");
		TenimalList tenimal = new TenimalList();
		tenimal.setPoliceId(policeno);
		tenimal.setPoliceName(policeName);
		tenimal.setCardType(cardType);
		tenimal.setCardId(cardId);
		tenimal.setCertId(idno);
		boolean isLogin = aduit(request,tenimal, "C");
		String json = null;
		if(isLogin){
			String msg = null;
			String[][] params = null;
            if(policeno!=null&&idno!=null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "revokecert" },
                        { "policeno", policeno },
                        { "idno", idno },
                        { "ip",ip}
                };
            } else if (policeno==null&&idno!=null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "revokecert" },
                        { "policeno", "" },
                        { "idno", idno },
                        { "ip",ip}
                };
            } else if (policeno!=null&&idno==null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "revokecert" },
                        { "policeno", policeno },
                        { "idno", "" },
                        { "ip",ip}
                };
            } else if (policeno==null&&idno==null){
                msg = "警号和身份证号都是空值,无法吊销证件";
            }
            String data = "吊销失败";
            if(params!=null){
                ServiceResponse sResponse = ServiceUtil.callService(params);
                logger.info(sResponse.getCode());
                if(sResponse.getCode() == 404 || sResponse.getCode() == 403){
                    msg = "服务没有开通";
                }else if(sResponse.getCode()==200){
                    msg = "吊销证件成功";
                    data = sResponse.getData();
                }else if(sResponse.getCode()==500){
                    msg = "服务出错";
                }
            }
            logger.info(msg);
            json = "{success:true,msg:'"+msg+"',data:'"+ data +"'}";
		}else{
			json = "{success:true,msg:'',data:'您还没有登录'}";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return mapping.findForward("revokeCert");
	}
	/**
	 * date:2012-03-30 author:qxp function:在线截图
	 */
	@SuppressWarnings("deprecation")
	public ActionForward getPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String policeno = ServletRequestUtils.getStringParameter(request, "policeno");
		String policeName = ServletRequestUtils.getStringParameter(request, "policename");
		String cardType = ServletRequestUtils.getStringParameter(request, "cardtype");
		String cardId = ServletRequestUtils.getStringParameter(request, "cardmodel");
		String idno = ServletRequestUtils.getStringParameter(request, "idno");
		String ip = ServletRequestUtils.getStringParameter(request, "ip");
		String status = ServletRequestUtils.getRequiredStringParameter(request, "status");
		TenimalList tenimal = new TenimalList();
		tenimal.setPoliceId(policeno);
		tenimal.setPoliceName(policeName);
		tenimal.setCardType(cardType);
		tenimal.setCardId(cardId);
		tenimal.setCertId(idno);
		boolean isLogin = aduit(request,tenimal, "A");
		String json = null;
		if(isLogin){
			String msg = null;
            String[][] params = null;
            if(policeno!=null&&idno!=null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "viewvpn" },
                        { "policeno", "" },
                        { "idno", idno },
                        { "ip",ip}
                };
            }else if(policeno==null&&idno!=null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "viewvpn" },
                        { "policeno", "" },
                        { "idno", idno },
                        { "ip",ip}
                };
            }else if(policeno!=null&&idno==null){
                params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "viewvpn" },
                        { "policeno", policeno },
                        { "idno", "" },
                        { "ip",ip}
                };
            }else if(policeno==null&&idno==null){
                msg = "警号和身份证号都是空值,无法截屏";
            }
			byte[] buf = null;
            if(params!=null){
                buf = ServiceUtil.callServiceToByte(params);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			Date date = new Date();
			String screenName = "printScreen_"+sdf.format(date)+"_"+(String.valueOf(Math.random()).substring(2))+".png";						//保存图片名
			String uploadDir = request.getRealPath("upload"); 	//保存图片路径
			String data = "截屏失败";
			int width = 400;
			int height = 300;
			String outFileName = uploadDir+"/"+screenName;
			if(buf!=null){
				downFile(buf, outFileName);
				ImageIcon icon = new ImageIcon(outFileName);
				width = icon.getIconWidth();
				height = icon.getIconHeight();
				data = "截屏成功";
                if(idno!=null){
                    SysTerminalStatus s = new SysTerminalStatus();
                    s.setIsOnline(status);
                    s.setUserId(idno);
                    s.settScreenTime(new Date());
                    s.settPrintScreen(outFileName);
                    addSysterminalStatus(s);
                }
			}else {
                if(msg==null){
                    msg = "不能连接服务或服务出错";
                }
				data = "截屏失败";
			}
			logger.info(msg);
			json = "{success:true,msg:'"+msg+"',data:'"+ data +"',screenName:'"+screenName+"',width:'"+width+"',height:'"+height+"'}";
		}else{
			json = "{success:true,msg:'',data:'您还没有登录',screenName:'',width:'',height:''}";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();		
		return mapping.findForward("printScreen");
	}

	private void downFile(byte[] buf ,String fileName) {
		try {
			String str = new String(buf,"iso8859-1");
			FileOutputStream out = new FileOutputStream(new File(fileName));
			out.write(str.getBytes("iso8859-1"));
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage());
		} catch (IOException e){
			logger.info(e.getMessage());
		}
	}
	
	private boolean aduit(HttpServletRequest request, TenimalList tenimal,String type){
		TenimalOperationAudit t = new TenimalOperationAudit();
		t.setCardId(tenimal.getCardId());
		t.setCardType(tenimal.getCardType());
		t.setCertId(tenimal.getCertId());
		t.setPoliceId(tenimal.getPoliceId());
		t.setPoliceName(tenimal.getPoliceName());
		t.setOperateTpye(type);// 截屏'A'，阻断 'B'、吊销证书'C' 、恢复接入'D'
		Date now = DateUtils.getNow();
		t.setOperateTime(now);
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			t.setOperater(account.getName());
			terminalService.revokeCert(t);
			return true;
		}else{
			return false;
		}
	}
	
	private void addSysterminalStatus(SysTerminalStatus sysTerminalStatus){
		terminalService.addSysTerminalStatus(sysTerminalStatus);
	}
}
