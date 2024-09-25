package com.litaal.newsfx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litaal.newsfx.model.NavLink;
import com.litaal.newsfx.service.IUserInfoService;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

	@Autowired
	private IUserInfoService userInfoService;

	@GetMapping("/navlinks")
	public ResponseEntity<List<NavLink>> navlinks() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		String role = user.getAuthorities().toArray()[0].toString();
		List<NavLink> links = userInfoService.getNavLinksByRole(role);
		if (links != null && !links.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(links);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

}
