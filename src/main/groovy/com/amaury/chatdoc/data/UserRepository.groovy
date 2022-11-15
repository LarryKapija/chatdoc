package com.amaury.chatdoc.data

import com.amaury.chatdoc.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email)
}