import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActionProxy implements  InvocationHandler{

    private Object target;

    public ActionProxy(Object target) {
        this.target = target;
    }

    public static Object newProxyInstance(Object target) {
        Class targetClass = target.getClass();
        Class[] interfaces = targetClass. getInterfaces ();
        return Proxy.newProxyInstance (targetClass. getClassLoader (),
                interfaces, new ActionProxy (target));

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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