package com.hzjava.monitorcenter.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.service.RunMonitorService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 运行监控管理
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/runMonitor" validate="false"
 *                parameter="m"
 * @struts.action-forward name="bizIndexJSON"
 *                        path="/WEB-INF/pages/monitor/bizIndexJSON.jsp"
 * @struts.action-forward name="equIndexJSON"
 *                        path="/WEB-INF/pages/monitor/equIndexJSON.jsp"
 * @struts.action-forward name="equDetail"
 *                        path="/WEB-INF/pages/monitor/equDetail.jsp"
 * @struts.action-forward name="bizStat"
 *                        path="/WEB-INF/pages/monitor/bizStat.jsp"
 */
public class RunMonitorAction extends DispatchActionSupport {
	private RunMonitorService runMonitorService;

	public ActionForm checkLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		Account account = SessionUtils.getAccount(request);
		String msg = "logout";
		if(account != null){
			msg = "login";
		}
		String path = request.getContextPath();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"',path:'"+path+"'}");
		writer.close();
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public ActionForward terminalIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		int pageIndex = start / limit + 1;
		Map model = runMonitorService.getTerminalIndexModel(pageIndex,limit);
		request.setAttribute("model", model);
		return mapping.findForward("terminalIndexJSON");
	}
	
	public ActionForward bizIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		int pageIndex = start / limit + 1;
		Map model = runMonitorService.getBizIndexModel(pageIndex);
		request.setAttribute("model", model);
		request.setCharacterEncoding("utf-8");
		return mapping.findForward("bizIndexJSON");
	}
    public ActionForward visMonitor(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int start = ServletRequestUtils.getIntParameter(request, "start", 0);
        int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
        int pageIndex = start / limit + 1;
        Map model = runMonitorService.getVisMonitor(pageIndex);
        request.setAttribute("model", model);
        request.setCharacterEncoding("utf-8");
        return mapping.findForward("bizIndexJSON");
    }

	public ActionForward bizStat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String businessName = ServletRequestUtils.getStringParameter(request,"name");
		Map model = runMonitorService.getBizStatModel(businessName);
		request.setAttribute("model", model);
		return mapping.findForward("bizStat");
	}

	public ActionForward equIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		int pageIndex = start / limit + 1;
		Map model = runMonitorService.getEquIndexModel(pageIndex);
		request.setAttribute("model", model);
		return mapping.findForward("equIndexJSON");
	}

	public ActionForward equDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = runMonitorService.getEquDetailModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("equDetail");
	}

	public ActionForward cpuChart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		double cpu = ServletRequestUtils.getRequiredDoubleParameter(request,
				"cpu");
		DefaultValueDataset dataset = new DefaultValueDataset(cpu);
		DialPlot dialplot = new DialPlot();
		dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
		dialplot.setDataset(dataset);
		StandardDialFrame dialFrame = new StandardDialFrame();
		dialFrame.setBackgroundPaint(new Color(1, 97, 183));
		dialplot.setDialFrame(dialFrame);

		StandardDialRange standarddialrange = new StandardDialRange(80D, 100D,
				Color.red);
		standarddialrange.setInnerRadius(0.52000000000000002D);
		standarddialrange.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange);
		StandardDialRange standarddialrange1 = new StandardDialRange(50D, 80D,
				Color.orange);
		standarddialrange1.setInnerRadius(0.52000000000000002D);
		standarddialrange1.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange1);
		StandardDialRange standarddialrange2 = new StandardDialRange(0D, 50D,
				Color.green);
		standarddialrange2.setInnerRadius(0.52000000000000002D);
		standarddialrange2.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange2);

		GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(
				255, 255, 255), new Point(), new Color(170, 170, 220));
		DialBackground dialbackground = new DialBackground(gradientpaint);
		dialbackground
				.setGradientPaintTransformer(new StandardGradientPaintTransformer(
						GradientPaintTransformType.VERTICAL));
		dialplot.setBackground(dialbackground);

		DialTextAnnotation dialtextannotation = new DialTextAnnotation("CPU");
		dialtextannotation.setFont(new Font("Dialog", 1, 14));
		dialtextannotation.setRadius(0.69999999999999996D);
		dialplot.addLayer(dialtextannotation);
		DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
		dialplot.addLayer(dialvalueindicator);

		StandardDialScale standarddialscale = new StandardDialScale(0, 100,
				-120D, -300D, 10D, 4);
		standarddialscale.setTickRadius(0.88D);
		standarddialscale.setTickLabelOffset(0.14999999999999999D);
		standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
		dialplot.addScale(0, standarddialscale);

		dialplot.addLayer(new org.jfree.chart.plot.dial.DialPointer.Pointer());
		// 实例化DialCap
		DialCap dialcap = new DialCap();
		dialcap.setRadius(0.10000000000000001D);
		dialplot.setCap(dialcap);

		JFreeChart chart = new JFreeChart(dialplot);
		chart.setBackgroundPaint(new Color(223, 232, 246));
		TextTitle title = new TextTitle("CPU", new Font("Dialog", 0, 15));
		chart.setTitle(title);

		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,chart, 200, 220);
		return null;
	}

	public ActionForward memChart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		double mem = ServletRequestUtils.getRequiredDoubleParameter(request,
				"mem");
		DefaultValueDataset dataset = new DefaultValueDataset(mem);
		DialPlot dialplot = new DialPlot();
		dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
		dialplot.setDataset(dataset);
		StandardDialFrame dialFrame = new StandardDialFrame();
		dialFrame.setBackgroundPaint(new Color(1, 97, 183));
		dialplot.setDialFrame(dialFrame);

		StandardDialRange standarddialrange = new StandardDialRange(80D, 100D,Color.red);
		standarddialrange.setInnerRadius(0.52000000000000002D);
		standarddialrange.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange);
		StandardDialRange standarddialrange1 = new StandardDialRange(50D, 80D,
				Color.orange);
		standarddialrange1.setInnerRadius(0.52000000000000002D);
		standarddialrange1.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange1);
		StandardDialRange standarddialrange2 = new StandardDialRange(0D, 50D,
				Color.green);
		standarddialrange2.setInnerRadius(0.52000000000000002D);
		standarddialrange2.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange2);

		GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(
				255, 255, 255), new Point(), new Color(170, 170, 220));
		DialBackground dialbackground = new DialBackground(gradientpaint);
		dialbackground
				.setGradientPaintTransformer(new StandardGradientPaintTransformer(
						GradientPaintTransformType.VERTICAL));
		dialplot.setBackground(dialbackground);

		DialTextAnnotation dialtextannotation = new DialTextAnnotation("MEMORY");
		dialtextannotation.setFont(new Font("Dialog", 1, 14));
		dialtextannotation.setRadius(0.69999999999999996D);
		dialplot.addLayer(dialtextannotation);
		DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
		dialplot.addLayer(dialvalueindicator);

		StandardDialScale standarddialscale = new StandardDialScale(0, 100,
				-120D, -300D, 10D, 4);
		standarddialscale.setTickRadius(0.88D);
		standarddialscale.setTickLabelOffset(0.14999999999999999D);
		standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
		dialplot.addScale(0, standarddialscale);

		dialplot.addLayer(new org.jfree.chart.plot.dial.DialPointer.Pointer());
		// 实例化DialCap
		DialCap dialcap = new DialCap();
		dialcap.setRadius(0.10000000000000001D);
		dialplot.setCap(dialcap);

		JFreeChart chart = new JFreeChart(dialplot);
		chart.setBackgroundPaint(new Color(223, 232, 246));
		TextTitle title = new TextTitle("MEMORY", new Font("Dialog", 0, 15));
		chart.setTitle(title);

		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,
				chart, 200, 220);
		return null;
	}

	public ActionForward diskChart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		double disk = ServletRequestUtils.getRequiredDoubleParameter(request,
				"disk");
		DefaultValueDataset dataset = new DefaultValueDataset(disk);
		DialPlot dialplot = new DialPlot();
		dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
		dialplot.setDataset(dataset);
		StandardDialFrame dialFrame = new StandardDialFrame();
		dialFrame.setBackgroundPaint(new Color(1, 97, 183));
		dialplot.setDialFrame(dialFrame);

		StandardDialRange standarddialrange = new StandardDialRange(80D, 100D,
				Color.red);
		standarddialrange.setInnerRadius(0.52000000000000002D);
		standarddialrange.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange);
		StandardDialRange standarddialrange1 = new StandardDialRange(50D, 80D,
				Color.orange);
		standarddialrange1.setInnerRadius(0.52000000000000002D);
		standarddialrange1.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange1);
		StandardDialRange standarddialrange2 = new StandardDialRange(0D, 50D,
				Color.green);
		standarddialrange2.setInnerRadius(0.52000000000000002D);
		standarddialrange2.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange2);

		GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(
				255, 255, 255), new Point(), new Color(170, 170, 220));
		DialBackground dialbackground = new DialBackground(gradientpaint);
		dialbackground
				.setGradientPaintTransformer(new StandardGradientPaintTransformer(
						GradientPaintTransformType.VERTICAL));
		dialplot.setBackground(dialbackground);

		DialTextAnnotation dialtextannotation = new DialTextAnnotation("DISK");
		dialtextannotation.setFont(new Font("Dialog", 1, 14));
		dialtextannotation.setRadius(0.69999999999999996D);
		dialplot.addLayer(dialtextannotation);
		DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
		dialplot.addLayer(dialvalueindicator);

		StandardDialScale standarddialscale = new StandardDialScale(0, 100,
				-120D, -300D, 10D, 4);
		standarddialscale.setTickRadius(0.88D);
		standarddialscale.setTickLabelOffset(0.14999999999999999D);
		standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
		dialplot.addScale(0, standarddialscale);

		dialplot.addLayer(new org.jfree.chart.plot.dial.DialPointer.Pointer());
		// 实例化DialCap
		DialCap dialcap = new DialCap();
		dialcap.setRadius(0.10000000000000001D);
		dialplot.setCap(dialcap);

		JFreeChart chart = new JFreeChart(dialplot);
		chart.setBackgroundPaint(new Color(223, 232, 246));
		TextTitle title = new TextTitle("DISK", new Font("Dialog", 0, 15));
		chart.setTitle(title);

		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,
				chart, 200, 220);
		return null;
	}

	public void setRunMonitorService(RunMonitorService runMonitorService) {
		this.runMonitorService = runMonitorService;
	}
}
