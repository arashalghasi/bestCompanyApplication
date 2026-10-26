package com.example.demo;

import java.time.Instant;

import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    public record Info(String name, String version, Instant builtAt) {
    }

    private final BuildProperties build;

    public InfoController(BuildProperties build) {
        this.build = build;
    }

    @GetMapping("/api/info")
    public Info info() {
        return new Info(build.getArtifact(), build.getVersion(), build.getTime());
    }
}
