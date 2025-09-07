import javax.swing.*;
import java.awt.*;
import java.io.*;

public class NotepadFileIO extends JFrame
{
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public NotepadFileIO()
    {
        // Window setup
        setTitle("Simple Notepad");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Text Area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // File chooser
        fileChooser = new JFileChooser();

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add items to File Menu
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Actions
        newItem.addActionListener(e -> textArea.setText(""));

        openItem.addActionListener(e -> openFile());

        saveItem.addActionListener(e -> saveFile());

        exitItem.addActionListener(e -> System.exit(0));
    }

    // Open file
    private void openFile() {
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Save file
    private void saveFile() {
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NotepadFileIO notepad = new NotepadFileIO();
            notepad.setVisible(true);
        });
    }
}

