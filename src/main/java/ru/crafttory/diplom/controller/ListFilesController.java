package ru.crafttory.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.crafttory.diplom.component.FileInfo;
import ru.crafttory.diplom.service.FileService;

import java.util.List;

@Controller
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListFilesController {

    private final FileService fileService;

    @GetMapping()
    public ResponseEntity<List<FileInfo>> getFiles(@RequestParam(name = "limit") Integer limit) {
        return ResponseEntity.ok(fileService.getList(limit));
    }
}
