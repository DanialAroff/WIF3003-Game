
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;


public class DrawLines extends ApplicationFrame {

    private ArrayList<Color> colours = new ArrayList<>();
    
    
   public DrawLines(String applicationTitle, String chartTitle, ArrayList<ArrayList<Line>> edges, int t) {
      super(applicationTitle);
      generateColour(t);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,
         "X-Axis" ,
         "Y-Axis" , drawLines(edges),
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel(xylineChart);
      chartPanel.setPreferredSize( new java.awt.Dimension(560 , 367));
      final XYPlot plot = xylineChart.getXYPlot( );
      
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
      
      int count = 0;
      //Assign colours for each thread
      for(int k = 0; k < edges.size();k++){
          
          Color colour = colours.get(k);
          
          for(int l = 0; l < edges.get(k).size(); l++){
            renderer.setSeriesPaint(count , colour);
            renderer.setSeriesStroke(count , new BasicStroke(3.0f));
            count++;
          }
      }
      
      xylineChart.removeLegend();
      plot.setRenderer(renderer);
      
      // setting the range of x-axis and y-axis so it can be 1000x1000
      NumberAxis domain = (NumberAxis)plot.getDomainAxis();
      domain.setRange(0.00, 1000.0);
      NumberAxis range = (NumberAxis)plot.getRangeAxis();
      range.setRange(0.00, 1000.0);
      setContentPane(chartPanel); 
   }
   
    private XYDataset drawLines(ArrayList<ArrayList<Line>> edges) {
        
        ArrayList<XYSeries> listOfSeries = new ArrayList();
        final XYSeriesCollection dataset = new XYSeriesCollection();      
        int lineNum = 0;
        
        for(int k = 0; k < edges.size(); k++){
            System.out.println("Thread " + k + " Lines created : " + edges.get(k).size() + " Colour : " + colours.get(k).toString());
        for (int i = 0; i < edges.get(k).size(); i++) {
            lineNum++;
            XYSeries currentSeries = new XYSeries("Line " + (lineNum));
            Line currentLine = edges.get(k).get(i);
            currentSeries.add(currentLine.getX1(), currentLine.getY1());
            currentSeries.add(currentLine.getX2(), currentLine.getY2());
            listOfSeries.add(currentSeries);
        }
      
   }
          for (int j = 0; j < listOfSeries.size(); j++) {
            dataset.addSeries(listOfSeries.get(j));
        }
       return dataset;
    
}
    
    //To generate colour for each thread using HSB and add it to a list
        private void generateColour(int t){
       
            Random random = new Random();
            
            for(int i = 0; i < t; i++){
             
            float hue = random.nextFloat();
            final float saturation = 0.9f;
            final float luminance = 1.0f;
            Color temp = Color.getHSBColor(hue, saturation, luminance);
            
            //To ensure it does not generate same colour
            while(colours.contains(temp)){
                
                hue = random.nextFloat();
            }
       
            temp = Color.getHSBColor(hue, saturation, luminance);
            colours.add(temp);

            }
       
   }
    
    
    
}