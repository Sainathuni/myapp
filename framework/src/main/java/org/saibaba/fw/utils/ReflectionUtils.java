
package org.saibaba.fw.utils;

//J2SE Classes
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;


public class ReflectionUtils {

  /**
   * An instance of a INTROSPECT_GET
   */
  public static final int INTROSPECT_GET = 0;

  /**
   * An instance of a INTROSPECT_SET
   */
  public static final int INTROSPECT_SET = 1;

  /** Default no parameters. */
  private static final Class noParams[] = new Class[0];

  /**
   * A default constructor
   */
  protected ReflectionUtils() {
    super();
  }

  /**
   * Invokes the object's underlying method represented by the method name
   * with the specified parameters.
   * 
   * @param target the object whose underlying method is invoked from.
   * @param methodName the object's method name
   * @param params the arguments to pass to a method call
   * @return the result from executing the specific method of the target object
   * @throws ReflectionException if failed to invoke
   */
  public static Object invoke(final Object target, final String methodName, final Object params[])
      throws ReflectionException {
    try {
      // attempt to find the method on the given object
      final Method m = findMethod(target.getClass(), methodName, typesOf(params));
      // if found, invoke the method
      return m.invoke(target, params);
    }
    catch (final Exception e) {
      throw new ReflectionException( e.getMessage(), e );
    }
  }

  /**
   * Returns a method object that reflects the specified public 
   * member method of the class or interface represented by the 
   * given Class object.
   * 
   * @param targetClass a target class
   * @param methodName a target class's method name
   * @param types an array of argument class types
   * @return a look-up method
   * @throws ReflectionException Thrown if the method is not found.
   */
  public static Method findMethod(final Class targetClass, final String methodName,
      final Class[] types) throws ReflectionException {

    Validate.notNull( targetClass, "A class must not be null");
    NoSuchMethodException notFound = null;
    try {
      // Attempt to get the method
      return targetClass.getMethod(methodName, types);
    }
    catch (final NoSuchMethodException e) {
      notFound = e;
    }

    // Before throwing notFound, iterate through targetClass's declared
    // methods excluding inherited methods and perform a compare (check)
    Method[] methods = targetClass.getDeclaredMethods();
    Method match = findMostExactMethod(methods, methodName, types);
    if (match == null) {

      // Make a second attempt to go up the chain to find a matching method
      methods = targetClass.getMethods();
      match = findMostExactMethod(methods, methodName, types);
      if (match == null) {
        // could not find a matching method so throw an exception
        throw new ReflectionException(
            "Either none or more than one match for " + methodName
                + " and could not determine best fit", notFound );
      }
    }
    return match;
  }

  /**
   * Finds a method of the specific object by name
   *  
   * @param target a target object 
   * @param methodName a method name to look up
   * @param params a list of arguments passed to a method
   * @return a method
   * @throws ReflectionException thrown if failed to find a method
   */
  public static Method findMethod(final Object target, final String methodName,
      final Object params[]) throws ReflectionException {
    return ReflectionUtils.findMethod(target.getClass(), methodName, typesOf(params));
  }

  /**
   * Given a target object, resursively looks for fields of a certain Class type
   * and invokes the same method on each occurrence of that field type. If it
   * finds one, after invoking the method, recurses again on that constituent.
   * 
   * @param ofType Target java.lang.Class type
   * @param target Target object that contains the java.util.Collection
   * @param methodName Method name to invoke
   * @param params Method parameters
   * @param shouldRecurseCollections Boolean indicating if java.util.Collection's 
   * should also be recursed
   * @param shouldInvokeMethodOnTarget Boolean indicating if method should be 
   * called on initial target
   * @exception ReflectionException Thrown if there was a problem performing the task
   */
  public static void recursiveInvoke(final Class<?> ofType, final Object target,
      final String methodName, final Object params[], final boolean shouldRecurseCollections,
      final boolean shouldInvokeMethodOnTarget) throws ReflectionException {
    try {
      Class klass = null;
      Field fld = null;
      Iterator itr = null;
      final List<Object> matchingFields = new ArrayList<Object>();
      boolean usedIntrospection = false;

      // Try to invoke method on original target.
      if (shouldInvokeMethodOnTarget
          && ofType.isAssignableFrom(target.getClass())) {
        try {
          invoke(target, methodName, params);
        }
        catch (final ReflectionException re) {
          // Target Object didn't have the method, ok, try it's
          // fields...
        }
      }

     
      final Field[] fields = target.getClass().getDeclaredFields();

      for (int i = 0; i < fields.length; i++) {
        matchingFields.clear();
        usedIntrospection = false;
        fld = fields[i];
        klass = fld.getType();

        if (ofType.isAssignableFrom(klass)) {
          Object obj = null;
          try {
            obj = fld.get(target);
            matchingFields.add(obj);
            invoke(obj, methodName, params);
          }
          catch (final Throwable e) {
            usedIntrospection = true;
            matchingFields.addAll(introspect(fld.getType(), target, null,
                INTROSPECT_GET));
          }
          finally {
            // recurse again if found one
            if (matchingFields.size() >= 1) {
              for (itr = matchingFields.iterator(); itr.hasNext();) {
                obj = itr.next();
                if (obj != null) {
									recursiveInvoke(ofType, obj, methodName, params,
                      shouldRecurseCollections, usedIntrospection);
								}
              }
            }
          }
        }

        if (shouldRecurseCollections
            && Collection.class.isAssignableFrom(fld.getType())) {
					recursiveInvokeCollection(ofType, target, methodName, params, fld);
				}
      }
    }
    catch (final Throwable e) {
      throw new ReflectionException(e.getMessage(), e);
    }
  }

  /**
   * Used to introspect for a non-public field of a given Class type and name
   * 
   * @return List of matching bean properties found
   */
  public static List<Object> introspect(final Class type, final Object target, final Object[] params,
      final int operation) throws ReflectionException {

    Method method = null;
    final List<Method> methods = new ArrayList<Method>();
    final List<Object> beanProps = new ArrayList<Object>();

    try {
      final BeanInfo info = Introspector.getBeanInfo(target.getClass());
      final PropertyDescriptor[] desc = info.getPropertyDescriptors();

      for (int i = 0; i < desc.length; i++) {
        if ((desc[i].getPropertyType() != null)
            && desc[i].getPropertyType().equals(type)) {
          if (operation == INTROSPECT_GET) {
						method = desc[i].getReadMethod();
					} else if (operation == INTROSPECT_SET) {
						method = desc[i].getWriteMethod();
					}

          // This just gets the first one, could be a problem?
          methods.add(method);
        }
      }
      
      // if (method != null) {
      if (methods.size() >= 1) {
        final Iterator itr = methods.iterator();
        while (itr.hasNext()) {
          method = (Method) itr.next();
          beanProps.add(invoke(target, method.getName(), params));
        }
      }
    }
    catch (final Exception e) {
      throw new ReflectionException(e.getMessage(), e);
    }

    return beanProps;
  }

  /**
   * Gets the creates an array of Class objects from an array of parameters
   * (which are of type Object)
   * 
   * @param params
   * @return an array of Class objects
   */
  public static Class[] typesOf(final Object params[]) {
    // If the parameter array is empty return the default noParams
    if (params == null) {
			return noParams;
		}
    // Construct an array of Class objects from the given parameter array
    final Class types[] = new Class[params.length];
    for (int i = 0; i < params.length; i++) {
			types[i] = (params[i] == null) ? Object.class : params[i].getClass();
		}

    return types;
  }

  /**
   * Creates an instance for the given targetClass.
   * 
   * @param targetClass
   *          the class for which a new instance will be created.
   * @param params
   *          the constructor's arguments.
   * @return The newly created object, which is of type targetClass.
   */
  @SuppressWarnings("rawtypes")
public static Object instantiate(final Class targetClass, final Object params[])
      throws ReflectionException {
    try {
      final Constructor m = findConstructor(targetClass, typesOf(params));
      return m.newInstance(params);
    }
    catch (final Exception e) {
      throw new ReflectionException(e.getMessage(), e);
    }
  }

  /**
   * Creates an instance for the given class name
   * @return The newly created object, which is of type targetClass.
   */
  public static Object instantiate(final String class_name)
      throws ReflectionException {

    return instantiate(class_name, noParams);
  }

  /**
   * Creates an instance for the given targetClass.
   * 
   * @param class_name the class name for which a new instance will be created.
   * @param params the constructor's arguments.
   * @return The newly created object, which is of type targetClass.
   */
  public static Object instantiate(final String class_name,
      final Object[] params) throws ReflectionException {

    Object instance = null;
    try {
      final Class aClass = Class.forName(class_name);
      instance = instantiate(aClass, params);
    }
    catch (final ClassNotFoundException e) {
      throw new ReflectionException(e.getMessage(), e);
    }
    return instance;
  }

  /**
   * Returns a <code>Constructor</code> object that reflects the specified
   * public constructor of the class represented by this <code>Class</code>
   * object. The <code>parameterTypes</code> parameter is an array of
   * <code>Class</code> objects that identify the constructor's formal
   * parameter types, in declared order.
   * 
   * <p>
   * The constructor to reflect is the public constructor of the class
   * represented by this <code>Class</code> object whose formal parameter
   * types match those specified by <code>parameterTypes</code>.
   * 
   * <p>
   * If there is a security manager, this method first calls the security
   * manager's <code>checkMemberAccess</code> method with <code>this</code>
   * and <code>Member.PUBLIC</code> as its arguments. If the class is in a
   * package, then this method also calls the security manager's
   * <code>checkPackageAccess</code> method with the package name as its
   * argument. Either of these calls could result in a SecurityException.
   * 
   * @param targetClass
   *          the class for which this <code>Constructor</code> is trying to
   *          be found on.
   * @param types
   *          the parameter array
   * @return the <code>Method</code> object of the public constructor that
   *         matches the specified <code>parameterTypes</code>
   * @exception NoSuchMethodException
   *              if a matching method is not found.
   * @see java.lang.reflect.Constructor
   */
  public static Constructor findConstructor(final Class targetClass, final Class[] types)
      throws ReflectionException {
    NoSuchMethodException notFound = null;
    try {
      return targetClass.getConstructor(types);
    }
    catch (final NoSuchMethodException e) {
      notFound = e;
    }
    final Constructor methods[] = targetClass.getConstructors();
    for (int i = 0; i < methods.length; i++) {
			if (checkParams(methods[i].getParameterTypes(), types)) {
				return methods[i];
			}
		}

    throw new ReflectionException(notFound.getMessage(), notFound);
  }
  /**
   * Augments the implementation to iterate through Fields that are of type
   * java.util.Collection
   * 
   * @param pOfType
   *          Target java.lang.Class type
   * @param pTarget
   *          Target object that contains the java.util.Collection
   * @param pMethodName
   *          Method name to invoke
   * @param pField
   *          Field of type java.util.Collection
   * @param pParams
   *          Method parameters
   * @exception ReflectionException
   *              Thrown if there was a problem performing the task
   */
  private static void recursiveInvokeCollection(final Class pOfType,
      final Object pTarget, final String pMethodName, final Object pParams[], final Field pField)
      throws ReflectionException {

    if ((pOfType == null) || (pTarget == null) || (pField == null)
        || (pMethodName == null)) {
			throw new ReflectionException(
          "Reflector.recursiveInvokeCollection contained null argument");
		}

    if ((pField != null) && Collection.class.isAssignableFrom(pField.getType())) {
      try {
        final Collection coll = (Collection) pField.get(pTarget);
        final Iterator itr = (coll != null) ? coll.iterator() : null;
        Object obj = null;
        while ((itr != null) && itr.hasNext()) {
          obj = itr.next();
          recursiveInvoke(pOfType, obj, pMethodName, pParams, false, true);
        }
      }
      catch (final Throwable t) {
        throw new ReflectionException(t.getMessage(), t);
      }
    }
  }
  /**
   * Find the most exact method by name using an array
   * of argument class types.
   *  
   * @param methods a list of method to look up
   * @param methodName a method name to look up
   * @param types a list of argument types
   * @return a method
   */
  private static Method findMostExactMethod(final Method[] methods,
      final String methodName, final Class[] types) {

    Method match = null;
    final List<Method> matchingMethods = new ArrayList<Method>();
    for (int i = 0; i < methods.length; i++) {
      if (check(methods[i], methodName, types)) {
        matchingMethods.add(methods[i]);
      }
    }
    // if there is only one, return it
    if (matchingMethods.size() == 1) {
      match = (Method) matchingMethods.iterator().next();
    }
    else if (matchingMethods.size() > 1) {
      match = findMostExactMethod(matchingMethods, types);
    }
    return match;
  }

  /**
   * Logic to determine "most exact" method match
   * 
   * @param matchingMethods a list of methods
   * @param types a list of argument types
   * @return a method
   */
  private static Method findMostExactMethod(final List matchingMethods, final Class[] types) {
    // Uh oh! more than one match, have to be more precise in our checking
    // now...
    // we know there is not one for the types or else above
    // targetClass.getMethod would
    // not have thrown an exception.

    // Logic: If types.length > 1, then it could get very complicated
    // (exponential in fact). Provide
    // support for only same superclass checking. Assumption made here all
    // have same superclass and same interfaces.

    // first check against interfaces
    final Class[] interfaces = types[0].getInterfaces();
    Method best = searchMethodsBasedOnInterfaces(matchingMethods, interfaces,
        types.length);
    if (best != null) {
			return best;
		}

    Class superClass = types[0].getSuperclass();
    while (superClass != null) {

      best = searchMethodsBasedOnClass(matchingMethods, superClass,
          types.length);
      if (best != null) {
				return best;
			}

      // if still not found, then check the interfaces of the superclass
      best = searchMethodsBasedOnInterfaces(matchingMethods, superClass
          .getInterfaces(), types.length);
      if (best != null) {
				return best;
			}

      superClass = superClass.getSuperclass();
    }
    return null;
  }

  /**
   * @param matchingMethods
   * @param interfaces
   * @param argSize
   * @return
   */
  private static Method searchMethodsBasedOnInterfaces(final List matchingMethods,
      final Class[] interfaces, final int argSize) {
    Method best = null;
    Class interfaceSuperClass = null;
    for (int i = 0; (interfaces != null) && (i < interfaces.length); i++) {
      best = searchMethodsBasedOnClass(matchingMethods, interfaces[i], argSize);
      if (best != null) {
				return best;
			}
    }

    // now go up its superclass chain (starting left to right)
    for (int i = 0; (interfaces != null) && (i < interfaces.length); i++) {
      interfaceSuperClass = interfaces[i].getSuperclass();
      while (interfaceSuperClass != null) {
        best = searchMethodsBasedOnClass(matchingMethods, interfaceSuperClass,
            argSize);
        if (best != null) {
					return best;
				}

        interfaceSuperClass = interfaceSuperClass.getSuperclass();
      }
    }
    return null;
  }

  /**
   * @param matchingMethods
   * @param targetClazz
   * @param argSize
   * @return
   */
  private static Method searchMethodsBasedOnClass(final List matchingMethods,
      final Class targetClazz, final int argSize) {
    final Class[] attempt = new Class[argSize];
    for (int i = 0; i < attempt.length; i++) {
			attempt[i] = targetClazz; // make all same
		}
    final Iterator itr = matchingMethods.iterator();
    Method candidate = null;
    while (itr.hasNext()) {
      candidate = (Method) itr.next();
      if (exactMatchClasses(candidate.getParameterTypes(), attempt)) {
				return candidate;
			}
    }
    return null;
  }

  /**
   * @param params1
   * @param params2
   * @return
   */
  private static boolean exactMatchClasses(final Class[] params1, final Class[] params2) {
    if (params1.length != params2.length) {
			return false;
		}

    for (int i = 0; i < params1.length; i++) {
      if (!params1[i].getName().equals(params2[i].getName())) {
				return false;
			}
    }
    return true;
  }
  /**
   * Check to determine if the parameters for a given method match that of the
   * target method. For a given array of types, determine if the class or
   * interface represented by each Class object is either the same as, or is a
   * superclass or superinterface of, the class or interface represented by the
   * specified Class parameter.
   * 
   * @param targetTypes
   * @param givenTypes
   * @return
   */
  @SuppressWarnings("unchecked")
	private static boolean checkParams(final Class targetTypes[], final Class givenTypes[]) {
    // Indicates whether or not the parameter types match.
    boolean matched = true;
    if (targetTypes.length == givenTypes.length) {
      for (int i = 0; matched && (i < targetTypes.length); i++) {
        // Check to make sure the parameter types match
        if ((targetTypes[i] != Object.class)
            && !targetTypes[i].isAssignableFrom(givenTypes[i])
            && ((givenTypes[i] != Integer.class) || (targetTypes[i] != Integer.TYPE))
            && ((givenTypes[i] != Long.class) || (targetTypes[i] != Long.TYPE))
            && ((givenTypes[i] != Byte.class) || (targetTypes[i] != Byte.TYPE))
            && ((givenTypes[i] != Short.class) || (targetTypes[i] != Short.TYPE))
            && ((givenTypes[i] != Float.class) || (targetTypes[i] != Float.TYPE))
            && ((givenTypes[i] != Double.class) || (targetTypes[i] != Double.TYPE))
            && ((givenTypes[i] != Boolean.class) || (targetTypes[i] != Boolean.TYPE))) {
          matched = false;
          break;
        }
      }
    }
    else {
      // If the number of parameters do not match, return false
      matched = false;
    }
    return matched;
  }

  /**
   * Performs a check to see if the method name is valid as well as the
   * parameters being passed.
   * 
   * @param m
   * @param methodName
   * @param types
   * @return
   */
  private static boolean check(final Method m, final String methodName, final Class types[]) {
    return m.getName().equals(methodName) ? checkParams(m.getParameterTypes(),
        types) : false;
  }
}