package com.fspace.config.gui;

import com.fspace.config.ContentModifierPreProcessor;

import org.apache.jmeter.processor.gui.AbstractPreProcessorGui;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.gui.JLabeledTextField;
import org.apache.jorphan.gui.ObjectTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ContentModifierPreProcessorGui extends AbstractPreProcessorGui {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentModifierPreProcessorGui.class);
    protected transient ObjectTableModel tableModel;
    private static final String COMPONENT_NAME = "Content Modifier PreProcessor";
    private JLabeledTextField sourceContentField;
    private final String COLUMN_NAMES[] = {"Expression", "Operand", "Value to Replace"};
    private JRadioButton useString;
    private JRadioButton useJson;
    private JRadioButton useXpath;
    private JRadioButton useRegex;
    private ButtonGroup group;

    public ContentModifierPreProcessorGui() {
        init();
        initFields();
    }

    @Override
    public String getLabelResource() {
        return getClass().getCanonicalName();
    }

    public String getStaticLabel() {
        return COMPONENT_NAME;
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof ContentModifierPreProcessor) {
            ContentModifierPreProcessor el = (ContentModifierPreProcessor)element;
//            this.sourceContentField.setText(el.getSourceContent());
        }
    }

    @Override
    public TestElement createTestElement() {
        ContentModifierPreProcessor preProcessor = new ContentModifierPreProcessor();
        modifyTestElement((TestElement)preProcessor);
        preProcessor.setComment("");
        return (TestElement)preProcessor;
    }

    @Override
    public void modifyTestElement(TestElement element) {
        super.configureTestElement(element);
        if (element instanceof ContentModifierPreProcessor) {
            ContentModifierPreProcessor el = (ContentModifierPreProcessor)element;
            //this.sourceContentField.setText(el.getSourceContent());
        }

    }

    private void init() {
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(getBoxPanel(), BorderLayout.NORTH); // This adds the Radio button Selection
        Box container = Box.createVerticalBox();
        container.add(getMainPanel());
        add(container, BorderLayout.CENTER);
//        JPanel container = new JPanel(new BorderLayout());
//        container.add(getMainPanel(), BorderLayout.CENTER);
//        add(container, "Center");
    }

    private Box getBoxPanel() {
        Box querySelectionBox = Box.createVerticalBox();
        querySelectionBox.add(makeTitlePanel());
        querySelectionBox.add(makeSourcePanel());
        return querySelectionBox;
    }

    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        GridBagConstraints editConstraints = new GridBagConstraints();
        editConstraints.anchor = GridBagConstraints.EAST;
        editConstraints.weightx = 1.0D;
        editConstraints.fill = GridBagConstraints.HORIZONTAL;
        editConstraints.insets = new Insets(2, 0, 0, 0);
        labelConstraints.insets = new Insets(2, 0, 0, 0);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.add(getScrollPane());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Add from clipboard"));
        buttonPanel.add(new JButton("Delete"));
        buttonPanel.add(new JButton("Up"));
        buttonPanel.add(new JButton("Down"));

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JScrollPane getScrollPane() {
        Object[][] data = {};
        JTable table = new JTable(data, COLUMN_NAMES);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

//    private JPanel getMainPanel() {
//        JPanel mainPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints labelConstraints = new GridBagConstraints();
//        labelConstraints.anchor = GridBagConstraints.WEST;
//        GridBagConstraints editConstraints = new GridBagConstraints();
//        editConstraints.anchor = GridBagConstraints.EAST;
//        editConstraints.weightx = 1.0D;
//        editConstraints.fill = GridBagConstraints.HORIZONTAL;
//        editConstraints.insets = new Insets(2, 0, 0, 0);
//        labelConstraints.insets = new Insets(2, 0, 0, 0);
//        mainPanel.add(getScrollPane());
//        return mainPanel;
//    }
//
//    private JScrollPane getScrollPane() {
//        Object[][] data = {};
//        JTable table = new JTable(data, COLUMN_NAMES);
//        JScrollPane scrollPane = new JScrollPane(table);
//        return scrollPane;
//    }

    private JPanel makeSourcePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Choose the replacer type")); //$NON-NLS-1$

        // Create a panel for the name input
        JPanel namePanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Variable Name: ");
        JTextField nameField = new JTextField();
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(nameField, BorderLayout.CENTER);

        panel.add(namePanel); // Add the name panel to the main panel

        useString = new JRadioButton("String");
        useJson = new JRadioButton("JSON");
        useRegex = new JRadioButton("RegEx");
        useXpath = new JRadioButton("XPath");

        group = new ButtonGroup();
        group.add(useString);
        group.add(useJson);
        group.add(useRegex);
        group.add(useXpath);

        // Create a panel for the radio buttons
        JPanel radioPanel = new JPanel();
        radioPanel.add(useString);
        radioPanel.add(useJson);
        radioPanel.add(useRegex);
        radioPanel.add(useXpath);

        // Add the radio panel to the main panel
        panel.add(radioPanel);

        useString.setSelected(true); //Defaults to string replacer
        // So we know which button is selected
        useString.setActionCommand(ContentModifierPreProcessor.USE_STRING);
        useJson.setActionCommand(ContentModifierPreProcessor.USE_JSON);
        useRegex.setActionCommand(ContentModifierPreProcessor.USE_REGEX);
        useXpath.setActionCommand(ContentModifierPreProcessor.USE_XPATH);

        return panel;
    }


    private void addField(JPanel panel, JLabeledTextField field, GridBagConstraints gbc) {
        List<JComponent> item = field.getComponentList();
        panel.add(item.get(0), gbc.clone());
        gbc.gridx++;
        gbc.weightx = 1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        panel.add(item.get(1), gbc.clone());
    }

    private void initConstraints(GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
    }
    private void resetContraints(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0;
        gbc.fill=GridBagConstraints.NONE;
    }

    private void addToPanel(JPanel panel, GridBagConstraints constraints, int col, int row, JComponent component) {
        constraints.gridx = col;
        constraints.gridy = row;
        panel.add(component, constraints);
    }

    private void initFields() {
        //this.sourceContentField.setText("");
    }

    @Override
    public void clearGui() {
        super.clearGui();
        initFields();
    }
}
