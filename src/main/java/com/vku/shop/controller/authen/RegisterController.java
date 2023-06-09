package com.vku.shop.controller.authen;

import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vku.shop.model.RoleDTO;
import com.vku.shop.model.UserDTO;

import com.vku.shop.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailSender mailSender;

	@GetMapping(value = "/register")
	public String register() {
		return "authen/register";
	}

	@PostMapping(value = "/register")
	public String register(HttpServletRequest request, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, @RequestParam(name = "repassword") String repassword) {
		String code = randomString(8);
		if (userService.findByEmail(email) != null) {
			UserDTO userDTO = userService.findByEmail(email);
			if (userDTO.getEmail() != null) {
				request.setAttribute("error", "The email address is already exist!");
				return "authen/register";
			} else {
				if (!password.equals(repassword)) {
					request.setAttribute("error", "The password do not match!");
					request.setAttribute("email", email);
//					userDTO.setPassword(repassword);
//					userDTO.setAvatar("1608484153089.png");
//					userService.update(userDTO);
					return "authen/register";
				}
			}
		} else {
			if (!password.equals(repassword)) {
				request.setAttribute("error", "The password do not match!");
				request.setAttribute("email", email);
				return "authen/register";
			} else {
				UserDTO userDTO = new UserDTO();
				userDTO.setEmail(email);
				userDTO.setPassword(new BCryptPasswordEncoder().encode(password));
				userDTO.setAvatar("1671850954243.jfif");
				RoleDTO roleDTO = new RoleDTO();
				roleDTO.setRoleId(3);
				userDTO.setRoleDTO(roleDTO);
				userDTO.setVerify(true);
				userService.insert(userDTO);
//				sendEmail("vudinhtan.it@gmail.com", email, "Welcome to Shop!",
//						"Hello, " + email.split("@")[0] + "! Please confirm that you can login in Shop!" + " Your confirmation code is: " + code);
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("emailRegister", email);
		session.setAttribute("codeVerify", code);
		return "authen/login";
	}

	@PostMapping(value = "/verify")
	public String verify(HttpServletRequest request, HttpSession session) {
		String code = request.getParameter("code");
		String codeVerify = (String) session.getAttribute("codeVerify");
		if (!code.equals(codeVerify)) {
			request.setAttribute("verifyFail", "Invalid code, please try again!");
		} else {
			String email = (String) session.getAttribute("emailRegister");
			UserDTO userDTO = userService.findByEmail(email);
			userDTO.setVerify(true);
			request.setAttribute("verifySuccess", "Verification successfull!");
			request.setAttribute("active", "active");
			userService.update(userDTO);
		}
		return "authen/verify";
	}

	@GetMapping(value = "/forgetPassword")
	public String forgetPass() {
		return "authen/forgetPassword";
	}

	@PostMapping(value = "/forgetPassWordSendCode")
	public String resendCode(HttpSession session, HttpServletRequest request) {
		String code = randomString(8);
		String email = (String) request.getParameter("email");
//		String email = (String) session.getAttribute("emailRegister");
		if (userService.findByEmail(email) == null) {
			
				request.setAttribute("error", "Email này không tồn tại, mời nhập lại!");
				return "authen/forgetPassword";
		}
		sendEmail(email, "Welcome to Shop!", "Hello, " + email.split("@")[0]
				+ "! Please confirm that you can login in Shop!" + " Your confirmation code is: " + code);
		request.setAttribute("resend", "resend");
		session.setAttribute("codeVerify", code);
		session.setAttribute("email", email);
		return "authen/verify";
	}

	@PostMapping(value = "/forgetPassWordVerify")
	public String forgetPassVerify(HttpServletRequest request, HttpSession session) {
		String code = request.getParameter("code");
		String codeVerify = (String) session.getAttribute("codeVerify");
		if (!code.equals(codeVerify)) {
			request.setAttribute("verifyFail", "Mã không hợp lệ, mời nhập lại");
			return "authen/verify";
		} else {
			String email = (String) session.getAttribute("email");
			request.setAttribute("verifySuccess", "Đổi mật khẩu thành công");
		}
		return "authen/ResetPassWord";
	}
	@GetMapping(value = "/ResetPass")
	public String rs() {
		return "authen/ResetPassWord";
	}
	@PostMapping(value = "/ResetPassWord")
	public String ResetPassWord(HttpServletRequest request, HttpSession session, 
			@RequestParam(name = "password") String password, @RequestParam(name = "repassword") String repassword) {
//		String email = (String) session.getAttribute("email");
		String email = request.getParameter("email");
		if (password.equals(repassword)) {
			UserDTO user = userService.findByEmail(email);
			user.setPassword(new BCryptPasswordEncoder().encode(repassword));
			userService.update(user);
			session.setAttribute("message", "Đổi mật khẩu thành công!");
			return "redirect:/login";
		} else {
			request.setAttribute("error", "Password do not match!");
			return "authen/ResetPassWord";
		}
	}

	@PostMapping(value = "get-news")
	public String getNews(@RequestParam(name = "email") String email) {
		sendEmail(email, "Welcome to Shop!",
				"Thank you for your interest, we will send you the latest notice if any. Please pay attention to your mail.");
		return "client/get_news";
	}

	public void sendEmail(String to, String subject, String content) {
		String from = "vdtan.20it7@vku.udn.vn";
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);

		mailSender.send(mailMessage);
	}

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}
