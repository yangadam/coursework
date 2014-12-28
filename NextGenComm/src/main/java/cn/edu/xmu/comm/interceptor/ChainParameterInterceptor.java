package cn.edu.xmu.comm.interceptor;

import cn.edu.xmu.comm.commons.annotation.ChainParameter;
import cn.edu.xmu.comm.commons.utils.ReflectionUtils;
import cn.edu.xmu.comm.commons.utils.StringUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.opensymphony.xwork2.util.ValueStack;

import java.lang.reflect.Field;

/**
 * 在Action之间传递对象参数的拦截器
 * result 的 type 为 chain
 *
 * @author Mengmeng Yang
 * @version 12/27/2014 0027
 */
public class ChainParameterInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ValueStack stack = actionInvocation.getStack();
        CompoundRoot root = stack.getRoot();
        if (root == null || root.size() <= 2) {
            return actionInvocation.invoke();
        }

        Object curAction = actionInvocation.getAction();
        Field[] fields = curAction.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ChainParameter.class)) {
                ChainParameter annotation = field.getAnnotation(ChainParameter.class);
                String paraName = annotation.fieldName();
                paraName = StringUtils.isEmpty(paraName) ? field.getName() : paraName;
                Object preAction = root.get(1);
                Object value = ReflectionUtils.getFieldValue(preAction, paraName);
                ReflectionUtils.setFieldValue(curAction, paraName, value);
            }
        }
        return actionInvocation.invoke();
    }

}
