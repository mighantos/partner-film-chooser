package com.mighantos.partner_film_chooser.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class AlreadyExistsException : Exception()