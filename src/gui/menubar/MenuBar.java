package gui.menubar;

import gui.frames.MainFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{

    private MainFrame mainFrame;
    
	public MenuBar(MainFrame frame){
        this.mainFrame = frame;
		initialize();
	}
	
	private void initialize(){
		setLayout(new FlowLayout(80));
		JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");
        this.add(fileMenu);
        this.add(editMenu);
        this.add(viewMenu);
        this.add(helpMenu);
        
        JMenuItem newAction = new JMenuItem("New");
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem cutAction = new JMenuItem("Cut");
        JMenuItem copyAction = new JMenuItem("Copy");
        JMenuItem pasteAction = new JMenuItem("Paste");
        
        //view 
        JMenuItem editorView = new JMenuItem("editor");
        JMenuItem agendaView = new JMenuItem("agenda");
        JMenuItem simulatorView = new JMenuItem("simulator");
        
        newAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        openAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        exitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        cutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        copyAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        pasteAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        
        fileMenu.add(newAction);
        newAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the new action");
            }
        });
        fileMenu.add(openAction);
        openAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the open action");
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        exitAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        editMenu.add(cutAction);
        cutAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the cut action");
            }
        });
        editMenu.add(copyAction);
        copyAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the copy action");
            }
        });
        editMenu.add(pasteAction);
        pasteAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the paste action");
            }
        });
        
        //view
        viewMenu.add(editorView);
        editorView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("editor view");
                mainFrame.changeView(MainFrame.Views.EDITOR);
            }
        });

        viewMenu.add(simulatorView);
        simulatorView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("simulator view");
                mainFrame.changeView(MainFrame.Views.SIMULATOR);

            }
        });
        
        viewMenu.add(agendaView);
        agendaView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("agenda view");
                mainFrame.changeView(MainFrame.Views.AGENDA);

            }
        });
	}
	
}
