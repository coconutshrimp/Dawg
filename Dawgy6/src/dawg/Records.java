/**
 * 
 */
package dawg;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author jammavi
 *
 */
public class Records extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<String> contestsList;

	public Records() {

		addComponents();

	}

	private class RecordsViewListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				String selected = contestsList.getSelectedValue();
				ControllingFrame.setSelectedContest(selected);
			}
		}

	}

	private void addComponents() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		// Create a label for the title
		JLabel titleLabel = new JLabel("Previous Contests");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Create a list of previous contests
		String[] contests = { "2021", "2022", "Current Contest"};
		
		contestsList = new JList<>(contests);
		contestsList.addListSelectionListener(new RecordsViewListener());
		contestsList.setFont(new Font("Arial", Font.PLAIN, 16));
		contestsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane listScrollPane = new JScrollPane(contestsList);

		
		add(titleLabel, BorderLayout.NORTH);
		add(listScrollPane, BorderLayout.CENTER);

	}
}