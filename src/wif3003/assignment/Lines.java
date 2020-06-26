
package wif3003.assignment;

import java.awt.Color; 
import java.awt.BasicStroke; 
import java.util.ArrayList;
import java.util.Random;

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

    
    private static ArrayList<Color> colours = new ArrayList<>();
    
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
      renderer.setSeriesPaint(0 , colours.get(0));
      renderer.setSeriesPaint(1 , colours.get(1));
      renderer.setSeriesStroke(0 , new BasicStroke(3.0f));
      renderer.setSeriesStroke(1 , new BasicStroke(3.0f));
      renderer.setSeriesPaint(2 , colours.get(2));
      renderer.setSeriesPaint(3 , colours.get(3));
      renderer.setSeriesStroke(2 , new BasicStroke(3.0f));
      renderer.setSeriesStroke(3 , new BasicStroke(3.0f));
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
      
       final XYSeries firefox2 = new XYSeries("Firefox1");          
      firefox2.add(20.0 , 10.0);                    
      firefox2.add(60.0 , 60.0);          
      
      final XYSeries chrome1 = new XYSeries("Chrome1");          
      chrome1.add(100.0 , 100.0);          
//      chrome.add( 2.0 , 5.0 );          
      chrome1.add(200.0 , 60.0);          
   
      final XYSeriesCollection dataset = new XYSeriesCollection();          
      dataset.addSeries(firefox);          
      dataset.addSeries(chrome);    
      dataset.addSeries(firefox2);          
      dataset.addSeries(chrome1);     
      return dataset;
   }

   
   public static void generateColour(int t){
       
       //to get rainbow, pastel colors
            Random random = new Random();
            
            for(int i = 0; i < t; i++){
             
            float hue = random.nextFloat();
            final float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
            final float luminance = 1.0f; //1.0 for brighter, 0.0 for black
            Color temp = Color.getHSBColor(hue, saturation, luminance);
            
            while(colours.contains(temp)){
                
                hue = random.nextFloat();
            }
       
            temp = Color.getHSBColor(hue, saturation, luminance);
            colours.add(temp);

            }
            
         System.out.println(colours.toString()); 
       
   }
   
   public static void main(String[] args) {
       
       generateColour(10);
       
      Lines chart = new Lines("Browser Usage Statistics",
         "Which Browser are you using?");
      chart.pack();          
      RefineryUtilities.centerFrameOnScreen(chart);          
      chart.setVisible(true); 
   }
}