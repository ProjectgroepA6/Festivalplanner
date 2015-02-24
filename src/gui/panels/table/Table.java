package gui.panels.table;

import agenda.Agenda;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gjoosen on 24/02/15.
 */

public class Table extends  JPanel{
    
    private Agenda agenda;
    
    public Table(Agenda agenda){
        this.agenda = agenda;
        System.out.println("test");
        JTable table = new JTable(new TableModel(this.agenda));
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        super.setLayout(new BorderLayout());
        super.add(scrollPane, BorderLayout.CENTER);
    }
}

