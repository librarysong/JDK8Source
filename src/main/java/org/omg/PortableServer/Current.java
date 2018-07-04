/***** Lobxxx Translate Finished ******/
package org.omg.PortableServer;


/**
* org/omg/PortableServer/Current.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/re/workspace/8-2-build-windows-amd64-cygwin/jdk8u45/3627/corba/src/share/classes/org/omg/PortableServer/poa.idl
* Thursday, April 30, 2015 12:42:10 PM PDT
*/


/**
	 * The PortableServer::Current interface, derived from 
	 * CORBA::Current, provides method implementations with 
	 * access to the identity of the object on which the 
	 * method was invoked. The Current interface is provided 
	 * to support servants that implement multiple objects, 
	 * but can be used within the context of POA-dispatched 
	 * method invocations on any servant. To provide location 
	 * transparency, ORBs are required to support use of 
	 * Current in the context of both locally and remotely 
	 * invoked operations. An instance of Current can be 
	 * obtained by the application by issuing the 
	 * CORBA::ORB::resolve_initial_references("POACurrent") 
	 * operation. Thereafter, it can be used within the 
	 * context of a method dispatched by the POA to obtain 
	 * the POA and ObjectId that identify the object on 
	 * which that operation was invoked.
	 * <p>
	 *  PortableServer :: Current接口派生自CORBA :: Current,提供了访问方法被调用的对象的标识的方法实现。
	 *  Current接口用于支持实现多个对象的服务,但可以在任何服务方上调用POA方法调用的上下文中使用。为了提供位置透明度,需要ORB在本地和远程调用操作的上下文中支持使用Current。
	 * 通过发出CORBA :: ORB :: resolve_initial_references("POACurrent")操作,应用程序可以获取Current的实例。
	 */
public interface Current extends CurrentOperations, org.omg.CORBA.Current, org.omg.CORBA.portable.IDLEntity 
{
} // interface Current
