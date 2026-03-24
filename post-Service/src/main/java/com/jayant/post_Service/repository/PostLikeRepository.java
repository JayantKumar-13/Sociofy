package com.jayant.post_Service.repository;

import com.jayant.post_Service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    @Transactional                        // Custom Delete call needs a Transaction
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
