package com.example.mission04.domain.like.controller;

import com.example.mission04.domain.like.service.LikeService;
import com.example.mission04.global.dto.ResponseDto;
import com.example.mission04.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@Tag(name = "likes", description = "좋아요 관련 API")
@RequestMapping("/api/v1/lectures")
@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{lectureId}/likes")
    @Operation(summary = "선택한 강의 좋아요 기능", description = "선택한 강의에 좋아요를 누를 수 있는 API")
    public ResponseDto<AtomicReference<String>> likes(
            @PathVariable Long lectureId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        AtomicReference<String> result = likeService.likes(userDetails.getUsername(), lectureId);
        return ResponseDto.success("선택한 강의 좋아요 기능", result);
    }
}
