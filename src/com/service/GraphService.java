package com.service;


import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.BasicStroke;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

public class GraphService extends HttpServlet {

	private static DefaultPieDataset dataset;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		DefaultPieDataset dataset = (DefaultPieDataset)request.getAttribute("dataset");
		JFreeChart chart = createChart(dataset);
		chart.setBorderPaint(Color.black);
		chart.setBorderStroke(new BasicStroke(10.0f));
		chart.setBorderVisible(true);
		
		if(chart != null){
			int width = 400;
			int height = 200;
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			response.setContentType("image/png");
			OutputStream out = response.getOutputStream();
			ChartUtilities.writeChartAsPNG(out, chart, width, height, info);
		}
	}

	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(dataset);
		return new ChartPanel(chart);
	}

	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Stock", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}

}
