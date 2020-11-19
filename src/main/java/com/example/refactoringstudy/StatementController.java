package com.example.refactoringstudy;

import com.example.refactoringstudy.domain.Statement;
import com.example.refactoringstudy.dto.StatementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatementController {
    @Autowired
    private StatementService statementService;

    @PostMapping("statement")
    public Statement getStatement(@RequestBody StatementDto dto) {
        return statementService.statement(dto.getInvoice(), dto.getPlays());
    }
}
