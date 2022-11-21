package com.insung.core.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserSaveForm {

    // 사용자 아이디
    @NotBlank(message = "아이디를 작성해주세요.")
    @Length(min = 4, max = 10, message = "4~10자리로 입력해주세요.")
    private String user_id;
    // 사용자 비밀번호
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Length(min = 4, max = 10, message = "4~10자리로 입력해주세요.")
    private String pwd;
    // 사용자명
    @NotBlank(message = "이름을 입력해주세요.")
    @Length(min = 3, max = 7, message = "3~7자리로 입력해주세요.")
    private String user_nm;
    // 이메일 주소
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "형식이 맞지 않습니다.")
    private String email_addr;
    // 이메일주소 도메인
    private String email_addr_dm;
    // 유저 권한 디폴트 세팅
    private String user_auth_cd;

}
