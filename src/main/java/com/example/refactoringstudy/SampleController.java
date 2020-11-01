package com.example.refactoringstudy;

import com.example.refactoringstudy.dto.StatementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @PostMapping("statement")
    public String getStatement(@RequestBody StatementDto dto) {
        return sampleService.statement(dto.getInvoice(), dto.getPlays());
    }
}
