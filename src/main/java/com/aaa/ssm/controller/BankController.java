package com.aaa.ssm.controller;

import com.aaa.ssm.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * className:BankController
 * discription:
 * author:jiasanshui
 * createTime:2019-01-07 17:27
 */
@Controller
@RequestMapping("bank")
public class BankController {

    @Autowired
    private BankService bankService;

    /**
     * 判断该用户是否绑定银行卡
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("isBind")
    public Object isBindBankCard(String userName){
        List<Map> bcList = bankService.getBankCardByUName(userName);
        if (bcList!=null&&bcList.size()>0){
            return 1;
        }
        return 0;
    }

    /**
     * 查看银行卡
     * @return
     */
    @RequestMapping("myBankCard")
    public Object getbankcard(HttpSession session, Model model){
        String userName = (String)session.getAttribute("userName");
        List<Map> bankCardList = bankService.getBankCardByUName(userName);
        model.addAttribute("bcList",bankCardList);
        return "forward:/jump/index";
    }
}
