package org.java.spring.controller;

import org.java.spring.config.AppConfig.AppInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/** Health-style endpoint showing a bean injected from @Configuration. */
@RestController
public class HomeController {

    private final AppInfo appInfo;

    public HomeController(AppInfo appInfo) { this.appInfo = appInfo; }

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("app", appInfo.name(), "version", appInfo.version(),
                "hint", "GET /api/employees | H2 console at /h2-console");
    }
}
