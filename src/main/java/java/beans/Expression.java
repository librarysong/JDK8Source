/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2000, 2013, Oracle and/or its affiliates. All rights reserved.
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

package java.beans;

/**
 * An <code>Expression</code> object represents a primitive expression
 * in which a single method is applied to a target and a set of
 * arguments to return a result - as in <code>"a.getFoo()"</code>.
 * <p>
 * In addition to the properties of the super class, the
 * <code>Expression</code> object provides a <em>value</em> which
 * is the object returned when this expression is evaluated.
 * The return value is typically not provided by the caller and
 * is instead computed by dynamically finding the method and invoking
 * it when the first call to <code>getValue</code> is made.
 *
 * <p>
 *  一个<code> Expression </code>对象表示一个原始表达式,其中一个方法被应用到一个目标和一组参数返回一个结果 - 如<code>"a.getFoo()"</code> 。
 * <p>
 *  除了超类的属性之外,<code> Expression </code>对象提供了一个<em>值</em>,它是在计算此表达式时返回的对象。
 * 返回值通常不由调用者提供,而是通过动态地找到方法并在第一次调用<code> getValue </code>时调用它来计算。
 * 
 * 
 * @see #getValue
 * @see #setValue
 *
 * @since 1.4
 *
 * @author Philip Milne
 */
public class Expression extends Statement {

    private static Object unbound = new Object();

    private Object value = unbound;

    /**
     * Creates a new {@link Expression} object
     * for the specified target object to invoke the method
     * specified by the name and by the array of arguments.
     * <p>
     * The {@code target} and the {@code methodName} values should not be {@code null}.
     * Otherwise an attempt to execute this {@code Expression}
     * will result in a {@code NullPointerException}.
     * If the {@code arguments} value is {@code null},
     * an empty array is used as the value of the {@code arguments} property.
     *
     * <p>
     *  为指定的目标对象创建一个新的{@link Expression}对象,以调用由名称和参数数组指定的方法。
     * <p>
     *  {@code target}和{@code methodName}值不应为{@code null}。
     * 否则,尝试执行此{@code Expression}将导致{@code NullPointerException}。
     * 如果{@code arguments}值是{@code null},则使用空数组作为{@code arguments}属性的值。
     * 
     * 
     * @param target  the target object of this expression
     * @param methodName  the name of the method to invoke on the specified target
     * @param arguments  the array of arguments to invoke the specified method
     *
     * @see #getValue
     */
    @ConstructorProperties({"target", "methodName", "arguments"})
    public Expression(Object target, String methodName, Object[] arguments) {
        super(target, methodName, arguments);
    }

    /**
     * Creates a new {@link Expression} object with the specified value
     * for the specified target object to invoke the  method
     * specified by the name and by the array of arguments.
     * The {@code value} value is used as the value of the {@code value} property,
     * so the {@link #getValue} method will return it
     * without executing this {@code Expression}.
     * <p>
     * The {@code target} and the {@code methodName} values should not be {@code null}.
     * Otherwise an attempt to execute this {@code Expression}
     * will result in a {@code NullPointerException}.
     * If the {@code arguments} value is {@code null},
     * an empty array is used as the value of the {@code arguments} property.
     *
     * <p>
     *  使用指定的目标对象的指定值创建一个新的{@link Expression}对象,以调用由名称和参数数组指定的方法。
     *  {@code value}值用作{@code value}属性的值,因此{@link #getValue}方法将返回它而不执行此{@code Expression}。
     * <p>
     * {@code target}和{@code methodName}值不应为{@code null}。
     * 否则,尝试执行此{@code Expression}将导致{@code NullPointerException}。
     * 如果{@code arguments}值是{@code null},则使用空数组作为{@code arguments}属性的值。
     * 
     * 
     * @param value  the value of this expression
     * @param target  the target object of this expression
     * @param methodName  the name of the method to invoke on the specified target
     * @param arguments  the array of arguments to invoke the specified method
     *
     * @see #setValue
     */
    public Expression(Object value, Object target, String methodName, Object[] arguments) {
        this(target, methodName, arguments);
        setValue(value);
    }

    /**
     * {@inheritDoc}
     * <p>
     * If the invoked method completes normally,
     * the value it returns is copied in the {@code value} property.
     * Note that the {@code value} property is set to {@code null},
     * if the return type of the underlying method is {@code void}.
     *
     * <p>
     *  {@inheritDoc}
     * <p>
     *  如果调用的方法正常完成,则返回的值将复制到{@code value}属性中。请注意,如果底层方法的返回类型是{@code void},则{@code value}属性设置为{@code null}。
     * 
     * 
     * @throws NullPointerException if the value of the {@code target} or
     *                              {@code methodName} property is {@code null}
     * @throws NoSuchMethodException if a matching method is not found
     * @throws SecurityException if a security manager exists and
     *                           it denies the method invocation
     * @throws Exception that is thrown by the invoked method
     *
     * @see java.lang.reflect.Method
     * @since 1.7
     */
    @Override
    public void execute() throws Exception {
        setValue(invoke());
    }

    /**
     * If the value property of this instance is not already set,
     * this method dynamically finds the method with the specified
     * methodName on this target with these arguments and calls it.
     * The result of the method invocation is first copied
     * into the value property of this expression and then returned
     * as the result of <code>getValue</code>. If the value property
     * was already set, either by a call to <code>setValue</code>
     * or a previous call to <code>getValue</code> then the value
     * property is returned without either looking up or calling the method.
     * <p>
     * The value property of an <code>Expression</code> is set to
     * a unique private (non-<code>null</code>) value by default and
     * this value is used as an internal indication that the method
     * has not yet been called. A return value of <code>null</code>
     * replaces this default value in the same way that any other value
     * would, ensuring that expressions are never evaluated more than once.
     * <p>
     * See the <code>execute</code> method for details on how
     * methods are chosen using the dynamic types of the target
     * and arguments.
     *
     * <p>
     *  如果此实例的value属性尚未设置,此方法将使用这些参数动态查找带有指定methodName的方法,并调用它。
     * 方法调用的结果首先被复制到该表达式的value属性中,然后作为<code> getValue </code>的结果返回。
     * 如果value属性已经设置,通过调用<code> setValue </code>或以前调用<code> getValue </code>,则返回value属性而不查找或调用方法。
     * <p>
     *  默认情况下,将<code> Expression </code>的value属性设置为唯一的私有(非<code> null </code>)值,并且此值用作方法尚未被称为。
     *  <code> null </code>的返回值以与任何其他值相同的方式替换此默认值,确保表达式从不被评估多次。
     * <p>
     * 有关如何使用目标和参数的动态类型选择方法的详细信息,请参阅<code> execute </code>方法。
     * 
     * 
     * @see Statement#execute
     * @see #setValue
     *
     * @return The result of applying this method to these arguments.
     * @throws Exception if the method with the specified methodName
     * throws an exception
     */
    public Object getValue() throws Exception {
        if (value == unbound) {
            setValue(invoke());
        }
        return value;
    }

    /**
     * Sets the value of this expression to <code>value</code>.
     * This value will be returned by the getValue method
     * without calling the method associated with this
     * expression.
     *
     * <p>
     * 
     * @param value The value of this expression.
     *
     * @see #getValue
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /*pp*/ String instanceName(Object instance) {
        return instance == unbound ? "<unbound>" : super.instanceName(instance);
    }

    /**
     * Prints the value of this expression using a Java-style syntax.
     * <p>
     *  将此表达式的值设置为<code> value </code>。此值将由getValue方法返回,而不调用与此表达式相关联的方法。
     * 
     */
    public String toString() {
        return instanceName(value) + "=" + super.toString();
    }
}
