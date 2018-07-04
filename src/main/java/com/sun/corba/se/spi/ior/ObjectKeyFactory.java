/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.corba.se.spi.ior;

import org.omg.CORBA_2_3.portable.InputStream ;

import com.sun.corba.se.spi.ior.ObjectKey ;
import com.sun.corba.se.spi.ior.ObjectKeyTemplate ;

/** Construct ObjectKey and ObjectKeyTemplate instances from their
 * CDR-marshalled representation.
 * <p>
 *  CDR编组表示。
 * 
 */
public interface ObjectKeyFactory
{
    /** Create an ObjectKey from its octet sequence representation.
    /* <p>
     */
    ObjectKey create( byte[] key ) ;

    /** Create an ObjectKeyTemplate from its representation
     * in an InputStream.
     * <p>
     *  在InputStream中。
     */
    ObjectKeyTemplate createTemplate( InputStream is ) ;
}
