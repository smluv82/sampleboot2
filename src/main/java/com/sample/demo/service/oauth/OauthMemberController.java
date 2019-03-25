package com.sample.demo.service.oauth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.biz.oauth.service.OauthMemberService;
import com.sample.demo.common.component.ResultCodeUtil;
import com.sample.demo.vo.oauth.OauthMember;
import com.sample.demo.vo.result.ResultVo;
import com.sample.demo.vo.result.ReturnStatusCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@RepositoryRestResource(path="/member")
//public interface OauthMemberController extends CrudRepository<OauthMember, Long> {

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oauth/member")
public class OauthMemberController {

	private final ResultCodeUtil resultCodeUtil;
	private final OauthMemberService oauthMemberService;

	@GetMapping("/{email}/select")
	private ResponseEntity<ResultVo<OauthMember>> selectUserId(@PathVariable final String email) {
		log.debug("/select call");
		try {
			return ResponseEntity.ok(resultCodeUtil.getResultInfo(ReturnStatusCode.SUCCESS, oauthMemberService.findByEmail(email)));
		}catch(Exception e) {
			log.error("Exception : {}", e);
			return ResponseEntity.ok(resultCodeUtil.getResultInfo(ReturnStatusCode.FATAL_ERROR, null));
		}
	}
}
