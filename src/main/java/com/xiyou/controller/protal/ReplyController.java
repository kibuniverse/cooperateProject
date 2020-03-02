package com.xiyou.controller.protal;


import com.xiyou.common.Const;
import com.xiyou.common.ServletResponse;
import com.xiyou.pojo.Comment;
import com.xiyou.pojo.Reply;
import com.xiyou.pojo.User;
import com.xiyou.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPBinding;
import java.util.Date;

@Controller
@RequestMapping("/reply/")
public class ReplyController {

    @Autowired
    private IReplyService iReplyService;

    @ResponseBody
    @RequestMapping("addToComment.do")
    public ServletResponse<Reply> addReplyToComment(HttpSession session,String content,Integer commentId,Integer receiveUserId){
       User user = (User) session.getAttribute(Const.CURRENT_USER);
       if(user == null){
           return ServletResponse.createByErrorMessage("用户未登录！");
       }
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setCommentId(commentId);
        reply.setSendUserId(user.getId());
        reply.setReceiveUserId(receiveUserId);
        Date time = new Date();
        reply.setTime(time);
        reply.setIsDeleted(true);
        return iReplyService.addReplyToComment(reply);

    }




}
