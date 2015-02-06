package src;

import src.agenda.Act;
import src.agenda.Agenda;
import src.agenda.Artist;
import src.agenda.Stage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI {

	private JFrame frame;

	int posX=0,posY=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
        
        //test agenda.
        Agenda agenda = new Agenda();
        agenda.addArtist(new Artist("Iron Maiden", "Heavy metal"));
        agenda.addStage(new Stage("Mainstage"));
        agenda.addAct(new Act(agenda.getStages().get(0), "Heavy metal", agenda.getArtists().get(0)));
        
        System.out.println(agenda);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1020, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		
		
		JPanel menuBarPanel = new JPanel();
		menuBarPanel.setBackground(new Color(0, 128, 128));
		menuBarPanel.setBounds(0, 0, 1020, 45);
		frame.getContentPane().add(menuBarPanel);
		menuBarPanel.addMouseListener(new MouseAdapter()
		{
			   public void mousePressed(MouseEvent e)
			   {
			      posX=e.getX();
			      posY=e.getY();
			   }
			});
		menuBarPanel.addMouseMotionListener(new MouseAdapter()
		{
		     public void mouseDragged(MouseEvent evt)
		     {
				//sets frame position when mouse dragged			
				setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
							
		     }
		});
		ImageIcon closeIcon = new ImageIcon("res/img/Xicon.png");
	    JLabel closeLabel = new JLabel(closeIcon);
	    closeLabel.setBounds(983, 11, 18, 18);
	    closeLabel.addMouseListener(new MouseAdapter() {
	      public void mouseClicked(MouseEvent me) {
	    	  frame.dispose();
	      }
	    });
	    ImageIcon min = new ImageIcon("res/img/_icon.png");
	    JLabel minLabel = new JLabel(min);
	    minLabel.setBounds(948, 11, 18, 18);
	    minLabel.addMouseListener(new MouseAdapter() {
	      public void mouseClicked(MouseEvent me) {
	    	  frame.setState(Frame.ICONIFIED);
	      }
	    });
	    menuBarPanel.setLayout(null);
	    menuBarPanel.add(closeLabel);
	    menuBarPanel.add(minLabel);
	    
		
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(51, 160, 160));
		contentPanel.setBounds(0, 45, 1020, 655);
		frame.getContentPane().add(contentPanel);
	}

	protected void setLocation(int i, int j) {
		frame.setLocation(i,j);
	}
}
