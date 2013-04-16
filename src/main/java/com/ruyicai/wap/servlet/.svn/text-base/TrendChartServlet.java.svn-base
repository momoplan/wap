package com.ruyicai.wap.servlet;


import java.io.IOException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruyicai.wap.controller.TrendChartController;

/**
 * Servlet implementation class TrendChartServlet
 */
public class TrendChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrendChartController trendChartController = new TrendChartController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrendChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			trendChartController.trendChartSSQ();
			trendChartController.trendChart22X5();
			trendChartController.trendChart3D();
			trendChartController.trendChartDLC();
			trendChartController.trendChartDLT();
			trendChartController.trendChartPLS();
			trendChartController.trendChartPLW();
			trendChartController.trendChartQLC();
			trendChartController.trendChartQXC();
			trendChartController.trendChartSSQ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
