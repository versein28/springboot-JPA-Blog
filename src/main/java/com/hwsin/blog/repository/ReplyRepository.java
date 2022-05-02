package com.hwsin.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hwsin.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	@Modifying
	@Query(value="INSERT INTO reply(id, userId, boardId, content, createDate) VALUES(tmax.REPLYIDNEXT.NEXTVAL, ?1, ?2, ?3, sysdate)", nativeQuery = true)
	int mSave(int userId, int boardId, String content); // 업데이트된 행의 개수를 리턴해줌.  
}