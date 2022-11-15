package com.amaury.chatdoc.data

import com.amaury.chatdoc.data.entity.Role

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository extends JpaRepository<Role, Long> {
}