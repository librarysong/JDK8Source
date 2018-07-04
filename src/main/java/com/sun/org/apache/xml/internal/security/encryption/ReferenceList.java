/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2007, 2015, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * <p>
 *  根据一个或多个贡献者许可协议授予Apache软件基金会(ASF)。有关版权所有权的其他信息,请参阅随此作品分发的NOTICE文件。
 *  ASF根据Apache许可证2.0版("许可证")向您授予此文件;您不能使用此文件,除非符合许可证。您可以通过获取许可证的副本。
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  除非适用法律要求或书面同意,否则根据许可证分发的软件按"原样"分发,不附带任何明示或暗示的担保或条件。请参阅管理许可证下的权限和限制的特定语言的许可证。
 * 
 */
package com.sun.org.apache.xml.internal.security.encryption;

import java.util.Iterator;

/**
 * <code>ReferenceList</code> is an element that contains pointers from a key
 * value of an <code>EncryptedKey</code> to items encrypted by that key value
 * (<code>EncryptedData</code> or <code>EncryptedKey</code> elements).
 * <p>
 * It is defined as follows:
 * <xmp>
 * <element name='ReferenceList'>
 *     <complexType>
 *         <choice minOccurs='1' maxOccurs='unbounded'>
 *             <element name='DataReference' type='xenc:ReferenceType'/>
 *             <element name='KeyReference' type='xenc:ReferenceType'/>
 *         </choice>
 *     </complexType>
 * </element>
 * </xmp>
 *
 * <p>
 *  <code> ReferenceList </code>是包含从<code> EncryptedKey </code>的键值到由该键值加密的项目(<code> EncryptedData </code>
 * 或<code> EncryptedKey < / code>元素)。
 * <p>
 *  其定义如下：
 * <xmp>
 * <element name='ReferenceList'>
 * <complexType>
 * <choice minOccurs='1' maxOccurs='unbounded'>
 * <element name='DataReference' type='xenc:ReferenceType'/>
 * <element name='KeyReference' type='xenc:ReferenceType'/>
 * </choice>
 * </complexType>
 * </element>
 * </xmp>
 * 
 * 
 * @author Axl Mattheus
 * @see Reference
 */
public interface ReferenceList {

    /** DATA TAG */
    int DATA_REFERENCE = 0x00000001;

    /** KEY TAG */
    int KEY_REFERENCE  = 0x00000002;

    /**
     * Adds a reference to this reference list.
     *
     * <p>
     *  添加对此引用列表的引用。
     * 
     * 
     * @param reference the reference to add.
     * @throws IllegalAccessException if the <code>Reference</code> is not an
     *   instance of <code>DataReference</code> or <code>KeyReference</code>.
     */
    void add(Reference reference);

    /**
     * Removes a reference from the <code>ReferenceList</code>.
     *
     * <p>
     *  从<code> ReferenceList </code>中删除引用。
     * 
     * 
     * @param reference the reference to remove.
     */
    void remove(Reference reference);

    /**
     * Returns the size of the <code>ReferenceList</code>.
     *
     * <p>
     *  返回<code> ReferenceList </code>的大小。
     * 
     * 
     * @return the size of the <code>ReferenceList</code>.
     */
    int size();

    /**
     * Indicates if the <code>ReferenceList</code> is empty.
     *
     * <p>
     *  指示<code> ReferenceList </code>是否为空。
     * 
     * 
     * @return <code><b>true</b></code> if the <code>ReferenceList</code> is
     *     empty, else <code><b>false</b></code>.
     */
    boolean isEmpty();

    /**
     * Returns an <code>Iterator</code> over all the <code>Reference</code>s
     * contained in this <code>ReferenceList</code>.
     *
     * <p>
     *  在<code> ReferenceList </code>中包含的所有<code>引用</code>中返回<code>迭代器</code>。
     * 
     * 
     * @return Iterator.
     */
    Iterator<Reference> getReferences();

    /**
     * <code>DataReference</code> factory method. Returns a
     * <code>DataReference</code>.
     * <p>
     * <code> DataReference </code>工厂方法。返回<code> DataReference </code>。
     * 
     * 
     * @param uri
     * @return a <code>DataReference</code>.
     */
    Reference newDataReference(String uri);

    /**
     * <code>KeyReference</code> factory method. Returns a
     * <code>KeyReference</code>.
     * <p>
     *  <code> KeyReference </code>工厂方法。返回<code> KeyReference </code>。
     * 
     * @param uri
     * @return a <code>KeyReference</code>.
     */
    Reference newKeyReference(String uri);
}
