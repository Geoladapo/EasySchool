package com.oladapo.EasySchool.controller;

import com.oladapo.EasySchool.model.Courses;
import com.oladapo.EasySchool.model.EasyClass;
import com.oladapo.EasySchool.model.Person;
import com.oladapo.EasySchool.repository.CoursesRepository;
import com.oladapo.EasySchool.repository.EasyClassRepository;
import com.oladapo.EasySchool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private EasyClassRepository easyClassRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    @GetMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        List<EasyClass> easyClasses = easyClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("easyClass", easyClasses);
        modelAndView.addObject("easyClass", new EasyClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("easyClass") EasyClass easyClass){
        easyClassRepository.save(easyClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<EasyClass> easyClass = easyClassRepository.findById(id);
        for(Person person : easyClass.get().getPersons()){
            person.setEasyClass(null);
            personRepository.save(person);
        }
        easyClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<EasyClass> easyClass = easyClassRepository.findById(classId);
        modelAndView.addObject("easyClass",easyClass.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("easyClass",easyClass.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        EasyClass easyClass = (EasyClass) session.getAttribute("easyClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+easyClass.getClassId()
                    +"&error=true");
            return modelAndView;
        }
        personEntity.setEasyClass(easyClass);
        personRepository.save(personEntity);
        easyClass.getPersons().add(personEntity);
        easyClassRepository.save(easyClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+easyClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        EasyClass eazyClass = (EasyClass) session.getAttribute("easyClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setEasyClass(null);
        eazyClass.getPersons().remove(person.get());
        EasyClass easyClassSaved = easyClassRepository.save(eazyClass);
        session.setAttribute("easyClass",easyClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+eazyClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model){
        List<Courses> courses = coursesRepository.findByOrderByName(); // return the data in an asc order
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("courses", new Courses());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses course) {
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id
            ,HttpSession session,@RequestParam(required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses",courses.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("courses",courses.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person person,
                                           HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) session.getAttribute("courses");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()
                    +"&error=true");
            return modelAndView;
        }
        personEntity.getCourses().add(courses);
        courses.getPersons().add(personEntity);
        personRepository.save(personEntity);
        session.setAttribute("courses",courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }

    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId,
                                                HttpSession session) {
        Courses courses = (Courses) session.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        person.get().getCourses().remove(courses);
        courses.getPersons().remove(person);
        personRepository.save(person.get());
        session.setAttribute("courses",courses);
        ModelAndView modelAndView = new
                ModelAndView("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }
}
