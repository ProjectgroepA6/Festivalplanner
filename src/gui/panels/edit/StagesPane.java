package gui.panels.edit;

import agenda.Agenda;
import agenda.Stage;
import gui.panels.edit.dialogs.StageDialogPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by gjoosen on 19/02/15.
 */
public class StagesPane extends JPanel {

    private JList stagesList;
    private final Agenda agenda;
    private DefaultListModel model;
    
    private JPanel detailsPanel;
    
    public StagesPane(Agenda agenda, JPanel detailsPanel){
        this.agenda = agenda;
        this.detailsPanel = detailsPanel;
        
        super.setLayout(new BorderLayout());
        super.add(new Label("Stages"), BorderLayout.NORTH);
        super.add(new JPanel(), BorderLayout.EAST);
        super.add(new JPanel(), BorderLayout.WEST);
        super.add(this.buttonRow(), BorderLayout.SOUTH);

        //the JList needs an array, so you have to go from the List to an array.

        this.model = new DefaultListModel();

        for(Stage stage: this.agenda.getStages()){
            this.model.addElement(stage);
        }
        
        this.stagesList = new JList();
        this.stagesList.setModel(this.model);

        //the cell renderer.
        this.stagesList.setCellRenderer(new StageCellRenderer());
        
        //double click
        this.stagesList.addMouseListener(new DoubleClickStage(this.stagesList, this));
        
        //the JList inside a scrollPane.
        JScrollPane scrollPane = new JScrollPane(this.stagesList);

        super.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel buttonRow(){
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        JButton addButton = new JButton("add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new StageDialogPanel(agenda, model);
                dialog.setLocationRelativeTo(null);
                dialog.pack();
                dialog.setLocation(getCenterOfScreen(dialog));
                dialog.setVisible(true);
            }
        });
        buttonPane.add(addButton);
        
        JButton remove = new JButton("remove");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object object = model.remove(stagesList.getSelectedIndex());
                agenda.removeStage(object);
                
                System.out.println(model);
                System.out.println(agenda.getStages());
            }
        });
        
        buttonPane.add(remove);
        return buttonPane;
    }
    
    public void editDialog(){
        JDialog dialog = new StageDialogPanel(StagesPane.this.agenda, model, (Stage) stagesList.getSelectedValue());
        dialog.setLocation(getCenterOfScreen(dialog));
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
    }

    private Point getCenterOfScreen(JDialog dialog){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int x = (int) ((screenSize.getWidth() - dialog.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - dialog.getHeight()) / 2);

        Point center = new Point(x, y);
        System.out.println(center.getX() + " - " + center.getY());
        return center;
    }
}

class StageCellRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    public StageCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        Stage stage = (Stage) value;
        setText(stage.getName());
        if (isSelected) {
            setBackground(HIGHLIGHT_COLOR);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
          return this;
    }   
}

class DoubleClickStage extends MouseAdapter {
    protected JList list;
    private StagesPane pane;

    public DoubleClickStage(JList l, StagesPane pane){
        this.pane = pane;
        list = l;
    }

    public void mouseClicked(MouseEvent e){
        if(e.getClickCount() == 2){
            int index = list.locationToIndex(e.getPoint());
            ListModel dlm = list.getModel();
            Object item = dlm.getElementAt(index);;
            list.ensureIndexIsVisible(index);
            System.out.println("Double clicked on " + item);
            this.pane.editDialog();
        }
    }
}
