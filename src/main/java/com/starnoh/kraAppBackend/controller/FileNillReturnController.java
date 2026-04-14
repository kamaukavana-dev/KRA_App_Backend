package com.starnoh.kraAppBackend.controller;


import com.starnoh.kraAppBackend.dto.FileReturnsDto;
import com.starnoh.kraAppBackend.service.FileNillReturnsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nil-returns")
public class FileNillReturnController {
    private final FileNillReturnsService fileNillReturnsService;

    public FileNillReturnController(FileNillReturnsService fileNillReturnsService) {
        this.fileNillReturnsService = fileNillReturnsService;
    }

    public ResponseEntity<?> fileNillReturns(
            @RequestBody FileReturnsDto request
            ){
        return ResponseEntity.ok(
                fileNillReturnsService.fileReturns(request)
        );
    }
}
