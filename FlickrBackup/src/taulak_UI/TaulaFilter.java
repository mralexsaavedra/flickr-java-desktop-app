package taulak_UI;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableRowSorter;
import java.awt.Dimension;

public class TaulaFilter extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JTable table;
    private JTextField filterText;
    private JTextField statusText;
    private TableRowSorter<TaulaModeloa> sorter;

    public TaulaFilter() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        TaulaModeloa model = new TaulaModeloa();
        sorter = new TableRowSorter<TaulaModeloa>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        int viewRow = table.getSelectedRow();
                        if (viewRow < 0) {
                            statusText.setText("");
                        } else {
                            int modelRow = 
                                table.convertRowIndexToModel(viewRow);
                            statusText.setText(
                                String.format("Selected Row in view: %d. " +
                                    "Selected Row in model: %d.", 
                                    viewRow, modelRow));
                        }
                    }
                }
        );


        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        JPanel form = new JPanel(new SpringLayout());
        JLabel l1 = new JLabel("Filter Text:", SwingConstants.TRAILING);
        form.add(l1);
        filterText = new JTextField();
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        l1.setLabelFor(filterText);
        form.add(filterText);
        JLabel l2 = new JLabel("Status:", SwingConstants.TRAILING);
        form.add(l2);
        statusText = new JTextField();
        l2.setLabelFor(statusText);
        form.add(statusText);
        SpringUtilities.makeCompactGrid(form, 2, 2, 6, 6, 6, 6);
        add(form);
    }

    /** 
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() {
        RowFilter<TaulaModeloa, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(filterText.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }


    private static void createAndShowGUI() {
        JFrame frame = new JFrame("TableFilterDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TaulaFilter newContentPane = new TaulaFilter();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
}
