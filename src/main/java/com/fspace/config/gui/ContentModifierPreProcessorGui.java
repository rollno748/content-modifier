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
    private final String COLUMN_NAMES[] = {"queryPath", "replaceWith"};
    private JRadioButton useStringReplace;
    private JRadioButton useJson;
    private JRadioButton useXpath;
    private JRadioButton useRegex;
    private ButtonGroup group;

    public ContentModifierPreProcessorGui() {
        init();
        initFields();
    }
    public String getStaticLabel() {
        return COMPONENT_NAME;
    }
    @Override
    public String getLabelResource() {
        return getClass().getCanonicalName();
    }
    @Override
    public TestElement createTestElement() {
        ContentModifierPreProcessor preProcessor = new ContentModifierPreProcessor();
        modifyTestElement((TestElement)preProcessor);
        preProcessor.setComment("");
        return (TestElement)preProcessor;
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof ContentModifierPreProcessor) {
            ContentModifierPreProcessor el = (ContentModifierPreProcessor)element;
            this.sourceContentField.setText(el.getSourceContent());
        }
    }

    @Override
    public void modifyTestElement(TestElement element) {

    }

    private void init() {
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(getBoxPanel(), BorderLayout.NORTH); // This adds the Radio button Selection
        JPanel container = new JPanel(new BorderLayout());
        container.add(getMainPanel(), "North");
        add(container, "Center");
    }

    private Box getBoxPanel() {
        Box querySelectionBox = Box.createVerticalBox();
        querySelectionBox.add(makeTitlePanel());
        querySelectionBox.add(makeSourcePanel());
        return querySelectionBox;
    }

    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = 24;
        GridBagConstraints editConstraints = new GridBagConstraints();
        editConstraints.anchor = 23;
        editConstraints.weightx = 1.0D;
        editConstraints.fill = 2;
        editConstraints.insets = new Insets(2, 0, 0, 0);
        labelConstraints.insets = new Insets(2, 0, 0, 0);
        mainPanel.add(getScrollPane());
//        addToPanel(mainPanel, labelConstraints, 0, 0, new JLabel("FIFO Queue Name to Get Data From: ", 4));
//        addToPanel(mainPanel, editConstraints, 1, 0, this.sourceContent = new JTextField(20));
        addToPanel(mainPanel, labelConstraints, 0, 1, new JLabel("Content : ", 4));
        addToPanel(mainPanel, editConstraints, 1, 1, this.sourceContentField = new JLabeledTextField());
        return mainPanel;
    }

    private JScrollPane getScrollPane() {
        Object[][] data = {};
        JTable table = new JTable(data, COLUMN_NAMES);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    private JPanel makeSourcePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Choose the replacer type")); //$NON-NLS-1$

        useStringReplace = new JRadioButton("String Replace");
        useJson = new JRadioButton("Json");
        useRegex = new JRadioButton("RegEx");
        useXpath = new JRadioButton("XPath");

        group = new ButtonGroup();
        group.add(useStringReplace);
        group.add(useJson);
        group.add(useRegex);
        group.add(useXpath);

        panel.add(useStringReplace);
        panel.add(useJson);
        panel.add(useRegex);
        panel.add(useXpath);

        useStringReplace.setSelected(true); //Defaults to string replacer
        // So we know which button is selected
        useStringReplace.setActionCommand(ContentModifierPreProcessor.USE_STRING);
        useJson.setActionCommand(ContentModifierPreProcessor.USE_JSON);
        useRegex.setActionCommand(ContentModifierPreProcessor.USE_REGEX);
        useXpath.setActionCommand(ContentModifierPreProcessor.USE_XPATH);

//        GridBagConstraints gbc = new GridBagConstraints();
//        initConstraints(gbc);
//        addField(panel, sourceContentField, gbc);
//        resetContraints(gbc);
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
        this.sourceContentField.setText("");
    }

    @Override
    public void clearGui() {
        super.clearGui();
        initFields();
    }
}
