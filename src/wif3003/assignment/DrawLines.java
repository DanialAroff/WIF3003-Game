
package wif3003.assignment;

import java.awt.Color; 
import java.awt.BasicStroke; 
import java.util.ArrayList;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;


public class DrawLines extends ApplicationFrame {

   public DrawLines(String applicationTitle, String chartTitle, ArrayList edges) {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "X-Axis" ,
         "Y-Axis" ,
         drawLines(edges) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel(xylineChart);
      chartPanel.setPreferredSize( new java.awt.Dimension(560 , 367));
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
      renderer.setSeriesPaint(0 , Color.BLUE);
      renderer.setSeriesPaint(1 , Color.GREEN);
      renderer.setSeriesStroke(0 , new BasicStroke(4.0f));
      renderer.setSeriesStroke(1 , new BasicStroke(3.0f));
      plot.setRenderer(renderer); 
      setContentPane(chartPanel); 
   }
   
   private XYDataset createDataset() {
      final XYSeries firefox = new XYSeries("Firefox");          
      firefox.add(1.0 , 1.0);          
      firefox.add(5.0 , 4.0);          
      firefox.add(12.0 , 6.0);          
      
      final XYSeries chrome = new XYSeries("Chrome");          
      chrome.add(1.0 , 4.0);          
//      chrome.add( 2.0 , 5.0 );          
      chrome.add(10.0 , 6.0);          
   
      final XYSeriesCollection dataset = new XYSeriesCollection();          
      dataset.addSeries(firefox);          
      dataset.addSeries(chrome);         
      return dataset;
   }
   
    private XYDataset drawLines(ArrayList<Line> edges) {
        ArrayList<XYSeries> listOfSeries = new ArrayList();
        
        for (int i = 0; i < edges.size(); i++) {
            XYSeries currentSeries = new XYSeries("Line " + (i+1));
            Line currentLine = edges.get(i);
            currentSeries.add(currentLine.getX1(), currentLine.getY1());
            currentSeries.add(currentLine.getX2(), currentLine.getY2());
            listOfSeries.add(currentSeries);
        }
        
        final XYSeriesCollection dataset = new XYSeriesCollection();          
        for (int j = 0; j < listOfSeries.size(); j++) {
            dataset.addSeries(listOfSeries.get(j));
        }
        return dataset;
   }

//   public static void main(String[] args) {
//      DrawLines chart = new DrawLines("Browser Usage Statistics",
//         "Which Browser are you using?");
//      chart.pack();          
//      RefineryUtilities.centerFrameOnScreen(chart);          
//      chart.setVisible(true); 
//   }
}