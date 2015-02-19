package gui.panels.edit.dialogs;

import agenda.Agenda;
import agenda.Artist;
import agenda.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gjoosen on 19/02/15.
 */
public class AddStageDialogPanel extends JDialog{

    private Agenda agenda;
    private DefaultListModel model;
    private JTextField name;
    
    public AddStageDialogPanel(Agenda agenda, DefaultListModel model){
        this.agenda = agenda;
        this.model = model;
        
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(new Label("new Stage"));
        main.add(this.namePanel());
        main.add(this.buttons());
        super.add(main);
        super.setVisible(true);
        super.pack();


    }
    
    private JPanel namePanel(){
        JPanel name = new JPanel();
        name.setLayout(new BoxLayout(name, BoxLayout.X_AXIS));
        name.add(new JLabel("Name"));
        this.name = new JTextField();
        name.add(this.name);
        return name;
    }

    private JPanel buttons(){
        JPanel buttons = new JPanel();
        
        JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        buttons.add(save);
        
        JButton cancel = new JButton("cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        buttons.add(cancel);
        return buttons;
    }
    
    private void save(){
        if(this.name.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Name can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }else{
            Stage stage = new Stage(this.name.getText());
            this.agenda.addStage(stage);
            this.model.addElement(stage);
            dispose();
        }
    }
    
    private void cancel(){
        dispose();
    }
    
    protected JButton saveButton() {
        JButton button = new JButton("save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        return button;
    }
}