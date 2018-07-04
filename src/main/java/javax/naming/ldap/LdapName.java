/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2003, 2013, Oracle and/or its affiliates. All rights reserved.
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

package javax.naming.ldap;

import javax.naming.Name;
import javax.naming.InvalidNameException;

import java.util.Enumeration;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/**
 * This class represents a distinguished name as specified by
 * <a href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</a>.
 * A distinguished name, or DN, is composed of an ordered list of
 * components called <em>relative distinguished name</em>s, or RDNs.
 * Details of a DN's syntax are described in RFC 2253.
 *<p>
 * This class resolves a few ambiguities found in RFC 2253
 * as follows:
 * <ul>
 * <li> RFC 2253 leaves the term "whitespace" undefined. The
 *      ASCII space character 0x20 (" ") is used in its place.
 * <li> Whitespace is allowed on either side of ',', ';', '=', and '+'.
 *      Such whitespace is accepted but not generated by this code,
 *      and is ignored when comparing names.
 * <li> AttributeValue strings containing '=' or non-leading '#'
 *      characters (unescaped) are accepted.
 * </ul>
 *<p>
 * String names passed to <code>LdapName</code> or returned by it
 * use the full Unicode character set. They may also contain
 * characters encoded into UTF-8 with each octet represented by a
 * three-character substring such as "\\B4".
 * They may not, however, contain characters encoded into UTF-8 with
 * each octet represented by a single character in the string:  the
 * meaning would be ambiguous.
 *<p>
 * <code>LdapName</code> will properly parse all valid names, but
 * does not attempt to detect all possible violations when parsing
 * invalid names.  It is "generous" in accepting invalid names.
 * The "validity" of a name is determined ultimately when it
 * is supplied to an LDAP server, which may accept or
 * reject the name based on factors such as its schema information
 * and interoperability considerations.
 *<p>
 * When names are tested for equality, attribute types, both binary
 * and string values, are case-insensitive.
 * String values with different but equivalent usage of quoting,
 * escaping, or UTF8-hex-encoding are considered equal.  The order of
 * components in multi-valued RDNs (such as "ou=Sales+cn=Bob") is not
 * significant.
 * <p>
 * The components of a LDAP name, that is, RDNs, are numbered. The
 * indexes of a LDAP name with n RDNs range from 0 to n-1.
 * This range may be written as [0,n).
 * The right most RDN is at index 0, and the left most RDN is at
 * index n-1. For example, the distinguished name:
 * "CN=Steve Kille, O=Isode Limited, C=GB" is numbered in the following
 * sequence ranging from 0 to 2: {C=GB, O=Isode Limited, CN=Steve Kille}. An
 * empty LDAP name is represented by an empty RDN list.
 *<p>
 * Concurrent multithreaded read-only access of an instance of
 * <tt>LdapName</tt> need not be synchronized.
 *<p>
 * Unless otherwise noted, the behavior of passing a null argument
 * to a constructor or method in this class will cause a
 * NullPointerException to be thrown.
 *
 * <p>
 *  此类表示由<a href=\"http://wwwietforg/rfc/rfc2253txt\"> RFC 2253 </a>指定的可分辨名称。
 * 可分辨名称或DN由称为<em>的组件的有序列表组成,相对可分辨名称</em>或RDNs RFC 2253中描述了DN语法的详细信息。
 * p>
 *  此类解决了RFC 2253中发现的一些歧义,如下所示：
 * <ul>
 * <li> RFC 2253保留"whitespace"未定义ASCII空格字符0x20("")在其位置使用<li>允许在',',';','=' +'这样的空格被接受但不是由此代码生成的,并且在比较名称时
 * 被忽略<li>接受包含'='或非前导'#'字符(非转义)的AttributeValue字符串。
 * </ul>
 * p>
 *  传递给<code> LdapName </code>或由它返回的字符串名称使用完整的Unicode字符集他们还可能包含编码为UTF-8的字符,每个八位字节由三个字符的子字符串"\\\\ B4"表示。
 * 但是,可能不包含编码为UTF-8的字符,每个八位字节由字符串中的单个字符表示：意义将是模糊的。
 * p>
 * <code> LdapName </code>将正确解析所有有效的名称,但不会在解析无效名称时尝试检测所有可能的违规。
 * 在接受无效名称时,"慷ous"一个名称的"有效性"提供给LDAP服务器,LDAP服务器可以基于其架构信息和互操作性注意事项等因素接受或拒绝该名称。
 * p>
 *  当测试名称的相等性时,属性类型(包括二进制和字符串值)是不区分大小写的字符串值,具有不同但等效的引用,转义或UTF8十六进制编码的用法被视为等于多值RDN中组件的顺序(例如"ou = Sales + 
 * cn = Bob")不重要。
 * <p>
 * LDAP名称的组件(即RDN)被编号具有n个RDN的LDAP名称的索引范围从0到n-1此范围可写为[0,n)最右边的RDN在索引0处,例如,识别名："CN = Steve Kille,O = Isode
 * 有限,C = GB"以范围从0到2的以下序列编号：{C = GB, O = Isode有限,CN = Steve Kille}空的LDAP名称由空RDN列表表示。
 * p>
 *  <tt> LdapName </tt>实例的并发多线程只读访问不需要同步
 * p>
 *  除非另有说明,否则将null参数传递给此类中的构造函数或方法的行为将导致抛出NullPointerException异常
 * 
 * 
 * @author Scott Seligman
 * @since 1.5
 */

public class LdapName implements Name {

    private transient List<Rdn> rdns;   // parsed name components
    private transient String unparsed;  // if non-null, the DN in unparsed form
    private static final long serialVersionUID = -1595520034788997356L;

    /**
     * Constructs an LDAP name from the given distinguished name.
     *
     * <p>
     *  根据给定的可分辨名称构造LDAP名称
     * 
     * 
     * @param name  This is a non-null distinguished name formatted
     * according to the rules defined in
     * <a href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</a>.
     *
     * @throws InvalidNameException if a syntax violation is detected.
     * @see Rdn#escapeValue(Object value)
     */
    public LdapName(String name) throws InvalidNameException {
        unparsed = name;
        parse();
    }

    /**
     * Constructs an LDAP name given its parsed RDN components.
     * <p>
     * The indexing of RDNs in the list follows the numbering of
     * RDNs described in the class description.
     *
     * <p>
     * 根据其解析的RDN组件构造LDAP名称
     * <p>
     *  列表中的RDN的索引遵循类描述中描述的RDN的编号
     * 
     * 
     * @param rdns The non-null list of <tt>Rdn</tt>s forming this LDAP name.
     */
    public LdapName(List<Rdn> rdns) {

        // if (rdns instanceof ArrayList<Rdn>) {
        //      this.rdns = rdns.clone();
        // } else if (rdns instanceof List<Rdn>) {
        //      this.rdns = new ArrayList<Rdn>(rdns);
        // } else {
        //      throw IllegalArgumentException(
        //              "Invalid entries, list entries must be of type Rdn");
        //  }

        this.rdns = new ArrayList<>(rdns.size());
        for (int i = 0; i < rdns.size(); i++) {
            Object obj = rdns.get(i);
            if (!(obj instanceof Rdn)) {
                throw new IllegalArgumentException("Entry:" + obj +
                        "  not a valid type;list entries must be of type Rdn");
            }
            this.rdns.add((Rdn)obj);
        }
    }

    /*
     * Constructs an LDAP name given its parsed components (the elements
     * of "rdns" in the range [beg,end)) and, optionally
     * (if "name" is not null), the unparsed DN.
     *
     * <p>
     *  根据其解析的组件(在范围[beg,end)中的"rdns"的元素)构造LDAP名称,并且可选地(如果"name"不为null),解析的DN
     * 
     */
    private LdapName(String name, List<Rdn> rdns, int beg, int end) {
        unparsed = name;
        // this.rdns = rdns.subList(beg, end);

        List<Rdn> sList = rdns.subList(beg, end);
        this.rdns = new ArrayList<>(sList);
    }

    /**
     * Retrieves the number of components in this LDAP name.
     * <p>
     *  检索此LDAP名称中的组件数
     * 
     * 
     * @return The non-negative number of components in this LDAP name.
     */
    public int size() {
        return rdns.size();
    }

    /**
     * Determines whether this LDAP name is empty.
     * An empty name is one with zero components.
     * <p>
     *  确定此LDAP名称是否为空空名称为零组件
     * 
     * 
     * @return true if this LDAP name is empty, false otherwise.
     */
    public boolean isEmpty() {
        return rdns.isEmpty();
    }

    /**
     * Retrieves the components of this name as an enumeration
     * of strings. The effect of updates to this name on this enumeration
     * is undefined. If the name has zero components, an empty (non-null)
     * enumeration is returned.
     * The order of the components returned by the enumeration is same as
     * the order in which the components are numbered as described in the
     * class description.
     *
     * <p>
     * 检索此名称的组件作为字符串的枚举对此枚举对此名称的更新的影响未定义如果名称具有零个组件,则返回空(非空)枚举枚举返回的组件的顺序为与组分按类描述中所述进行编号的顺序相同
     * 
     * 
     * @return A non-null enumeration of the components of this LDAP name.
     * Each element of the enumeration is of class String.
     */
    public Enumeration<String> getAll() {
        final Iterator<Rdn> iter = rdns.iterator();

        return new Enumeration<String>() {
            public boolean hasMoreElements() {
                return iter.hasNext();
            }
            public String nextElement() {
                return iter.next().toString();
            }
        };
    }

    /**
     * Retrieves a component of this LDAP name as a string.
     * <p>
     *  以字符串形式检索此LDAP名称的组件
     * 
     * 
     * @param  posn The 0-based index of the component to retrieve.
     *              Must be in the range [0,size()).
     * @return The non-null component at index posn.
     * @exception IndexOutOfBoundsException if posn is outside the
     *          specified range.
     */
    public String get(int posn) {
        return rdns.get(posn).toString();
    }

    /**
     * Retrieves an RDN of this LDAP name as an Rdn.
     * <p>
     *  检索此LDAP名称的RDN作为Rdn
     * 
     * 
     * @param   posn The 0-based index of the RDN to retrieve.
     *          Must be in the range [0,size()).
     * @return The non-null RDN at index posn.
     * @exception IndexOutOfBoundsException if posn is outside the
     *            specified range.
     */
    public Rdn getRdn(int posn) {
        return rdns.get(posn);
    }

    /**
     * Creates a name whose components consist of a prefix of the
     * components of this LDAP name.
     * Subsequent changes to this name will not affect the name
     * that is returned and vice versa.
     * <p>
     *  创建其组件由此LDAP名称的组件的前缀组成的名称对此名称的后续更改不会影响返回的名称,反之亦然
     * 
     * 
     * @param  posn     The 0-based index of the component at which to stop.
     *                  Must be in the range [0,size()].
     * @return  An instance of <tt>LdapName</tt> consisting of the
     *          components at indexes in the range [0,posn).
     *          If posn is zero, an empty LDAP name is returned.
     * @exception   IndexOutOfBoundsException
     *              If posn is outside the specified range.
     */
    public Name getPrefix(int posn) {
        try {
            return new LdapName(null, rdns, 0, posn);
        } catch (IllegalArgumentException e) {
            throw new IndexOutOfBoundsException(
                "Posn: " + posn + ", Size: "+ rdns.size());
        }
    }

    /**
     * Creates a name whose components consist of a suffix of the
     * components in this LDAP name.
     * Subsequent changes to this name do not affect the name that is
     * returned and vice versa.
     *
     * <p>
     * 创建一个名称,其组件由此LDAP名称中的组件的后缀组成此名称的后续更改不会影响返回的名称,反之亦然
     * 
     * 
     * @param  posn     The 0-based index of the component at which to start.
     *                  Must be in the range [0,size()].
     * @return  An instance of <tt>LdapName</tt> consisting of the
     *          components at indexes in the range [posn,size()).
     *          If posn is equal to size(), an empty LDAP name is
     *          returned.
     * @exception IndexOutOfBoundsException
     *          If posn is outside the specified range.
     */
    public Name getSuffix(int posn) {
        try {
            return new LdapName(null, rdns, posn, rdns.size());
        } catch (IllegalArgumentException e) {
            throw new IndexOutOfBoundsException(
                "Posn: " + posn + ", Size: "+ rdns.size());
        }
    }

    /**
     * Determines whether this LDAP name starts with a specified LDAP name
     * prefix.
     * A name <tt>n</tt> is a prefix if it is equal to
     * <tt>getPrefix(n.size())</tt>--in other words this LDAP
     * name starts with 'n'. If n is null or not a RFC2253 formatted name
     * as described in the class description, false is returned.
     *
     * <p>
     *  确定此LDAP名称是否以指定的LDAP名称前缀开头名称<tt> n </tt>是等于<tt> getPrefix(nsize())</tt>的前缀 - 换句话说,此LDAP名称以'n'开头如果n为空或
     * 不是类描述中描述的RFC2253格式化名称,则返回false。
     * 
     * 
     * @param n The LDAP name to check.
     * @return  true if <tt>n</tt> is a prefix of this LDAP name,
     * false otherwise.
     * @see #getPrefix(int posn)
     */
    public boolean startsWith(Name n) {
        if (n == null) {
            return false;
        }
        int len1 = rdns.size();
        int len2 = n.size();
        return (len1 >= len2 &&
                matches(0, len2, n));
    }

    /**
     * Determines whether the specified RDN sequence forms a prefix of this
     * LDAP name.  Returns true if this LdapName is at least as long as rdns,
     * and for every position p in the range [0, rdns.size()) the component
     * getRdn(p) matches rdns.get(p). Returns false otherwise. If rdns is
     * null, false is returned.
     *
     * <p>
     *  确定指定的RDN序列是否形成此LDAP名称的前缀如果此LdapName至少与rdns一样长,则返回true,对于范围[0,rdnssize())中的每个位置p,组件getRdn(p)与rdnsget 
     * p)否则返回false如果rdns为null,则返回false。
     * 
     * 
     * @param rdns The sequence of <tt>Rdn</tt>s to check.
     * @return  true if <tt>rdns</tt> form a prefix of this LDAP name,
     *          false otherwise.
     */
    public boolean startsWith(List<Rdn> rdns) {
        if (rdns == null) {
            return false;
        }
        int len1 = this.rdns.size();
        int len2 = rdns.size();
        return (len1 >= len2 &&
                doesListMatch(0, len2, rdns));
    }

    /**
     * Determines whether this LDAP name ends with a specified
     * LDAP name suffix.
     * A name <tt>n</tt> is a suffix if it is equal to
     * <tt>getSuffix(size()-n.size())</tt>--in other words this LDAP
     * name ends with 'n'. If n is null or not a RFC2253 formatted name
     * as described in the class description, false is returned.
     *
     * <p>
     * 确定此LDAP名称是否以指定的LDAP名称结尾后缀如果名称<tt> n </tt>等于<tt> getSuffix(size() -  nsize())</tt>换句话说,此LDAP名称以'n'结尾如果
     * n为null或不是类描述中描述的RFC2253格式化名称,则返回false。
     * 
     * 
     * @param n The LDAP name to check.
     * @return true if <tt>n</tt> is a suffix of this name, false otherwise.
     * @see #getSuffix(int posn)
     */
    public boolean endsWith(Name n) {
        if (n == null) {
            return false;
        }
        int len1 = rdns.size();
        int len2 = n.size();
        return (len1 >= len2 &&
                matches(len1 - len2, len1, n));
    }

    /**
     * Determines whether the specified RDN sequence forms a suffix of this
     * LDAP name.  Returns true if this LdapName is at least as long as rdns,
     * and for every position p in the range [size() - rdns.size(), size())
     * the component getRdn(p) matches rdns.get(p). Returns false otherwise.
     * If rdns is null, false is returned.
     *
     * <p>
     *  确定指定的RDN序列是否形成此LDAP名称的后缀如果此LdapName至少与rdns一样长,并且对于范围[size() -  rdnssize(),size())中的每个位置p,返回true getR
     * dn (p)matches rdnsget(p)返回false否则如果rdns为null,返回false。
     * 
     * 
     * @param rdns The sequence of <tt>Rdn</tt>s to check.
     * @return  true if <tt>rdns</tt> form a suffix of this LDAP name,
     *          false otherwise.
     */
    public boolean endsWith(List<Rdn> rdns) {
        if (rdns == null) {
            return false;
        }
        int len1 = this.rdns.size();
        int len2 = rdns.size();
        return (len1 >= len2 &&
                doesListMatch(len1 - len2, len1, rdns));
    }

    private boolean doesListMatch(int beg, int end, List<Rdn> rdns) {
        for (int i = beg; i < end; i++) {
            if (!this.rdns.get(i).equals(rdns.get(i - beg))) {
                return false;
            }
        }
        return true;
    }

    /*
     * Helper method for startsWith() and endsWith().
     * Returns true if components [beg,end) match the components of "n".
     * If "n" is not an LdapName, each of its components is parsed as
     * the string form of an RDN.
     * The following must hold:  end - beg == n.size().
     * <p>
     * 如果组件[beg,end)与"n"的组件匹配,则startsWith()和endsWith()的辅助方法如果"n"不是LdapName,则每个组件都将被解析为RDN的字符串形式。
     * 必须保持：end-beg == nsize()。
     * 
     */
    private boolean matches(int beg, int end, Name n) {
        if (n instanceof LdapName) {
            LdapName ln = (LdapName) n;
            return doesListMatch(beg, end, ln.rdns);
        } else {
            for (int i = beg; i < end; i++) {
                Rdn rdn;
                String rdnString = n.get(i - beg);
                try {
                    rdn = (new Rfc2253Parser(rdnString)).parseRdn();
                } catch (InvalidNameException e) {
                    return false;
                }
                if (!rdn.equals(rdns.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Adds the components of a name -- in order -- to the end of this name.
     *
     * <p>
     *  将名称的组件按顺序添加到此名称的结尾
     * 
     * 
     * @param   suffix The non-null components to add.
     * @return  The updated name (not a new instance).
     *
     * @throws  InvalidNameException if <tt>suffix</tt> is not a valid LDAP
     *          name, or if the addition of the components would violate the
     *          syntax rules of this LDAP name.
     */
    public Name addAll(Name suffix) throws InvalidNameException {
         return addAll(size(), suffix);
    }


    /**
     * Adds the RDNs of a name -- in order -- to the end of this name.
     *
     * <p>
     *  将名称的RDN(按顺序)添加到此名称的结尾
     * 
     * 
     * @param   suffixRdns The non-null suffix <tt>Rdn</tt>s to add.
     * @return  The updated name (not a new instance).
     */
    public Name addAll(List<Rdn> suffixRdns) {
        return addAll(size(), suffixRdns);
    }

    /**
     * Adds the components of a name -- in order -- at a specified position
     * within this name. Components of this LDAP name at or after the
     * index (if any) of the first new component are shifted up
     * (away from index 0) to accommodate the new components.
     *
     * <p>
     *  在此名称中的指定位置按顺序添加名称的组件在此第一个新组件的索引(如果有)之后或之后的此LDAP名称的组件向上移动(远离索引0),以适应新组件
     * 
     * 
     * @param suffix    The non-null components to add.
     * @param posn      The index at which to add the new component.
     *                  Must be in the range [0,size()].
     *
     * @return  The updated name (not a new instance).
     *
     * @throws  InvalidNameException if <tt>suffix</tt> is not a valid LDAP
     *          name, or if the addition of the components would violate the
     *          syntax rules of this LDAP name.
     * @throws  IndexOutOfBoundsException
     *          If posn is outside the specified range.
     */
    public Name addAll(int posn, Name suffix)
        throws InvalidNameException {
        unparsed = null;        // no longer valid
        if (suffix instanceof LdapName) {
            LdapName s = (LdapName) suffix;
            rdns.addAll(posn, s.rdns);
        } else {
            Enumeration<String> comps = suffix.getAll();
            while (comps.hasMoreElements()) {
                rdns.add(posn++,
                    (new Rfc2253Parser(comps.nextElement()).
                    parseRdn()));
            }
        }
        return this;
    }

    /**
     * Adds the RDNs of a name -- in order -- at a specified position
     * within this name. RDNs of this LDAP name at or after the
     * index (if any) of the first new RDN are shifted up (away from index 0) to
     * accommodate the new RDNs.
     *
     * <p>
     * 在此名称内的指定位置处添加名称的RDN在此第一个新RDN的索引(如果有)之后或之后的此LDAP名称的RDN被向上移位(远离索引0),以适应新RDN
     * 
     * 
     * @param suffixRdns        The non-null suffix <tt>Rdn</tt>s to add.
     * @param posn              The index at which to add the suffix RDNs.
     *                          Must be in the range [0,size()].
     *
     * @return  The updated name (not a new instance).
     * @throws  IndexOutOfBoundsException
     *          If posn is outside the specified range.
     */
    public Name addAll(int posn, List<Rdn> suffixRdns) {
        unparsed = null;
        for (int i = 0; i < suffixRdns.size(); i++) {
            Object obj = suffixRdns.get(i);
            if (!(obj instanceof Rdn)) {
                throw new IllegalArgumentException("Entry:" + obj +
                "  not a valid type;suffix list entries must be of type Rdn");
            }
            rdns.add(i + posn, (Rdn)obj);
        }
        return this;
    }

    /**
     * Adds a single component to the end of this LDAP name.
     *
     * <p>
     *  将单个组件添加到此LDAP名称的结尾
     * 
     * 
     * @param comp      The non-null component to add.
     * @return          The updated LdapName, not a new instance.
     *                  Cannot be null.
     * @exception       InvalidNameException If adding comp at end of the name
     *                  would violate the name's syntax.
     */
    public Name add(String comp) throws InvalidNameException {
        return add(size(), comp);
    }

    /**
     * Adds a single RDN to the end of this LDAP name.
     *
     * <p>
     *  将单个RDN添加到此LDAP名称的结尾
     * 
     * 
     * @param comp      The non-null RDN to add.
     *
     * @return          The updated LdapName, not a new instance.
     *                  Cannot be null.
     */
    public Name add(Rdn comp) {
        return add(size(), comp);
    }

    /**
     * Adds a single component at a specified position within this
     * LDAP name.
     * Components of this LDAP name at or after the index (if any) of the new
     * component are shifted up by one (away from index 0) to accommodate
     * the new component.
     *
     * <p>
     *  在此LDAP名称中的指定位置添加单个组件在此组件的索引(如果有)之后或之后的此LDAP名称的组件向上移位一(远离索引0),以容纳新组件
     * 
     * 
     * @param  comp     The non-null component to add.
     * @param  posn     The index at which to add the new component.
     *                  Must be in the range [0,size()].
     * @return          The updated LdapName, not a new instance.
     *                  Cannot be null.
     * @exception       IndexOutOfBoundsException
     *                  If posn is outside the specified range.
     * @exception       InvalidNameException If adding comp at the
     *                  specified position would violate the name's syntax.
     */
    public Name add(int posn, String comp) throws InvalidNameException {
        Rdn rdn = (new Rfc2253Parser(comp)).parseRdn();
        rdns.add(posn, rdn);
        unparsed = null;        // no longer valid
        return this;
    }

    /**
     * Adds a single RDN at a specified position within this
     * LDAP name.
     * RDNs of this LDAP name at or after the index (if any) of the new
     * RDN are shifted up by one (away from index 0) to accommodate
     * the new RDN.
     *
     * <p>
     *  在此LDAP名称内的指定位置添加单个RDN在新的RDN的索引(如果有)之后或之后,此LDAP名称的RDN将向上移一(远离索引0),以容纳新的RDN
     * 
     * 
     * @param  comp     The non-null RDN to add.
     * @param  posn     The index at which to add the new RDN.
     *                  Must be in the range [0,size()].
     * @return          The updated LdapName, not a new instance.
     *                  Cannot be null.
     * @exception       IndexOutOfBoundsException
     *                  If posn is outside the specified range.
     */
    public Name add(int posn, Rdn comp) {
        if (comp == null) {
            throw new NullPointerException("Cannot set comp to null");
        }
        rdns.add(posn, comp);
        unparsed = null;        // no longer valid
        return this;
    }

    /**
     * Removes a component from this LDAP name.
     * The component of this name at the specified position is removed.
     * Components with indexes greater than this position (if any)
     * are shifted down (toward index 0) by one.
     *
     * <p>
     * 从此LDAP名称中删除组件删除指定位置处此名称的组件将索引大于此位置的组件(如果有)向下(向索引0)移动一个
     * 
     * 
     * @param posn      The index of the component to remove.
     *                  Must be in the range [0,size()).
     * @return          The component removed (a String).
     *
     * @throws          IndexOutOfBoundsException
     *                  if posn is outside the specified range.
     * @throws          InvalidNameException if deleting the component
     *                  would violate the syntax rules of the name.
     */
    public Object remove(int posn) throws InvalidNameException {
        unparsed = null;        // no longer valid
        return rdns.remove(posn).toString();
    }

    /**
     * Retrieves the list of relative distinguished names.
     * The contents of the list are unmodifiable.
     * The indexing of RDNs in the returned list follows the numbering of
     * RDNs as described in the class description.
     * If the name has zero components, an empty list is returned.
     *
     * <p>
     *  检索相对可分辨名称的列表列表的内容不可修改返回列表中的RDN的索引遵循类描述中描述的RDN的编号如果名称具有零个组件,则返回空列表
     * 
     * 
     * @return  The name as a list of RDNs which are instances of
     *          the class {@link Rdn Rdn}.
     */
    public List<Rdn> getRdns() {
        return Collections.unmodifiableList(rdns);
    }

    /**
     * Generates a new copy of this name.
     * Subsequent changes to the components of this name will not
     * affect the new copy, and vice versa.
     *
     * <p>
     *  生成此名称的新副本对此名称的组件的后续更改不会影响新副本,反之亦然
     * 
     * 
     * @return A copy of the this LDAP name.
     */
    public Object clone() {
        return new LdapName(unparsed, rdns, 0, rdns.size());
    }

    /**
     * Returns a string representation of this LDAP name in a format
     * defined by <a href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</a>
     * and described in the class description. If the name has zero
     * components an empty string is returned.
     *
     * <p>
     * 以<a href=\"http://wwwietforg/rfc/rfc2253txt\"> RFC 2253 </a>定义的格式返回此LDAP名称的字符串表示形式,并在类描述中描述如果名称的零组件为空
     * 字符串返回。
     * 
     * 
     * @return The string representation of the LdapName.
     */
    public String toString() {
        if (unparsed != null) {
            return unparsed;
        }
        StringBuilder builder = new StringBuilder();
        int size = rdns.size();
        if ((size - 1) >= 0) {
            builder.append(rdns.get(size - 1));
        }
        for (int next = size - 2; next >= 0; next--) {
            builder.append(',');
            builder.append(rdns.get(next));
        }
        unparsed = builder.toString();
        return unparsed;
    }

    /**
     * Determines whether two LDAP names are equal.
     * If obj is null or not an LDAP name, false is returned.
     * <p>
     * Two LDAP names are equal if each RDN in one is equal
     * to the corresponding RDN in the other. This implies
     * both have the same number of RDNs, and each RDN's
     * equals() test against the corresponding RDN in the other
     * name returns true. See {@link Rdn#equals(Object obj)}
     * for a definition of RDN equality.
     *
     * <p>
     *  确定两个LDAP名称是否相等如果obj为null或不是LDAP名称,则返回false
     * <p>
     *  如果每个RDN在一个中等于对方中相应的RDN,则两个LDAP名称是相等的这意味着两个具有相同数量的RDN,并且每个RDN的equals()测试与其他名称中的相应RDN返回true参见{@link Rdn#equals(Object obj)}
     * 定义RDN等式。
     * 
     * 
     * @param  obj      The possibly null object to compare against.
     * @return          true if obj is equal to this LDAP name,
     *                  false otherwise.
     * @see #hashCode
     */
    public boolean equals(Object obj) {
        // check possible shortcuts
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LdapName)) {
            return false;
        }
        LdapName that = (LdapName) obj;
        if (rdns.size() != that.rdns.size()) {
            return false;
        }
        if (unparsed != null && unparsed.equalsIgnoreCase(
                that.unparsed)) {
            return true;
        }
        // Compare RDNs one by one for equality
        for (int i = 0; i < rdns.size(); i++) {
            // Compare a single pair of RDNs.
            Rdn rdn1 = rdns.get(i);
            Rdn rdn2 = that.rdns.get(i);
            if (!rdn1.equals(rdn2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares this LdapName with the specified Object for order.
     * Returns a negative integer, zero, or a positive integer as this
     * Name is less than, equal to, or greater than the given Object.
     * <p>
     * If obj is null or not an instance of LdapName, ClassCastException
     * is thrown.
     * <p>
     * Ordering of LDAP names follows the lexicographical rules for
     * string comparison, with the extension that this applies to all
     * the RDNs in the LDAP name. All the RDNs are lined up in their
     * specified order and compared lexicographically.
     * See {@link Rdn#compareTo(Object obj) Rdn.compareTo(Object obj)}
     * for RDN comparison rules.
     * <p>
     * If this LDAP name is lexicographically lesser than obj,
     * a negative number is returned.
     * If this LDAP name is lexicographically greater than obj,
     * a positive number is returned.
     * <p>
     * 将此LdapName与指定的对象进行比较返回一个负整数,零或正整数,因为此名称小于,等于或大于给定对象
     * <p>
     *  如果obj为null或不是LdapName的实例,则抛出ClassCastException
     * <p>
     *  LDAP名称的排序遵循字符串比较的字典规则,扩展名适用于LDAP名称中的所有RDN所有RDN按照其指定的顺序排列,并按字典顺序排列比较请参见{@link Rdn#compareTo(Object obj) RdncompareTo(Object obj)}
     * 用于RDN比较规则。
     * <p>
     *  如果此LDAP名称按字母顺序小于obj,则返回负数如果此LDAP名称按字母顺序大于obj,则返回正数
     * 
     * @param obj The non-null LdapName instance to compare against.
     *
     * @return  A negative integer, zero, or a positive integer as this Name
     *          is less than, equal to, or greater than the given obj.
     * @exception ClassCastException if obj is null or not a LdapName.
     */
    public int compareTo(Object obj) {

        if (!(obj instanceof LdapName)) {
            throw new ClassCastException("The obj is not a LdapName");
        }

        // check possible shortcuts
        if (obj == this) {
            return 0;
        }
        LdapName that = (LdapName) obj;

        if (unparsed != null && unparsed.equalsIgnoreCase(
                        that.unparsed)) {
            return 0;
        }

        // Compare RDNs one by one, lexicographically.
        int minSize = Math.min(rdns.size(), that.rdns.size());
        for (int i = 0; i < minSize; i++) {
            // Compare a single pair of RDNs.
            Rdn rdn1 = rdns.get(i);
            Rdn rdn2 = that.rdns.get(i);

            int diff = rdn1.compareTo(rdn2);
            if (diff != 0) {
                return diff;
            }
        }
        return (rdns.size() - that.rdns.size());        // longer DN wins
    }

    /**
     * Computes the hash code of this LDAP name.
     * The hash code is the sum of the hash codes of individual RDNs
     * of this  name.
     *
     * <p>
     * 
     * 
     * @return An int representing the hash code of this name.
     * @see #equals
     */
    public int hashCode() {
        // Sum up the hash codes of the components.
        int hash = 0;

        // For each RDN...
        for (int i = 0; i < rdns.size(); i++) {
            Rdn rdn = rdns.get(i);
            hash += rdn.hashCode();
        }
        return hash;
    }

    /**
     * Serializes only the unparsed DN, for compactness and to avoid
     * any implementation dependency.
     *
     * <p>
     * 计算此LDAP名称的哈希码哈希码是此名称的单个RDN的哈希码的总和
     * 
     * 
     * @serialData      The DN string
     */
    private void writeObject(ObjectOutputStream s)
            throws java.io.IOException {
        s.defaultWriteObject();
        s.writeObject(toString());
    }

    private void readObject(ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        unparsed = (String)s.readObject();
        try {
            parse();
        } catch (InvalidNameException e) {
            // shouldn't happen
            throw new java.io.StreamCorruptedException(
                    "Invalid name: " + unparsed);
        }
    }

    private void parse() throws InvalidNameException {
        // rdns = (ArrayList<Rdn>) (new RFC2253Parser(unparsed)).getDN();

        rdns = new Rfc2253Parser(unparsed).parseDn();
    }
}