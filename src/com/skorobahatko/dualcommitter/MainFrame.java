package com.skorobahatko.dualcommitter;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private static final Border BORDER = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

	private JPanel contentPane;
	private JFileChooser fileChooser;

	private JTextField repo1PathTextField;
	private JButton repo1ChooseButton;
	private JLabel unstagedChangesRepo1Label;
	private JScrollPane unstagedChangesRepo1ScrollPane;
	private JList<String> unstagedChangesRepo1List;
	private JLabel stagedChangesRepo1Label;
	private JScrollPane stagedChangesRepo1ScrollPane;
	private JList<String> stagedChangesRepo1List;

	private JTextField repo2PathTextField;
	private JButton repo2ChooseButton;
	private JLabel unstagedChangesRepo2Label;
	private JScrollPane unstagedChangesRepo2ScrollPane;
	private JList<String> unstagedChangesRepo2List;
	private JLabel stagedChangesRepo2Label;
	private JScrollPane stagedChangesRepo2ScrollPane;
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
		initializeUnstagedChangesLists();
		initializeStagedChangesLists();
		initLabels();
		initCommitMessageTextField();
		initStageChangesButton();
		initUnstageChangesButton();
		initCommitButton();
	}

	private void initMainFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setTitle("DualCommitter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 628);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.DARK_GRAY);
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
														.addComponent(stagedChangesRepo1ScrollPane, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
														.addComponent(unstagedChangesRepo1ScrollPane, Alignment.LEADING,
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
																.addComponent(stagedChangesRepo2ScrollPane,
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
																				.addComponent(unstagedChangesRepo2ScrollPane,
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
						.addComponent(unstagedChangesRepo1ScrollPane, GroupLayout.PREFERRED_SIZE, 159,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(unstagedChangesRepo2ScrollPane, GroupLayout.PREFERRED_SIZE, 159,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(stagedChangesRepo1Label)
						.addComponent(stagedChangesRepo2Label))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(stagedChangesRepo1ScrollPane, GroupLayout.PREFERRED_SIZE, 159,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(stagedChangesRepo2ScrollPane, GroupLayout.PREFERRED_SIZE, 159,
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
		repo1PathTextField.setText("D:\\workspace\\MultiCommiter4");
		repo1PathTextField.setEditable(false);
		repo1PathTextField.setBackground(Color.DARK_GRAY);
		repo1PathTextField.setForeground(Color.LIGHT_GRAY);
		repo1PathTextField.setBorder(BorderFactory.createCompoundBorder(BORDER,
	            BorderFactory.createEmptyBorder(4, 4, 4, 4)));

		repo2PathTextField = new JTextField();
		repo2PathTextField.setColumns(10);
		repo2PathTextField.setFont(font);
		repo2PathTextField.setText("D:\\\\workspace\\\\MultiCommiter4");
		repo2PathTextField.setEditable(false);
		repo2PathTextField.setBackground(Color.DARK_GRAY);
		repo2PathTextField.setForeground(Color.LIGHT_GRAY);
		repo2PathTextField.setBorder(BorderFactory.createCompoundBorder(BORDER,
	            BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		
		repo1PathTextField.getDocument().addDocumentListener(getPathTextFieldChangeListener());
		repo2PathTextField.getDocument().addDocumentListener(getPathTextFieldChangeListener());
	}
	
	private DocumentListener getPathTextFieldChangeListener() {
		return new DocumentListener() {
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
		};
	}

	private void initializeChooseRepoButton(JButton button, JTextField pathToRepoTextField) {
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.LIGHT_GRAY);
		
		button.addActionListener(e -> {
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File pathToRepo = fileChooser.getSelectedFile();
				pathToRepoTextField.setText(pathToRepo.getAbsolutePath());
			}
		});
	}

	private void initializeUnstagedChangesLists() {		
		unstagedChangesRepo1List = new JList<>();
		unstagedChangesRepo1List.setSelectionModel(getDefaultSelectionModel());
		unstagedChangesRepo1List.setBackground(Color.DARK_GRAY);
		unstagedChangesRepo1List.setForeground(Color.RED);
		
		unstagedChangesRepo1ScrollPane = new JScrollPane();
		unstagedChangesRepo1ScrollPane.setViewportView(unstagedChangesRepo1List);
		unstagedChangesRepo1ScrollPane.setBorder(BORDER);
		unstagedChangesRepo1ScrollPane.getVerticalScrollBar().setUI(getScrollBarUI());
		unstagedChangesRepo1ScrollPane.getHorizontalScrollBar().setUI(getScrollBarUI());
		
		unstagedChangesRepo2List = new JList<>();		
		unstagedChangesRepo2List.setSelectionModel(getDefaultSelectionModel());
		unstagedChangesRepo2List.setBackground(Color.DARK_GRAY);
		unstagedChangesRepo2List.setForeground(Color.RED);
		
		unstagedChangesRepo2ScrollPane = new JScrollPane();
		unstagedChangesRepo2ScrollPane.setViewportView(unstagedChangesRepo2List);
		unstagedChangesRepo2ScrollPane.setBorder(BORDER);
		unstagedChangesRepo2ScrollPane.getVerticalScrollBar().setUI(getScrollBarUI());
		unstagedChangesRepo2ScrollPane.getHorizontalScrollBar().setUI(getScrollBarUI());
		
		try {
			fillUnstagedChangesLists();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private BasicScrollBarUI getScrollBarUI() {
		return new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = Color.GRAY;
		        this.trackColor = Color.DARK_GRAY;
		    }
		};
	}

	private void fillUnstagedChangesLists() throws Exception {
		String[] repo1UnstagedChangesList = getUnstagedChangesList(repo1PathTextField.getText());
		String[] repo2UnstagedChangesList = getUnstagedChangesList(repo2PathTextField.getText());

		unstagedChangesRepo1List.setListData(repo1UnstagedChangesList);
		unstagedChangesRepo2List.setListData(repo2UnstagedChangesList);
	}

	private void initializeStagedChangesLists() {
		stagedChangesRepo1List = new JList<>();
		stagedChangesRepo1List.setSelectionModel(getDefaultSelectionModel());
		stagedChangesRepo1List.setBackground(Color.DARK_GRAY);
		
		stagedChangesRepo1ScrollPane = new JScrollPane();
		stagedChangesRepo1ScrollPane.setViewportView(stagedChangesRepo1List);
		stagedChangesRepo1ScrollPane.setBorder(BORDER);
		stagedChangesRepo1ScrollPane.getVerticalScrollBar().setUI(getScrollBarUI());
		stagedChangesRepo1ScrollPane.getHorizontalScrollBar().setUI(getScrollBarUI());
		
		stagedChangesRepo2List = new JList<>();
		stagedChangesRepo2List.setSelectionModel(getDefaultSelectionModel());
		stagedChangesRepo2List.setBackground(Color.DARK_GRAY);
		
		stagedChangesRepo2ScrollPane = new JScrollPane();
		stagedChangesRepo2ScrollPane.setViewportView(stagedChangesRepo2List);
		stagedChangesRepo2ScrollPane.setBorder(BORDER);
		stagedChangesRepo2ScrollPane.getVerticalScrollBar().setUI(getScrollBarUI());
		stagedChangesRepo2ScrollPane.getHorizontalScrollBar().setUI(getScrollBarUI());

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
	}

	private String[] getStagedChangesList(String path) throws Exception {
		String command = "git diff --name-only --cached";
		Process process = executeCommand(command, path);
		
		if (process.exitValue() == 0) {
			List<String> result = readProcessExecutionResult(process);
			return result.toArray(new String[0]);
		} else {
			return new String[0];
		}
	}
	
	private List<String> readProcessExecutionResult(Process process) throws IOException {
		List<String> result = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			result.add(line);
		}
		return result;
	}
	
	private void initCommitMessageTextField() {
		commitMessageTextArea = new JTextArea();
		commitMessageTextArea.setBackground(Color.DARK_GRAY);
		commitMessageTextArea.setForeground(Color.LIGHT_GRAY);
				
		commitMessageTextArea.setBorder(BORDER);
	}

	private void initLabels() {
		unstagedChangesRepo1Label = new JLabel("Unstaged Changes");
		unstagedChangesRepo1Label.setForeground(Color.LIGHT_GRAY);
		
		unstagedChangesRepo2Label = new JLabel("Unstaged Changes");
		unstagedChangesRepo2Label.setForeground(Color.LIGHT_GRAY);
		
		stagedChangesRepo1Label = new JLabel("Staged Changes");
		stagedChangesRepo1Label.setForeground(Color.LIGHT_GRAY);
		
		stagedChangesRepo2Label = new JLabel("Staged Changes");
		stagedChangesRepo2Label.setForeground(Color.LIGHT_GRAY);
		
		commitMessageLabel = new JLabel("Commit Message");
		commitMessageLabel.setForeground(Color.LIGHT_GRAY);
	}

	private void initStageChangesButton() {
		stageChangesButton = new JButton("Stage Changes");
		stageChangesButton.setBackground(Color.DARK_GRAY);
		stageChangesButton.setForeground(Color.LIGHT_GRAY);
		
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
		unstageChangesButton.setBackground(Color.DARK_GRAY);
		unstageChangesButton.setForeground(Color.LIGHT_GRAY);

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
		commitButton.setBackground(Color.DARK_GRAY);
		commitButton.setForeground(Color.LIGHT_GRAY);

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
		
		if (!process.waitFor(1, TimeUnit.SECONDS)) {
			process.destroy();
		}

		return process;
	}

}
