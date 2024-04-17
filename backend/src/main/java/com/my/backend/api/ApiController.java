package com.my.backend.api;


import com.my.backend.dataBase.AnswerFromBd;
import com.my.backend.dataBase.BdController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/com/my")
public class ApiController {

    public static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private BdController bdController;

    @GetMapping("/cntMsg{userId}")
    public ResponseEntity<Object> getPostById(@RequestParam("userId") String userId) {

        LOG.info(userId);

        AnswerFromBd res = bdController.getCntMessageById(userId);

        LOG.info(res.toString());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
