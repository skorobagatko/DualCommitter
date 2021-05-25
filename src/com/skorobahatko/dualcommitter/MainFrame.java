package com.skorobahatko.dualcommitter;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.DefaultListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JFileChooser fileChooser;

	private JTextField repo1PathTextField;
	private JButton repo1ChooseButton;
	private JLabel unstagedChangesRepo1Label;
	private JList<String> unstagedChangesRepo1List;
	private JLabel stagedChangesRepo1Label;
	private JList<String> stagedChangesRepo1List;

	private JTextField repo2PathTextField;
	private JButton repo2ChooseButton;
	private JLabel unstagedChangesRepo2Label;
	private JList<String> unstagedChangesRepo2List;
	private JLabel stagedChangesRepo2Label;
	private JList<String> stagedChangesRepo2List;

	private JLabel commitMessageLabel;
	private JTextArea commitMessageTextArea;
	private JButton stageChangesButton;
	private JButton unstageChangesButton;
	private JButton commitButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() throws InterruptedException {
		initComponents();
		initMainFrame();
	}

	private void initComponents() throws InterruptedException {
		initializeFileChooser();
		initializeRepoPathTextFields();

		repo1ChooseButton = new JButton("Choose");
		repo2ChooseButton = new JButton("Choose");
		initializeChooseRepoButton(repo1ChooseButton, repo1PathTextField);
		initializeChooseRepoButton(repo2ChooseButton, repo2PathTextField);

		unstagedChangesRepo1Label = new JLabel("Unstaged Changes");
		unstagedChangesRepo2Label = new JLabel("Unstaged Changes");

		initializeUnstagedChangesLists();

		stagedChangesRepo1Label = new JLabel("Staged Changes");
		stagedChangesRepo2Label = new JLabel("Staged Changes");

		initializeStagedChangesLists();

		commitMessageLabel = new JLabel("Commit Message");
		commitMessageTextArea = new JTextArea();

		initStageChangesButton();
		initUnstageChangesButton();
		initCommitButton();
	}

	private void initMainFrame() {
		setTitle("DualCommitter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 628);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPane
										.createParallelGroup(
												Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
												.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(
														unstagedChangesRepo1Label, Alignment.LEADING)
												.addGroup(
														gl_contentPane.createSequentialGroup()
																.addComponent(
																		stagedChangesRepo1Label,
																		GroupLayout.PREFERRED_SIZE, 107,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(178))
												.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
														.createParallelGroup(Alignment.TRAILING)
														.addComponent(stagedChangesRepo1List, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
														.addComponent(unstagedChangesRepo1List, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(repo1PathTextField)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(repo1ChooseButton)))
														.addGap(12)))
												.addGap(20)
												.addGroup(gl_contentPane.createParallelGroup(
														Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(stagedChangesRepo2List,
																		GroupLayout.PREFERRED_SIZE, 273,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED))
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				stagedChangesRepo2Label,
																				GroupLayout.PREFERRED_SIZE, 107,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED))
																.addGroup(gl_contentPane
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_contentPane
																				.createParallelGroup(Alignment.LEADING)
																				.addGroup(gl_contentPane
																						.createSequentialGroup()
																						.addComponent(
																								repo2PathTextField,
																								GroupLayout.PREFERRED_SIZE,
																								190,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(repo2ChooseButton,
																								GroupLayout.PREFERRED_SIZE,
																								77,
																								GroupLayout.PREFERRED_SIZE))
																				.addComponent(unstagedChangesRepo2List,
																						GroupLayout.PREFERRED_SIZE, 273,
																						GroupLayout.PREFERRED_SIZE))
																		.addGroup(gl_contentPane.createSequentialGroup()
																				.addComponent(unstagedChangesRepo2Label,
																						GroupLayout.PREFERRED_SIZE, 107,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.RELATED))))))
										.addComponent(commitMessageLabel, GroupLayout.PREFERRED_SIZE, 107,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(commitMessageTextArea, GroupLayout.PREFERRED_SIZE, 426,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(commitButton, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(stageChangesButton, Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(unstageChangesButton))))
								.addContainerGap(11, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(repo1ChooseButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(repo1PathTextField, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(repo2PathTextField, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
								.addComponent(repo2ChooseButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)))
				.addGap(22)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(unstagedChangesRepo1Label)
						.addComponent(unstagedChangesRepo2Label))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(unstagedChangesRepo1List, GroupLayout.PREFERRED_SIZE, 159,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(unstagedChangesRepo2List, GroupLayout.PREFERRED_SIZE, 159,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(stagedChangesRepo1Label)
						.addComponent(stagedChangesRepo2Label))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(stagedChangesRepo1List, GroupLayout.PREFERRED_SIZE, 159,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(stagedChangesRepo2List, GroupLayout.PREFERRED_SIZE, 159,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(commitMessageLabel).addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(stageChangesButton)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(unstageChangesButton)
								.addGap(7).addComponent(commitButton))
						.addComponent(commitMessageTextArea, GroupLayout.PREFERRED_SIZE, 91,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(74, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	private void initializeFileChooser() {
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select path to the repository");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

	private void initializeRepoPathTextFields() {
		Font font = new Font("Monospaced", Font.PLAIN, 12);

		repo1PathTextField = new JTextField();
		repo1PathTextField.setColumns(10);
		repo1PathTextField.setFont(font);
		repo1PathTextField.setText("D:\\tmp\\test");
		repo1PathTextField.setEditable(false);

		repo2PathTextField = new JTextField();
		repo2PathTextField.setColumns(10);
		repo2PathTextField.setFont(font);
		repo2PathTextField.setText("D:\\tmp\\test2");
		repo2PathTextField.setEditable(false);
		
		repo1PathTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					fillUnstagedChangesLists();
					fillStagedChangesLists();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}

	private void initializeChooseRepoButton(JButton button, JTextField pathToRepoTextField) {
		button.addActionListener(e -> {
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File pathToRepo = fileChooser.getSelectedFile();
				pathToRepoTextField.setText(pathToRepo.getAbsolutePath());
			}
		});
	}

	private void initializeUnstagedChangesLists() {
		unstagedChangesRepo1List = new JList<>();
		unstagedChangesRepo2List = new JList<>();
		unstagedChangesRepo1List.setSelectionModel(getDefaultSelectionModel());
		unstagedChangesRepo2List.setSelectionModel(getDefaultSelectionModel());

		try {
			fillUnstagedChangesLists();
		} catch (Exception e) {
			e.printStackTrace();
		}

		unstagedChangesRepo1List.setForeground(Color.RED);
		unstagedChangesRepo2List.setForeground(Color.RED);
	}

	private void fillUnstagedChangesLists() throws Exception {
		String[] repo1UnstagedChangesList = getUnstagedChangesList(repo1PathTextField.getText());
		String[] repo2UnstagedChangesList = getUnstagedChangesList(repo2PathTextField.getText());

		unstagedChangesRepo1List.setListData(repo1UnstagedChangesList);
		unstagedChangesRepo2List.setListData(repo2UnstagedChangesList);
	}

	private void initializeStagedChangesLists() {
		stagedChangesRepo1List = new JList<>();
		stagedChangesRepo2List = new JList<>();
		stagedChangesRepo1List.setSelectionModel(getDefaultSelectionModel());
		stagedChangesRepo2List.setSelectionModel(getDefaultSelectionModel());

		try {
			fillStagedChangesLists();
		} catch (Exception e) {
			e.printStackTrace();
		}

		stagedChangesRepo1List.setForeground(Color.GREEN);
		stagedChangesRepo2List.setForeground(Color.GREEN);
	}

	private void fillStagedChangesLists() throws Exception {
		String[] repo1StagedChangesList = getStagedChangesList(repo1PathTextField.getText());
		String[] repo2StagedChangesList = getStagedChangesList(repo2PathTextField.getText());

		stagedChangesRepo1List.setListData(repo1StagedChangesList);
		stagedChangesRepo2List.setListData(repo2StagedChangesList);
	}

	private ListSelectionModel getDefaultSelectionModel() {
		return new DefaultListSelectionModel() {
			boolean gestureStarted = false;

			@Override
			public void setSelectionInterval(int index0, int index1) {
				if (!gestureStarted) {
					if (isSelectedIndex(index0)) {
						super.removeSelectionInterval(index0, index1);
					} else {
						super.addSelectionInterval(index0, index1);
					}
				}
				gestureStarted = true;
			}

			@Override
			public void setValueIsAdjusting(boolean isAdjusting) {
				if (isAdjusting == false) {
					gestureStarted = false;
				}
			}
		};
	}

	private String[] getUnstagedChangesList(String path) throws Exception {
		String command = "git ls-files --deleted --modified --others --exclude-standard";
		Process process = executeCommand(command, path);

		if (process.exitValue() == 0) {
			List<String> result = readProcessExecutionResult(process);
			return result.toArray(new String[0]);
		} else {
			return new String[0];
		}

		return result.toArray(new String[0]);
	}

	private String[] getStagedChangesList(String path) throws Exception {
		String command = "git diff --name-only --cached";
		Process process = executeCommand(command, path);

		List<String> result = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			result.add(line);
		}

		return result.toArray(new String[0]);
	}

	private void initStageChangesButton() {
		stageChangesButton = new JButton("Stage Changes");

		stageChangesButton.addActionListener(e -> {

			stageChangesFor(unstagedChangesRepo1List, repo1PathTextField);
			stageChangesFor(unstagedChangesRepo2List, repo2PathTextField);

			try {
				fillUnstagedChangesLists();
				fillStagedChangesLists();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			unstagedChangesRepo1List.clearSelection();
			unstagedChangesRepo2List.clearSelection();
			stagedChangesRepo1List.clearSelection();
			stagedChangesRepo2List.clearSelection();
		});
	}

	private void stageChangesFor(JList<String> unstagedChangesList, JTextField pathTextField) {
		List<String> selectedUnstagedChanges = unstagedChangesList.getSelectedValuesList();
		for (String fileName : selectedUnstagedChanges) {
			String command = "git add \"" + fileName + "\"";
			try {
				executeCommand(command, pathTextField.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void initUnstageChangesButton() {
		unstageChangesButton = new JButton("Unstage Changes");

		unstageChangesButton.addActionListener(e -> {

			unstageChangesFor(stagedChangesRepo1List, repo1PathTextField);
			unstageChangesFor(stagedChangesRepo2List, repo2PathTextField);

			try {
				fillUnstagedChangesLists();
				fillStagedChangesLists();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			unstagedChangesRepo1List.clearSelection();
			unstagedChangesRepo2List.clearSelection();
			stagedChangesRepo1List.clearSelection();
			stagedChangesRepo2List.clearSelection();
		});
	}

	private void unstageChangesFor(JList<String> stagedChangesList, JTextField pathTextField) {
		List<String> selectedStagedChanges = stagedChangesList.getSelectedValuesList();
		for (String fileName : selectedStagedChanges) {
			String command = "git reset HEAD \"" + fileName + "\"";
			try {
				executeCommand(command, pathTextField.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void initCommitButton() {
		commitButton = new JButton("Commit");

		commitButton.addActionListener(e -> {
					
			if (stagedChangesRepo1List.getModel().getSize() != 0) {
				commitChangesFor(stagedChangesRepo1List, repo1PathTextField);
			}
			
			if (stagedChangesRepo2List.getModel().getSize() != 0) {
				commitChangesFor(stagedChangesRepo2List, repo2PathTextField);
			}
						
			try {
				fillUnstagedChangesLists();
				fillStagedChangesLists();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			unstagedChangesRepo1List.clearSelection();
			unstagedChangesRepo2List.clearSelection();
			stagedChangesRepo1List.clearSelection();
			stagedChangesRepo2List.clearSelection();
			
			commitMessageTextArea.setText("");
		});
	}

	private void commitChangesFor(JList<String> stagedChangesList, JTextField pathTextField) {

		String command = "git commit -m \"" + commitMessageTextArea.getText() + "\"";
		try {
			executeCommand(command, pathTextField.getText());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private Process executeCommand(String command, String dir) throws Exception {
		Process process = Runtime.getRuntime().exec("cmd /c " + command, null, new File(dir));
		process.waitFor();

		return process;
	}

}
