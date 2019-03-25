package com.sample.demo.biz.oauth.mapper;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.demo.vo.oauth.OauthMember;

@Repository
public interface OauthMemberRepository extends CrudRepository<OauthMember, Long> {
	OauthMember findByEmail(final String email) throws Exception;
}
