package com.idea.web;

import com.idea.entity.Seckill;
import com.idea.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by zhangyong on 16/5/27.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> seckillList = seckillService.queryAll();
        logger.info("{}", seckillList);
        model.addAttribute("seckillList", seckillList);
        return "list";  //WEB-INF/jsp/list.jsp
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String queryById(@PathVariable("seckillId") Long seckillId, Model model){
        if (seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.queryById(seckillId);
        model.addAttribute("seckill", seckill);
        return "detail";
    }
}
