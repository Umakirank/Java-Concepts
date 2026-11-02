package org.java.spring.dto;

import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) + BEAN VALIDATION
 *
 * WHY DTO? Never expose entities directly: avoids leaking internal fields, lazy-loading issues, and
 * lets the API evolve independently of the database.
 *
 * VALIDATION annotations (jakarta.validation): @NotNull, @NotBlank, @Size, @Min, @Max, @Email,
 * @Positive, @Pattern. Triggered by @Valid on the controller parameter; failure ->
 * MethodArgumentNotValidException (handled in GlobalExceptionHandler).
 */
public record EmployeeRequest(
        @NotBlank(message = "name is required") String name,
        @NotBlank @Email(message = "invalid email") String email,
        @Positive(message = "salary must be positive") double salary,
        @NotBlank(message = "department is required") String department) {
}
