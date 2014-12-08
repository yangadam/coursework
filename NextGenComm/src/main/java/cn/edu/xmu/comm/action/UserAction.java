package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.domain.Owner;
import cn.edu.xmu.comm.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@Controller
public class UserAction extends ActionSupport {

    @Autowired
    private UserService userService;

    @Override
    public String execute() {
        Owner owner = new Owner();
        owner.setName("111");
        owner.setUsername("222");
        owner.setPassword("333");
        owner.setSalt("444");
        userService.addOwner(owner);

        return SUCCESS;
    }

}
