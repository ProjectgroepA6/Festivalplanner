package gui.frames;

import gui.menubar.MenuBar;
import gui.panels.agenda.AgendaPane;
import gui.panels.edit.EditPane;

import javax.swing.JFrame;
import javax.swing.JPanel;

import agenda.Agenda;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
    public enum Views{EDITOR, SIMULATOR, AGENDA};
    
    private Agenda agenda;
    private JPanel currentPanel;
    
    public MainFrame(){
        this.agenda = new Agenda();
        this.currentPanel = new EditPane(this.agenda);
        this.add(this.currentPanel);
		this.setResizable(true);
		this.setBounds(100,100,1440,900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(false);
		this.setJMenuBar(new MenuBar(this));
		this.setVisible(true);

        if(System.getProperties().getProperty("os.name").equals("Mac OS X")) {
            try {
                Class c = Class.forName("com.apple.eawt.FullScreenUtilities");
                Method m = c.getMethod("setWindowCanFullScreen", Window.class, Boolean.TYPE);
                m.invoke(c, this, true);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
	}
    
    public void changeView(Views view){
        switch(view){
            case EDITOR:
                this.updateView(new EditPane(this.agenda));
                break;
            case AGENDA:
                this.updateView(new AgendaPane(this.agenda));
                break;
            case SIMULATOR:
                //komt later nog.
                break;
        }
    }
    
    private void updateView(JPanel panel){
        this.currentPanel = panel;
        this.setContentPane(this.currentPanel);
        this.revalidate();
    }
}
