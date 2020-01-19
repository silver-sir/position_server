package com.tct.positionApp.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tct.positionApp.service.FencesService;
import com.tct.positionApp.service.ParentsService;
import com.tct.positionApp.util.MapUtil;
import com.tct.positionApp.util.SecurityUtil;
import com.tct.positionApp.util.WebUtil;
import com.tct.positionApp.wechat.WechatApi;
import com.tct.positionApp.wechat.WechatConf;
import com.tct.positionApp.wechat.WechatTemplate;
import com.tct.positionApp.wechat.WechatTemplateItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author wwz
 * @version 1 (2018/8/16)
 * @since Java7
 */
@Slf4j
@RestController
@RequestMapping("api/wx_small_app")
public class WechatController {

    @Resource
    ParentsService parentsService;

    @Resource
    FencesService fencesService;

    @GetMapping("/get_openid_by_js_code")
    public Map<String, Object> getOpenIdByJSCode(String js_code) {
        return WechatApi.getOpenIdByJSCode(js_code);
    }

    @PostMapping("/template_send")
    public Map<String, Object> templateSend() {
        JSONObject body = JSON.parseObject(WebUtil.getBody());
        int parent_id = Integer.parseInt(body.getString("parent_id"));
        int student_id = Integer.parseInt(body.getString("student_id"));

        parentsService.setUpdateFormIdParent(body.getString("formId"),parent_id);
        parentsService.setUpdateOpenIdParent(body.getString("openId"),parent_id);

        fencesService.setUpdateStatusByStudentId(1,student_id);

        return MapUtil.newHashMap("status", 0);
    }

    @GetMapping("/validate")
    public void validate(String signature, String timestamp, String nonce, String echostr) {
        final StringBuilder attrs = new StringBuilder();
        Stream.of(WechatConf.token, timestamp, nonce)//
                .sorted()//
                .forEach((item) -> attrs.append(item));
        String sha1 = SecurityUtil.getSha1(attrs.toString());
        if (StrUtil.equalsIgnoreCase(sha1, signature)) {
            WebUtil.write(echostr);
            return;
        }
        log.error("#0820 WechatController.validate() error, attrs = {}", attrs);
    }
}
