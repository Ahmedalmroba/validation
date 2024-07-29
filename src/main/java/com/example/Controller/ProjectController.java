package com.example.Controller;
import com.example.ApiResponse.Api;
import com.example.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
        private List<Project> projects = new ArrayList<>();

        @GetMapping("/get")
        public ResponseEntity getprojects() {
            return ResponseEntity.status(200).body(projects);
        }

        @PostMapping("/add")
        public ResponseEntity addproject(@Valid @RequestBody Project project, Errors errors) {

            if (errors.hasErrors()) {
                String Message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(Message);
            }
            projects.add(project);
            return ResponseEntity.status(200).body(new Api("user added"));
        }

        @PutMapping("/update/{index}")
        public ResponseEntity updatprojct(@PathVariable int index, @Valid @RequestBody  Project project, Errors errors) {

            if (errors.hasErrors()) {
                String Message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(Message);
            }
            projects.set(index, project);
            return ResponseEntity.status(200).body(new Api("user updated"));
        }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteproject(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(400).body(new Api("user index out of bounds"));
    }
    @GetMapping("/search")
    public ResponseEntity searchProjcTtitle(@PathVariable  String title) {

        for (Project project : projects) {
            if (project.getTitle().equals(title)) {
                return ResponseEntity.status(200).body(title);

            }
        }
        return ResponseEntity.status(400).body(new Api("user not found"));
    }
        @PutMapping("/status/{index}")
        public ResponseEntity changeProjectStatus(@Valid @PathVariable int index ,Errors errors) {
            if (errors.hasErrors()) {
                String Message = errors.getFieldError().getDefaultMessage();
            if (index < 0 || index >= projects.size()) {
                return ResponseEntity.status(400).body("Invalid project index");
            }
            Project project = projects.get(index);
            String currentStatus = project.getStatus();

            if (currentStatus == null) {
                return ResponseEntity.status(400).body("Project status cannot be null");
            }

            if (currentStatus.equalsIgnoreCase("not started")) {
                project.setStatus("In Progress");
                return ResponseEntity.ok("Project status changed to In Progress");
            } else if (currentStatus.equalsIgnoreCase("in progress")) {
                project.setStatus("Completed");
                return ResponseEntity.ok("Project status changed to Completed");
            } else if (currentStatus.equalsIgnoreCase("completed")) {
                return ResponseEntity.status(200).body("Project is already completed");
            } else {
                return ResponseEntity.status(400).body("Invalid project status");
            }}
            return ResponseEntity.status(400).body("Invalid project status");
}
    @GetMapping("/company/{companyName}")
    public ResponseEntity<List<Project>> getProjectsByCompany(@PathVariable String companyName) {
        List<Project> companyProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equals(companyName)) {
                companyProjects.add(project);
            }
        }
        if (companyProjects.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(companyProjects);
}

}

