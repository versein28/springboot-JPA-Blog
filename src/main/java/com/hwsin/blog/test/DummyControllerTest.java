package com.hwsin.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwsin.blog.model.RoleType;
import com.hwsin.blog.model.Users;
import com.hwsin.blog.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DummyControllerTest {

	@Autowired
	private UsersRepository usersRepository;
	
	//http://localhost:8080/blog/dummy/join (요청)
	//username, password, email 값을 body에 담아서 보낸다.
	/*
	 * @PostMapping("/dummy/join")//insert public String join(String username,
	 * String password, String email) {// key=value(약속된 규칙)
	 * 
	 * log.info("username:" + username); log.info("password:" + password);
	 * log.info("email:" + email);
	 * 
	 * return "회원가입이 완료되었습니다."; }
	 */
	
	@GetMapping("/dummy/join/{id}")
	public Users detail(@PathVariable int id) {
		//user의 4를 찾으면 내가 DB에서 못찾아오면 user가 null이 될 것 아냐?
		//그럼 return null이 리턴이 되자나 그럼 프로그램에 문제가 있지 않겠니?
		//optional로 너의 user객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		

		Users user = usersRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id:"+id);
			}
		});
		//요청 : 웹브라우저
		//user 객체 = 자바오브젝트
		//변환 (웹 브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		//스프링 부트= MessageConverter라는 얘가 응답시에 자동 작동
		//만약에 자바 오브젝트를 리턴하게되면 MessageConverter가 Jackson 라이브러리를 호출해서 
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	
	@PostMapping("/dummy/join")//insert
	public String join(Users user) {// key=value(약속된 규칙)
		
		log.info("username:" + user.getUsername());
		log.info("password:" + user.getPassword());
		log.info("email:" + user.getEmail());
		
		user.setRole(RoleType.USER);
		usersRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
}
