package com.sample.demo.service.oauth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sample.demo.vo.oauth.OauthMember;

@RepositoryRestResource(path="/member")
//public interface OauthMemberController extends CrudRepository<OauthMember, Long>, PagingAndSortingRepository<OauthMember, Long> {
public interface OauthMemberController extends CrudRepository<OauthMember, Long> {
}
