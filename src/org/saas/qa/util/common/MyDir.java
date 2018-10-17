package org.saas.qa.util.common;

import org.saas.qa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.File;

@Component
public class MyDir {
    @Autowired
    HttpSession session;
    //创建目录
    public void makeDir(String dir){
           String path = "/Users/bone/myWork/source/";
           //String path = "C:/Users/Bone/IdeaProjects/source/";
           File file = new File(path + dir);
           if(!file.exists()){
               file.mkdir();
           }
    }

    //获取目录
    public String getDir(){
        //根据当前登录的人员获取对应的dir
        User user = (User)session.getAttribute(SaasToolsConstants.USER_SESSION);
        String dir= "/Users/bone/myWork/source/" + user.getLoginname();
        //String dir = "C:/Users/Bone/IdeaProjects/source/" + user.getLoginname();
        return dir;
    }
}
