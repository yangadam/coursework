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

public class AuthorizationInterceptor extends AbstractInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    private String permission;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        User user = SessionUtils.getUser();
        if (user == null) {
            logger.info("用户没有登录");
            return "login";
        }
        if (permission == null || !StringUtils.isBlank(permission) && !permission.contains(user.getType())) {
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
            throw new Exception(e);
        }
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
