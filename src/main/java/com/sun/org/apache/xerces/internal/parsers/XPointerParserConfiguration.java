/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2007, 2015, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*
 * Copyright 2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 *  版权所有2005 Apache软件基金会。
 * 
 *  根据Apache许可证2.0版("许可证")授权;您不能使用此文件,除非符合许可证。您可以通过获取许可证的副本
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  除非适用法律要求或书面同意,否则根据许可证分发的软件按"原样"分发,不附带任何明示或暗示的担保或条件。请参阅管理许可证下的权限和限制的特定语言的许可证。
 * 
 */
package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xpointer.XPointerHandler;
import com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler;
import com.sun.org.apache.xerces.internal.xinclude.XIncludeNamespaceSupport;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;

/**
 * This parser configuration includes an <code>XPointerHandler</code> in the pipeline
 * before the schema validator, or as the last component in the pipeline if there is
 * no schema validator.  Using this pipeline will enable processing according to the
 * XML Inclusions specification with XPointers, to the conformance level described in
 * <code>XPointerHandler.</code>.
 *
 * <p>
 *  此解析器配置包括在模式验证器之前的管道中的<code> XPointerHandler </code>,或者如果没有模式验证器,则作为流水线中的最后一个组件。
 * 使用此管道将根据XML包含规范使用XPointers进行处理,以符合<code> XPointerHandler。</code>中描述的一致性级别。
 * 
 * 
 * @see com.sun.org.apache.xerces.internal.xpointer.XPointerHandler
 */
public class XPointerParserConfiguration extends XML11Configuration {

    private XPointerHandler fXPointerHandler;

    private XIncludeHandler fXIncludeHandler;

    /** Feature identifier: allow notation and unparsed entity events to be sent out of order. */
    protected static final String ALLOW_UE_AND_NOTATION_EVENTS =
        Constants.SAX_FEATURE_PREFIX + Constants.ALLOW_DTD_EVENTS_AFTER_ENDDTD_FEATURE;

    /** Feature identifier: fixup base URIs. */
    protected static final String XINCLUDE_FIXUP_BASE_URIS =
        Constants.XERCES_FEATURE_PREFIX + Constants.XINCLUDE_FIXUP_BASE_URIS_FEATURE;

    /** Feature identifier: fixup language. */
    protected static final String XINCLUDE_FIXUP_LANGUAGE =
        Constants.XERCES_FEATURE_PREFIX + Constants.XINCLUDE_FIXUP_LANGUAGE_FEATURE;

    /** Property identifier: error reporter. */
    protected static final String XPOINTER_HANDLER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.XPOINTER_HANDLER_PROPERTY;

    /** Property identifier: error reporter. */
    protected static final String XINCLUDE_HANDLER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.XINCLUDE_HANDLER_PROPERTY;

    /** Property identifier: error reporter. */
    protected static final String NAMESPACE_CONTEXT =
        Constants.XERCES_PROPERTY_PREFIX + Constants.NAMESPACE_CONTEXT_PROPERTY;

    /** Default constructor. */
    public XPointerParserConfiguration() {
        this(null, null, null);
    } // <init>()

    /**
     * Constructs a parser configuration using the specified symbol table.
     *
     * <p>
     *  使用指定的符号表构造解析器配置。
     * 
     * 
     * @param symbolTable The symbol table to use.
     */
    public XPointerParserConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    } // <init>(SymbolTable)

    /**
     * Constructs a parser configuration using the specified symbol table and
     * grammar pool.
     * <p>
     *
     * <p>
     *  使用指定的符号表和语法池构造解析器配置。
     * <p>
     * 
     * 
     * @param symbolTable The symbol table to use.
     * @param grammarPool The grammar pool to use.
     */
    public XPointerParserConfiguration(
        SymbolTable symbolTable,
        XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    } // <init>(SymbolTable,XMLGrammarPool)

    /**
     * Constructs a parser configuration using the specified symbol table,
     * grammar pool, and parent settings.
     * <p>
     *
     * <p>
     *  使用指定的符号表,语法池和父设置构造一个解析器配置。
     * <p>
     * 
     * @param symbolTable    The symbol table to use.
     * @param grammarPool    The grammar pool to use.
     * @param parentSettings The parent settings.
     */
    public XPointerParserConfiguration(
        SymbolTable symbolTable,
        XMLGrammarPool grammarPool,
        XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);

        fXIncludeHandler = new XIncludeHandler();
        addCommonComponent(fXIncludeHandler);

        fXPointerHandler = new XPointerHandler();
        addCommonComponent(fXPointerHandler);

        final String[] recognizedFeatures = {
            ALLOW_UE_AND_NOTATION_EVENTS,
            XINCLUDE_FIXUP_BASE_URIS,
            XINCLUDE_FIXUP_LANGUAGE
        };
        addRecognizedFeatures(recognizedFeatures);

        // add default recognized properties
        final String[] recognizedProperties =
            { XINCLUDE_HANDLER, XPOINTER_HANDLER, NAMESPACE_CONTEXT };
        addRecognizedProperties(recognizedProperties);

        setFeature(ALLOW_UE_AND_NOTATION_EVENTS, true);
        setFeature(XINCLUDE_FIXUP_BASE_URIS, true);
        setFeature(XINCLUDE_FIXUP_LANGUAGE, true);

        setProperty(XINCLUDE_HANDLER, fXIncludeHandler);
        setProperty(XPOINTER_HANDLER, fXPointerHandler);
        setProperty(NAMESPACE_CONTEXT, new XIncludeNamespaceSupport());


    } // <init>(SymbolTable,XMLGrammarPool)}


        /** Configures the pipeline. */
    protected void configurePipeline() {
        super.configurePipeline();

        //configure DTD pipeline
        fDTDScanner.setDTDHandler(fDTDProcessor);
        fDTDProcessor.setDTDSource(fDTDScanner);

        fDTDProcessor.setDTDHandler(fXIncludeHandler);
        fXIncludeHandler.setDTDSource(fDTDProcessor);
        fXIncludeHandler.setDTDHandler(fXPointerHandler);
        fXPointerHandler.setDTDSource(fXIncludeHandler);
        fXPointerHandler.setDTDHandler(fDTDHandler);
        if (fDTDHandler != null) {
            fDTDHandler.setDTDSource(fXPointerHandler);
        }

        // configure XML document pipeline: insert after DTDValidator and
        // before XML Schema validator
        XMLDocumentSource prev = null;
        if (fFeatures.get(XMLSCHEMA_VALIDATION) == Boolean.TRUE) {
            // we don't have to worry about fSchemaValidator being null, since
            // super.configurePipeline() instantiated it if the feature was set
            prev = fSchemaValidator.getDocumentSource();
        }
        // Otherwise, insert after the last component in the pipeline
        else {
            prev = fLastComponent;
            fLastComponent = fXPointerHandler;
        }

        XMLDocumentHandler next = prev.getDocumentHandler();
                prev.setDocumentHandler(fXIncludeHandler);
                fXIncludeHandler.setDocumentSource(prev);

                if (next != null) {
                        fXIncludeHandler.setDocumentHandler(next);
            next.setDocumentSource(fXIncludeHandler);
        }

                fXIncludeHandler.setDocumentHandler(fXPointerHandler);
                fXPointerHandler.setDocumentSource(fXIncludeHandler);
    } // configurePipeline()

        protected void configureXML11Pipeline() {
                super.configureXML11Pipeline();

        // configure XML 1.1. DTD pipeline
                fXML11DTDScanner.setDTDHandler(fXML11DTDProcessor);
                fXML11DTDProcessor.setDTDSource(fXML11DTDScanner);

        fDTDProcessor.setDTDHandler(fXIncludeHandler);
        fXIncludeHandler.setDTDSource(fXML11DTDProcessor);
        fXIncludeHandler.setDTDHandler(fXPointerHandler);
        fXPointerHandler.setDTDSource(fXIncludeHandler);
        fXPointerHandler.setDTDHandler(fDTDHandler);
        if (fDTDHandler != null) {
            fDTDHandler.setDTDSource(fXPointerHandler);
        }


                // configure XML document pipeline: insert after DTDValidator and
                // before XML Schema validator
                XMLDocumentSource prev = null;
                if (fFeatures.get(XMLSCHEMA_VALIDATION) == Boolean.TRUE) {
                        // we don't have to worry about fSchemaValidator being null, since
                        // super.configurePipeline() instantiated it if the feature was set
                        prev = fSchemaValidator.getDocumentSource();
                }
                // Otherwise, insert after the last component in the pipeline
                else {
                        prev = fLastComponent;
                        fLastComponent = fXPointerHandler;
                }

        XMLDocumentHandler next = prev.getDocumentHandler();
                prev.setDocumentHandler(fXIncludeHandler);
                fXIncludeHandler.setDocumentSource(prev);

                if (next != null) {
                        fXIncludeHandler.setDocumentHandler(next);
            next.setDocumentSource(fXIncludeHandler);
        }

                fXIncludeHandler.setDocumentHandler(fXPointerHandler);
                fXPointerHandler.setDocumentSource(fXIncludeHandler);


        } // configureXML11Pipeline()

    public void setProperty(String propertyId, Object value)
        throws XMLConfigurationException {

        //if (propertyId.equals(XINCLUDE_HANDLER)) {
        //}

        super.setProperty(propertyId, value);
    } // setProperty(String,Object)
}
