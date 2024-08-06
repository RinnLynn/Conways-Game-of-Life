 

import java.awt.*;
import javax.swing.*;
import java.util.Random;


/** The view (graphical) component */
public class LifeView extends JPanel
{
    private static final long serialVersionUID = 1L;
    private static int SIZE = 60;

    /** store a reference to the current state of the grid */
    private LifeCell[][] grid;
    private Color color=Color.BLUE;
    private boolean randomColor=false;

    public LifeView()
    {
        grid = new LifeCell[SIZE][SIZE];
    }

    /** entry point from the model, requests grid be redisplayed */
    public void updateView( LifeCell[][] mg )
    {
        grid = mg;
        //randomColor=false;
        repaint();
    }

    public void updateViewColor(boolean randomColor)
    {
        if(randomColor=true) this.randomColor=true;

    }    
    
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int testWidth = getWidth() / (SIZE+1);
        int testHeight = getHeight() / (SIZE+1);
        // keep each life cell square
        int boxSize = Math.min(testHeight, testWidth);
        Random rand = new Random();
        
        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
                if (grid[r][c] != null)
                {
                    if ( grid[r][c].isAliveNow() )
                        //g.setColor( Color.BLUE );
                        if (randomColor) {
                                float rr = rand.nextFloat();
                                float gg = rand.nextFloat();
                                float bb = rand.nextFloat();
                                color = new Color(rr, gg, bb);    
                        g.setColor( color );
                        } else {
                           g.setColor(color); 
                        }
                    else
                        g.setColor( new Color(235,235,255) );

                    g.fillRect( (c+1)*boxSize, (r+1)* boxSize, boxSize-2, boxSize-2);
                }
            }
        }
    }
}
