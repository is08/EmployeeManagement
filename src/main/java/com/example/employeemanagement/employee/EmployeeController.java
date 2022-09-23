package com.example.employeemanagement.employee;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    List<EmployeeEntity> getAllEmployees(){
        return employeeService.allEmployees();
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeEntity> getSpecificEmployee(@PathVariable Integer id){
        try{
            EmployeeEntity employee = employeeService.getOne(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity addEmployee(@RequestBody ObjectNode objectNode){
        String firstName = objectNode.get("firstName").asText();
        String lastName = objectNode.get("lastName").asText();
        Integer salary = objectNode.get("salary").asInt();

        try {
            employeeService.add(new EmployeeEntity(firstName, lastName, salary));
            return ResponseEntity.status(200).body("Saved!");
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Uh oh" + e.getMessage());
        }
    }

    @PutMapping("")
    ResponseEntity updateEmployee(@RequestBody EmployeeEntity employee){
        try{
            employeeService.update(employee);
            return ResponseEntity.status(200).body("Updated!");
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not updated!" + e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity deleteEmployee(@PathVariable Integer id){
        try{
            employeeService.delete(id);
            return ResponseEntity.status(200).body("Deleted!");
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not deleted! " + e.getMessage());
        }
    }
}
