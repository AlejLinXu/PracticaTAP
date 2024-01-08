import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActionProxy implements InvocationHandler{
    private final Object target;

    public ActionProxy(Object target) {
        this.target = target;
    }

    /**
     * Create a proxy instance for the target object
     * @param target
     * @return proxy instance
     */
    public static Object newProxyInstance(Object target) {
        Class targetClass = target.getClass();
        Class[] interfaces = targetClass. getInterfaces ();
        return Proxy.newProxyInstance (targetClass.getClassLoader(),
                interfaces, new ActionProxy (target));

    }

    /**
     * Invokes the method on the target object
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object invocationResult = null;
        try {
            System.out.println("Before method " + method.getName());
            invocationResult = method.invoke(this.target, args);
            System.out.println("After method " + method.getName());
        } catch (InvocationTargetException ite) {
            throw ite.getTargetException ();
        } catch (Exception e) {
            System.err.println("Invocation of " + method.getName() + " failed");
        } finally {
            return invocationResult;
        }
    }
}