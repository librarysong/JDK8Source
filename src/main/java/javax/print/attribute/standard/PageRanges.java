/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2000, 2004, Oracle and/or its affiliates. All rights reserved.
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

package javax.print.attribute.standard;

import javax.print.attribute.Attribute;
import javax.print.attribute.SetOfIntegerSyntax;
import javax.print.attribute.DocAttribute;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintJobAttribute;

/**
 * Class PageRanges is a printing attribute class, a set of integers, that
 * identifies the range(s) of print-stream pages that the Printer object uses
 * for each copy of each document which are to be printed. Nothing is printed
 * for any pages identified that do not exist in the document(s). The attribute
 * is associated with <I>print-stream</I> pages, not application-numbered pages
 * (for example, the page numbers found in the headers and or footers for
 * certain word processing applications).
 * <P>
 * In most cases, the exact pages to be printed will be generated by a device
 * driver and this attribute would not be required. However, when printing an
 * archived document which has already been formatted, the end user may elect to
 * print just a subset of the pages contained in the document. In this case, if
 * a page range of <CODE>"<I>n</I>-<I>m</I>"</CODE> is specified, the first page
 * to be printed will be page <I>n.</I> All subsequent pages of the document
 * will be printed through and including page <I>m.</I>
 * <P>
 * If a PageRanges attribute is not specified for a print job, all pages of
 * the document will be printed. In other words, the default value for the
 * PageRanges attribute is always <CODE>{{1, Integer.MAX_VALUE}}</CODE>.
 * <P>
 * The effect of a PageRanges attribute on a multidoc print job (a job with
 * multiple documents) depends on whether all the docs have the same page ranges
 * specified or whether different docs have different page ranges specified, and
 * on the (perhaps defaulted) value of the {@link MultipleDocumentHandling
 * MultipleDocumentHandling} attribute.
 * <UL>
 * <LI>
 * If all the docs have the same page ranges specified, then any value of {@link
 * MultipleDocumentHandling MultipleDocumentHandling} makes sense, and the
 * printer's processing depends on the {@link MultipleDocumentHandling
 * MultipleDocumentHandling} value:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All the input docs will be combined together into one
 * output document. The specified page ranges of that output document will be
 * printed.
 * <P>
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All the input docs will be combined together
 * into one output document, and the first impression of each input doc will
 * always start on a new media sheet. The specified page ranges of that output
 * document will be printed.
 * <P>
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- For each separate input doc, the
 * specified page ranges will be printed.
 * <P>
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- For each separate input doc, the
 * specified page ranges will be printed.
 * </UL>
 * <UL>
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- For each separate input doc, its own
 * specified page ranges will be printed..
 * <P>
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- For each separate input doc, its own
 * specified page ranges will be printed..
 * </UL>
 * </UL>
 * <P>
 * <B>IPP Compatibility:</B> The PageRanges attribute's canonical array form
 * gives the lower and upper bound for each range of pages to be included in
 * and IPP "page-ranges" attribute. See class {@link
 * javax.print.attribute.SetOfIntegerSyntax SetOfIntegerSyntax} for an
 * explanation of canonical array form. The category name returned by
 * <CODE>getName()</CODE> gives the IPP attribute name.
 * <P>
 *
 * <p>
 *  类PageRanges是一个打印属性类,一组整数,用于标识打印机对象用于每个要打印的每个文档的副本的打印流页面的范围。
 * 对于标识的任何页面,不打印任何内容不存在于文档中该属性与<I>打印流</I>页面相关联,而不与应用程序编号的页面相关联(例如,在某些文字处理应用程序的标题和/或页脚中找到的页码)。
 * <P>
 * 在大多数情况下,要打印的确切页面将由设备驱动程序生成,并且不需要此属性。
 * 但是,当打印已经格式化的归档文档时,最终用户可以选择仅打印页面的子集在这种情况下,如果指定了<CODE>"<I> n </I>  -  <I> m </I>"</CODE>的页范围,则要打印的第一页将是
 * 页<I> n </I>文档的所有后续页将通过并包括页<I> m </I>。
 * 在大多数情况下,要打印的确切页面将由设备驱动程序生成,并且不需要此属性。
 * <P>
 *  如果未为打印作业指定PageRanges属性,则将打印文档的所有页面。换句话说,PageRanges属性的默认值始终为<CODE> {{1,IntegerMAX_VALUE}} </CODE>
 * <P>
 * PageRanges属性对多点打印作业(具有多个文档的作业)的影响取决于是否所有文档具有指定的相同页面范围,或不同的文档是否具有指定的不同页面范围,以及是否(可能是默认值) {@link MultipleDocumentHandling MultipleDocumentHandling}
 * 属性。
 * <UL>
 * <LI>
 *  如果所有文档具有指定的相同页面范围,则{@link MultipleDocumentHandling MultipleDocumentHandling}的任何值都是有意义的,打印机的处理取决于{@link MultipleDocumentHandling MultipleDocumentHandling}
 * 值：。
 * <UL>
 * <LI>
 *  SINGLE_DOCUMENT  - 所有输入文档将组合在一起成为一个输出文档将输出该输出文档的指定页面范围
 * <P>
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET  - 所有输入文档将组合在一起成为一个输出文档,每个输入文档的第一印象将始终在新的媒体工作表上开始输出文档的指定页面范围将被打印
 * <P>
 * <LI>
 *  SEPARATE_DOCUMENTS_UNCOLLATED_COPIES  - 对于每个单独的输入文档,将打印指定的页面范围
 * <P>
 * <LI>
 *  SEPARATE_DOCUMENTS_COLLATED_COPIES  - 对于每个单独的输入文档,将打印指定的页面范围
 * </UL>
 * <UL>
 * <LI>
 *  SEPARATE_DOCUMENTS_UNCOLLATED_COPIES  - 对于每个单独的输入文档,将打印其自己指定的页面范围
 * <P>
 * <LI>
 *  SEPARATE_DOCUMENTS_COLLATED_COPIES  - 对于每个单独的输入文档,将打印其自己指定的页面范围
 * </UL>
 * </UL>
 * <P>
 * <B> IPP兼容性：</B> PageRanges属性的规范数组形式给出了要包含在的每个页面范围的下限和上限,以及IPP"page-ranges"属性请参阅{@link javaxprintattributeSetOfIntegerSyntax SetOfIntegerSyntax}
 * 
 * @author  David Mendenhall
 * @author  Alan Kaminsky
 */
public final class PageRanges   extends SetOfIntegerSyntax
        implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    private static final long serialVersionUID = 8639895197656148392L;


    /**
     * Construct a new page ranges attribute with the given members. The
     * members are specified in "array form;" see class {@link
     * javax.print.attribute.SetOfIntegerSyntax SetOfIntegerSyntax} for an
     * explanation of array form.
     *
     * <p>
     * 规范数组形式<CODE> getName()</CODE>返回的类别名称给出了IPP属性名称。
     * <P>
     * 
     * 
     * @param  members  Set members in array form.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>members</CODE> is null or
     *     any element of <CODE>members</CODE> is null.
     * @exception  IllegalArgumentException
     *     (unchecked exception) Thrown if any element of
     *   <CODE>members</CODE> is not a length-one or length-two array. Also
     *     thrown if <CODE>members</CODE> is a zero-length array or if any
     *     member of the set is less than 1.
     */
    public PageRanges(int[][] members) {
        super (members);
        if (members == null) {
            throw new NullPointerException("members is null");
        }
        myPageRanges();
    }
    /**
     * Construct a new  page ranges attribute with the given members in
     * string form.
     * See class {@link javax.print.attribute.SetOfIntegerSyntax
     * SetOfIntegerSyntax}
     * for explanation of the syntax.
     *
     * <p>
     *  使用给定成员构造新的页面范围属性成员在"数组形式"中指定;请参阅类{@link javaxprintattributeSetOfIntegerSyntax SetOfIntegerSyntax}以获取
     * 数组形式的说明。
     * 
     * 
     * @param  members  Set members in string form.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>members</CODE> is null or
     *     any element of <CODE>members</CODE> is null.
     * @exception  IllegalArgumentException
     *     (Unchecked exception) Thrown if <CODE>members</CODE> does not
     *    obey  the proper syntax.  Also
     *     thrown if the constructed set-of-integer is a
     *     zero-length array or if any
     *     member of the set is less than 1.
     */
    public PageRanges(String members) {
        super(members);
        if (members == null) {
            throw new NullPointerException("members is null");
        }
        myPageRanges();
    }

    private void myPageRanges() {
        int[][] myMembers = getMembers();
        int n = myMembers.length;
        if (n == 0) {
            throw new IllegalArgumentException("members is zero-length");
        }
        int i;
        for (i = 0; i < n; ++ i) {
          if (myMembers[i][0] < 1) {
            throw new IllegalArgumentException("Page value < 1 specified");
          }
        }
    }

    /**
     * Construct a new page ranges attribute containing a single integer. That
     * is, only the one page is to be printed.
     *
     * <p>
     *  以字符串形式使用给定的成员构造新的页面范围属性有关语法的说明,请参阅class {@link javaxprintattributeSetOfIntegerSyntax SetOfIntegerSyntax}
     * 。
     * 
     * 
     * @param  member  Set member.
     *
     * @exception  IllegalArgumentException
     *     (Unchecked exception) Thrown if <CODE>member</CODE> is less than
     *     1.
     */
    public PageRanges(int member) {
        super (member);
        if (member < 1) {
            throw new IllegalArgumentException("Page value < 1 specified");
        }
    }

    /**
     * Construct a new page ranges attribute containing a single range of
     * integers. That is, only those pages in the one range are to be printed.
     *
     * <p>
     * 构造包含单个整数的新页面范围属性即只有一个页面要打印
     * 
     * 
     * @param  lowerBound  Lower bound of the range.
     * @param  upperBound  Upper bound of the range.
     *
     * @exception  IllegalArgumentException
     *     (Unchecked exception) Thrown if a null range is specified or if a
     *     non-null range is specified with <CODE>lowerBound</CODE> less than
     *     1.
     */
    public PageRanges(int lowerBound, int upperBound) {
        super (lowerBound, upperBound);
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Null range specified");
        } else if (lowerBound < 1) {
            throw new IllegalArgumentException("Page value < 1 specified");
        }
    }

    /**
     * Returns whether this page ranges attribute is equivalent to the passed
     * in object. To be equivalent, all of the following conditions must be
     * true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is an instance of class PageRanges.
     * <LI>
     * This page ranges attribute's members and <CODE>object</CODE>'s members
     * are the same.
     * </OL>
     *
     * <p>
     *  构造包含单个整数范围的新页面范围属性也就是说,只打印一个范围中的那些页面
     * 
     * 
     * @param  object  Object to compare to.
     *
     * @return  True if <CODE>object</CODE> is equivalent to this page ranges
     *          attribute, false otherwise.
     */
    public boolean equals(Object object) {
        return (super.equals(object) && object instanceof PageRanges);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <P>
     * For class PageRanges, the category is class PageRanges itself.
     *
     * <p>
     *  返回此页面范围属性是否等同于传入的对象要等效,所有以下条件都必须为true：
     * <OL TYPE=1>
     * <LI>
     *  <CODE>对象</CODE>不为空
     * <LI>
     *  <CODE>对象</CODE>是类PageRanges的实例
     * <LI>
     *  此页面范围属性的成员,并且<CODE>对象</CODE>的成员相同
     * </OL>
     * 
     * 
     * @return  Printing attribute class (category), an instance of class
     *          {@link java.lang.Class java.lang.Class}.
     */
    public final Class<? extends Attribute> getCategory() {
        return PageRanges.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <P>
     * For class PageRanges, the category name is <CODE>"page-ranges"</CODE>.
     *
     * <p>
     * 
     * @return  Attribute category name.
     */
    public final String getName() {
        return "page-ranges";
    }

}