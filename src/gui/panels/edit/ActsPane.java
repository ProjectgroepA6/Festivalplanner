package gui.panels.edit;

import agenda.Act;
import agenda.Agenda;
import gui.panels.edit.dialogs.ActDialogPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gjoosen on 19/02/15.
 */
public class ActsPane extends JPanel {

    private JList actsList;
    private final Agenda agenda;
    private DefaultListModel model;
    
    private JPanel detailsPanel;

    public ActsPane(Agenda agenda, JPanel panel){
        this.agenda = agenda;
        this.model = new DefaultListModel();
        this.detailsPanel = panel;
        super.setLayout(new BorderLayout());
        super.add(new Label("Acts"), BorderLayout.NORTH);
        super.add(new JPanel(), BorderLayout.EAST);
        super.add(new JPanel(), BorderLayout.WEST);
        super.add(this.buttonRow(), BorderLayout.SOUTH);

        for(Act act: this.agenda.getActs()){
            this.model.addElement(act);
        }

        this.actsList = new JList();
        this.actsList.setModel(this.model);

        //the cell renderer.
        this.actsList.setCellRenderer(new ActCellRenderer());
        
        
        this.actsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if(e.getValueIsAdjusting()) {
                    JDialog dialog = new ActDialogPanel(ActsPane.this.agenda, model, (Act) actsList.getSelectedValue());
                    dialog.setLocation(getCenterOfScreen(dialog));
                    dialog.setLocationRelativeTo(null);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });

        //the JList inside a scrollPane.
        JScrollPane scrollPane = new JScrollPane(this.actsList);

        super.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel buttonRow(){
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        
        //add button
        JButton addButton = new JButton("add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new ActDialogPanel(agenda, model);
                dialog.pack();
                dialog.setLocation(getCenterOfScreen(dialog));
                dialog.setVisible(true);
            }
        });
        buttonPane.add(addButton);
        
        //remove button
        JButton removeButton = new JButton("remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object object = model.remove(actsList.getSelectedIndex());
                agenda.removeAct((Act) object);
                
                System.out.println(model.size());
                System.out.println(agenda.getActs().size());
            }
        });
        buttonPane.add(removeButton);
        return buttonPane;
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

class ActCellRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    public ActCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        Act act = (Act) value;
        setText(act.getName());
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
