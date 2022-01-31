package itmo.cosmolabs.controller;

import itmo.cosmolabs.model.Process;
import itmo.cosmolabs.repository.ProcessRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private ProcessRepository processRepository;
    public ManagerController(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @PostMapping("/approve")
    public void approve(Long processId) {
        Process process = processRepository.findProcessById(processId);
        process.setApprove(true);
        processRepository.save(process);
    }
}
