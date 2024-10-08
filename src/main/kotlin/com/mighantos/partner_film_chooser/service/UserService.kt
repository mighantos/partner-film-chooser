package com.mighantos.partner_film_chooser.service

import com.mighantos.partner_film_chooser.model.User
import com.mighantos.partner_film_chooser.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(
    private val repository: UserRepository,
) {
    @Transactional
    fun create(id: UUID): User {
        val dbUser = repository.findById(id)
        if (dbUser.isPresent)
            return dbUser.get()
        return repository.save(User(id))
    }
}