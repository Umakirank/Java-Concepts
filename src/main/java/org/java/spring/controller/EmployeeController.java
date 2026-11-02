package org.java.spring.controller;

import jakarta.validation.Valid;
import org.java.spring.dto.EmployeeRequest;
import org.java.spring.dto.EmployeeResponse;
import org.java.spring.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * SPRING MVC / REST CONTROLLER
 *
 * @RestController = @Controller + @ResponseBody : return values are written as JSON (Jackson).
 * @RequestMapping("/api/employees") : base URL.
 * HTTP verbs: @GetMapping, @PostMapping, @PutMapping (replace), @PatchMapping (partial), @DeleteMapping.
 * Binding: @PathVariable (/employees/5), @RequestParam (?page=1), @RequestBody (JSON -> object),
 *          @RequestHeader, @Valid (trigger validation).
 * ResponseEntity : full control over status code, headers and body.
 *
 * REQUEST FLOW: Client -> DispatcherServlet (front controller) -> HandlerMapping -> Controller ->
 *   Service -> Repository -> DB, then HttpMessageConverter writes the JSON response.
 *
 * REST STATUS CODES: 200 OK, 201 Created, 204 No Content, 400 Bad Request, 401 Unauthorized,
 *   403 Forbidden, 404 Not Found, 409 Conflict, 500 Internal Server Error.
 * IDEMPOTENT: GET, PUT, DELETE. NOT idempotent: POST.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/employees/" + created.id())).body(created);   // 201
    }

    @GetMapping("/{id}")
    public EmployeeResponse get(@PathVariable Long id) { return service.getById(id); }

    @GetMapping
    public List<EmployeeResponse> all() { return service.getAll(); }

    /** Example: /api/employees/search?text=an&page=0&size=5&sort=salary */
    @GetMapping("/search")
    public Page<EmployeeResponse> search(@RequestParam(defaultValue = "") String text,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "id") String sort) {
        return service.search(text, PageRequest.of(page, size, Sort.by(sort)));
    }

    @GetMapping("/high-earners")
    public List<EmployeeResponse> highEarners(@RequestParam(defaultValue = "70000") double min) {
        return service.highEarners(min);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)               // 204
    public void delete(@PathVariable Long id) { service.delete(id); }
}
