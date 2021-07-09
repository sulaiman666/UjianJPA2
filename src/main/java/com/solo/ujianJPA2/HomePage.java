package com.solo.ujianJPA2;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.solo.ujianJPA2.entity.UserModel;
import com.solo.ujianJPA2.repository.UserRepository;
import com.solo.ujianJPA2.util.FileUploadUtil;

@Controller
public class HomePage {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/")
	public String viewIndex(Model model) {
		model.addAttribute("usermodel", new UserModel()); 
		return "index.html";
	}
	
	@PostMapping("/user/add")
	//public String addContact(@ModelAttribute ContactModel contactModel,@RequestParam("photo") MultipartFile file, Model model) {
	public String addUser(@RequestParam("fullname")String name,@RequestParam("email")String email,@RequestParam("platform")String platform,
			 @RequestParam("cv") MultipartFile file, Model model) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		UserModel userModel = new UserModel(0, name, email, platform, fileName);
		userModel.setCv(fileName);
		this.userRepository.save(userModel);
		
		String uploadDir = "C:/cvupload/" + fileName;
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
}
