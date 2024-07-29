package com.example.Controller;
import com.example.Api.Api;
import com.example.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private List<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getevents() {
        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity addevent(@Valid @RequestBody Event event, Errors errors) {

        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new Api("user added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatevent(@PathVariable int index, @Valid @RequestBody  Event event, Errors errors) {

        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new Api("user updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteproject(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(400).body(new Api("user index out of bounds"));
    }
    @PutMapping("/changeCapacity/{id}")
    public ResponseEntity<String> changeCapacity(@PathVariable int id, @RequestParam int newCapacity) {
        for (Event event : events) {
            if (event.getId() == id) {
                event.setCapacity(newCapacity);
                return ResponseEntity.ok("Capacity changed for Event with ID " + id);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
    }

    @GetMapping("/search/{id}")
    public ResponseEntity search(@PathVariable  int id) {

        for (Event event : events) {
            if (event.getId()== id) {
                return ResponseEntity.status(200).body(id);

            }
        }
        return ResponseEntity.status(400).body(new Api("user not found"));
    }
}