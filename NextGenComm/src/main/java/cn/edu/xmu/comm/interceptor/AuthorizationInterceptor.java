package cn.edu.xmu.comm.interceptor;

import cn.edu.xmu.comm.commons.exception.NoPermissionException;
import cn.edu.xmu.comm.commons.exception.NoUserInSessionException;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.commons.utils.StringUtils;
import cn.edu.xmu.comm.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登陆和权限检查的拦截器
 *
 * @author Mengmeng Yang
 * @version 1/7/2015 0008
 */
public class AuthorizationInterceptor extends AbstractInterceptor {

    //日志
    private static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    private String permission;

    /**
     * 登陆和权限检查
     *
     * @param actionInvocation 调用Action
     * @return 结果
     * @throws Exception 异常
     * @see java.lang.Exception
     */
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        User user = SessionUtils.getUser();
        if (user == null) {
            logger.info("用户没有登录");
            return "login";
        }
        if (!StringUtils.isBlank(permission) && !permission.contains(user.getType())) {
            logger.info("用户没有权限");
            return "unauthorized";
        }
        try {
            return actionInvocation.invoke();
        } catch (NoUserInSessionException ex) {
            return "login";
        } catch (NoPermissionException ex) {
            return "unauthorized";
        } catch (Throwable e) {
            logger.info(e.getMessage() + "\n");
            e.printStackTrace();
            return "login";
        }
    }

    /**
     * 权限字符串
     *
     * @return 权限字符串
     */
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
