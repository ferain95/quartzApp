package com.example.demo.workspace;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartz")
public class WorkspaceController {

    private final WorkspaceService service;

    @Autowired
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

//    @PostMapping("/crontrigger")
//    public void cronTrigger(){
//        cronService.cronTriggerJob();
//    }

}
