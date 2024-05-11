package com.rrss.backend.controller;

import com.rrss.backend.dto.AddForumCategoryRequest;
import com.rrss.backend.dto.ForumCategoryDto;
import com.rrss.backend.service.ForumCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/forum-categories")
public class ForumCategoryController {

    private final ForumCategoryService forumCategoryService;

    public ForumCategoryController(ForumCategoryService forumCategoryService) {
        this.forumCategoryService = forumCategoryService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGE_FORUM_CATEGORY')")
    public ResponseEntity<ForumCategoryDto> addForumCategory(@RequestBody AddForumCategoryRequest addForumCategoryRequest) {
        return new ResponseEntity<>(forumCategoryService.addForumCategory(addForumCategoryRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ForumCategoryDto>> getForumCategories() {
        return ResponseEntity.ok(forumCategoryService.getForumCategories());
    }
    
    @PutMapping("/{forum-category-id}")
    @PreAuthorize("hasAnyAuthority('MANAGE_FORUM_CATEGORY')")
    public ResponseEntity<ForumCategoryDto> updateForumCategory(@RequestBody AddForumCategoryRequest addForumCategoryRequest, @PathVariable("forum-category-id") Long forumCategoryId) {
        return ResponseEntity.ok(forumCategoryService.updateForumCategory(addForumCategoryRequest,forumCategoryId));
    }

}
