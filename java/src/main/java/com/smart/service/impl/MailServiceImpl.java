package com.smart.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.smart.model.LoginUser;
import com.smart.model.User;
import com.smart.model.UserActivationDetail;
import com.smart.property.AppProperties;
import com.smart.property.FileStorageProperties;
import com.smart.service.IMailService;
import com.smart.util.UtilMessages;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class MailServiceImpl implements IMailService{

	private String uiHost;
	private String serverHost;
	private boolean isProd;
	private String prodHost;
	
	@Autowired
	private JavaMailSender sender;

	@Autowired
	private Configuration freemarkerConfig;

	@Autowired
    public MailServiceImpl(AppProperties appProperties) {
		this.uiHost = appProperties.getUiHost();
		this.serverHost = appProperties.getServerHost();
		this.isProd = appProperties.isProd();
		this.prodHost = appProperties.getProdHost();
		
	}


	
	

	@Override
	public void sendActivationEmail(User user) throws Exception{
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			Map<String, Object> model = new HashMap();
			model.put("isProd",isProd);
			model.put("prodHost", prodHost);
			model.put("uiHost", uiHost);
			model.put("serverHost", serverHost);
			model.put("username", user.getUsername());
			model.put("token", user.getActivationDetail().getActivationToken());
			model.put("expireAt", user.getActivationDetail().getTokenExpireOn());
			freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/mail-templates");
			Template t = freemarkerConfig.getTemplate("user-activation.ftl");
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			helper.setTo(isProd ? user.getEmail() : "pawansankhle@gmail.com");
			helper.setText(text, true);
			helper.setSubject("Account Activation");
			sender.send(message);
		}catch(Exception ex) {
			System.err.println("Error in sending email: "+ex.getMessage());
		}
	}

	@Override
	public void sendForgetPasswordEmail(UserActivationDetail uad, LoginUser user) {

		try {

			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			Map<String, Object> model = new HashMap();
			model.put("isProd",isProd);
			model.put("prodHost", prodHost);
			model.put("uiHost", uiHost);
			model.put("serverHost", serverHost);
			model.put("username", user.getUsername());
			model.put("token", uad.getActivationToken());
			model.put("expireAt", uad.getTokenExpireOn());
			freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/mail-templates");
			Template t = freemarkerConfig.getTemplate("forget-password.ftl");
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			helper.setTo("pawansankhle@gmail.com");
			helper.setText(text, true);
			helper.setSubject("Account Activation");
			sender.send(message);

		}catch(Exception ex) {
			System.err.println("Error in sending email: "+ex.getMessage());
		}
	}
	
	@Override
	public String getactivateUserResponse(String msg) {
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		Template t;
		String text = "";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			if(msg != null) {
	        	model.put("error", true);
	        	model.put("message", msg);
	        }else {
	        	model.put("error", false);
	        	model.put("message", UtilMessages.USER_ACTIVATION_SUCCESS);
	        }
			model.put("isProd",isProd);
			model.put("prodHost", prodHost);
			model.put("uiHost", uiHost);
			model.put("serverHost", serverHost);
			t = freemarkerConfig.getTemplate("user-activation-response.ftl");
			text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error in sending getactivateUserResponse email : "+e.getMessage());
		}
		return text;
	}
}
