/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2007, 2015, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*
 * Copyright 1999-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 *  版权所有1999-2004 Apache软件基金会。
 * 
 *  根据Apache许可证2.0版("许可证")授权;您不能使用此文件,除非符合许可证。您可以通过获取许可证的副本
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  除非适用法律要求或书面同意,否则根据许可证分发的软件按"原样"分发,不附带任何明示或暗示的担保或条件。请参阅管理许可证下的权限和限制的特定语言的许可证。
 * 
 */
/*
 * $Id: FuncTranslate.java,v 1.2.4.1 2005/09/14 20:18:45 jeffsuttor Exp $
 * <p>
 *  $ Id：FuncTranslate.java,v 1.2.4.1 2005/09/14 20:18:45 jeffsuttor Exp $
 * 
 */
package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;

/**
 * Execute the Translate() function.
 * @xsl.usage advanced
 * <p>
 *  执行Translate()函数。 @ xsl.usage advanced
 * 
 */
public class FuncTranslate extends Function3Args
{
    static final long serialVersionUID = -1672834340026116482L;

  /**
   * Execute the function.  The function must return
   * a valid object.
   * <p>
   *  执行该功能。函数必须返回有效的对象。
   * 
   * @param xctxt The current execution context.
   * @return A valid XObject.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {

    String theFirstString = m_arg0.execute(xctxt).str();
    String theSecondString = m_arg1.execute(xctxt).str();
    String theThirdString = m_arg2.execute(xctxt).str();
    int theFirstStringLength = theFirstString.length();
    int theThirdStringLength = theThirdString.length();

    // A vector to contain the new characters.  We'll use it to construct
    // the result string.
    StringBuffer sbuffer = new StringBuffer();

    for (int i = 0; i < theFirstStringLength; i++)
    {
      char theCurrentChar = theFirstString.charAt(i);
      int theIndex = theSecondString.indexOf(theCurrentChar);

      if (theIndex < 0)
      {

        // Didn't find the character in the second string, so it
        // is not translated.
        sbuffer.append(theCurrentChar);
      }
      else if (theIndex < theThirdStringLength)
      {

        // OK, there's a corresponding character in the
        // third string, so do the translation...
        sbuffer.append(theThirdString.charAt(theIndex));
      }
      else
      {

        // There's no corresponding character in the
        // third string, since it's shorter than the
        // second string.  In this case, the character
        // is removed from the output string, so don't
        // do anything.
      }
    }

    return new XString(sbuffer.toString());
  }
}
