package com.demo.service.impl;

import com.demo.model.User;
import com.demo.service.MailService;
import freemarker.template.Template;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pinggang Yu on 2016/10/24.
 */
@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public void sendUserMail(User user, String confirmCode) {
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(user.getEmailAddress());
            mimeMessageHelper.setSubject("Test");
            mimeMessageHelper.setFrom("test@test.com");
            mimeMessageHelper.setText(getMailText(user, confirmCode), true);

            /*添加附件*/
   /*         String filePath = this.getClass().getClassLoader().getResource("../statics/").getPath();
            File file1 = new File(filePath+"test.docx");
            File file2 = new File(filePath+"1.jpg");

            mimeMessageHelper.addAttachment(MimeUtility.encodeWord(file1.getName()), file1);
            mimeMessageHelper.addAttachment("test.docx", file1);
            mimeMessageHelper.addAttachment("1.jpg", file2);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
    private String getMailText(User user, String confirmCode) throws Exception {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("hello.ftl");
        Map map = new HashMap();
        map.put("user", user);
        map.put("confirmCode", confirmCode);
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        return htmlText;
    }
}
