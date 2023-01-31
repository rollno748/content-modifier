package com.fspace.config;

import com.fspace.config.utils.VariableSettings;
import org.apache.jmeter.testbeans.BeanInfoSupport;
import org.apache.jmeter.testbeans.gui.TableEditor;
import org.apache.jmeter.testbeans.gui.TypeEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;

public class ContentProcessorConfigBeanInfo extends BeanInfoSupport {
    /**
     * This BeanInfo creates the UI property for PreProcessor Element.
     * @param Variable - String/numbers/Alphanumeric/Large content
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentProcessorConfigBeanInfo.class);

    private String contentVariable;
    private static final String PATH = "expressionPath";
    private static final String[] PATH_TAGS = new String[4];
    static final int STRING_REPLACE = 0;
    static final int JSON = 1;
    static final int REGEX = 2;
    static final int XPATH = 3;

    static {
        PATH_TAGS[STRING_REPLACE] = "path.stringReplace";
        PATH_TAGS[JSON] = "path.json";
        PATH_TAGS[REGEX] = "path.regex";
        PATH_TAGS[XPATH] = "path.xpath";
    }
    protected ContentProcessorConfigBeanInfo() {
        super(ContentProcessorConfig.class);

        createPropertyGroup("Select Processing Method", new String[]{"contentVariable", PATH});

        createPropertyGroup("Modification Arena", new String[]{"modificationArena", PATH});

        PropertyDescriptor processPropertyDesc =  property("contentVariable");
        processPropertyDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
        processPropertyDesc.setValue(DEFAULT, "");
        processPropertyDesc.setDisplayName("Content");
        processPropertyDesc.setShortDescription("Content to use to replace");

        processPropertyDesc =  property(PATH, TypeEditor.ComboStringEditor);
        processPropertyDesc.setValue(RESOURCE_BUNDLE, getBeanDescriptor().getValue(RESOURCE_BUNDLE));
        processPropertyDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
        processPropertyDesc.setValue(DEFAULT, PATH_TAGS[STRING_REPLACE]);
        processPropertyDesc.setValue(NOT_OTHER, Boolean.FALSE);
        processPropertyDesc.setValue(NOT_EXPRESSION, Boolean.FALSE);
        processPropertyDesc.setValue(TAGS, PATH_TAGS);
        processPropertyDesc.setDisplayName("Expression Path Type");
        processPropertyDesc.setShortDescription("Select the Expression Path Type to use it for replacing the content");

        PropertyDescriptor argsPropDesc = property("modificationArena", TypeEditor.TableEditor);
        argsPropDesc.setValue(TableEditor.CLASSNAME, VariableSettings.class.getName());
        argsPropDesc.setValue(TableEditor.HEADERS, new String[]{ "Path Expression", "Replace with"} );
        argsPropDesc.setValue(TableEditor.OBJECT_PROPERTIES, new String[]{ VariableSettings.CONFIG_KEY, VariableSettings.CONFIG_VALUE } );
        argsPropDesc.setValue(DEFAULT, new ArrayList<>());
        argsPropDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
        argsPropDesc.setDisplayName("Modify content paths");
        argsPropDesc.setShortDescription("Each line of expression path replacement will be processed sequentially");
    }
}
