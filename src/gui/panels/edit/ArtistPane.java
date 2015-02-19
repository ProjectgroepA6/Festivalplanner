package gui.panels.edit;

import agenda.Agenda;
import agenda.Artist;
import gui.panels.edit.dialogs.AddArtistDialogPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjoosen on 13/02/15.
 */
public class ArtistPane extends JPanel {

    private JList artistList;
    private Agenda agenda;
    private DefaultListModel model;
    
    public ArtistPane(Agenda agenda){
        this.agenda = agenda;
        this.model = new DefaultListModel();

        super.setLayout(new BorderLayout());
        super.add(new Label("Artists"), BorderLayout.NORTH);
        super.add(new JPanel(), BorderLayout.EAST);
        super.add(new JPanel(), BorderLayout.WEST);
        super.add(this.buttonRow(), BorderLayout.SOUTH);

        //the JList needs an array, so you have to go from the List to an array.

        for(Artist artist: this.agenda.getArtists()){
            this.model.addElement(artist);
        }
        
        //initialize the JList with the artist objects.
        this.artistList = new JList();

        this.artistList.setModel(this.model);
        
        //the cell renderer.
        this.artistList.setCellRenderer(new ArtistCellRenderer());

        //the JList inside a scrollPane.
        JScrollPane scrollPane = new JScrollPane(this.artistList);
        super.add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel buttonRow(){
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        
        JButton addArtist = new JButton("add");
        addArtist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new AddArtistDialogPanel(agenda, model);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        
        buttonPane.add(addArtist);
        
        JButton removeButton = new JButton("remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object object = model.remove(artistList.getSelectedIndex());
                agenda.removeArtist((Artist) object);
                
                System.out.println(agenda.getArtists());
                System.out.println(model);
            }
        });
        buttonPane.add(removeButton);
        
        return buttonPane;
    }
}

class ArtistCellRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    public ArtistCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        Artist stage = (Artist) value;
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
