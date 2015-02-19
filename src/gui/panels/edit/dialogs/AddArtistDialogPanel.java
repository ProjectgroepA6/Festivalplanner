package gui.panels.edit.dialogs;

import agenda.Agenda;
import agenda.Artist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gjoosen on 19/02/15.
 */
public class AddArtistDialogPanel extends JDialog{

    private JTextField name, genre;
    private Agenda agenda;

    private DefaultListModel model;

    public AddArtistDialogPanel(Agenda agenda, DefaultListModel model){
        this.agenda = agenda;
        this.model = model;

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(new Label("new Stage"));
        main.add(this.namePanel());
        main.add(this.genrePanel());
        main.add(this.buttons());
        super.add(main);
        super.setVisible(true);
        super.pack();

    }

    private JPanel genrePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new Label("Genre:"));
        this.genre = new JTextField();
        panel.add(this.genre);
        return panel;
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

    protected void save(){
        if(this.name.getText().equals("") && this.genre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Name and Genre can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }else if(this.genre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Genre can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }else if(this.name.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Name can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Save artist
        Artist artist = new Artist(this.name.getText(), this.genre.getText());
        this.model.addElement(artist);
        this.agenda.addArtist(artist);
        super.dispose();
    }

    private void cancel(){
        dispose();
    }
}