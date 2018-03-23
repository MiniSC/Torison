package com.friend.controller;

import com.alibaba.fastjson.JSON;
import com.friend.model.FriendStatus;
import com.model.Result;
import com.torison.Friend.FriendSerivce;
import com.torison.Friend.model.Friend;
import com.torison.Friend.model.FriendForm;
import com.torison.Friend.model.FriendWords;
import com.torison.Friend.model.FwordsForm;
import com.torison.User.UserService;
import com.torison.User.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/friend")
public class FriendController {
    private static final Log log = LogFactory.getLog(FriendController.class);


    @Autowired
    private FriendSerivce friendSerivce;

    @Autowired
    private UserService userService;

    /**
     * initialize MyFriend view
     * @return
     */
    @RequestMapping(value = "/toFriend")
    public String toFriend(){
        return "test/Friend/Friend";
    }

    /**
     * getmyfriendlist
     * @param request
     * @return
     */
    @RequestMapping(value = "/getFriendList")
    @ResponseBody
    public Result getFriendList(HttpServletRequest request,String status){
        Result result = new Result();
        Integer userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        List<FriendForm> friendFormList=null;
        if (status.equals("confirm")){
             friendFormList = friendSerivce.listFriend(userid, FriendStatus.CONFIRM.code());
        }
        if (status.equals("apply"))
        {
             friendFormList = friendSerivce.listFriend(userid,FriendStatus.APPLY.code());
        }

        result.setSuccess(true);
        result.setObj(friendFormList);
        return result;
    }

    /**
     * addfriend
     * @param friend
     * @param request
     * @return
     */
    @RequestMapping("/addfriend")
    @ResponseBody
    public Result addfriend(Friend friend,HttpServletRequest request){
        Integer userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        User user = userService.getUserByAcc(friend.getFriendid().toString());
        friend.setFriendid(user.getId());
        Result result = new Result();
        if (!friendSerivce.testFriendExist(userid,friend.getFriendid())){
            friend.setUserid(userid);
            log.info(JSON.toJSONString(friend));
            if(friendSerivce.addFriend(friend)!=0){
                result.setSuccess(true);
                result.setMsg("添加成功，等待对方确认");
                return result;
            }
        }else {
            result.setMsg("添加失败：对方已经是您的好友");
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(false);
        result.setMsg("添加异常，请联系客服");
        return result;
    }

    /**
     * update Relation
     * @param friendid
     * @param request
     * @return
     */
    @RequestMapping("/updateRelation")
    @ResponseBody
    public Result updateRelation(String friendid,HttpServletRequest request){
        Result result = new Result();
        User user = userService.getUserByAcc(friendid);
        Friend friend = new Friend();
        friend.setUserid(user.getId());
        friend.setFriendid(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
        friend.setStatus(0);
        if (friendSerivce.updateRelation(friend)==1){
            result.setSuccess(true);
            result.setMsg("确认成功");
            return result;
        }
        result.setMsg("确认失败");
        return result;
    }



    /**
     * to leave words
     * @param toId
     * @return
     */
    @RequestMapping(value = "/toleavewords")
    public String toLeaveWords(Model model, String toId){
        System.out.println(toId);
        User user = userService.getUserByAcc(toId);
        toId = user.getId().toString();
        model.addAttribute("toId",toId);
        return "test/Friend/FriendWords";
    }

    /**
     * leaveWords
     * @param toId
     * @param msg
     * @return
     */
    @RequestMapping(value = "/leaveWords")
    @ResponseBody
    public Result leaveWords(String toId,String msg,HttpServletRequest request){
        Result result = new Result();
        log.info(JSON.toJSONString("toID:"+toId+"   msg:"+msg));
        FriendWords friendWords = new FriendWords();
        friendWords.setFromid(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
        friendWords.setToid(Integer.parseInt(toId));
        friendWords.setWords(msg);
        friendSerivce.inserWords(friendWords);
        return result;
    }

    /**
     * listLeaveWords
     * @param request
     * @return
     */
    @RequestMapping("/listLeaveWords")
    @ResponseBody
    public Result listLeaveWords(HttpServletRequest request){
        Integer userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        List<FwordsForm> friendWords = friendSerivce.listwords(userid);
        Result result = new Result();
        result.setObj(friendWords);
        result.setSuccess(true);
        return result;
    }

    /**
     * delete Friend by ids
     * @param request
     * @param Fid
     * @return
     */
    @RequestMapping("/deleteFriend")
    @ResponseBody
    public Result deleteFriend(HttpServletRequest request,String fid){
        Integer userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        Friend friend = new Friend();
        fid = userService.getUserByAcc(fid).getId().toString();
        friend.setFriendid(Integer.parseInt(fid));
        friend.setUserid(userid);
        Result result = new Result();
        if (friendSerivce.deleteFriend(friend)==1){
            result.setSuccess(true);
            result.setMsg("删除成功");
            return result;
        }
        return result;
    }


}
