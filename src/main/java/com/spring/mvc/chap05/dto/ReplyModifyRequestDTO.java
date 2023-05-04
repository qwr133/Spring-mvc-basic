package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Setter
@Getter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class ReplyModifyRequestDTO {

    //원본글번호, 댓글번호, 게시글

    @NotBlank
    @Min(0) @Max(Long.MAX_VALUE) //반드시 정수 입력하도록 체크
    private long bno;

    @NotBlank
    @Min(0)
    @Max(Long.MAX_VALUE)
    private long rno;

    @NotBlank
    private String text;

    public Reply toEntity() {
        return Reply.builder()
                .replyNo(this.rno)
                .boardNo(this.bno)
                .replyText(this.text)
                .build();

    }
}
