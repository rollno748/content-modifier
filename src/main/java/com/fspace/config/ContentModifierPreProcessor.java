package com.fspace.config;

import org.apache.jmeter.processor.PreProcessor;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.TestStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ContentModifierPreProcessor extends AbstractTestElement implements PreProcessor, TestStateListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentModifierPreProcessor.class);

    public static final String USE_STRING = "string";
    public static final String USE_JSON = "json";
    public static final String USE_REGEX = "regex";
    public static final String USE_XPATH = "xpath";
    public static final String SOURCE_CONTENT = "sourceContent";
    public static final String QUERY_PATH = "queryPath";


    public ContentModifierPreProcessor(){
    }

    @Override
    public void process() {

    }

    @Override
    public void testStarted() {

    }
    @Override
    public void testEnded() {
    }
    @Override
    public void testStarted(String host) {
        testStarted();
    }
    @Override
    public void testEnded(String host) {
        testEnded();
    }

    public String getReplacerType() {
        return getPropertyAsString("");
    }

    public String getSourceContent() {
        return "Hello World";
    }

    public String getQueryPath() {
        return ".+";
    }
}
