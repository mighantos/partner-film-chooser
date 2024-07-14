package com.mighantos.partner_film_chooser.service

import com.mighantos.partner_film_chooser.dto.UserDetailsDto
import com.mighantos.partner_film_chooser.dto.UserDto
import com.mighantos.partner_film_chooser.exception.AlreadyExistsException
import com.mighantos.partner_film_chooser.exception.NotFoundException
import com.mighantos.partner_film_chooser.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {
    fun getUser(id: UUID): UserDetailsDto {
        val user = userRepository.findById(id).orElseThrow { NotFoundException() }
        return UserDetailsDto(user)
    }

    fun createUser(userDto: UserDto): UUID {
        if(userRepository.existsByEmail(userDto.email))
            throw AlreadyExistsException()
        val user = userRepository.save(userDto.toUser())
        return user.id!!
    }
}