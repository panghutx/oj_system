package cn.young.oj.controller;

import cn.young.oj.compile.Answer;
import cn.young.oj.compile.Question;
import cn.young.oj.compile.Task;
import cn.young.oj.entity.Topic;
import cn.young.oj.entity.UserInfo;
import cn.young.oj.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TopicController {
    @Autowired
    private TopicService topicService;

    @ResponseBody
    @RequestMapping("/problem")
    private List<Topic> selectAll(){
        return topicService.selectAll();
    }

    @ResponseBody
    @RequestMapping("/singleProblem")
    private Topic selectOne(Integer uid){
        if(uid==null){
            return null;
        }
        return topicService.selectOne(uid);
    }

    @ResponseBody
    @RequestMapping("/compile")
    private Answer submit(Integer uid, String code, HttpServletRequest req){
        //获取java工作目录
        String workingDir = System.getProperty("user.dir");
        System.out.println("工作目录："+workingDir);
        //判断用户是否登录，未登录不能提交代码
        HttpSession session = req.getSession(false);
        if(session==null){
            return null;
        }
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if (userInfo==null){
            return null;
        }
        Question question = new Question();
        //将用户提交代码与测试用例拼接
        int i = code.lastIndexOf("}");
        if(i==-1){
            return null;
        }
        final String testCode = topicService.selectOne(uid).getTestCode();
        String ret = code.substring(0,i)+testCode+"}";
        question.setCode(ret);
        Task task = new Task();
        Answer answer = task.compileAndRun(question);
        return answer;
    }
}
