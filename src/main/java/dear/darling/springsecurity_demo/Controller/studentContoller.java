package dear.darling.springsecurity_demo.Controller;


import dear.darling.springsecurity_demo.Model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class studentContoller {

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1,"naveen", 59),
            new Student(2, "Roshini", 432)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students") //We need CSRF token to use those methods
    public Student addStudent(@RequestBody Student s){
        students.add(s);
        return s;
    }
}
