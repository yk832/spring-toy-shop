package com.insung.core.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class UserSaveForm {

    // 사용자 아이디
    @Pattern(regexp = "^[a-zA-Z]{1}[a-z0-9_]{4,15}$", message = "5~15자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.")
    private String user_id;

    // 사용자 비밀번호
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String pwd;

    // 사용자명
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{3,10}$", message = "이름은 3~10자로 입력해주세요.")
    private String user_nm;

    // 이메일 주소
    @Pattern(regexp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email_addr;

    // 이메일주소 도메인
    private String email_addr_dm;

    // 유저 권한 디폴트 세팅
    private String user_auth_cd;

}
