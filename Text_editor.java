import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Text_editor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JMenuItem openMenuItem, saveMenuItem, closeMenuItem, printMenuItem;
    private JMenuItem cutMenuItem, copyMenuItem, pasteMenuItem;
    private JButton saveAndSubmitButton;

    public Text_editor() {
        setTitle("Text Editor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the text area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the file menu
        JMenu fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        closeMenuItem = new JMenuItem("Close");
        printMenuItem = new JMenuItem("Print");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(closeMenuItem);
        fileMenu.add(printMenuItem);

        // Create the edit menu
        JMenu editMenu = new JMenu("Edit");
        cutMenuItem = new JMenuItem("Cut");
        copyMenuItem = new JMenuItem("Copy");
        pasteMenuItem = new JMenuItem("Paste");
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // Set the menu bar
        setJMenuBar(menuBar);

        // Create the "Save and Submit" button
        saveAndSubmitButton = new JButton("Save and Submit");
        saveAndSubmitButton.addActionListener(this);
        add(saveAndSubmitButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveAndSubmitButton) {
            saveToFile();
            closeFile();
        }
    }

    private void saveToFile() {
        String text = textArea.getText();
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write(text);
                writer.close();
                System.out.println("File saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving the file.");
                e.printStackTrace();
            }
        }
    }

    private void closeFile() {
        dispose();
        System.out.println("File closed.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Text_editor textEditor = new Text_editor();
                textEditor.setVisible(true);
            }
        });
    }
}
