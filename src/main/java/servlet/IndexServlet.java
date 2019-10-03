package servlet;

import servermc.annotation.Controller;
import servermc.annotation.RequestMapping;
import servlet.entity.User;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-10-03 4:41 PM
 */
@Controller
public class IndexServlet {

    @RequestMapping("/findUser")
    public User findUser(){
        User user = new User();
        user.setName("huang");
        user.setAge(18);
        return user;
    }
}