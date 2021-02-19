package com.example.demo.workspace;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartz")
public class WorkspaceController {

    private final WorkspaceService service;


    public WorkspaceController(WorkspaceService service){
        this.service = service;
    }

    @PostMapping("/printHelloWorld")
    public void runPrintHelloWorld(){
        service.runPrintHelloWorldJob();
    }

    @DeleteMapping("/{timerId}")
    public Boolean deleteJob(@PathVariable String timerId){
        return service.deleteTimer(timerId);
    }

    @PostMapping("/printTime")
    public void printTime(){
        service.printTimeJob();
    }

}
