package com.fspace.config;

import org.apache.jmeter.processor.PreProcessor;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.TestElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ContentProcessorConfig extends AbstractTestElement implements PreProcessor, Cloneable, TestBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentProcessorConfig.class);
    private String contentVariable;
    private String expressionPath;
    @Override
    public void process() {
        //Logic goes here
        LOGGER.info("Expression path: {}, Replaceable String: {}", getExpressionPath(), getContentVariable());
    }


    private String whoAmI(String id, TestElement te) {
        return id + "@" + System.identityHashCode(te) + " '" + te.getName() + " '" + (LOGGER.isDebugEnabled()? Thread.currentThread().getName() : "");
    }

    //Getters and setters
    public String getContentVariable() {
        return contentVariable;
    }

    public void setContentVariable(String contentVariable) {
        this.contentVariable = contentVariable;
    }

    public String getExpressionPath() {
        return expressionPath;
    }

    public void setExpressionPath(String expressionPath) {
        this.expressionPath = expressionPath;
    }
}
