package com.hwsin.shop;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hwsin.shop.model.RoleType;
import com.hwsin.shop.model.Users;
import com.hwsin.shop.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DummyControllerTest {

	@Autowired
	private UsersRepository usersRepository;
	
	//http://localhost:8080/shop/dummy/join (요청)
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
	
	//save함수는 id를 전달하지 않으면 insert를 해주고
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.
	//email,password 수정
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			usersRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다.해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id:"+id;
	}
	//함수 종료시 자동 commit이 됨
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public Users updateUser(@PathVariable int id, @RequestBody Users requestUser) { //json 데이터를 요청 => Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아준다) 
		System.out.println("id:"+id);
		System.out.println("password:" + requestUser.getPassword());
		System.out.println("email:"+ requestUser.getEmail());
		
		Users user = usersRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//usersRepository.save(user);
		
		//더티 체킹
		return user;
	}
	@GetMapping("/dummy/users")
	public List<Users> list(){
		return usersRepository.findAll();
	}
	
	//한 페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public Page<Users> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<Users> pagingUser = usersRepository.findAll(pageable);
		
		/*
		 * if(pagingUser.isLast()) {
		 * 
		 * }
		 */
		List<Users> users = pagingUser.getContent();
		return pagingUser;
	}
	
	@GetMapping("/dummy/user/{id}")
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
