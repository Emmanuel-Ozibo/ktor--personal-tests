package com.example.plugins

import com.example.auth.signup.SignupRequest
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun Application.configureValidation() {
    install(RequestValidation) {
        validate<SignupRequest> { signupRequest ->
            when {
                signupRequest.email.isEmpty() -> ValidationResult.Invalid("Email cannot be empty/blank")
                signupRequest.firstName.isEmpty() -> ValidationResult.Invalid("First name cannot be empty")
                signupRequest.lastName.isEmpty() -> ValidationResult.Invalid("Last name cannot be empty")
                signupRequest.password.isEmpty() -> ValidationResult.Invalid("Password cannot be empty")
                else -> ValidationResult.Valid
            }
        }
    }
}
