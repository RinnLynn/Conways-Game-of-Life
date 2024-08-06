import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

    /*
     *  This is the Model component.
     */
    private static int SIZE = 150;
    private LifeCell[][] grid;
    public int[][] neighbors= new int[SIZE][SIZE];
    
    LifeView myView;
    Timer timer;

    /** Construct a new model using a particular file */
    public LifeModel(LifeView view, String fileName) throws IOException
    {       
        int r, c;
        grid = new LifeCell[SIZE][SIZE];
        for ( r = 0; r < SIZE; r++ )
            for ( c = 0; c < SIZE; c++ )
                grid[r][c] = new LifeCell();

        if ( fileName == null ) //use random population
        {                                           
            for ( r = 0; r < SIZE; r++ )
            {
                for ( c = 0; c < SIZE; c++ )
                {
                   if ( Math.random() > 0.85) //15% chance of a cell starting alive
                        grid[r][c].setAliveNow(true);
                   
                }
            }
            
            //grid[11][12].setAliveNow(true);
            //grid[12][12].setAliveNow(true);
             //         grid[13][12].setAliveNow(true);
             //                       grid[11][11].setAliveNow(true);
             //                                   grid[11][12].setAliveNow(true);
             //                                               grid[11][13].setAliveNow(true);
        }
        else
        {                 
            Scanner input = new Scanner(new File(fileName));
            int numInitialCells = input.nextInt();
            for (int count=0; count<numInitialCells; count++)
            {
                r = input.nextInt();
                c = input.nextInt();
                grid[r][c].setAliveNow(true);
            }
            input.close();
        }

        myView = view;
        myView.updateView(grid);

    }

    /** Constructor a randomized model */
    public LifeModel(LifeView view) throws IOException
    {
        this(view, null);
    }

    /** pause the simulation (the pause button in the GUI */
    public void pause()
    {
        timer.stop();
    }
    
    /** resume the simulation (the pause button in the GUI */
    public void resume()
    {
        timer.restart();
    }
    
    /** run the simulation (the pause button in the GUI */
    public void run()
    {
        timer = new Timer(50, this);
        timer.setCoalesce(true);
        timer.start();
       
    }
    
    public void reset()
    {
        int r, c;
        
                    for ( r = 0; r < SIZE; r++ )
            {
                for ( c = 0; c < SIZE; c++ )
                {
                   if ( Math.random() > 0.85) //15% chance of a cell starting alive
                        grid[r][c].setAliveNow(true);
                   
                }
            }
               // myView.updateView(grid);
           myView.updateView(grid);
           //timer.restart();    
    }
    
        public void randomColor()
    {
        myView.updateViewColor(true);
        //this.run();
    }
    
    
    /** called each time timer fires */
    public void actionPerformed(ActionEvent e)
    {
        oneGeneration();
        myView.updateView(grid);
         //pause();
    }

    /** main logic method for updating the state of the grid / simulation */
    private void oneGeneration()
    {
        /* 
         * student code here 
         */
        //int neighbors; 
        
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++)
            {
                neighbors[r][c] = 0;
                
                if (r > 0){
                        if (grid[r-1][c].isAliveNow() == true)
                        {
                        neighbors[r][c]++; 
                        }
                }
                //else {neighbors[r][c]++;}
                if (c>0){
                    if (grid[r][c-1].isAliveNow() == true)
                    {
                    neighbors[r][c]++; 
                    }
                }
                //else {neighbors[r][c]++;}
                if (r>0&&c>0){
                    if (grid[r-1][c-1].isAliveNow() == true)
                    {
                    neighbors[r][c]++; 
                    }
                }
                //else {neighbors[r][c]++;}                
                
                
                
                if (r< (SIZE-1)){
                    if (grid[r+1][c].isAliveNow() == true)
                    {
                    neighbors[r][c]++; 
                    }   
                } 
                //else {neighbors[r][c]++;}
                
                if(c<(SIZE-1)){
                if (grid[r][c+1].isAliveNow() == true)
                {
                    neighbors[r][c]++; 
                }
                }
                //else {neighbors[r][c]++;}
                
                
                
                 if(r<(SIZE-1)&&c<(SIZE-1)){
                if (grid[r+1][c+1].isAliveNow() == true)
                {
                    neighbors[r][c]++; 
                }              
                }
                //else {neighbors[r][c]++;}
            
                if (r>0&&c<(SIZE-1)){
                    if (grid[r-1][c+1].isAliveNow() == true)
                    {
                    neighbors[r][c]++; 
                    }
                }
                //else {neighbors[r][c]++;}              
                if (r<(SIZE-1)&&c>0){
                    if (grid[r+1][c-1].isAliveNow() == true)
                    {
                    neighbors[r][c]++; 
                    }
                }
                //else {neighbors[r][c]++;}                   
                

            

            }
        }
        
                //System.out.println(" Neibors "+neighbors[11][12]);
                //System.out.println(" Neibors "+neighbors[12][12]);
                //System.out.println(" Neibors "+neighbors[13][12]);
                
                for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++)
            {
                        if (neighbors[r][c] == 3 && grid[r][c].isAliveNow() ==false)
                {
                    grid[r][c].setAliveNow(true); 
                }
                else if (neighbors[r][c]  == 0 || neighbors[r][c]  == 1||neighbors[r][c]  == 4||neighbors[r][c]  == 5||
                        neighbors[r][c]  == 6|| neighbors[r][c]  == 7||neighbors[r][c]  ==8&& grid[r][c].isAliveNow() == true){
                    grid[r][c].setAliveNow(false);
                }
                else if ((neighbors[r][c] == 2||neighbors[r][c]  == 3)&& grid[r][c].isAliveNow() == true){
                    grid[r][c].setAliveNow(true);
                }
            }
        }
        
    }
}

