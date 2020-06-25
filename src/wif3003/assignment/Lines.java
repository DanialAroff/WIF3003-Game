
package wif3003.assignment;

import java.awt.Color; 
import java.awt.BasicStroke; 

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


public class Lines extends ApplicationFrame {

   public Lines(String applicationTitle, String chartTitle) {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Category" ,
         "Score" ,
         createDataset() ,
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

   public static void main(String[] args) {
      Lines chart = new Lines("Browser Usage Statistics",
         "Which Browser are you using?");
      chart.pack();          
      RefineryUtilities.centerFrameOnScreen(chart);          
      chart.setVisible(true); 
   }
}