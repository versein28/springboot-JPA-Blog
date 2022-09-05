package com.hwsin.blog.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwsin.blog.model.Cart;
import com.hwsin.blog.model.RoleType;
import com.hwsin.blog.model.Users;
import com.hwsin.blog.repository.CartRepository;
import com.hwsin.blog.repository.UsersRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌.
@Service
public class UserService {

	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public void 회원가입(Users users) {
		String rawPassword = users.getPassword(); // 1234 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬
		users.setPassword(encPassword);
		장바구니생성(users); // 장바구니 생성
		userRepository.save(users);
	}
	
	@Transactional
	private void 장바구니생성(Users users) {
        users.createCart(cartRepository.save(new Cart()));
	}
	
	/*
	 * 전통적인 로그인 방식
	 * 
	 * @Transactional(readOnly = true)//Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
	 * public Users 로그인(Users user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
	@Transactional(readOnly = true)
	public Users 회원찾기(String username) {
		Users user = userRepository.findByUsername(username).orElseGet(() -> {
			return new Users();
		});
		System.out.println("회원찾기 결과:" + user);
		return user;
	}

	@Transactional
	public void 회원수정(Users user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		// select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서!!
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주거든요.
		Users persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});

		// Validate 체크 => oauth 필드에 값이 없으면 수정 가능

		if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}

		// 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 이 자동으로 됩니다.
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
	}

	@Transactional
	public String 비밀번호찾기(String username, String email) {
		Users user = userRepository.findByUsernameAndEmail(username, email).orElseThrow(() -> {
			return new IllegalArgumentException("존재하지 않는  계정");
		});
		System.out.println("계정유무확인 결과:" + user);

		// 임시비밀번호를 발급한다
		UUID uid = UUID.randomUUID();
		String pwd = uid.toString().substring(0, 6);

		String encPassword = encoder.encode(pwd);
		user.setPassword(encPassword); // 암호화 시킨 비밀번호를 저장

		return pwd;
	}

}
